<template>
  <div class="min-h-screen bg-[#1E1B18] text-[#E8E2D9] p-4">

    <header class="flex justify-between items-center mb-6">
      <h1 class="text-2xl font-bold text-[#8B1F14]">
        PANIER
      </h1>

      <span class="text-xs bg-[#8B1F14] px-2 py-1 rounded">
        {{ items.length }} items
      </span>
    </header>

    <div v-if="items.length === 0" class="text-[#6B6B6B] text-center mt-20">
      Ton panier est vide
    </div>

    <div v-else class="space-y-3">

      <div
        v-for="(item, index) in items"
        :key="index"
        class="bg-[#141414] border border-[#2a2a2a] p-3 rounded-lg flex justify-between items-center"
      >

        <div>
          <p class="font-bold">
            {{ item.name }}
          </p>

          <p class="text-sm text-[#6B6B6B]">
            {{ item.size }} • {{ item.price }}€
          </p>
        </div>

        <div class="flex items-center gap-3">

          <button @click="decrease(index)" class="px-2">-</button>

          <span>{{ item.quantity }}</span>

          <button @click="increase(index)" class="px-2">+</button>

        </div>

      </div>

    </div>

    <div class="fixed bottom-0 left-0 w-full p-4 bg-[#141414] border-t border-[#2a2a2a]">

      <div class="flex justify-between items-center mb-2">
        <span>Total</span>
        <span class="font-bold">{{ total }}€</span>
      </div>

      <button
        @click="createOrder"
        class="w-full bg-[#8B1F14] py-3 rounded font-bold"
      >
        Commander
      </button>

    </div>

  </div>
</template>

<script setup>
import { ref, computed, onMounted } from "vue"
import axios from "axios"
import { useRouter } from "vue-router"

const router = useRouter()

const items = ref([])

const clientId = localStorage.getItem("client_id")
const tableId = localStorage.getItem("table_id")

onMounted(() => {
  items.value = JSON.parse(localStorage.getItem("cart") || "[]")
})

const increase = (i) => {
  items.value[i].quantity++
  save()
}

const decrease = (i) => {
  if (items.value[i].quantity > 1) {
    items.value[i].quantity--
    save()
  }
}

const save = () => {
  localStorage.setItem("cart", JSON.stringify(items.value))
}

const total = computed(() =>
  items.value.reduce((sum, i) => sum + i.price * i.quantity, 0)
)

const createOrder = async () => {
  try {
    const res = await axios.post("http://localhost:8080/api/order", {
      clientId: localStorage.getItem("client_id"),
      tableNumber: Number(tableId),
      items: items.value.map(i => ({
        cocktailSizeId: i.sizeId,
        quantity: i.quantity
      }))
    })

    localStorage.removeItem("cart")
    router.push(`/order/${res.data.id}`)

  } catch (e) {
    console.error(e)
  }
}
</script>