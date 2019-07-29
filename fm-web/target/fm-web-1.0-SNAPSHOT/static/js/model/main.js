// 可以从其他文件 import 进来
const Foo = { template: '' }

// 2. 定义路由
// 每个路由应该映射一个组件。 其中"component" 可以是
const routes = [
    {
        path: '/:index*',
        component: Foo
    },
]

// 3. 创建 router 实例，然后传 `routes` 配置
const router = new VueRouter({
    routes // (缩写) 相当于 routes: routes
})

new Vue({
    el: "#app",
    router:router,

    methods:{
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        },
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },
        open(url,firstname,secondname){
            this.iframeUrl = url;
            if (firstname) {
                this.breads=[
                    firstname,
                    secondname
                ];
            }else {
                this.breads=[];
            }
        },
    },
    data() {
        return {
            count: 0,
            homepage:"/lostquery/i",
            iframeUrl:"/lostquery/i",
            breads:[],
            menus: []
        };
    },
    created() {
        var _this = this;
        //ajax获取菜单
        $.get("/menu/findMenuByLoginUser", data => {
            console.info(data);
            _this.count = data.length;
            _this.menus = data;
        });
    },
});


