layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;
    var mylayer = layui.layer;
    var base = "http://localhost:8080/images/scenery/";

    //新闻列表
    tableIns = table.render({
        elem: '#sceneryList',
        url : '/sceneryGetAll',
        id: 'idTest',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limit : 10,
        limits:[10,15,20,25],
        id : "sceneryList",
        cols:[[
            {type: 'checkbox', fixed:'left', },
            {type:'numbers',title:'序号'},
            {field: 'sceneryId', title: 'ID', width:80, align:"center"},
            {field: 'sceneryCity', title: '城市',width:80,align:"center"},
            {field: 'sceneryTitle', title: '标题',align:'center'},
            {field: 'sceneryPrice', title: '促销价',align:'center'},
            {field: 'sceneryOldPrice', title: '价格',align:'center'},
            {field: 'imgFileName', title: '图片',width:120,align:'center',templet: function (d) {
                    return "<div id=\"photo-list_" + d.sceneryId + "\"><img onclick='showSceneryPic(this);' src=\"" + base + d.imgFileName + "\"  style=\"width: 40px;\" lay-event=\"showPic\" ></div>";
                }},
            {field: 'sceneryDetailed1', title: '描述1',align:'center'},
            {field: 'sceneryDetailed2', title: '描述2',align:'center'},
            {field: 'sceneryDetailed3', title: '描述3',align:'center'},
            {field: 'openTime', title: '开放时间',align:'center'},
            {field: 'sceneryType', title: '景点类型',width:120, align:'center'},
            {field: 'create_Time', title: '创建时间',width:120, align:'center'},
            {title: '操作',  templet:'#sceneryOperate',width:200,fixed:"right",align:"center"}
        ]],done: function (res, curr, count) {
            $("[data-field='sceneryType']").children().each(function(){
                if($(this).text()=='1'){
                    $(this).text("周边景点")
                }else if($(this).text()=='2'){
                    $(this).text("国内旅游景点")
                }
            });
        }
    });



    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click", function () {
        table.reload("sceneryList", {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                key: $(".searchVal").val()  //搜索的关键字
            }
        })
    });


    //添加景点
    function addScenery(){
        var index = layui.layer.open({
            title : "添加景点",
            type : 2,
            content : "/sceneryAddScenery",
        })
        layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(index);
        })
    }


    $(".addScenery_btn").click(function(){
        addScenery();
    })


    //编辑景点
    function editScenery(edit){
        console.log(edit);
        var index = layui.layer.open({
            title : "编辑",
            type : 2,
            content : "/editScenery",
            success : function(layero, index){

                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find("#sceneryId").val(edit.sceneryId);
                    body.find("#sceneryCity").val(edit.sceneryCity);
                    body.find("#sceneryTitle").val(edit.sceneryTitle);
                    body.find("#sceneryPrice").val(edit.sceneryPrice);
                    body.find("#sceneryOldPrice").val(edit.sceneryOldPrice);
                    body.find("#sceneryDetile1").val(edit.sceneryDetailed1);
                    body.find("#sceneryDetile2").val(edit.sceneryDetailed2);
                    body.find("#sceneryDetile3").val(edit.sceneryDetailed3);
                    body.find("#opentime").val(edit.openTime);
                    body.find("input[name=sceType][value=1]").attr("checked",edit.sceneryType == 1 ? true : false);
                    body.find("input[name=sceType][value=2]").attr("checked",edit.sceneryType == 2 ? true : false);
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回景点列表', '.layui-layer-setwin .layui-layer-close', {
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

    //列表操作
    table.on('tool(sceneryList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑

            editScenery(data);

        } else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此景点？',{icon:3, title:'提示信息'},function(index){
                $.post("/deleteSceneryById",{
                    id : data.sceneryId  //将需要删除的newsId作为参数传入
                },function(data){
                    tableIns.reload();
                    layer.close(index);
                    layer.msg("删除成功",{icon:1});

                })
            });
        } else if(layEvent === 'look'){ //预览
            layer.alert("此功能需要前台展示，实际开发中传入对应的必要参数进行文章内容页面访问")
        }
    });



})

function showSceneryPic(obj) {
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