<script setup>
import { onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'

const route = useRoute()
const router = useRouter()

onMounted(() => {
  const tableId = route.params.id

  localStorage.setItem('table_id', tableId)
  router.replace('/')
})
</script>

<template>
  <div></div>
</template>
