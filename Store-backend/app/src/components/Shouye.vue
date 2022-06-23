<template>
  <div class="app">
    <div v-if="islogin">
      <div class="ma"></div>
      <h1 class="ni">欢迎您 {{ name }}</h1>
    </div>
    <div v-else class="nologin">
      请先登陆
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      getNumberList: [],
    };
  },
  methods: {},
  computed: {
    islogin() {
      if (localStorage.getItem("token") != null) {
        this.$store.state.software.token = localStorage.getItem("token");
        return true;
      }
      return false;
    },
    name() {
      const token = localStorage.getItem("token") 
      if (token != null) {
        console.log(token)
        this.$store.state.software.name = localStorage.getItem("name");
        return localStorage.getItem("name");
      }
      return "";
    },
  },
  mounted() {
    // this.$store.dispatch("getNumber");
  },
};
</script>

<style scoped>
.ma {
  height: 30vh;
}
.ni {
  text-align: center;
  margin-top: 10vh;
}
.app {
  background-color: white;
}
.show {
  width: 18vw;
  height: 38vh;
  background-color: aqua;
  float: left;
  margin-left: 6vw;
  margin-top: 5vh;
}
.card {
  width: 100%;
  height: 100%;
}
/* .show:hover{

} */
.nologin {
  text-align: center;
}
</style>
