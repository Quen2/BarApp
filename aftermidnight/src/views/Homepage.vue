<template>
  <div class="home">
    <h1>Bienvenue 👋</h1>

    <div class="actions">
      <button @click="goLogin">Connexion</button>
      <button @click="menu">Passer commande</button>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from "vue-router"

const router = useRouter()

const goLogin = () => {
  router.push("/login")
}

const menu = () => {
  router.push("/menu")
}
</script>

<style scoped>
.home {
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.actions {
  display: flex;
  gap: 20px;
  margin-top: 20px;
}

button {
  padding: 10px 20px;
  cursor: pointer;
}
</style>