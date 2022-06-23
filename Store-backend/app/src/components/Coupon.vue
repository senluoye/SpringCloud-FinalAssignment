<template>
    <div class="coupon">
        <div v-if="!islogin">
            请先登陆
        </div>
        <div class="two" v-else>
            <el-dropdown @command="handleCommand" class="dropdown">
                <span class="el-dropdown-link">
                    选择类型<i class="el-icon-arrow-down el-icon--right"></i>
                </span>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item command="a">满减优惠卷</el-dropdown-item>
                    <el-dropdown-item command="b">折扣优惠卷</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
            <div v-if="isDiscount" v-loading="loading">
                <el-form ref="form" :model="discountForm">
                    <el-form-item label="商品名称" class="item">
                        <el-input :disabled="true" v-model="discountForm.name"></el-input>
                    </el-form-item>
                    <el-form-item label="折扣" class="item">
                        <el-input v-model="discountForm.discount"></el-input>
                    </el-form-item>
                    <el-form-item label="数量" class="item">
                        <el-input v-model="discountForm.number"></el-input>
                    </el-form-item>
                    <el-form-item class="item commit">
                        <el-button type="primary" @click="discountHandler()">点击创建</el-button>
                        <el-button @click="onCancel()">取消</el-button>
                    </el-form-item>
                </el-form>
            </div>
            <div v-else>
                <el-form ref="form" :model="reductForm" v-loading="loading">
                    <el-form-item label="商品名称" class="item">
                        <el-input :disabled="true" v-model="reductForm.name"></el-input>
                    </el-form-item>
                    <el-form-item label="满足金额" class="item">
                        <el-input v-model="reductForm.count"></el-input>
                    </el-form-item>
                    <el-form-item label="减少金额" class="item">
                        <el-input v-model="reductForm.reduce"></el-input>
                    </el-form-item>
                    <el-form-item label="数量" class="item">
                        <el-input v-model="reductForm.number"></el-input>
                    </el-form-item>
                    <!-- <input id="file" type="file" multiple name="file" /> -->
                    <el-form-item class="item commit">
                        <el-button type="primary" @click="reduceHandler()">创建</el-button>
                        <el-button @click="onCancel()">取消</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            loading:false,
            isDiscount:true,
            multipleSelection: [],
            discountForm: {
                name: this.$store.state.software.softwareDetail.name,
                discount: 0,
                number: 0,
                commodityId: this.$store.state.software.softwareDetail.id,
            },
            reductForm:{
                commodityId: this.$store.state.software.softwareDetail.id,
                name: this.$store.state.software.softwareDetail.name,
                count: 0,
                reduce: 0,
                number: 0,
            },
        };
    },
    methods: {
        handleCommand(command) {
            console.log(command)
            if (command === 'a') {
                this.isDiscount = false
            } else {
                this.isDiscount = true
            }
        },
        discountHandler() {
            console.log(this)
            this.loading = true;
            console.log(this.loading)
            this.$store.dispatch("addDiscountCoupon", JSON.stringify({
                discount: this.discountForm.discount,
                number: this.discountForm.number,
                commodityId: this.discountForm.commodityId,
            }));
            this.loading = false;
            console.log(this.loading)
            alert("创建成功")
        },
        reduceHandler() {
            console.log(this)
            this.loading = true;
            console.log(this.loading)
            this.$store.dispatch("addReductCoupon", JSON.stringify({
                count: this.reductForm.count,
                reduce: this.reductForm.reduce,
                number: this.reductForm.number,
                commodityId: this.reductForm.commodityId,
            }));
            this.loading = false;
            console.log(this.loading)
            alert("创建成功")
        },
        onCancel() {
            this.$router.push({
                path: "/commodity",
            });
        }
    },
    computed: {
        cartList() {
            return this.$store.state.software.cartList;
        },
        islogin() {
            if (localStorage.getItem("token") != null) {
                this.$store.state.software.token = localStorage.getItem("token");
                return true;
            }
            return false;
        },
        totalPrice() {
            var sum = 0;
            this.cartList.forEach(
                (element) => (sum += element.cart_price * element.cart_count)
            );
            return sum;
        },
    },
    mounted() {     },
};
</script>

<style scope>

.dropdown{
    margin-top: 10vh;
    margin-bottom: 5vh;
}
.commit {
    margin-top: 30px;
}

.two {
    width: 50vw;
    margin: 0 auto;
}

.bg-white {
    background: #ffffff;
}

.item {
    height: 70px;
}

.grid-content {
    min-height: 36px;
}

.upload {
    margin-top: 40px;
}
</style>
