<template>
    <div id="registerapp">
        <div class="register-from">
            <div class="register-word">
                欢迎登陆
            </div>
            <v-denglu></v-denglu>
            <div>
                <span class="lijidenglu">
                    没有账号？
                    <router-link to='/register'>立即注册</router-link>
                </span>
            </div>
        </div>
    </div>
</template>

<script>
import denglu from '../components/denglu';

export default {
    components:{
        'v-denglu' :denglu
    }
}
</script>

<style scoped>

#registerapp{
    position:relative;
    height: 100vh;
    width:100vw;
    background-size: cover;    
    background-color: aliceblue;
}
.register-from{
    position:absolute;
    width: 430px;
    height: 380px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    left: 50%;
    margin-left:-215px;
    margin-top: 100px;
    transition: all 0.5s;
}
.register-from:hover{
    box-shadow: 0 2px 30px 0 rgba(94, 84, 84, 0.719);
    border-radius: 30px;
    background-color: rgba(248, 248, 248, 0.493);
    /* margin-top: 12vh; */
}
.demo-ruleForm{
    width: 380px;
    margin:0 auto;
}
.register-word{
    height: 80px;
    font-family:"Hiragino Sans GB";
    font-size: 20px;
    display: -moz-box;/*兼容Firefox*/
    display: -webkit-box;/*兼容FSafari、Chrome*/
    -moz-box-align: center;/*兼容Firefox*/
    -webkit-box-align: center;/*兼容FSafari、Chrome */
    -moz-box-pack: center;/*兼容Firefox*/
    -webkit-box-pack: center;/*兼容FSafari、Chrome */
}
.lijidenglu{
    margin-left: 220px;
}
.kuaijie{
    width: 430px;
    height: 140px;
}
.kuaijieone{
    text-align: center;
    font-size: 20px;
    margin-top: 20px;
}
.kuaijietwo{
    width: 270px;
    height: 50px;
    margin: 0 auto;
    margin-top: 30px;
}
.weibo{
    margin-left: 75px;
}
.weixin{
    float: right;
}
</style>