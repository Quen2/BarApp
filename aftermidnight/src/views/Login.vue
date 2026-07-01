<template>
  <div class="login">

    <h1>🍸 BARMAN LOGIN</h1>

    <form @submit.prevent="login">

      <input
        v-model="email"
        type="email"
        placeholder="Email"
      />

      <input
        v-model="password"
        type="password"
        placeholder="Password"
      />

      <button type="submit">
        Login
      </button>

    </form>

    <p v-if="error" class="error">
      {{ error }}
    </p>

  </div>
</template>

<script setup>
import { ref } from "vue"
import axios from "axios"
import { useRouter } from "vue-router"

const email = ref("")
const password = ref("")
const error = ref(null)

const router = useRouter()

const login = async () => {
  error.value = null

  try {
    const res = await axios.post(
      "http://localhost:8080/api/barmakers/login",
      {
        email: email.value,
        password: password.value
      }
    )

    const barmaker = res.data

    localStorage.setItem("barmaker_id", barmaker.id)
    localStorage.setItem("barmaker_name", barmaker.name)

    router.push("/orders")

  } catch (e) {
    error.value = "Invalid credentials"
  }
}
</script>

<style scoped>
.login {
  min-height: 100vh;
  background: #0b0b10;
  color: white;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

form {
  display: flex;
  flex-direction: column;
  gap: 10px;
  width: 250px;
}

input {
  padding: 10px;
  border-radius: 6px;
  border: none;
}

button {
  padding: 10px;
  background: #ff2bd6;
  border: none;
  color: white;
  cursor: pointer;
  border-radius: 6px;
}

.error {
  color: red;
  margin-top: 10px;
}
</style>