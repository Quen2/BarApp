<template>
  <div class="min-h-screen flex items-center justify-center bg-[#1E1B18] text-[#E8E2D9] px-6">

    <div class="w-full max-w-sm">

    <div class="mb-10 text-center">
        <h1 class="text-3xl font-bold tracking-widest">
          AFTER MIDNIGHT
        </h1>
      </div>

      <!-- FORM -->
      <form @submit.prevent="login" class="flex flex-col gap-4">

        <input
          v-model="email"
          type="email"
          placeholder="Email"
          class="bg-[#141414] border border-[#2a2a2a] rounded-lg px-4 py-3 text-sm outline-none focus:border-[#8B1F14] transition"
        />

        <input
          v-model="password"
          type="password"
          placeholder="Password"
          class="bg-[#141414] border border-[#2a2a2a] rounded-lg px-4 py-3 text-sm outline-none focus:border-[#8B1F14] transition"
        />

        <button
          type="submit"
          class="mt-2 bg-[#8B1F14] hover:bg-[#6f1610] transition text-[#E8E2D9] font-bold py-3 rounded-lg"
        >
          Se connecter
        </button>

      </form>

      <!-- ERROR -->
      <p v-if="error" class="text-red-400 text-sm mt-4 text-center">
        {{ error }}
      </p>

    </div>

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
    error.value = "Combinaison email/mot de passe invalide"
  }
}
</script>