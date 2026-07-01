<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import axios from 'axios'

const route = useRoute()
const order = ref(null)
const loading = ref(true)

onMounted(async () => {
  const res = await axios.get(`http://localhost:8080/api/order/${route.params.id}`)
  order.value = res.data
  loading.value = false
})
</script>

<template>
  <div class="min-h-screen bg-[#1E1B18] text-[#E8E2D9] p-6">

    <div v-if="!order" class="text-center text-[#6B6B6B]">
      Loading order...
    </div>

    <div v-else class="max-w-2xl mx-auto">

      <!-- HEADER -->
      <div class="mb-6">
        <h1 class="text-2xl font-bold text-[#8B1F14]">
          Suivi de commande
        </h1>

        <p class="text-sm text-[#6B6B6B]">
          Table {{ order.tableNumber }} • {{ order.status }}
        </p>
      </div>

      <!-- ITEMS -->
      <div class="space-y-3">

        <div
          v-for="item in order.orderItems"
          :key="item.id"
          class="bg-[#141414] border border-[#2a2a2a] rounded-xl p-4 flex justify-between items-center"
        >

          <div>
            <p class="font-bold">
              {{ item.cocktailSize.cocktail.name }}
            </p>

            <p class="text-xs text-[#6B6B6B]">
              x{{ item.quantity }}
            </p>
          </div>

          <span class="text-sm text-[#8B1F14]">
            {{ item.status }}
          </span>

        </div>

      </div>

    </div>
  </div>
</template>