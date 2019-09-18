<%@page import="java.util.List"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="model.Card"%>
<%@page import="model.enumerate.AssociatorState"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="login_message" type="model.message.LoginMessage"
	scope="session"></jsp:useBean>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/including.jsp">
    <jsp:param name="title" value="管理员主页-用户统计查看"/>
</jsp:include>
<script type="text/javascript" src="<%=request.getContextPath() + "/js/echarts.js"%>"></script>
</head>


<body class="">

	<jsp:include page="../common/header.jsp">
        <jsp:param name="name" value="管理员"/>
    </jsp:include>

    <%@ include file="../common/sidebar_manager.jsp"%>

	<div class="content">
		<div class="header">
			<h1 class="page-title">用户统计查看</h1>
		</div>
		<ul class="breadcrumb">
			<li><a href="<%=request.getContextPath() + "/user"%>">主页</a></li>
			<li><span class="divider">/</span></li>
            <li>统计查看</li>
			<li><span class="divider">/</span></li>
            <li>用户统计查看</li>
		</ul>
		<%NumberFormat nf = NumberFormat.getInstance();%>
		<div class="container-fluid">
			<div class="row-fluid">
		<div class="block">
	        <div class="block-heading">
	            <a>用户支出统计图表</a>
	            <div id="chartF" class="block-body" style="height: 400px;">
	            	<div id="main" style="height: 400px;"></div>
	            </div>
	        </div>
        </div>
		<div class="block">
        <div class="block-heading">


            <a>用户支出详细列表</a>
        </div>
        <div id="widget2container" class="block-body">
            <table class="table list">
              <tbody>
                    <%for (Card associator: (List<Card>)session.getAttribute("associators")){ %>
	                <tr>
	                    <td>
	                        <%-- <p><i class="icon-user"></i>酒店名称：<%=hostel.getName()%></p> --%>
	                        <p>用户账号：<%=associator.getLogin()%></p>
							<p>用户消费额：<%=nf.format(associator.getTotalConsum())%></p>
							<p>用户状态：<%=AssociatorState.getString(AssociatorState.values()[associator.getState()])%></p>
							<p>用户等级：<%=associator.getLevel()%></p>
	                    </td>
	                </tr>
                    <%} %>
              </tbody>
            </table>
        </div>
    </div>
			</div>
			<%@ include file="../common/footer.jsp"%>
		</div>
	</div>

	<script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        
        var chart = document.getElementById('main');
        var father = document.getElementById('chartF');
        var resizeWorldMapContainer = function () {
		    chart.style.width = father.style.width+'px';
		    chart.style.height = father.style.height+'px';
		};
		
		resizeWorldMapContainer();
		console.log(father);
		console.log(chart);
		var myChart = echarts.init(chart);
        // 指定图表的配置项和数据
        var option = {
		    title : {
		        text: '用户支出统计'
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['用户总支出']
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            data : [
		                <%for (Card associator: (List<Card>)session.getAttribute("associators")){ %>
		            		'<%=associator.getLogin()%>',
		            	<%} %>
		            ]
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series : [
		       
		        {
		            name:'用户总支出',
		            type:'bar',
		            data:[
		                <%for (Card associator: (List<Card>)session.getAttribute("associators")){ %>
		            		<%=associator.getTotalConsum()%>,
		            	<%} %>
		            ]
		        }
		    ]
		};

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        window.onresize = function () {
		    //重置容器高宽
		    resizeWorldMapContainer();
		    myChart.resize();
		};		
    </script>
</body>
</html>
