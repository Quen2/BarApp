-- ============================================================
-- Données de test (seed) pour l'application de cocktails
-- À exécuter après V1__init_schema.sql
-- ============================================================

-- ----------------------------------------
-- Barmakers (mot de passe en clair ici pour le seed : "password123",
-- haché avec BCrypt — remplacez par un hash généré par votre appli
-- si vous voulez pouvoir vous connecter avec ces comptes)
-- ----------------------------------------

WITH new_barmaker AS (
    INSERT INTO barmakers (name, email, password_hash)
    VALUES ('Alice Dupont', 'alice@aftermidnight.bar',
            '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy')
    RETURNING id
),

-- ----------------------------------------
-- Clients (juste des id, pas de credentials)
-- ----------------------------------------

new_client_1 AS (
    INSERT INTO clients (id)
    VALUES (gen_random_uuid())
    RETURNING id
),
new_client_2 AS (
    INSERT INTO clients (id)
    VALUES (gen_random_uuid())
    RETURNING id
),

-- ----------------------------------------
-- Catégories
-- ----------------------------------------

cat_signature AS (
    INSERT INTO categories (barmaker_id, name, position)
    SELECT id, 'Cocktails Signature', 1 FROM new_barmaker
    RETURNING id
),
cat_classiques AS (
    INSERT INTO categories (barmaker_id, name, position)
    SELECT id, 'Classiques', 2 FROM new_barmaker
    RETURNING id
),
cat_sans_alcool AS (
    INSERT INTO categories (barmaker_id, name, position)
    SELECT id, 'Sans Alcool', 3 FROM new_barmaker
    RETURNING id
),

-- ----------------------------------------
-- Cocktails
-- ----------------------------------------

cocktail_mojito AS (
    INSERT INTO cocktails (category_id, name, ingredients, description, position)
    SELECT id, 'Mojito', 'Rhum blanc, menthe fraîche, citron vert, sucre de canne, eau gazeuse',
           'Le classique cubain, frais et pétillant', 1
    FROM cat_classiques
    RETURNING id
),
cocktail_old_fashioned AS (
    INSERT INTO cocktails (category_id, name, ingredients, description, position)
    SELECT id, 'Old Fashioned', 'Bourbon, sucre, Angostura bitters, zeste d''orange',
           'Un cocktail intemporel, fort en caractère', 2
    FROM cat_classiques
    RETURNING id
),
cocktail_after_midnight AS (
    INSERT INTO cocktails (category_id, name, ingredients, description, position)
    SELECT id, 'After Midnight', 'Gin, liqueur de violette, jus de citron, blanc d''œuf',
           'La signature de la maison', 1
    FROM cat_signature
    RETURNING id
),
cocktail_virgin_mojito AS (
    INSERT INTO cocktails (category_id, name, ingredients, description, position)
    SELECT id, 'Virgin Mojito', 'Menthe fraîche, citron vert, sucre de canne, eau gazeuse',
           'Toute la fraîcheur, sans alcool', 1
    FROM cat_sans_alcool
    RETURNING id
),

-- ----------------------------------------
-- Tailles et prix des cocktails
-- ----------------------------------------

size_mojito_s AS (
    INSERT INTO cocktail_sizes (cocktail_id, size, price)
    SELECT id, 'S', 7.50 FROM cocktail_mojito
    RETURNING id, cocktail_id
),
size_mojito_m AS (
    INSERT INTO cocktail_sizes (cocktail_id, size, price)
    SELECT id, 'M', 9.50 FROM cocktail_mojito
    RETURNING id, cocktail_id
),
size_mojito_l AS (
    INSERT INTO cocktail_sizes (cocktail_id, size, price)
    SELECT id, 'L', 11.50 FROM cocktail_mojito
    RETURNING id, cocktail_id
),
size_old_fashioned_m AS (
    INSERT INTO cocktail_sizes (cocktail_id, size, price)
    SELECT id, 'M', 10.50 FROM cocktail_old_fashioned
    RETURNING id, cocktail_id
),
size_after_midnight_m AS (
    INSERT INTO cocktail_sizes (cocktail_id, size, price)
    SELECT id, 'M', 12.00 FROM cocktail_after_midnight
    RETURNING id, cocktail_id
),
size_after_midnight_l AS (
    INSERT INTO cocktail_sizes (cocktail_id, size, price)
    SELECT id, 'L', 14.00 FROM cocktail_after_midnight
    RETURNING id, cocktail_id
),
size_virgin_mojito_s AS (
    INSERT INTO cocktail_sizes (cocktail_id, size, price)
    SELECT id, 'S', 5.00 FROM cocktail_virgin_mojito
    RETURNING id, cocktail_id
),
size_virgin_mojito_m AS (
    INSERT INTO cocktail_sizes (cocktail_id, size, price)
    SELECT id, 'M', 6.50 FROM cocktail_virgin_mojito
    RETURNING id, cocktail_id
),

-- ----------------------------------------
-- Panier actif pour le client 1 (avec quelques articles)
-- ----------------------------------------

cart_client_1 AS (
    INSERT INTO carts (client_id)
    SELECT id FROM new_client_1
    RETURNING id
),
cart_item_1 AS (
    INSERT INTO cart_items (cart_id, cocktail_size_id, quantity)
    SELECT cart_client_1.id, size_mojito_m.id, 2
    FROM cart_client_1, size_mojito_m
    RETURNING id
),
cart_item_2 AS (
    INSERT INTO cart_items (cart_id, cocktail_size_id, quantity)
    SELECT cart_client_1.id, size_after_midnight_m.id, 1
    FROM cart_client_1, size_after_midnight_m
    RETURNING id
),

-- ----------------------------------------
-- Commande déjà passée pour le client 2 (en cours de préparation)
-- ----------------------------------------

order_client_2 AS (
    INSERT INTO orders (client_id, status)
    SELECT id, 'EN_COURS_DE_PREPARATION' FROM new_client_2
    RETURNING id
),
order_item_1 AS (
    INSERT INTO order_items (order_id, cocktail_size_id, status, quantity)
    SELECT order_client_2.id, size_old_fashioned_m.id, 'ASSEMBLAGE', 1
    FROM order_client_2, size_old_fashioned_m
    RETURNING id
)

-- ----------------------------------------
-- Dernier order_item : déclenche le trigger de recalcul du statut
-- global de la commande (reste "EN_COURS_DE_PREPARATION" puisque
-- cet item n'est pas encore "TERMINEE")
-- ----------------------------------------

INSERT INTO order_items (order_id, cocktail_size_id, status, quantity)
SELECT order_client_2.id, size_virgin_mojito_s.id, 'PREPARATION_INGREDIENTS', 2
FROM order_client_2, size_virgin_mojito_s;