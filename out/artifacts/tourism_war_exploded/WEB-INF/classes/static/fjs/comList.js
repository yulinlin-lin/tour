layui.use(['form', 'layer', 'laydate', 'table', 'laytpl'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;

    //新闻列表
    var tableIns = table.render({
        elem: '#comList',
        url: '/findAllCOmmet',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limit: 10,
        limits: [10, 15, 20, 25],
        cols: [[
            {type: 'checkbox', fixed: 'left',},
            {type: 'numbers', title: '序号'},
            {field: 'id', title: '自增ID', width: 80, align: "center"},
            {field: 'userId',hidden: true,title: '用户ID',align: "center"},
            {field: 'userName', title: '评论用户姓名', align: "center"},
            {field: 'goodsId', title: '商品ID', align: 'center'},
            {field: 'content', title: '评论内容', align: 'center'},
            {field: 'contentType', title: '评论商品的类别', align: 'center'},
            {field: 'createTime', title: '评论时间', width: 180, align: 'center'},
            {title: '操作', templet: '#newsListBar', width: 120, fixed: "right", align: "center"}
        ]], done: function (res, curr, count) {
            $("[data-field='contentType']").children().each(function(){
                if($(this).text()=='1'){
                    $(this).text("线路")
                }else if($(this).text()=='2'){
                    $(this).text("酒店")
                }else if($(this).text()=='3'){
                    $(this).text("景点")
                }
            });

            // $("[data-field='sex']").children().each(function(){
            //     if($(this).text()=='1'){
            //         $(this).text("男")
            //     }else if($(this).text()=='0'){
            //         $(this).text("女")
            //     }
            // });
        }
    });


    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click", function () {
        // if ($(".searchVal").val() != '') {
        table.reload("comList", {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                key: $(".searchVal").val()  //搜索的关键字
            }
        })
    });

    //编辑user
    // function editComment(edit) {
    //     // alert("hello world  edit");
    //     var index = layui.layer.open({
    //         title: "编辑评论",
    //         type: 2,
    //         content: "/userEdit",
    //         success: function (layero, index) {
    //             var body = layui.layer.getChildFrame('body', index);
    //             if (edit) {
    //                 body.find("#userId").val(edit.userId);
    //                 body.find("#U_name").val(edit.userName);
    //                 body.find("#U_pass").val(edit.password);
    //                 body.find("#U_phone").val(edit.phone);
    //
    //                 body.find("input[name=sex][value=0]").attr("checked",edit.sex == 0 ? true : false);
    //                 body.find("input[name=sex][value=1]").attr("checked",edit.sex == 1 ? true : false);
    //
    //                 body.find("input[name=roleId][value=1]").attr("checked",edit.roleType == 1 ? true : false);
    //                 body.find("input[name=roleId][value=2]").attr("checked",edit.roleType == 2 ? true : false);
    //                 body.find("input[name=roleId][value=3]").attr("checked",edit.roleType == 3 ? true : false);
    //
    //                 form.render();
    //             }
    //             setTimeout(function () {
    //                 layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
    //                     tips: 3
    //                 });
    //             }, 500)
    //         }
    //     })
    //     layui.layer.full(index);
    //     //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
    //     $(window).on("resize", function () {
    //         layui.layer.full(index);
    //     })
    // }


    //添加user
    // function addComment() {
    //     // alert("hello world")
    //     var index = layui.layer.open({
    //         title: "添加游客",
    //         type: 2,
    //         content: "/userAddUser",
    //     })
    //     layui.layer.full(index);
    //     //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
    //     $(window).on("resize", function () {
    //         layui.layer.full(index);
    //     })
    // }


    // $(".addUser_btn").click(function () {
    //     addComment();
    // })


    //列表操作
    table.on('tool(comList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;

        if (layEvent === 'edit') { //编辑

            // editComment(data);

        } else if (layEvent === 'del') { //删除
            layer.confirm('确定删除此评论？', {icon: 3, title: '提示信息'}, function (index) {
                obj.del();  //删除对应行的结构  DOM   就是没有真正地删除；
                $.post("/deleteCommentById", {
                    id: data.id  //将需要删除的newsId作为参数传入
                }, function (data) {
                    if (data.code == 0){
                        tableIns.reload();
                        layer.close(index);
                        layer.msg("删除成功",{icon:1});
                    }else{
                        layer.msg(data.msg,{icon: 2});
                    }


                })
            });
        } else if (layEvent === 'look') { //预览
            layer.alert("此功能需要前台展示，实际开发中传入对应的必要参数进行文章内容页面访问")
        }
    });

})