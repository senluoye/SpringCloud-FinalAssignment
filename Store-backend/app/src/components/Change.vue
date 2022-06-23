<template>
  <div class="one">
    <div class="three">
      <div class="thzuo">
        <el-image
          style="width: 200px; height: 200px"
          :src="'http://localhost:18083/' + softwareDetail.cover"
        ></el-image>
      </div>
      <div class="thyou">
        <div class="bbbb">
          商品名称：
        </div>
        <div class="bbb">
          {{ softwareDetail.name }}
        </div>
        <el-input
          v-model="name"
          placeholder="请输入新的名称"
          class="change"
        ></el-input>
        <div class="bb">
          <div class="bbbb">
            价格：
          </div>
          <div class="bbb">
            {{ softwareDetail.price }}
          </div>
        </div>
        <el-input
          v-model="price"
          placeholder="请输入新的价格"
          class="change"
        ></el-input>
        <div class="bb">
          <div class="bbbb">
            数量：
          </div>
          <div class="bbb">
            {{ softwareDetail.num }}
          </div>
        </div>
        <el-input
          v-model="num"
          placeholder="请输入新的数量"
          class="change"
        ></el-input>
        <div class="bb">
          <div class="bbbb">
            描述：
          </div>
          <div class="bbb">
            {{ softwareDetail.description }}
          </div>
        </div>
        <el-input
          v-model="description"
          placeholder="请输入新的描述"
          class="change"
        ></el-input>
        <div class="ee">
          <el-button @click="commit()" type="primary">修改</el-button>
          <el-button @click="couponHandler()" type="primary">增加优惠卷</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      name: "",
      description: "",
      price: "",
      num: "",
      description: "",
    };
  },
  methods: {
    couponHandler() {
      this.$router.push({
        path: "/coupon",
      });
    },
    commit() {
      let commodity = {
        id: this.softwareDetail.id,
        name: this.name,
        description: this.description,
        price: this.price,
        num: this.num,
        creatorId: localStorage.getItem("id"),
      };
      let obj = { commodity: commodity };
      this.$store.dispatch("modify", JSON.stringify(obj));
      setTimeout(() => {
        if (this.$store.state.software.isChange) {
          this.$alert("修改成功", "提示", {
            confirmButtonText: "确定",
            callback: (action) => {
              this.$message({
                type: "info",
                message: `action: ${action}`,
              });
            },
          });
          this.$router.push({
            path: "/commodity",
          });
        }
      }, 2000);
    },
  },
  computed: {
    softwareDetail() {
      console.log("https://localhost:18083/" + this.$store.state.software.softwareDetail.cover)
      console.log(this.$store.state.software.softwareDetail.cover)  
      return this.$store.state.software.softwareDetail;
    },
    priceList() {
      return this.$store.state.software.priceList;
    },
  },
  mounted() {},
};
</script>

<style scoped>
.change {
  margin-bottom: 20px;
}
.two {
  width: 100%;
}

.three {
  width: 100%;
  height: 500px;
}

.thzuo {
  float: left;
  text-align: center;
  margin-top: 100px;
  margin-left: 8vw;
}

.thyou {
  float: right;
  width: 632px;
  height: 375px;
  margin-right: 18vw;
}

.aa {
  width: 100%;
  height: 27px;
  font-size: 27px;
  margin-bottom: 30px;
}

.bb {
  width: 100%;
  height: 30px;
  margin-bottom: 10px;
}

.bbb {
  font-size: 20px;
  float: left;
}

.bbbb {
  margin-right: 20px;
  float: left;
  font-size: 20px;
}
.cc {
  margin-bottom: 30px;
}
.dd {
  margin-bottom: 30px;
}
.ddd {
  margin-right: 20px;
}
.ee {
  margin-top: 30px;
  float: right;
  margin-right: 60px;
}
</style>
