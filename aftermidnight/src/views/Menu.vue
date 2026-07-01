<template>
  <div class="w-screen h-screen flex flex-col bg-[#1E1B18] text-[#E8E2D9]">
    <header class="flex justify-between w-full p-4 shrink-0">
      <div class="title">
        <span>AFTER</span>
        <span class="text-[#8B1F14]">M</span>
        <span>IDNIGHT</span>
      </div>

      <div class="flex gap-4 items-center">
        <span class="text-lg text-[#8B1F14] font-bold">{{
          tableId ? `Table ${tableId}` : ''
        }}</span>
        <button
          class="relative text-sm font-bold bg-[#8B1F14] hover:bg-[#8D1820] text-[#E8E2D9] py-1 px-3 rounded cursor-pointer"
          @click="cart"
        >
          Panier
          <span
            v-if="cartCount > 0"
            class="absolute -top-2 -right-2 bg-white text-black text-xs w-5 h-5 flex items-center justify-center rounded-full"
          >
            {{ cartCount }}
          </span>
        </button>
      </div>
    </header>

    <!-- CONTENT SCROLLABLE -->
    <main class="flex-1 overflow-y-auto p-4">
      <div v-if="loading">loading cocktails...</div>

      <div v-else>
        <p class="text-2xl font-bold">CARTE</p>
        <p class="text-2xl font-bold text-[#8B1F14]">COCKTAILS</p>

        <p class="text-sm text-[#6B6B6B]">
          Une sélection de cocktails inspirés de la nuit parisienne.
        </p>

        <section v-for="category in categories" :key="category.id" class="mt-6">
          <div class="grid">
            <MenuItem
              v-for="cocktail in category.cocktails"
              :key="cocktail.id"
              :cocktail="cocktail"
              @add-to-cart="addToCart"
            />
          </div>
        </section>
      </div>
    </main>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import MenuItem from '@/components/MenuItem.vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const categories = ref([])
const loading = ref(true)
const cartCount = ref(0)

const tableId = localStorage.getItem('table_id')

const cart = () => {
  router.push('/cart')
}

onMounted(async () => {
  try {
    const res = await axios.get('http://localhost:8080/api/category')
    categories.value = res.data
  } catch (e) {
    console.error(e)
  } finally {
    loading.value = false
  }

  const cart = JSON.parse(localStorage.getItem('cart') || '[]')
  cartCount.value = cart.length
})

const addToCart = ({ cocktail, size }) => {
  const cart = JSON.parse(localStorage.getItem('cart') || '[]')

  cart.push({
    cocktailId: cocktail.id,
    name: cocktail.name,
    sizeId: size.id,
    size: size.size,
    price: size.price,
    quantity: 1,
  })

  localStorage.setItem('cart', JSON.stringify(cart))
  cartCount.value = cart.length
}
</script>
