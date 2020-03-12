<%@ page import="com.lzq.bean.*" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.lzq.service.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head lang="en">
    <meta charset="utf-8" />
    <title>个人信息</title>
    <link rel="stylesheet" type="text/css" href="css/public.css" />
    <link rel="stylesheet" type="text/css" href="css/myorder.css" />
</head>

<body>
<%
    User u = (User) session.getAttribute("user");
    String orderId = request.getParameter("orderId");
    Order order = OrderService.getById(Integer.parseInt(orderId));
    List<OrderGoods> orderGoodsList = OrderGoodsService.getByOrderId(Integer.parseInt(orderId));
    Address address = AddressService.findAddressById(order.getAddressId());

%>
    <!------------------------------head------------------------------>
    <%@include file="include_header.jsp"%>
    <!------------------------------idea------------------------------>
    <div class="address mt">
        <div class="wrapper clearfix"><a href="index.html" class="fl">首页</a><span>/</span><a href="myorderq.jsp" class="on">我的订单</a><span>/</span><a href="#" class="on">订单详情</a></div>
    </div>
    <!------------------------------Bott------------------------------>
    <div class="Bott">
        <div class="wrapper clearfix">
            <div class="zuo fl">
                <h3>
                    <a href="#"><img src="img/<%=u.getUserPhoto()%>" /></a>
                    <p class="clearfix"><span class="fl"><%=u.getNickName()%></span>
                        <span class="fr" onclick="quit()" >[退出登录]</span></p>
                    <script type="text/javascript">
                        function quit() {
                            $.post("quitUser.do",function (data) {
                                layer.msg(data.msg);
                                location.href = "/index.jsp"
                            });

                        }
                    </script>
                    </p>
                </h3>
                <div>
                    <h4>我的交易</h4>
                    <ul>
                        <li><a href="cart.jsp">我的购物车</a></li>
                        <li class="on"><a href="myorderq.jsp">我的订单</a></li>
                        <li><a href="myprod.html">评价晒单</a></li>
                    </ul>
                    <h4>个人中心</h4>
                    <ul>
                        <li><a href="mygxin.jsp">我的中心</a></li>
                        <li><a href="address.jsp">地址管理</a></li>
                    </ul>
                    <h4>账户管理</h4>
                    <ul>
                        <li><a href="mygrxx.jsp">个人信息</a></li>
                        <li><a href="remima.jsp">修改密码</a></li>
                    </ul>
                </div>
            </div>
            <div class="you fl">
                <div class="my clearfix">
                    <h2>订单详情<a href="#">请谨防钓鱼链接或诈骗电话，了解更多&gt</a></h2>
                    <h3>订单号：<span><%=order.getExpressNo()%></span></h3>
                </div>
                <div class="orderList">
                    <div class="orderList1">
                        <h3><%=order.getStatus()==0?"待发货":(order.getStatus()==1?"已发货":"已取件")%></h3>

                        <%
                            for (OrderGoods orderGoods : orderGoodsList) {
                                String img = "errorGoodsImg.jpg" ;
                                Gson g = new Gson();
                                if("[".equals(orderGoods.getGoodsImg())){
                                    img = "errorGoodsImg.jpg";
                                }else{
                                    List imgs = g.fromJson(orderGoods.getGoodsImg(),List.class);
                                    img = (String)imgs.get(0);
                                }
                           %>
                        <div class="clearfix">
                            <a href="#" class="fl"><img width="300px" src="img/imgs/<%=img%>" /></a>
                            <p class="fl"><a href="#"><%=orderGoods.getGoodsName()%></a>
                                <a href="#">¥<%=orderGoods.getGoodsPrice()%>×<%=orderGoods.getGoodsNum()%></a></p>
                        </div>

                        <% }%>
                    </div>
                    <div class="orderList1">
                        <h3>收货信息</h3>
                        <p>姓 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：<span><%=address.getUserName()%></span></p>
                        <p>联系电话：<span><%=address.getUserPhone()%></span></p>
                        <p>收货地址：<span><%=RegionService.findByCode(address.getProvinceId()+"").getName()%>&nbsp;
                            <%=RegionService.findByCode(address.getCityId()+"").getName()%>&nbsp;
                            <%=RegionService.findByCode(address.getAreaId()+"").getName()%>&nbsp;
                            <%=RegionService.findByCode(address.getStreeId()+"").getName()%>
                        </span></p>
                    </div>
                    <div class="orderList1">
                        <h3>支付方式及送货时间</h3>
                        <p>支付方式：<span><%=PayMentsService.getById(order.getPaymentId()).getName()%></span></p>
                        <p>送货方式：<span><%=TransportsService.getById(order.getTransportId()).getName()%></span></p>
                    </div>
                    <div class="orderList1 hei">
                        <h3><strong>商品总价：</strong><span>¥<%=order.getMoney()%></span></h3>
                        <p><strong>运费：</strong><span>¥0</span></p>
                        <p><strong>订单金额：</strong><span>¥<%=order.getMoney()%></span></p>
                        <p><strong>实付金额：</strong><span>¥<%=order.getMoney()%></span></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <<!--返回顶部-->
      <%@include file="footer.jsp"%>
        <script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
        <script src="js/public.js" type="text/javascript" charset="utf-8"></script>
        <script src="js/user.js" type="text/javascript" charset="utf-8"></script>
</body>

</html>