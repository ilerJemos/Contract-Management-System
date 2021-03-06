<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>合同管理系统 - 菜单栏</title>
		<link href="css/frame.css" rel="stylesheet" type="text/css" />
	</head>

	<body>
		<div class="menu">
			<dl>
				<dt>
					起草
				</dt>
				<dd>
					<a href="toDraft" target="main">起草合同</a>
				</dd>
				<dd>
					<a href="toDdghtList" target="main">待定稿合同</a>
				</dd>
				<dd>
					<a href="#">已定稿合同</a>
				</dd>
				<dd>
					<a href="#">查询过程</a>
				</dd>
			</dl>
			<dl>
				<dt>
					会签
				</dt>
				<dd>
					<a href="toDhqhtList" target="main">待会签合同</a>
				</dd>
				<dd>
					<a href="#">已会签合同</a>
				</dd>
			</dl>
			<dl>
				<dt>
					审批
				</dt>
				<dd>
					<a href="toDshphtList" target="main">待批合同</a>
				</dd>
				<dd>
					<a href="#">已批合同</a>
				</dd>
			</dl>
			<dl>
				<dt>
					签订
				</dt>
				<dd>
					<a href="toDqdhtList" target="main">待签合同</a>
				</dd>
				<dd>
					<a href="#">已签合同</a>
				</dd>
			</dl>
		</div>
	</body>
</html>
