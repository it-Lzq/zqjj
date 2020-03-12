<%@ page import="com.lzq.bean.*" %>
<%@ page import="com.lzq.bean.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.lzq.service.impl.SqlCartServiceImpl" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.lzq.service.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head lang="en">
    <meta charset="utf-8" />
    <title>order</title>
    <link rel="stylesheet" type="text/css" href="css/public.css" />
    <link rel="stylesheet" type="text/css" href="css/proList.css" />
    <link rel="stylesheet" type="text/css" href="css/mygxin.css" />
</head>

<body>
    <!----------------------------------------order------------------>
    <%@ include file="include_header.jsp"%>
    <%
        User u = (User) session.getAttribute("user");
        ArrayList<Carts> orderCarts = (ArrayList<Carts>)session.getAttribute("orderCarts");
        Order order = (Order) session.getAttribute("order");
        List<OrderGoods> orderGoodsList = OrderGoodsService.getByOrderId(order.getId());
        List<CnRegion> citys = RegionService.findByLevel(1);
        List<Address> addressList = AddressService.findByUserId(u.getId());
        List<Payments> paymentsList = PayMentsService.getPayments();
        List<Transports> transportsList = TransportsService.getTransports();


    %>
    <div class="order cart mt">
        <!-----------------site------------------->
        <div class="site">
            <p class="wrapper clearfix"><span class="fl">订单确认</span><img class="top" src="img/temp/cartTop02.png"></p>
        </div>
        <!-----------------orderCon------------------->
        <div class="orderCon wrapper clearfix">
            <div class="orderL fl">
                <!--------h3---------------->
                <h3>收件信息<a onclick="addressShow(0,-1)"  class="fr edit" >新增地址</a></h3>
                <!--------addres---------------->
                <div class="addres clearfix">
                    <%
                        for(Address address: addressList){
                    %>
                    <div class="addre fl <%=address.getId()==order.getAddressId()?"on":""%>" onclick="changeAddress(<%=address.getId()%>)">
                        <div class="tit clearfix">
                            <p class="fl"><%=address.getUserName()%>
                                <span class="default " id="<%=address.getIsDefault()==1?"ondefault":""%>" onclick="setDefault(<%=address.getId()%>,this)"><%=address.getIsDefault()==1?"[默认地址]":"设为默认"%></span>
                            </p>
                            <p class="fr"><a href="/delAddress.do?id=<%=address.getId()%>">删除</a><span>|</span>
                                <a onclick="addressShow(1,<%=address.getId()%>,'<%=address.getUserName()%>',<%=address.getUserPhone()%>)" class="edit">编辑</a>
                            </p>
                        </div>
                        <div class="addCon">
                            <p id="addAll"><%=RegionService.findByCode(address.getProvinceId()+"").getName()%>&nbsp;
                                <%=RegionService.findByCode(address.getCityId()+"").getName()%>&nbsp;
                                <%=RegionService.findByCode(address.getAreaId()+"").getName()%>&nbsp;
                                <%=RegionService.findByCode(address.getStreeId()+"").getName()%>
                            </p>
                            <%=address.getUserAddress()%>
                            <p><%=address.getUserPhone()%></p>
                        </div>
                    </div>
                    <%
                        }
                    %>

                </div>
                <h3>支付方式</h3>
                <!--------way---------------->
                <div class="way clearfix">
                    <% for (int i = 0; i < paymentsList.size(); i++) {
                        Payments payments = paymentsList.get(i);%>
                    <img onclick="changePayMents(<%=payments.getId()%>)"  class="<%=payments.getId()==order.getPaymentId()?"on":""%>" src="img/<%=payments.getImg()%>">
                <% }%>
                </div>
                <h3>选择快递</h3>
                <!--------dis---------------->
                <div class="dis clearfix">
                    <%
                        for (int i = 0; i < transportsList.size(); i++) {
                            Transports transports = transportsList.get(i);
                        %>
                    <span onclick="changeTransports(<%=transports.getId()%>)" class="<%=transports.getId()==order.getTransportId()?"on":""%>"><%=transports.getName()%></span>
                    <%}%>
                </div>
            </div>
            <div class="orderR fr">
                <div class="msg">
                    <h3>订单内容<a href="cart.jsp" class="fr">返回购物车</a></h3>
                    <!--------ul---------------->
                    <%
                        double priceA = 0.00;
                        for (int i = 0; i < orderGoodsList.size(); i++) {
                            OrderGoods orderGoods = orderGoodsList.get(i);
                            String img = "errorGoodsImg.jpg" ;
                            Gson g = new Gson();
                            if("[".equals(orderGoods.getGoodsImg())){
                                img = "errorGoodsImg.jpg";
                            }else{
                                List imgs = g.fromJson(orderGoods.getGoodsImg(),List.class);
                                img = (String)imgs.get(0);
                            }
                     %>
                    <ul class="clearfix">
                        <li class="fl"><img width="100px" height="100px" src="img/imgs/<%=img%>"></li>
                        <li class="fl">
                            <p><%=orderGoods.getGoodsName().length()<=18?orderGoods.getGoodsName():orderGoods.getGoodsName().substring(0,17)+"..."%></p>
                            <p>单价：<%=orderGoods.getGoodsPrice()%></p>
                            <p>数量：<%=orderGoods.getGoodsNum()%></p>
                        </li>
                        <li class="fr">￥<%=orderGoods.getGoodsPrice()*orderGoods.getGoodsNum()%></li>
                    </ul>
                        <%}
                    %>
                </div>
                <!--------tips---------------->
                <div class="tips">
                    <p><span class="fl">商品金额：</span><span class="fr">￥<%=order.getMoney()%></span></p>
                    <p><span class="fl">优惠金额：</span><span class="fr">￥0.00</span></p>
                    <p><span class="fl">运费：</span><span class="fr">免运费</span></p>
                </div>
                <!--------tips count---------------->
                <div class="count tips">
                    <p><span class="fl">合计：</span><span class="fr">￥<%=order.getMoney()%></span></p>
                </div>
                <!--<input type="button" name="" value="去支付">--><a href="ok.html" class="pay">去支付</a></div>
        </div>
    </div>
    <!--编辑弹框-->
    <!--遮罩-->
    <div class="mask"></div>
<%--    地址修改--%>
    <div class="adddz editAddre">
        <form action="addressUpdate.do" method="post">
            <input type="hidden"  id="aid" name="aid" value="0">
            <input type="text" placeholder="姓名" class="on" name="username" id="username" />
            <input type="text" placeholder="手机号" name="userphone" id="userphone"/>
            <div class="city">
                <select id="select1" onchange="selectChange(this)" name="provinceCode">
                <%
                for (CnRegion r:citys) {
                  %>
                    <option value="<%=r.getCode()%>" ><%=r.getName()%></option>
                <%  } %>
                </select>
                <select id="select2" onchange="selectChange(this)" name="cityCode">
                    <option value="城市/地区">城市/地区</option>
                </select>
                <select id="select3" onchange="selectChange(this)" name="areaCode">
                    <option value="区/县">区/县</option>
                </select>
                <select id="select4" name="streeCode">
                    <option value="配送区域">配送区域</option>
                </select>
            </div>
            <textarea name="addMesage" rows="" cols="" placeholder="详细地址"></textarea>
            <div class="bc"><input id="changeAdd"  type="submit"  value="保存" /><input type="button" value="取消" /></div>
        </form>
    </div>
    <!--返回顶部-->
    <jsp:include page="footer.jsp"></jsp:include>


    <script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/public.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/pro.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/user.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">

        var username = document.getElementById("username");
        var userphone = document.getElementById("userphone");
        //取货方式
        function changeTransports(id) {
            $.get("Transport.do","id="+id,function (result) {
            })
        }

        //更改支付方式
        function changePayMents(id) {
            $.post("changePayMents.do","id="+id,function () {

            })
        }
        //更改地址
        function changeAddress(id) {
            $.post("addressUp.do","id="+id,function () {
                
            })
        }



        //设为默认
        function setDefault(id,span) {
            if(span.innerHTML = "设为默认") {
                $.post("setDefault.do", "id=" + id, function (result) {
                    var $ondefault = document.getElementById("ondefault");
                    $ondefault.innerHTML = "设为默认";
                    $ondefault.id = "";
                    span.innerHTML = "[默认地址]";
                    span.id = "ondefault";
                })
            }
        }

        //显示添加地址或编辑地址
        function addressShow(type,id,name1,phone1) {
            console.log(name1);
            if(type == 0)  {
                document.getElementById("changeAdd").value = "添加";
                username.value = "";
                userphone.value="";
            }
            if(type == 1)  {
                document.getElementById("changeAdd").value = "修改";
                username.value = name1;
                userphone.value= phone1;
            }
            var aid = document.getElementById("aid");
            aid.value = id;
            $(".editAddre").show();
        }
        //递归显示地址选择
        function selectChange(ele) {
            change(ele.value);
        }
        function change(code){
            $.post("getRegions.do","code="+code,function (result) {
                if(result.status == 200){
                    var citys = result.citys;
                    var select = document.getElementById("select"+result.level);
                    select.options.length = 0;
                    for (var i = 0; i < citys.length; i++) {
                        var option = document.createElement("option");
                        option.innerText = citys[i].name;
                        option.value = citys[i].code;
                        select.appendChild(option);
                    }
                    if(result.level != 4) change(citys[0].code);
                }
            })
        }
    </script>
</body>

</html>