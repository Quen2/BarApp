<template>
  <div class="orders">

    <header class="header">
      <h1>🍸 Orders Dashboard</h1>
      <p>Barman panel</p>
    </header>

    <div v-if="loading" class="loading">
      loading orders...
    </div>

    <div v-else class="grid">

      <div
        v-for="order in orders"
        :key="order.id"
        class="order-card"
      >

        <!-- HEADER ORDER -->
        <div class="order-header">
          <div>
            <h2>Table {{ order.tableNumber }}</h2>
            <span class="status" :class="order.status">
              {{ formatStatus(order.status) }}
            </span>
          </div>

          <div class="date">
            {{ formatDate(order.createdAt) }}
          </div>
        </div>

        <!-- ITEMS -->
        <div class="items">

          <div
            v-for="item in order.items"
            :key="item.id"
            class="item"
          >

            <div class="item-left">
              <div class="cocktail">
                {{ item.cocktailSize.size }}
              </div>

              <div class="qty">
                x{{ item.quantity }}
              </div>
            </div>

            <div class="item-right">
              <span class="item-status">
                {{ formatItemStatus(item.status) }}
              </span>
            </div>

          </div>

        </div>

      </div>

    </div>

  </div>
</template>

<script setup>
import { ref, onMounted } from "vue"
import axios from "axios"

const orders = ref([])
const loading = ref(true)

onMounted(async () => {
  try {
    const res = await axios.get("http://localhost:8080/api/order")
    orders.value = res.data
  } finally {
    loading.value = false
  }
})

const formatStatus = (status) => {
  switch (status) {
    case "COMMANDEE": return "🟡 Commandée"
    case "EN_COURS_DE_PREPARATION": return "🟠 En préparation"
    case "TERMINEE": return "🟢 Terminée"
    default: return status
  }
}

const formatItemStatus = (status) => {
  switch (status) {
    case "PREPARATION_INGREDIENTS": return "🧊 Ingredients"
    case "ASSEMBLAGE": return "🍸 Assemblage"
    case "DRESSAGE": return "✨ Dressage"
    case "TERMINEE": return "✅ Terminé"
    default: return status
  }
}

const formatDate = (date) => {
  return new Date(date).toLocaleTimeString()
}
</script>

<style scoped>
.orders {
  min-height: 100vh;
  background: #0b0b10;
  color: white;
  padding: 20px;
  font-family: sans-serif;
}

/* HEADER */
.header {
  margin-bottom: 20px;
}

/* GRID */
.grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(320px, 1fr));
  gap: 15px;
}

/* ORDER CARD */
.order-card {
  background: #15151c;
  border: 1px solid #2a2a2a;
  border-radius: 12px;
  padding: 15px;
}

/* ORDER HEADER */
.order-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 15px;
}

.status {
  font-size: 12px;
  padding: 3px 8px;
  border-radius: 6px;
  background: #333;
}

/* ITEMS */
.items {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.item {
  display: flex;
  justify-content: space-between;
  padding: 8px;
  background: #1c1c25;
  border-radius: 8px;
}

.item-left {
  display: flex;
  gap: 10px;
}

.cocktail {
  font-weight: bold;
}

.qty {
  opacity: 0.7;
}

.item-status {
  font-size: 12px;
  opacity: 0.8;
}

/* LOADING */
.loading {
  opacity: 0.6;
}
</style>