layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;

    var base = "http://localhost:8080/images/hotel/";

    //新闻列表
    var tableIns = table.render({
        elem: '#hotelList',
        url : '/hotelGetAll',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limit : 20,
        limits:[10,15,20,25],
        id : "hotelList",
        cols:[[
            {type: 'checkbox', fixed:'left', },
            {type:'numbers',title:'序号'},
            {field: 'hotelId', title: 'ID', width:80, align:"center"},
            {field: 'hotelLocation', title: '地址',width:80,align:"center"},
            {field: 'hotelName', title: '名字',align:'center'},
            {field: 'hotelPrice', title: '促销价格',align:'center'},
            {field: 'hotelOldPrice', title: '原价格',width:120, align:'center'},
            {field: 'imgFileName', title: '图片',width:120, align:'center',templet: function (d) {
                    return "<div id=\"photo-list_" + d.hotelId + "\"><img class='img_sp' onclick='showPic(this);' layer-src='" + base + d.imgFileName + "' src=\"" + base + d.imgFileName + "\"  style=\"width: 40px;\" lay-event=\"showPic\" alt='我是酒店图片名' /></div>";
                }},
            {field: 'hotelDetailed', title: '描述',width:120, align:'center'},
            {field: 'htype', title: '类型 ',width:120, align:'center'},
            {field: 'tel', title: '电话 ',width:120, align:'center'},
            {field: 'status', title: '状态 ',width:120, align:'center',templet: '#activeTemp'},
            {field: 'createTime', title: '创建时间 ',width:120, align:'center'},
            {title: '操作',  templet:'#hotelOperate',width:120,fixed:"right",align:"center"}
        ]],done: function (res, curr, count) {
            $("[data-field='htype']").children().each(function(){
                if($(this).text()=='1'){
                    $(this).text("四川")
                }else if($(this).text()=='2'){
                    $(this).text("国内")
                }else if($(this).text()=='3'){
                    $(this).text("国外")
                }
            });
        }
    });



    //    监听开关点击事件
    form.on('switch(showstatus)', function (data) {
        var contexts;
        var sta;
        var x = data.elem.checked;
        console.log(x);
        if (x == true){
            contexts = "上架";
            sta = 1;
        }else{
            contexts = "下架";
            sta = 0;
        }
        layer.open({
            content: "你确定要"+contexts+"?",
            btn: ['确定','取消'],
            yes: function (index,layero) {
                //    按钮1确定的回调
                data.elem.checked = x;
                //    对商品进行上架或者下架处理
                $.ajax({
                    type: "post",
                    url: "/updateHotelStatus",
                    dataType: "json",
                    data: {"id":data.value,"status": sta},
                    success: function (data) {
                        if (data.code == 0){
                            layer.msg(contexts+'成功',
                                // 提示的样式
                                {icon: 1, time: 2000,});

                            // 数据重载 active那里的？
                            tableIns.reload();
                        }
                    }
                });
                form.render();
                layer.close(index);
            }
            ,btn2: function (index,layero) {
                data.elem.checked = x;
                form.render();
                layer.close(index);
            }
            , cancel: function () {
                //右上角关闭回调
                data.elem.checked = !x;
                form.render();
                // return false; //开启该代码可禁止点击该按钮关闭
            }
        });
        return false;
    });


    $(".search_btn").on("click", function () {
        table.reload("hotelList", {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                key: $(".searchVal").val()  //搜索的关键字
            }
        })
    });


    //编
    function editHotel(edit){
        var index = layui.layer.open({
            title : "编辑",
            type : 2,
            content : "/editHotel",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".hotelId").val(edit.hotelId);
                    body.find(".hotelName").val(edit.hotelName);
                    body.find(".hotelLocation").val(edit.hotelLocation);
                    body.find(".hotelPrice").val(edit.hotelPrice);
                    body.find(".hotelOldPrice").val(edit.hotelOldPrice);
                    body.find(".hotelDetailed").val(edit.hotelDetailed);
                    body.find(".tel").val(edit.tel);
                    body.find("input[name=htype][value=1]").attr("checked",edit.htype == 1? true:false);
                    body.find("input[name=htype][value=2]").attr("checked",edit.htype == 2? true:false);
                    body.find("input[name=htype][value=3]").attr("checked",edit.htype == 3? true:false);
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回酒店列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
        layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(index);
        })
    }

    //添加酒店
    function addHotel(){
        var index = layui.layer.open({
            title : "添加酒店",
            type : 2,
            content : "/showAddHotel",
        })
        layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(index);
        })
    }


    $(".addHotel_btn").click(function(){
        addHotel();
    })

    //列表操作
    table.on('tool(hotelList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑

            editHotel(data);

        } else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此酒店？',{icon:3, title:'提示信息'},function(index){
                $.post("/deleteHotelById",{
                    id : data.hotelId //将需要删除的newsId作为参数传入
                },function(data){
                    tableIns.reload();
                    layer.close(index);
                    layer.msg("删除成功",{icon:1});

                })
            });
        } else if(layEvent === 'look'){ //预览
            layer.alert("此功能需要前台展示，实际开发中传入对应的必要参数进行文章内容页面访问")
        }else if (layEvent == 'showPic'){

                // layer.photos({
                //     photos: '#pic_' + data.hotelId,
                //     //0-6的选择，指定弹出图片动画类型，默认随机
                //     anim: 5
                // });


        }
    });

});

function showPic(obj) {
    var paren = $(obj).parent();
    var idstr = paren.attr("id");
    var id_str = '#'+idstr;
    layui.use("layer",function () {
        var layer = layui.layer;
        layer.photos({
            photos: id_str,
            shadeClose: false,
            closeBtn: 2,
            anim: 0
        });

    });
}