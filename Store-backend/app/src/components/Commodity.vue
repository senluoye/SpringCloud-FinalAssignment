<template>
  <div class="app">
    <div v-if="!islogin">
      请先登录
    </div>
    <div v-else>
      <div class="toutou">
        <el-card shadow="always" class="top">
          <div class="all">
            所有商品
          </div>
        </el-card>
      </div>
      <div class="shangpin">
        <el-row>
          <el-col :span="6" v-for="project in currentPageList" :key="project.id">
            <div @click="changeDetails(project)">
              <el-card class="cards">
                <el-row>
                  <el-col :span="8">
                    <div class="grid-content bg-white"></div>
                  </el-col>
                  <el-col :span="8">
                    <el-image style="width: 100px; height: 100px" 
                    :src="'http://localhost:18083/' + project.cover">
                    </el-image>
                  </el-col>
                  <el-col :span="8">
                    <div class="grid-content bg-white"></div>
                  </el-col>
                </el-row>
                <div style="margin-top: 20px">
                  <div class="name">商品名称:{{ project.name }}</div>
                  <div class="description">
                    商品描述:{{ project.description }}
                  </div>
                  <div class="description">商品价格:{{ project.price }}</div>
                  <div class="description">商品数量:{{ project.num }}</div>
                </div>
              </el-card>
            </div>
          </el-col>
        </el-row>
      </div>
      <div class="block">
        <el-pagination background layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="8"
          :total="softwareList.length">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      currentPageList: [],
    };
  },
  methods: {
    handleCurrentChange(val) {
      this.currentPageList = this.softwareList.slice((val - 1) * 8, val * 8);
    },
    changeDetails(project) {
      this.$store.state.software.softwareDetail = project;
      console.log(this.$store.state.software.softwareDetail)
      this.$router.push({
        path: "/change",
      });
    },
  },
  computed: {
    softwareList() {
      this.currentPageList = this.$store.state.software.softwareList.slice(
        0,
        8
      );
      return this.$store.state.software.softwareList;
    },
    islogin() {
      if (localStorage.getItem("token") != null) {
        this.$store.state.software.token = localStorage.getItem("token");
        return true;
      }
      return false;
    },
  },
  mounted() {
    this.$store.dispatch("getSoftwares");
  },
};
</script>

<style scoped>
.name {
  margin-top: 10px;
}
.all {
  /* float: left; */
  width: 100%;
}
.add {
  float: right;
  margin-right: 5vw;
}
.top {
  height: 70px;
}
.app {
  background-color: white;
}
.aa {
  font-size: 13px;
}

.toutou {
  height: 80px;
}

.cards {
  width: 33vh;
  height: 16vw;
  margin-top: 2vh;
  margin-left: 2vw;
  transition: all 0.3s;
}
.cards:hover {
  box-shadow: 4px 10px 10px #888888;
}
.bg-white {
  background: #ffffff;
}

.grid-content {
  min-height: 36px;
}
.block {
  float: right;
  margin-right: 10vw;
  margin-top: 3vh;
}
</style>
