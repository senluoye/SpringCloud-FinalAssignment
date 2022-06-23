import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import Shouye from '../components/Shouye.vue'
import Commodity from '../components/Commodity.vue'
import Add from '../components/Add.vue'
import Self from '../views/self'
import Change from '../components/Change'
import Coupon from '../components/Coupon'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}

Vue.use(VueRouter)

const routes = [{
    path: '/',
    redirect: '/shouye',
    name: 'Home',
    component: Home,
    children: [{
        path: '/shouye',
        name: 'Shouye',
        component: Shouye
    },

    {
        path: '/commodity',
        name: 'commodity',
        component: Commodity
    },
    {
        path: '/add',
        name: 'Add',
        component: Add
    },
    {
        path: '/self',
        name: 'Self',
        component: Self
    },
    {
        path: '/change',
        name: 'Change',
        component: Change
    },
    {
        path: '/coupon',
        name: 'Coupon',
        component: Coupon
    },
    ]
},
{
    path: '/login',
    name: 'Login',
    component: Login
},
{
    path: '/register',
    name: 'Register',
    component: Register
},
]

const router = new VueRouter({
    // base: '/vue3/',
    // mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router