<template>
  <div class="menu">

    <!-- HEADER -->
    <header class="header">
      <div class="title">
        🍸 AFTER MIDNIGHT
      </div>

      <div class="sub">
        {{ tableId ? `TABLE ${tableId}` : "" }}
      </div>
    </header>

    <!-- LOADING -->
    <div v-if="loading" class="loading">
      loading cocktails...
    </div>

    <!-- CONTENT -->
    <div v-else class="content">

      <section
        v-for="category in categories"
        :key="category.id"
        class="category"
      >
        <h2 class="category-title">
          {{ category.name }}
        </h2>

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

    <!-- CART BUTTON -->
    <button class="cart-btn">
      🖤 CART • {{ cartCount }}
    </button>

  </div>
</template>

<script setup>
import { ref, onMounted } from "vue"
import axios from "axios"
import MenuItem from "@/components/MenuItem.vue"

const categories = ref([])
const loading = ref(true)
const cartCount = ref(0)

const tableId = localStorage.getItem("table_id")

onMounted(async () => {
  try {
    const res = await axios.get("http://localhost:8080/api/category")
    categories.value = res.data
  } catch (e) {
    console.error(e)
  } finally {
    loading.value = false
  }

  const cart = JSON.parse(localStorage.getItem("cart") || "[]")
  cartCount.value = cart.length
})

const addToCart = ({ cocktail, size }) => {
  const cart = JSON.parse(localStorage.getItem("cart") || "[]")

  cart.push({
    cocktailId: cocktail.id,
    name: cocktail.name,
    sizeId: size.id,
    size: size.size,
    price: size.price,
    quantity: 1
  })

  localStorage.setItem("cart", JSON.stringify(cart))
  cartCount.value = cart.length
}
</script>

<style scoped>
/* ===== BASE ===== */
.menu {
  min-height: 100vh;
  background: #0b0b10;
  color: #f2f2f2;
  font-family: "Arial", sans-serif;
  padding: 20px;
  position: relative;
  overflow-x: hidden;
}

/* ===== HEADER ===== */
.header {
  text-align: center;
  margin-bottom: 30px;
}

.title {
  font-size: 28px;
  font-weight: bold;
  letter-spacing: 3px;
  color: #ff2bd6;
  text-shadow: 0 0 10px #ff2bd6, 0 0 20px #8a2be2;
}

.sub {
  font-size: 12px;
  opacity: 0.7;
  margin-top: 5px;
  letter-spacing: 2px;
}

/* ===== LOADING ===== */
.loading {
  text-align: center;
  opacity: 0.6;
  font-style: italic;
}

/* ===== CATEGORY ===== */
.category {
  margin-bottom: 40px;
}

.category-title {
  font-size: 18px;
  margin-bottom: 15px;
  color: #00f5ff;
  text-transform: uppercase;
  letter-spacing: 2px;
  border-left: 3px solid #ff2bd6;
  padding-left: 10px;
}

/* ===== GRID ===== */
.grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(260px, 1fr));
  gap: 15px;
}

/* ===== CART BUTTON ===== */
.cart-btn {
  position: fixed;
  bottom: 20px;
  right: 20px;

  background: linear-gradient(135deg, #ff2bd6, #8a2be2);
  border: none;
  color: white;

  padding: 14px 18px;
  border-radius: 40px;

  font-weight: bold;
  cursor: pointer;

  box-shadow: 0 0 15px rgba(255, 43, 214, 0.5);
  transition: 0.2s;
}

.cart-btn:hover {
  transform: scale(1.05);
  box-shadow: 0 0 25px rgba(255, 43, 214, 0.8);
}
</style>