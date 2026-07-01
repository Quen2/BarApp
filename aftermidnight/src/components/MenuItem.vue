<template>
  <div class="cocktail-card">
    <h3>{{ cocktail.name }}</h3>

    <p class="desc">{{ cocktail.description }}</p>
    <p class="ingredients">{{ cocktail.ingredients }}</p>

    <div class="sizes">
      <button
        v-for="size in cocktail.sizes"
        :key="size.id"
        @click="emitAddToCart(size)"
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

<style scoped>
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
</style>