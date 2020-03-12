<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <%@ page import="com.google.gson.Gson" %>
    <%@ page import="com.lzq.bean.Goods" %>
    <%@ page import="java.util.List" %>
    <%@ page import="com.lzq.service.GoodsService" %>
    <%@ page import="com.lzq.bean.GoodsClass" %>
    <%@ page import="com.lzq.service.GoodsClassService" %>
    <%@ page import="java.util.ArrayList" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head lang="en">
    <meta charset="utf-8" />
    <title>泽庆软饰</title>
    <link rel="stylesheet" type="text/css" href="css/public.css" />
    <link rel="stylesheet" type="text/css" href="css/proList.css" />
</head>
<%--
    页面在访问时, 会传递不同的参数.
    1.  type
            0   :   查询所有商品
            1   :   一级分类商品
            2   :   二级分类商品
            3   :   模糊查询商品

    2.  classId1    :   一级分类商品查询时,. 传递的一级分类id
    3.  classId2    :   二级分类商品查询时,. 传递的二级分类id
    4.  goodsName   :   模糊查询商品时, 用户输入的内容
--%>
                                        <%
        request.setCharacterEncoding("utf-8");
        List<Goods> goodsList = null;
        String type = request.getParameter("type");
        String classId1 = request.getParameter("classId1");
        String classId2 = request.getParameter("classId2");
        String goodsName = request.getParameter("goodsName");
        String orderBy = request.getParameter("orderBy");
        int orderByInt = orderBy==null?0:Integer.parseInt(orderBy);
        GoodsClass goodsClassShow = new GoodsClass(0,0,goodsName);
        GoodsClass goodsClassShow2 = new GoodsClass(0,0,"");
        if(type == null){
            type = "";
        }
        if( classId1!=null ) {
            if(goodsName!=null && goodsName.equals("All Goods")){
                type = "0";
            }else{
                type = "1";
            }

        }
        switch (type){
            case "0":
                //查询所有商品
                if(orderBy==null){
                    goodsList = GoodsService.fingAll();
                }else{
                    goodsList = GoodsService.findAllOrder(orderByInt);
                }
                goodsClassShow.setClassName("All Goods");
                break;
            case "1":
                //查询一级分类商品
                goodsList = GoodsService.findGoodsByClass1(Integer.parseInt(classId1),1,50,orderByInt);
                goodsClassShow = GoodsClassService.findById(Integer.parseInt(classId1));
                break;
            case "2":
                //查询二级分类商品
                goodsList = GoodsService.findGoodsByClass2(Integer.parseInt(classId2),1,50,orderByInt);
                goodsClassShow2 = GoodsClassService.findById(Integer.parseInt(classId2));
                goodsClassShow = GoodsClassService.findById(goodsClassShow2.getParentId());
                break;
            case "3":
                //模糊查询商品
                goodsList = GoodsService.findGoodsLikeName(goodsName,1,50,orderByInt);
                goodsClassShow = new GoodsClass(0,0,goodsName);
                break;
        }
    %>



<body>
    <!------------------------------head------------------------------>
    <%@include file="include_header.jsp"%>
        <!------------------------------banner------------------------------>
        <div class="banner">
            <a href="#"><img src="img/temp/banner1.jpg" /></a>
        </div>
        <!-----------------address------------------------------->
        <div class="address">
            <div class="wrapper clearfix"><a href="index.html">首页</a><span>/</span>
                <a href="flowerDer.jsp?type=<%=type%>&classId1=<%=goodsClassShow.getId()%>&goodsName=<%=goodsClassShow.getClassName()%>" class="on">
                    <%=goodsClassShow.getClassName()%>
                </a>
            </div>
        </div>
        <!-------------------current---------------------->
        <div class="current">
            <div class="wrapper clearfix">
                <h3 class="fl">
                    <a href="flowerDer.jsp?type=<%=type%>&classId2=<%=goodsClassShow2.getId()%>">
                        <%=goodsClassShow2.getClassName()%>
                    </a>
                </h3>
                <div class="fr choice">
                    <p class="default">排序方式</p>
                    <ul class="select">
                        <li onclick="orderBy(1)">价格从高到低</li>
                        <li onclick="orderBy(0)">价格从低到高</li>
                        <script>
                            function orderBy(type) {
                                var url = window.location.href;
                                url = url.replace("&orderBy=1", "");
                                url = url.replace("&orderBy=0", "");
                                if (type == 1) {
                                    url = url + "&orderBy=1";
                                } else {
                                    url = url + "&orderBy=0";
                                }
                                if (url != window.location.href) {
                                    window.location.replace(url);
                                }

                            }
                        </script>
                    </ul>
                </div>
            </div>
        </div>
        <!----------------proList------------------------->
        <ul class="proList wrapper clearfix">

            <%

        if(goodsList == null || goodsList.size() == 0){
            //没有商品
            %>
                                                            <h1 align="center">很遗憾, 商品不存在</h1>
                                                            <%
        }else{
        for(Goods g:goodsList){

            //是一个JSON格式的数组
            String imgs = g.getImgs();//['xxx.jpg','xxxx.jpg']
            String imgPath = null;
            if("[".equals(imgs)){
                imgPath = "errorGoodsImg.jpg";
            }else{
                Gson gson = new Gson();
                List<String> imgsList = gson.fromJson(imgs, List.class);
                imgPath = imgsList.get(0);
            }



    %>
        <li>
            <a href="findGoods.do?goodsId=<%=g.getId()%>">
                <dl>
                    <dt><img src="img/imgs/<%=imgPath%>"></dt>
                    <dd>
                        <%
            if(g.getName().length() > 20){
%>
                            <%=g.getName().substring(0,20)+"..."%>
                                <%}else{%>
                                    <%=g.getName()%>
                        <%}%>
                    </dd>
                    <dd>￥
                        <%=g.getPrice()%>
                    </dd>
                </dl></a>
        </li>
        <%

        }

                }
    %>




                                                    </ul>
                                                    <!----------------mask------------------->
                                                    <div class="mask"></div>
                                                    <!-------------------mask内容------------------->
                                                    <div class="proDets"><img class="off" src="img/temp/off.jpg" />
                                                        <div class="tit clearfix">
                                                            <h4 class="fl">【渡一】非洲菊仿真花干花</h4><span class="fr">￥17.90</span></div>
                                                        <div class="proCon clearfix">
                                                            <div class="proImg fl"><img class="list" src="img/temp/proDet.jpg" />
                                                                <div class="smallImg clearfix"><img src="img/temp/proDet01.jpg" data-src="img/temp/proDet01_big.jpg"><img src="img/temp/proDet02.jpg" data-src="img/temp/proDet02_big.jpg"><img src="img/temp/proDet03.jpg" data-src="img/temp/proDet03_big.jpg">
                                                                    <img
                                                                        src="img/temp/proDet04.jpg" data-src="img/temp/proDet04_big.jpg"></div>
                                                            </div>
                                                            <div class="fr">
                                                                <div class="proIntro">
                                                                    <p>颜色分类</p>
                                                                    <div class="smallImg clearfix categ">
                                                                        <p class="fl"><img src="img/temp/prosmall01.jpg" alt="白瓷花瓶+20支快乐花" data-src="img/temp/proBig01.jpg"></p>
                                                                        <p class="fl"><img src="img/temp/prosmall02.jpg" alt="白瓷花瓶+20支兔尾巴草" data-src="img/temp/proBig02.jpg"></p>
                                                                        <p class="fl"><img src="img/temp/prosmall03.jpg" alt="20支快乐花" data-src="img/temp/proBig03.jpg"></p>
                                                                        <p class="fl"><img src="img/temp/prosmall04.jpg" alt="20支兔尾巴草" data-src="img/temp/proBig04.jpg"></p>
                                                                    </div>
                                                                    <p>数量</p>
                                                                    <div class="num clearfix"><img class="fl sub" src="img/temp/sub.jpg"><span class="fl" contentEditable="true">1</span><img class="fl add" src="img/temp/add.jpg">
                                                                        <p class="please fl">请选择商品属性!</p>
                                                                    </div>
                                                                </div>
                                                                <div class="btns clearfix">
                                                                    <a href="#2">
                                                                        <p class="buy fl">立即购买</p>
                                                                    </a>
                                                                    <a href="#2">
                                                                        <p class="cart fr">
                                                                            加入购物车</p>
                                                                    </a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <a class="more" href="proDetail.html">查看更多细节</a></div>
                                                <!--返回顶部-->
                                                <jsp:include page="footer.jsp"/>
                                                    <script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
                                                    <script src="js/public.js" type="text/javascript" charset="utf-8"></script>
                                                    <script src="js/nav.js" type="text/javascript" charset="utf-8"></script>
                                                    <script src="js/pro.js" type="text/javascript" charset="utf-8"></script>
                                                    <script src="js/cart.js" type="text/javascript" charset="utf-8"></script>
                                            </body>

                                    </html>