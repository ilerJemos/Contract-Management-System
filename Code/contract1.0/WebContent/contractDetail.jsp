<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.ruanko.model.ConDetailBusiModel"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Contract details</title>
		<link href="css/style.css" rel="stylesheet" media="screen"
			type="text/css" />
	</head>

	<body>
		<div class="preview">
		<%
			// Get contract details business object
			ConDetailBusiModel contract = new ConDetailBusiModel();
			if (request.getAttribute("conDetailBusiModel") != null) {
				contract = (ConDetailBusiModel)request.getAttribute("conDetailBusiModel");
			}
		%>

			<div class="viewbox">
				<div class="title">
					<%=contract.getName()%>
				</div>
				<div class="info">
					<small>Customer:</small><%=contract.getCustomer()%>
					<small>Drafter:</small><%=contract.getDraftsman()%>
					<small>Begin time:</small><%=contract.getBeginTime()%>
					<small>End time:</small><%=contract.getEndTime()%>
				</div>
				<div class="content">
					<%=contract.getContent()%>
				</div>
			</div>

			<div class="pfooter">
				<ul>
					<li>
						<a target="_blank" href="#">合同管理系统</a>
					</li>
					<li>
						｜
					</li>
					<li>
						<a target="_blank" href="#">Help</a>
					</li>
				</ul>

				<p>
					版权&nbsp;&copy;&nbsp;Ruanko COE&nbsp;
					<a href="http://www.4399.com" title="www.4399.com" target="_blank"><strong>www.4399.com</strong>
					</a>&nbsp;版权保留
				</p>
			</div>
		</div>
	</body>
</html>
