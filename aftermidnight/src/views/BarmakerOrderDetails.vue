<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import axios from 'axios'

const route = useRoute()
const order = ref(null)

const fetchOrder = async () => {
  const res = await axios.get(`http://localhost:8080/api/order/${route.params.id}`)
  order.value = res.data
}

onMounted(fetchOrder)
</script>

<template>
  <div class="min-h-screen bg-[#1E1B18] text-[#E8E2D9] p-6">

    <div v-if="!order" class="text-center text-[#6B6B6B]">
      Loading order...
    </div>

    <div v-else class="max-w-3xl mx-auto">

      <!-- HEADER -->
      <div class="mb-6">
        <h1 class="text-2xl font-bold text-[#8B1F14]">
          Bar - Commande
        </h1>

        <p class="text-sm text-[#6B6B6B]">
          Table {{ order.tableNumber }}
        </p>
      </div>

      <!-- ITEMS -->
      <div class="space-y-4">

        <div
          v-for="item in order.orderItems"
          :key="item.id"
          class="bg-[#141414] border border-[#2a2a2a] rounded-xl p-4"
        >

          <div class="flex justify-between items-center">

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

          <!-- ACTION -->
          <button
            class="mt-3 bg-[#8B1F14] hover:bg-[#6f1710] px-3 py-1 rounded text-sm"
            @click="nextStep(item)"
          >
            Étape suivante
          </button>

        </div>

      </div>

    </div>
  </div>
</template>

<script>
const nextStep = async (item) => {
  await axios.patch(`http://localhost:8080/api/orderitems/${item.id}/next`)
  fetchOrder()
}
</script>