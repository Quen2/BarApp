<template>
  <div class="w-screen h-screen flex flex-col items-center justify-center gap-8 bg-[#1E1B18] text-[#E8E2D9]">
    <h1 class="text-6xl font-bold">AFTER MIDNIGHT</h1>
    <img :src="CocktailVector" alt="cocktail" />

    <div class="actions">
      <button @click="menu" class="bg-[#8B1F14] hover:bg-[#8D1820] text-[#E8E2D9] font-bold py-2 px-4 rounded cursor-pointer">Passer commande</button>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from "vue-router"
import CocktailVector from "@/assets/Cocktail Vector.svg"


const router = useRouter()

const goLogin = () => {
  router.push("/login")
}

const menu = () => {
  router.push("/menu")
}
</script>

<style scoped>
</style>