import { createRouter, createWebHistory } from 'vue-router'
import Login from "@/views/Login"
import Blogs from "@/views/Blogs"
import BlogDetail from "@/views/BlogDetail"
import BlogEdit from "@/views/BlogEdit"

const routes = [
    {
        path: '/',
        name : 'Index',
        component:Blogs
    },
    {
        path: '/blogs',
        name : 'Blogs',
        component:Blogs
    },
    {
        path: '/login',
        name : 'Login',
        component:Login
    },
    {
        path: '/blog/add',
        name : 'BlogEdit',
        component:BlogEdit
    },
    {
        path: '/blog/:blogId',
        name : 'BlogDetail',
        component:BlogDetail
    },

    {
        path: '/blog/:blogId/edit',
        name : 'BlogEdit',
        component:BlogEdit
    }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
