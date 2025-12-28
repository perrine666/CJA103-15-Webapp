<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.type.model.*"%>

<% 
	TypeVO typeVO = (TypeVO) request.getAttribute("typeVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>活動類型資料修改</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>活動類型資料修改</h3>
		 <h4><a href="<%=request.getContextPath() %>/back_end/type/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="type.do" name="form1">
<table>
	<tr>
		<td>活動類型編號:<font color=red><b>*</b></font></td>
		<td><%=typeVO.getTypeId()%></td>
	</tr>
	<tr>
		<td>活動類型名稱:</td>
		<td><input type="TEXT" name="typeName" value="<%=typeVO.getTypeName()%>" size="45"/></td>
	</tr>
	<tr>
		<td>活動類型說明:</td>
		<td><input type="TEXT" name="note"   value="<%=typeVO.getNote()%>" size="45"/></td>
	</tr>
	<tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="typeId" value="<%=typeVO.getTypeId()%>">
<input type="submit" value="送出修改"></FORM>
</body>

</html>