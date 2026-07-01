import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/Homepage.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/table/:id',
      name: 'table',
      component: () => import('@/components/StockTableNumber.vue'),
    },
    {
      path: '/menu',
      name: 'menu',
      component: () => import('@/views/Menu.vue'),
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('@/views/Login.vue'),
    },
    {
      path: '/orders',
      name: 'orders',
      component: () => import('@/views/OrderList.vue'),
    },
  ],
})

export default router
