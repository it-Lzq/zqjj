<%--
  Created by IntelliJ IDEA.
  User: 李泽庆哇
  Date: 2020/2/6
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.lzq.util.DataUtil" %>
<%@ page import="com.lzq.bean.GoodsClass" %>
<%@ page import="java.util.List" %>
<%@ page import="com.lzq.service.GoodsClassService" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.lzq.bean.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>

<head lang="en">
    <meta charset="utf-8"/>
</head>

<body>
<div class="head">
    <div class="wrapper clearfix">
        <div class="clearfix" id="top">
            <h1 class="fl">
                <a href="index.html"><img src="img/logo.png"/></a>
            </h1>
            <div class="fr clearfix" id="top1">
                <p class="fl">
                    <%
                        User user = (User) session.getAttribute("user");
                        if (user == null) {

                    %>
                    <a href="login.html" id="login">登录</a>
                    <a href="reg.html" id="reg">注册</a>
                    <%} else {%>
                    <a href="mygxin.jsp">
                        <%=user.getNickName()%>
                    </a>
                    <%}%>
                </p>
                <form action="flowerDer.jsp" method="get" class="fl">
                    <input type="text" name="goodsName" placeholder="热门搜索：干花花瓶"/>
                    <input type="hidden" name="type" value="3">
                    <input type="submit" value="">
                </form>
                <div class="btn fl clearfix">
                    <a href="mygxin.jsp"><img src="img/grzx.png"/></a>
                    <a href="#" class="er1"><img src="img/ewm.png"/></a>
                    <a href="cart.jsp"><img src="img/gwc.png"/></a>
                    <p>
                        <a href="#"><img src="img/smewm.png"/></a>
                    </p>
                </div>
            </div>
        </div>
        <ul class="clearfix" id="bott">
            <li><a href="index.jsp">首页</a></li>
            <li><a href="flowerDer.jsp?type=0">所有商品</a>
                <div class="sList">
                    <div class="wrapper  clearfix">
                        <a href="paint.html">
                            <dl>
                                <dt><img src="img/nav1.jpg"/></dt>
                                <dd>浓情欧式</dd>
                            </dl>
                        </a>
                        <a href="paint.html">
                            <dl>
                                <dt><img src="img/nav2.jpg"/></dt>
                                <dd>浪漫美式</dd>
                            </dl>
                        </a>
                        <a href="paint.html">
                            <dl>
                                <dt><img src="img/nav3.jpg"/></dt>
                                <dd>雅致中式</dd>
                            </dl>
                        </a>
                        <a href="paint.html">
                            <dl>
                                <dt><img src="img/nav6.jpg"/></dt>
                                <dd>简约现代</dd>
                            </dl>
                        </a>
                        <a href="paint.html">
                            <dl>
                                <dt><img src="img/nav7.jpg"/></dt>
                                <dd>创意装饰</dd>
                            </dl>
                        </a>
                    </div>
                </div>
            </li>
            <%
                //从缓存中取出所有分类数据
                List<HashMap<String, Object>> class1 = (List<HashMap<String, Object>>) DataUtil.data.get("class");
                if (class1 == null) {
                    //从未存储过，从数据库查询
                    List<GoodsClass> goodsClassList = GoodsClassService.findAll();
                    //创建存储一级分类集合 class1
                    class1 = new ArrayList<>();
                    //从所有分类中将一级分类取出
                    for (int i = 0; i < goodsClassList.size(); i++) {
                        //类对象
                        GoodsClass goodsClass = goodsClassList.get(i);
                        if (goodsClass.getParentId() == 0) {
                            HashMap<String, Object> map = new HashMap<>();
                            //存储二级分类的集合class2
                            List<GoodsClass> class2 = new ArrayList<>();
                            //遍历存储
                            for (int j = 0; j < goodsClassList.size(); j++) {
                                if (goodsClassList.get(j).getParentId() == goodsClass.getId()) {
                                    class2.add(goodsClassList.get(j));
                                }
                            }
                            map.put("class1", goodsClass);
                            map.put("class2", class2);
                            class1.add(map);
                        }
                    }
                    DataUtil.data.put("class", class1);
                }
            %>
            <%
                for (int i = 0; i < class1.size(); i++) {
                    HashMap<String, Object> classMap = class1.get(i);
                    GoodsClass cla1 = (GoodsClass) classMap.get("class1");
                    List<GoodsClass> cla2List = (List<GoodsClass>) classMap.get("class2");

            %>
            <li>
                <a href="flowerDer.jsp?type=1&classId1=<%=cla1.getId()%>">
                    <%=cla1.getClassName()%>
                </a>
                <div class="sList2">
                    <div class="clearfix">
                        <%
                            for (GoodsClass goodsClass : cla2List) {
                        %>
                        <a href="flowerDer.jsp?type=2&classId2=<%=goodsClass.getId()%>">
                            <%=goodsClass.getClassName()%>
                        </a>
                        <%
                            }
                        %>
                    </div>
                </div>
            </li>
            <% } %>
        </ul>
    </div>
</div>
</body>

</html>