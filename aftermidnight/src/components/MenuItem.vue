<template>
  <div class="cocktail-card border border-[#6B6B6B] rounded p-4 flex flex-col items-center mt-2 border-dotted">
    <h3 class="text-lg font-bold">{{ cocktail.name }}</h3>
    <p class="text-sm">{{ cocktail.ingredients }}</p>
    <div class="flex justify-center gap-2 mt-2">
      <button
        v-for="size in cocktail.sizes"
        :key="size.id"
        @click="emitAddToCart(size)"
        class="text-sm font-bold bg-[#8B1F14] hover:bg-[#8D1820] text-[#E8E2D9] py-1 px-3 rounded cursor-pointer flex justify-center gap-2"
      >
        {{ size.size }} • {{ size.price }}€
      </button>
    </div>
  </div>
</template>

<script setup>
const props = defineProps({
  cocktail: {
    type: Object,
    required: true
  }
})

const emit = defineEmits(["add-to-cart"])

const emitAddToCart = (size) => {
  emit("add-to-cart", {
    cocktail: props.cocktail,
    size
  })
}
</script>
