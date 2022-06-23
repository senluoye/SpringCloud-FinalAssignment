import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import da from "element-ui/src/locale/lang/da";
import { Loading } from 'element-ui';

Vue.use(Vuex)

const moduleSoftware = {
    state: {
        loading: false,
        //软件列表
        softwareList: [],
        softwareDetail: {},
        user: {
            id: "",
            name: "",
            password: "",
        },
        code: -2,
        NumberList: [],
        token: '',
        isAdd: false,
        isChange: false,
    },
    mutations: {
        setDiscountList(state, data) {
            state.discountList = data;
        },
        setRecommendList(state, data) {
            state.recommendList = data;
        },
        setWinList(state, data) {
            state.winList = data;
        },
        setMacList(state, data) {
            state.macList = data;
        },
        setSoftwareList(state, data) {
            state.softwareList = data;
        },
        setSoftwareDetail(state, data) {
            state.softwareDetail = data;
        },
        setPriceList(state, data) {
            state.priceList = data;
        },
        setCartList(state, data) {
            state.cartList = data;
        },
        setClientList(state, data) {
            state.clientList = data;
        },
        setUserDetails(state, data) {
            state.islogin = true;
            state.user.id = data.data.id;
            state.user.name = data.data.name;
            state.user.password = data.data.password;
            state.token = data.data.token;
            localStorage.token = data.data.token;
            localStorage.id = data.data.id;
            localStorage.name = data.data.name;
            localStorage.password = data.data.password;
            console.log(localStorage.getItem("token"));
        },
        setIdAdd(state, data) {
            state.isAdd = true;
        },
        setIsChange(state) {
            state.isChange = true;
        }

    },
    actions: {
        addDiscountCoupon(context, data) {
            console.log(data)
            axios({
                url: '/api/coupon/admin/add/discount',
                method: 'post',
                headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json',
                    token: localStorage.getItem('token')
                },
                data: data
            }).then((res) => {
                if (res.status === 200) {
                    console.log(res.data);
                }
            }).catch((err) => {
                console.log(err)
            });
        },

        addReductCoupon(context, data) {
            axios({
                url: '/api/coupon/admin/add/reduce',
                method: 'post',
                headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json',
                    token: localStorage.getItem('token')
                },
                data: data
            }).then((res) => {
                if (res.status === 200) {
                    console.log(res.data);
                }
            }).catch((err) => {
                console.log(err)
            });
        },

        //修改商品信息
        modify(context, commodity) {
            axios({
                url: '/api/user/admin/commodities',
                method: 'put',
                headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json',
                    token: localStorage.getItem('token')
                },
                data: commodity
            }).then((res) => {
                console.log(res.data)
                if (res.data.code === 1) {
                    context.commit("setIsChange", res.data);
                    console.log(res)
                }
            }).catch((err) => {
                console.log(err)
            });
        },

        //拿到商品列表
        getSoftwares(context) {
            axios.get("/api/user/admin/commodities", {
                headers: {
                    Accept: 'application/json',
                    "Content-Type": 'application/json',
                    token: localStorage.getItem('token')
                }
            }).then((res) => {
                if (res.status === 200)
                    return res.data;
            }).then((json) => {
                setTimeout(() => {
                    console.log(json.data)
                    context.commit("setSoftwareList", Array.from(json.data));
                })
            }).catch((err) => {
                this.$alert("获取失败", err, {
                    confirmButtonText: "确定",
                    callback: (action) => {
                        this.$message({
                            type: "info",
                            message: `action: ${action}`,
                        });
                    },
                });
                loadingInstance.close();
            })
        },

        //增加商品
        addGoods(context, data) {
            axios({
                url: '/api/commodity/add',
                method: 'post',
                headers: {
                    'Content-Type': 'multipart/form-data',
                    token: localStorage.getItem('token')
                },
                data: data
            }).then((res) => {
                console.log(res)
                return res.data;
            }).then((json) => {
                context.commit("setIdAdd", json);
            }).catch((err) => {
                console.log(err)
            });
        },

        // 注册
        register(context, user) {
            console.log(user);
            axios.post("/api/user/register", {
                name: user.name,
                password: user.password,
                headers: {
                    Accept: 'application/json',
                    "Content-Type": 'application/json'
                }
            }).then((res) => {
                if (res.status === 200)
                    return res.data;
            })
        },

        // 登陆
        login(context, user) {
            axios.post("/api/user/login", {
                name: user.name,
                password: user.password,
                headers: {
                    Accept: 'application/json',
                    "Content-Type": 'application/json'
                }
            }).then((res) => {
                if (res.status === 200) {
                    return res.data;
                }
            }).then((json) => {
                setTimeout(() => {
                    if (json.code == 0)
                        context.commit("setUserDetails", json);
                })
            })
        },
    }
}

export default new Vuex.Store({
    modules: {
        software: moduleSoftware
    }
})