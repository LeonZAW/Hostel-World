<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.List"%>
<%@page import="model.Hostel"%>
<%@page import="model.enumerate.HostelState"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="login_message" type="model.message.LoginMessage"
	scope="session"></jsp:useBean>
<!DOCTYPE html>
<html lang="en">
<head> 
<jsp:include page="../common/including.jsp">
    <jsp:param name="title" value="管理员主页-酒店统计查看"/>
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
			<h1 class="page-title">酒店统计查看</h1>
		</div>
		<ul class="breadcrumb">
			<li><a href="<%=request.getContextPath() + "/user"%>">主页</a></li>
			<li><span class="divider">/</span></li>
            <li>统计查看</li>
			<li><span class="divider">/</span></li>
            <li>酒店统计查看</li>
		</ul>
     <%NumberFormat nf = NumberFormat.getInstance();%> 

		<div class="container-fluid">
			<div class="row-fluid">
		<div class="block">
	        <div class="block-heading">
	            <a>酒店统计图表</a>
	            <div id="chartF" class="block-body" style="height: 400px;">
	            	<div id="main" style="height: 400px;"></div>
	            </div>
	        </div>
        </div>
		<div class="block">
        <div class="block-heading">
            <a>酒店统计列表</a>
        </div>
        
        
        <div id="widget2container" class="block-body">
            <table class="table list">
              <tbody>
	                    <%for (Hostel hostel: (List<Hostel>)session.getAttribute("hostels")){ %>
		                <tr>
		                    <td>
		                        <p><!-- <i class="icon-user"></i> -->酒店名称：<%=hostel.getName()%></p>
		                        <p>酒店星级：<%=hostel.getStar()%>星级</p>
		                        <p>酒店收入：<%=nf.format(hostel.getTotalIncome())%></p>
		                        <p>酒店状态：<%=HostelState.getString(HostelState.values()[hostel.getState()])%></p>
		                        <p>酒店地址</p>
		                        <p><%=hostel.getAddress()%></p>
		                        <p>酒店简介</p>
		                        <p><%=hostel.getSynopsis()%></p>
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
		        text: '酒店收入统计'
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['酒店总收入']
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            data : [
		                <%for (Hostel hostel: (List<Hostel>)session.getAttribute("hostels")){%>
		            		'<%=hostel.getName()%>',
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
		            name:'酒店总收入',
		            type:'bar',
		            data:[
		                <%for (Hostel hostel: (List<Hostel>)session.getAttribute("hostels")){%>
		            		<%=hostel.getTotalIncome()%>,
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
