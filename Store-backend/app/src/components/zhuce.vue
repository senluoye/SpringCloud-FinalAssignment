<template>
  <el-form
    :model="ruleForm"
    status-icon
    :rules="rules"
    ref="ruleForm"
    label-width="100px"
    class="demo-ruleForm"
  >
    <el-form-item label="昵称" prop="name">
      <el-input v-model="ruleForm.name" placeholder="请填写昵称"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="pass">
      <el-input
        type="password"
        v-model="ruleForm.pass"
        autocomplete="off"
        placeholder="请填写密码"
      ></el-input>
    </el-form-item>
    <el-form-item label="确认密码" prop="checkPass">
      <el-input
        type="password"
        v-model="ruleForm.checkPass"
        autocomplete="off"
        placeholder="确认密码"
      ></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">注册</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.ruleForm.checkPass !== "") {
          this.$refs.ruleForm.validateField("checkPass");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        name: "",
        pass: "",
        checkPass: "",
      },
      user: {
        name: "",
        password: "",
      },
      rules: {
        pass: [
          { validator: validatePass, trigger: "blur" },
          {
            min: 6,
            max: 10,
            message: "长度在 6 到 10 个字符/数字",
            trigger: "blur",
          },
        ],
        checkPass: [{ validator: validatePass2, trigger: "blur" }],
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$store.dispatch("register", {
        name: this.ruleForm.name,
        password: this.ruleForm.pass,
      });
      setTimeout(() => {
        if (this.$store.state.software.code == -2) {
          this.$alert("注册成功", "", {
            confirmButtonText: "前往登陆",
            callback: (action) => {
              this.$message({
                type: "info",
                message: `action: ${action}`,
              });
            },
          });
          this.$router.push({
            path: "/login",
          });
        } else {
          this.$alert("注册失败", "", {
            confirmButtonText: "重新注册",
            callback: (action) => {
              this.$message({
                type: "info",
                message: `action: ${action}`,
              });
            },
          });
        }
      }, 1000);
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
  computed: {},
};
</script>

<style scoped></style>
