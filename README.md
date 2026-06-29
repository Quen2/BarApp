# BarApp

Schméma de la BDD : 

# Schéma de base de données - Application de commande de cocktails

## Diagramme entité-relation

```mermaid
erDiagram
  USERS ||--o{ CATEGORIES : "cree"
  CATEGORIES ||--o{ COCKTAILS : "contient"
  COCKTAILS ||--o{ COCKTAIL_SIZES : "decline_en"
  USERS ||--o| CARTS : "possede"
  CARTS ||--o{ CART_ITEMS : "contient"
  COCKTAIL_SIZES ||--o{ CART_ITEMS : "correspond_a"
  USERS ||--o{ ORDERS : "passe"
  ORDERS ||--o{ ORDER_ITEMS : "contient"
  COCKTAIL_SIZES ||--o{ ORDER_ITEMS : "correspond_a"

  USERS {
    uuid id PK
    string role
    string name
    string email
  }
  CATEGORIES {
    uuid id PK
    uuid barmaker_id FK
    string name
  }
  COCKTAILS {
    uuid id PK
    uuid category_id FK
    string name
    text ingredients
  }
  COCKTAIL_SIZES {
    uuid id PK
    uuid cocktail_id FK
    string size
    numeric price
  }
  CARTS {
    uuid id PK
    uuid client_id FK
    timestamp updated_at
  }
  CART_ITEMS {
    uuid id PK
    uuid cart_id FK
    uuid cocktail_size_id FK
    int quantity
  }
  ORDERS {
    uuid id PK
    uuid client_id FK
    string status
    timestamp created_at
  }
  ORDER_ITEMS {
    uuid id PK
    uuid order_id FK
    uuid cocktail_size_id FK
    string status
  }
```

## Description des tables

### USERS
Centralise les deux types d'utilisateurs (`role` = `client` ou `barmaker`).

### CATEGORIES
Catégories de la carte, créées par un barmaker.

### COCKTAILS
Un cocktail appartient à une catégorie et liste ses ingrédients.

### COCKTAIL_SIZES
Déclinaisons de taille (S, M, L) d'un cocktail, chacune avec son propre prix.

### CARTS
Panier actif d'un client. Un client a au plus un panier (contrainte `UNIQUE` sur `client_id`).

### CART_ITEMS
Lignes du panier, référençant une taille de cocktail précise et une quantité.

### ORDERS
Commande passée par un client. Statut global : `commandee`, `en_cours_de_preparation`, `terminee`.

### ORDER_ITEMS
Lignes d'une commande. Chaque ligne suit son propre avancement :
`preparation_ingredients` → `assemblage` → `dressage` → `terminee`.

## Règles de gestion

- Le statut global d'une commande passe automatiquement à `terminee` lorsque tous ses `order_items` sont à `terminee` (géré par trigger côté PostgreSQL).
- Le passage du panier à la commande copie les `cart_items` en `order_items`, puis vide le panier (fonction `checkout_cart`).
