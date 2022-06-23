<template>
  <div class="app" v-bind="this.user">
    <div v-if="!islogin">请先登陆</div>
    <div class="from" v-else>
      <div class="top"></div>

      <el-row :gutter="20" type="flex" class="row-bg item">
        <el-col :span="2">
          <div class="grid-content bg-purple">
            <h3><i class="el-icon-lollipop"></i>昵称</h3>
          </div>
        </el-col>
        <el-col :span="12">
          <div v-if="ischange" class="aa">
            {{ this.user.name }}
          </div>
          <div id="input1" class="grid-content bg-purple-light" v-else>
            <h3>
              <el-input v-model="user.name" placeholder="请输入内容"></el-input>
            </h3>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" type="flex" class="row-bg item">
        <el-col :span="2">
          <div class="grid-content bg-purple">
            <h3><i class="el-icon-message"></i>密码</h3>
          </div>
        </el-col>
        <el-col :span="12">
          <div v-if="ischange" class="aa">
            {{ this.user.password }}
          </div>
          <div id="input1" class="grid-content bg-purple-light" v-else>
            <h3>
              <el-input
                v-model="user.password"
                placeholder="请输入内容"
              ></el-input>
            </h3>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="buttom">
        <div class="grid-content bg-purple exit">
          <el-button type="primary" @click="tuichu">退出登录</el-button>
        </div>
      </el-row>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      user: {
        id: "",
        name: "",
        password: "",
      },
      ischange: true,
    };
  },
  methods: {
    zhuxiao() {
      console.log(this.$store.state.software.user);
      this.$store.dispatch("zhuxiao", this.user.email);
      this.$alert("注销成功", "提示", {
        confirmButtonText: "确定",
        callback: (action) => {
          this.$message({
            type: "info",
            message: `action: ${action}`,
          });
        },
      });
      this.$store.state.software.islogin = false;
    },
    surechange() {
      this.$store.dispatch("changeUser", {
        email: this.user.email,
        username: this.user.username,
        qq: this.user.qq,
        wechat: this.user.wechat,
        phone: this.user.phone,
      });
      this.ischange = true;
    },
    tuichu() {
      localStorage.removeItem("token");
      this.$router.push({
        path: "/login",
      });
    },
    change() {
      this.ischange = false;
    },
  },
  computed: {
    islogin() {
      if (localStorage.getItem("token") != null) {
        this.$store.state.software.token = localStorage.getItem("token");
        return true;
      }
      return false;
    },
  },
  mounted() {
    this.user.id = localStorage.getItem("id");
    this.user.name = localStorage.getItem("name");
    this.user.password = localStorage.getItem("password");
    console.log(this.user);
  },
};
</script>

<style scoped>
.exit {
  float: right;
}
.aa {
  margin-top: 20px;
}
.buttom {
  margin-top: 10vh;
  /* margin-left: 20vw; */
  width: 40vw;
}
.top {
  height: 10vh;
}
.app {
  background-color: white;
}
.from {
  margin-left: 20vw;
  /* margin-top: 0vh; */
  width: 60vw;
  height: 60vh;
}
.item {
  margin-top: 20px;
}
</style>
