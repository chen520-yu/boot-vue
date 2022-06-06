import Vue from 'vue'
import Router from 'vue-router'
import { createRouter, createWebHistory } from 'vue-router'
import Login from "../view/Login";
import Blogs from "../view/Blogs";
import BlogEdit from "../view/BlogEdit";
import BlogDetail from "../view/BlogDetail";

Vue.use(Router)

export default new Router({

  mode:'history',
  base:process.env.BABEL_URl,
  routes: [
    {
      path: '/',
      name: 'Index',
      redirect: {name:"login"}
    },
    {
      path: '/login',
      name: 'login',
      component:Login
    },
    {
      path: '/blogs',
      name: 'Blogs',
      component:Blogs
    },
    {
      path: '/blog/add',
      name: 'BlogAdd',
      component:BlogEdit
    },
    {
      path: '/login',
      name: 'login',
      component:Login,
      meta:{
          requireAuth:true
      }
    },
    {
      path: '/blog/:blogId',
      name: 'BlogDetail',
      component:BlogDetail,
    },
    {
      path: '/blog/:blogId/edit',
      name: 'BlogEdit',
      component:BlogEdit,
      meta:{
        requireAuth: true
      }
    }

  ]
})
