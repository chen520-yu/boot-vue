<template>
  <li v-for="n in evenNumbers" :key="n">{{ n }}</li>
</template>
<script>
let Vue;
Vue.createApp({
  data() {
    return {
      numbers: [ 1, 2, 3, 4, 5 ]
    }
  },
  computed: {
    evenNumbers() {
      return this.numbers.filter(number => number % 2 === 0)
    }
  }
})


</script>



