<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.type.model.*"%>

<%
  TypeVO typeVO = (TypeVO) request.getAttribute("typeVO"); 
%>

<html>
<head>
<title>活動類型資料</title>

<style>
  table#table-1 {
	background-color: lightgray;
    border: 2px solid darkgray;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>活動類型</h3>
		 <h4><a href="<%=request.getContextPath() %>/back_end/type/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>活動類型編號</th>
		<th>活動類型名稱</th>
		<th>活動類型說明</th>
	</tr>
	<tr>
		<td><%=typeVO.getTypeId()%></td>
		<td><%=typeVO.getTypeName()%></td>
		<td><%=typeVO.getNote()%></td>

	</tr>
</table>

</body>
</html>