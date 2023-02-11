import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Element from 'element-ui'
import "element-ui/lib/theme-chalk/index.css"
import axios from 'axios'

Vue.prototype.$axios = axios
Vue.use(Element)
createApp(App).use(store).use(router).mount('#app')
