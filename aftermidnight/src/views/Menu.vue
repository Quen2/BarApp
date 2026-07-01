<template>
  <div class="menu">
    <header class="header">
      <h1>🍸 Carte du bar</h1>
      <p v-if="tableId">Table {{ tableId }}</p>
    </header>

    <div v-if="loading">Chargement...</div>

    <div v-else>
        <div
            v-for="category in categories"
            :key="category.id"
            class="category"
        >
            <h2 class="category-title">
            {{ category.name }}
            </h2>

            <MenuItem
            v-for="cocktail in category.cocktails"
            :key="cocktail.id"
            :cocktail="cocktail"
            @add-to-cart="addToCart"
            />
        </div>
    </div>

    <!-- bouton panier simple -->
    <div class="cart-bar">
      🛒 Voir panier
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue"
import axios from "axios"

const categories = ref([])
const loading = ref(true)

const tableId = localStorage.getItem("table_id")
const clientId = localStorage.getItem("client_id")

onMounted(async () => {
  try {
    const res = await axios.get("http://localhost:8080/api/category")
    console.log("Categories API response:", res.data)
    categories.value = res.data
  } catch (e) {
    console.error("Erreur API category", e)
  } finally {
    loading.value = false
  }
})

const addToCart = async (cocktail, size) => {
  if (!tableId) {
    alert("Table non détectée")
    return
  }

  const payload = {
    clientId,
    tableId,
    cocktailSizeId: size.id,
    quantity: 1
  }

}
</script>

<style scoped>
.menu {
  padding: 20px;
  font-family: sans-serif;
  background: #0f0f0f;
  color: white;
  min-height: 100vh;
}

.header {
  margin-bottom: 20px;
}

.category {
  margin-bottom: 40px;
}

.category-title {
  font-size: 20px;
  margin-bottom: 10px;
  border-left: 4px solid #42b983;
  padding-left: 10px;
}

.cocktails {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  gap: 15px;
}

.cocktail-card {
  background: #1b1b1b;
  padding: 15px;
  border-radius: 12px;
  border: 1px solid #2a2a2a;
}

.desc {
  font-size: 14px;
  opacity: 0.8;
}

.ingredients {
  font-size: 12px;
  opacity: 0.6;
  margin-bottom: 10px;
}

.sizes {
  display: flex;
  gap: 8px;
  flex-wrap: wrap;
}

button {
  padding: 6px 10px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  background: #42b983;
  color: white;
}

button:hover {
  opacity: 0.8;
}

.cart-bar {
  position: fixed;
  bottom: 20px;
  right: 20px;
  background: #42b983;
  padding: 12px 18px;
  border-radius: 30px;
  cursor: pointer;
}
</style>