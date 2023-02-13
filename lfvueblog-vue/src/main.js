import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Element from 'element-plus'
import "element-plus/theme-chalk/index.css"
import axios from "axios"


createApp(App).use(store).use(router).use(Element).use(axios).mount('#app')
