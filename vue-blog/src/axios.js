import axios from "axios";
import Element from "element-ui"
import router from "./router"
import store from "./store"

axios.defaults.baseURL="http://localhost:8081"

axios.interceptors.request.use(config=>{
  return config
})

axios.interceptors.response.use(response=>{
  let res = response
  console.log(res)
},
  error=>{

  }
)


