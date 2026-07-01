<template>
  <div class="min-h-screen bg-[#1E1B18] text-[#E8E2D9] p-4">

    <header class="flex justify-between items-center mb-6">
      <div>
        <h1 class="text-2xl font-bold text-[#8B1F14]">
          COMMANDES
        </h1>
      </div>

      <div class="text-sm bg-[#8B1F14] px-3 py-1 rounded">
        LIVE
      </div>
    </header>

    <div v-if="loading" class="text-center text-[#6B6B6B]">
      loading orders...
    </div>

    <div v-else class="grid gap-4 md:grid-cols-2 lg:grid-cols-3">

      <div
        v-for="order in orders"
        :key="order.id"
        class="bg-[#141414] border border-[#2a2a2a] rounded-xl p-4"
      >

        <div class="flex justify-between items-start mb-3">

          <div>
            <h2 class="text-lg font-bold">
              Table {{ order.tableNumber }}
            </h2>

            <span
              class="text-xs px-2 py-1 rounded mt-1 inline-block"
              :class="statusClass(order.status)"
            >
              {{ formatStatus(order.status) }}
            </span>
          </div>

          <div class="text-xs text-[#6B6B6B]">
            {{ formatDate(order.createdAt) }}
          </div>

        </div>

        <div class="space-y-2">

          <div
            v-for="item in order.orderItems"
            :key="item.id"
            class="flex justify-between items-center bg-[#1c1c25] p-3 rounded-lg"
          >

            <div>
              <p class="font-semibold text-[#E8E2D9]">
                {{ item.cocktailSize.cocktail.name }}
              </p>

              <p class="text-sm text-[#6B6B6B]">
                {{ item.cocktailSize.size }} • x{{ item.quantity }}
              </p>
            </div>

            <span
              class="text-xs font-medium"
              :class="itemStatusClass(item.status)"
            >
              {{ formatItemStatus(item.status) }}
            </span>

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
    console.log(res.data)
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
    case "PREPARATION_INGREDIENTS": return "Ingredients"
    case "ASSEMBLAGE": return "Assemblage"
    case "DRESSAGE": return "Dressage"
    case "TERMINEE": return "Terminé"
    default: return status
  }
}

const statusClass = (status) => {
  switch (status) {
    case "COMMANDEE": return "bg-yellow-500 text-black"
    case "EN_COURS_DE_PREPARATION": return "bg-orange-500 text-black"
    case "TERMINEE": return "bg-green-600 text-white"
    default: return "bg-gray-600"
  }
}

const itemStatusClass = (status) => {
  switch (status) {
    case "PREPARATION_INGREDIENTS": return "text-blue-300"
    case "ASSEMBLAGE": return "text-orange-300"
    case "DRESSAGE": return "text-pink-300"
    case "TERMINEE": return "text-green-400"
    default: return "text-gray-400"
  }
}

const formatDate = (date) => {
  return new Date(date).toLocaleTimeString()
}
</script>