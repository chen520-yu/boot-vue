// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App.vue';
import router from './router'
import ElementUI from 'element-ui';
import store from "./store"

import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios';

import "./axios"

Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.prototype.$axios = axios
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App),
  components: { App },
  template: '<App/>'
})
