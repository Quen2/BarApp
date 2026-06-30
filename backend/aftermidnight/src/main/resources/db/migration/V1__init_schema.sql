-- ============================================================
-- Schéma PostgreSQL - Application de commande de cocktails
-- ============================================================

-- Extension pour générer des UUID
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- ----------------------------------------
-- Types énumérés
-- ----------------------------------------

-- ----------------------------------------
-- Table BARMAKERS (compte avec authentification)
-- ----------------------------------------

CREATE TABLE barmakers (
    id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name          VARCHAR(100) NOT NULL,
    email         VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    created_at    TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- ----------------------------------------
-- Table CLIENTS (pas de compte, juste un identifiant
-- généré côté backend dès l'arrivée du client sur l'app)
-- ----------------------------------------

CREATE TABLE clients (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at  TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- ----------------------------------------
-- Table CATEGORIES
-- ----------------------------------------

CREATE TABLE categories (
    id           UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    barmaker_id  UUID NOT NULL REFERENCES barmakers(id) ON DELETE CASCADE,
    name         VARCHAR(100) NOT NULL,
    position     INTEGER DEFAULT 0,
    created_at   TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- ----------------------------------------
-- Table COCKTAILS
-- ----------------------------------------

CREATE TABLE cocktails (
    id           UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    category_id  UUID NOT NULL REFERENCES categories(id) ON DELETE CASCADE,
    name         VARCHAR(100) NOT NULL,
    ingredients  TEXT NOT NULL,
    description  TEXT,
    position     INTEGER DEFAULT 0,
    created_at   TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- ----------------------------------------
-- Table COCKTAIL_SIZES
-- ----------------------------------------

CREATE TABLE cocktail_sizes (
    id           UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    cocktail_id  UUID NOT NULL REFERENCES cocktails(id) ON DELETE CASCADE,
    size         VARCHAR(1) NOT NULL CHECK (size IN ('S', 'M', 'L')),
    price        NUMERIC(6, 2) NOT NULL CHECK (price >= 0),
    UNIQUE (cocktail_id, size)
);

-- ----------------------------------------
-- Table CARTS (panier actif d'un client)
-- ----------------------------------------

CREATE TABLE carts (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    client_id   UUID NOT NULL UNIQUE REFERENCES clients(id) ON DELETE CASCADE,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at  TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- UNIQUE sur client_id => un seul panier actif par client

-- ----------------------------------------
-- Table CART_ITEMS
-- ----------------------------------------

CREATE TABLE cart_items (
    id                UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    cart_id           UUID NOT NULL REFERENCES carts(id) ON DELETE CASCADE,
    cocktail_size_id  UUID NOT NULL REFERENCES cocktail_sizes(id),
    quantity          INTEGER NOT NULL DEFAULT 1 CHECK (quantity > 0),
    UNIQUE (cart_id, cocktail_size_id)
);

-- ----------------------------------------
-- Table ORDERS
-- ----------------------------------------

CREATE TABLE orders (
    id           UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    client_id    UUID NOT NULL REFERENCES clients(id) ON DELETE CASCADE,
    status       VARCHAR(30) NOT NULL DEFAULT 'COMMANDEE'
        CHECK (status IN ('COMMANDEE', 'EN_COURS_DE_PREPARATION', 'TERMINEE')),
    created_at   TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at   TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- ----------------------------------------
-- Table ORDER_ITEMS
-- ----------------------------------------

CREATE TABLE order_items (
    id                UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    order_id          UUID NOT NULL REFERENCES orders(id) ON DELETE CASCADE,
    cocktail_size_id  UUID NOT NULL REFERENCES cocktail_sizes(id),
    status            VARCHAR(30) NOT NULL DEFAULT 'PREPARATION_INGREDIENTS'
        CHECK (status IN ('PREPARATION_INGREDIENTS', 'ASSEMBLAGE', 'DRESSAGE', 'TERMINEE')),
    quantity          INTEGER NOT NULL DEFAULT 1 CHECK (quantity > 0),
    created_at        TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- ============================================================
-- Index utiles
-- ============================================================

CREATE INDEX idx_categories_barmaker ON categories(barmaker_id);
CREATE INDEX idx_cart_items_cart ON cart_items(cart_id);
CREATE INDEX idx_cocktails_category ON cocktails(category_id);
CREATE INDEX idx_cocktail_sizes_cocktail ON cocktail_sizes(cocktail_id);
CREATE INDEX idx_orders_client ON orders(client_id);
CREATE INDEX idx_orders_status ON orders(status);
CREATE INDEX idx_order_items_order ON order_items(order_id);
CREATE INDEX idx_order_items_status ON order_items(status);

-- ============================================================
-- Trigger : mise à jour automatique du statut global de la commande
-- Passe la commande à 'TERMINEE' quand tous ses order_items sont 'TERMINEE'
-- ============================================================

CREATE OR REPLACE FUNCTION update_order_status()
RETURNS TRIGGER AS $$
DECLARE
    total_items INTEGER;
    finished_items INTEGER;
    target_order_id UUID;
BEGIN
    target_order_id := COALESCE(NEW.order_id, OLD.order_id);

    SELECT COUNT(*), COUNT(*) FILTER (WHERE status = 'TERMINEE')
    INTO total_items, finished_items
    FROM order_items
    WHERE order_id = target_order_id;

    IF total_items > 0 AND total_items = finished_items THEN
        UPDATE orders
        SET status = 'TERMINEE', updated_at = now()
        WHERE id = target_order_id AND status <> 'TERMINEE';
    ELSIF finished_items < total_items THEN
        -- Si au moins un item est passé en préparation, la commande est "en cours"
        UPDATE orders
        SET status = 'EN_COURS_DE_PREPARATION', updated_at = now()
        WHERE id = target_order_id AND status = 'COMMANDEE';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_update_order_status
AFTER UPDATE OF status ON order_items
FOR EACH ROW
EXECUTE FUNCTION update_order_status();

-- ============================================================
-- Fonction : transformer le panier d'un client en commande
-- Copie les cart_items vers une nouvelle order + order_items,
-- puis vide le panier. Retourne l'id de la commande créée.
-- ============================================================

CREATE OR REPLACE FUNCTION checkout_cart(p_client_id UUID)
RETURNS UUID AS $$
DECLARE
    v_cart_id UUID;
    v_order_id UUID;
    v_item_count INTEGER;
BEGIN
    SELECT id INTO v_cart_id FROM carts WHERE client_id = p_client_id;

    IF v_cart_id IS NULL THEN
        RAISE EXCEPTION 'Aucun panier trouvé pour ce client';
    END IF;

    SELECT COUNT(*) INTO v_item_count FROM cart_items WHERE cart_id = v_cart_id;

    IF v_item_count = 0 THEN
        RAISE EXCEPTION 'Le panier est vide';
    END IF;

    INSERT INTO orders (client_id, status)
    VALUES (p_client_id, 'COMMANDEE')
    RETURNING id INTO v_order_id;

    INSERT INTO order_items (order_id, cocktail_size_id, status, quantity)
    SELECT v_order_id, cocktail_size_id, 'PREPARATION_INGREDIENTS', quantity
    FROM cart_items
    WHERE cart_id = v_cart_id;

    DELETE FROM cart_items WHERE cart_id = v_cart_id;

    RETURN v_order_id;
END;
$$ LANGUAGE plpgsql;

-- Exemple d'utilisation :
