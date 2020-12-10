$(function () {

    // 给推荐线路上面的按钮绑定一个点击事件‘
    $("#foreignLine").on("click",function () {
    //    把四川那个class 属性移除
        $("#sichuanLine").removeClass("on");
        $("#domestic").removeClass("on");

    //    给当前的这个属性添加一个classi属性；
        $(this).addClass("on");

    //    异常加载国外的数据到这里来；
        getForeigLine(3);



    });

    //给线路    国外按钮绑定一个点击事件；
    $("#sichuanLine").on("click",function () {
        //    把四川那个class 属性移除
        $("#foreignLine").removeClass("on");
        $("#domestic").removeClass("on");

        //    给当前的这个属性添加一个classi属性；
        $(this).addClass("on");

        getForeigLine(1);
    });

    //给线中   国内绑定一个点击事件；
    $("#domestic").on("click",function () {
        //    把四川那个class 属性移除
        $("#sichuanLine").removeClass("on");
        $("#foreignLine").removeClass("on");

        //    给当前的这个属性添加一个classi属性；
        $(this).addClass("on");
        getForeigLine(2);
    });


    /*====================================================================*/

    // 给推荐景点上面的按钮绑定一个点击事件‘
    $("#domesticScenery").on("click",function () {
        //    把四川那个class 属性移除
        $("#aroundScenery").removeClass("on");

        //    给当前的这个属性添加一个classi属性；
        $(this).addClass("on");

        //    异常加载国外的数据到这里来；
        getSceneryByType(2);

    });

    //给景点    国外按钮绑定一个点击事件；
    $("#aroundScenery").on("click",function () {
        //    把四川那个class 属性移除
        $("#domesticScenery").removeClass("on");

        //    给当前的这个属性添加一个classi属性；
        $(this).addClass("on");

        getSceneryByType(1);
    });


    /*====================================================================*/


    // 给推荐酒店上面的按钮绑定一个点击事件‘
    $("#sichuanHotel").on("click",function () {
        //    把四川那个class 属性移除
        $("#foreignHotel").removeClass("on");
        $("#domesticHotel").removeClass("on");

        //    给当前的这个属性添加一个classi属性；
        $(this).addClass("on");

        //    异常加载国外的数据到这里来；
        getHotelByType(1);



    });

    //给酒店   国外按钮绑定一个点击事件；
    $("#foreignHotel").on("click",function () {
        //    把四川那个class 属性移除
        $("#sichuanHotel").removeClass("on");
        $("#domesticHotel").removeClass("on");

        //    给当前的这个属性添加一个classi属性；
        $(this).addClass("on");

        getHotelByType(3);
    });

    //给酒店   国内绑定一个点击事件；
    $("#domesticHotel").on("click",function () {
        //    把四川那个class 属性移除
        $("#sichuanHotel").removeClass("on");
        $("#foreignHotel").removeClass("on");

        //    给当前的这个属性添加一个classi属性；
        $(this).addClass("on");
        getHotelByType(2);
    });

});


//  根据类型来查询    多条线路信息；
function getForeigLine(lineType) {

    $.ajax({
       type: "POST",
       url: "/front_getLineByLIneType",
        data: {"lineType": lineType},
        dataType: "json",
        success: function (result) {
           if (result.code == 0){
               var htmls = "";

                var lines = result.data;
               $.each(lines,function (i,v) {
                   if (i < 8){
                       htmls += "<div class=\"layui-col-md3\">\n" +
                        "        <div class=\"boxpic\">\n" +
                        "            <div class=\"pic\">\n" +
                        "                <a href='/findOneLine?lineId="+v.lineId+"' target='_blank'><img  src=\"images/line/"+v.imgFileName+"\"  style=\"width: 250px;height: 230px;\"></a>\n" +
                        "            </div>\n" +
                        "<div id='xxxtitle'><a href='/findOneLine?lineId="+v.lineId+"' target='_blank' class='Tourismtitle'>"+v.lineTitle+"</a></div>"+
                        "            <div class=\"aap\">\n" +
                        "                <p class=\"pprice_oldprice\">\n" +
                        "                    <del class=\"deMoney\">\n" +
                        "                        原价:\n" +
                        "                        <i class=\"currency_sy\">￥</i>\n" +
                        "                        <span>"+v.lineOldPrice+"</span>\n" +
                        "                    </del>\n" +
                        "                    <span class=\"numprice\">\n" +
                        "\t\t\t\t\t\t\t\t<b>\n" +
                        "\t\t\t\t\t\t\t\t\t<i class=\"currency_sy\">￥</i>\n" +
                        "\t\t\t\t\t\t\t\t\t<span style='font-size: 32px;'>"+v.linePrice+"</span>\n" +
                        "\t\t\t\t\t\t\t\t</b>\n" +
                        "\t\t\t\t\t\t\t\t起\n" +
                        "\t\t\t\t\t\t\t</span>\n" +
                        "                </p>\n" +
                        "            </div>\n" +
                        "\n" +
                        "        </div>\n" +
                        "    </div>"


                       var aa = "";
                   }
               })
               $("#lineDivs").html(htmls);
           }
        }
    });

}

function getSceneryByType(type) {
    $.ajax({
        type: "POST",
        url: "/front_getSceneryByType",
        data: {"sceneryType": type},
        dataType: "json",
        success: function (result) {
            if (result.code == 0){
                var htmls = "";

                var scenerys = result.data;
                $.each(scenerys,function (i,v) {
                    if (i < 8){

                        htmls +="<div class=\"layui-col-md3\">\n" +
                            "        <div class=\"boxpic\">\n" +
                            "\n" +
                            "            <div class=\"pic\">\n" +
                            "                <a href='/findOneScenery?sceneryId="+v.sceneryId+"' target='_blank'><img src=\"images/scenery/"+v.imgFileName+"\" style=\"width: 250px;height: 230px;\"></a>\n" +
                            "            </div>\n" +
                            "\n" +
                            "<div id='xxxtitle'><a href='/findOneScenery?sceneryId="+v.sceneryId+"' target='_blank' class='Tourismtitle'>"+v.sceneryTitle+"</a></div>"+
                            "\n" +
                            "            <div class=\"aap\">\n" +
                            "                <p class=\"pprice_oldprice\">\n" +
                            "                    <del class=\"deMoney\">\n" +
                            "                        原价:\n" +
                            "                        <i class=\"currency_sy\">￥</i>\n" +
                            "                        <span>"+v.sceneryOldPrice+"</span>\n" +
                            "                    </del>\n" +
                            "                    <span class=\"numprice\">\n" +
                            "\t\t\t\t\t\t\t\t\t<b>\n" +
                            "\t\t\t\t\t\t\t\t\t\t<i class=\"currency_sy\">￥</i>\n" +
                            "\t\t\t\t\t\t\t\t\t\t<span style=\"font-size: 32px;\">"+v.sceneryPrice+"</span>\n" +
                            "\t\t\t\t\t\t\t\t\t</b>\n" +
                            "\t\t\t\t\t\t\t\t\t起\n" +
                            "\t\t\t\t\t\t\t\t</span>\n" +
                            "                </p>\n" +
                            "\n" +
                            "            </div>\n" +
                            "        </div>\n" +
                            "    </div>"

                    }
                })
                $("#sceneryDivs").html(htmls);
            }
        }
    });
}

function getHotelByType(type) {
    $.ajax({
        type: "POST",
        url: "/front_getHotelByType",
        data: {"hotelType": type},
        dataType: "json",
        success: function (result) {
            if (result.code == 0){
                var htmls = "";

                var hotels = result.data;
                $.each(hotels,function (i,v) {
                    if (i < 8){

                        htmls +="<div class=\"layui-col-md3\">\n" +
                            "        <div class=\"boxpic\">\n" +
                            "\n" +
                            "            <div class=\"pic\">\n" +
                            "                <a href='/findOneHotel?hotelId="+v.hotelId+"' target='_blank'><img src=\"images/hotel/"+v.imgFileName+"\" style=\"width: 250px;height: 230px;\"></a>\n" +
                            "            </div>\n" +
                            "\n" +
                            "<div id='xxxtitle'><a href='/findOneHotel?hotelId="+v.hotelId+"' target='_blank' class='Tourismtitle'>"+v.hotelName+"</a></div>"+
                            "\n" +
                            "            <div class=\"aap\">\n" +
                            "                <p class=\"pprice_oldprice\">\n" +
                            "                    <del class=\"deMoney\">\n" +
                            "                        原价:\n" +
                            "                        <i class=\"currency_sy\">￥</i>\n" +
                            "                        <span>"+v.hotelOldPrice+"</span>\n" +
                            "                    </del>\n" +
                            "                    <span class=\"numprice\">\n" +
                            "\t\t\t\t\t\t\t\t\t<b>\n" +
                            "\t\t\t\t\t\t\t\t\t\t<i class=\"currency_sy\">￥</i>\n" +
                            "\t\t\t\t\t\t\t\t\t\t<span style=\"font-size: 32px;\">"+v.hotelPrice+"</span>\n" +
                            "\t\t\t\t\t\t\t\t\t</b>\n" +
                            "\t\t\t\t\t\t\t\t\t起\n" +
                            "\t\t\t\t\t\t\t\t</span>\n" +
                            "                </p>\n" +
                            "\n" +
                            "            </div>\n" +
                            "        </div>\n" +
                            "    </div>"
                    }
                })
                $("#HotelsDiv").html(htmls);
            }
        }
    });
}




