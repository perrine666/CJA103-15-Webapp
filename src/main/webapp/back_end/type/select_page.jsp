<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Type: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: lightgray;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>活動類型管理</h3></td></tr>
</table>


<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='<%=request.getContextPath() %>/back_end/type/listAllType.jsp'>List</a> all Types.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/type/type.do" >
        <b>輸入活動類型編號:</b>
        <input type="text" name="typeId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="typeSvc" scope="page" class="com.type.model.TypeService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/type/type.do" >
       <b>選擇活動類型編號:</b>
       <select size="1" name="typeId">
         <c:forEach var="typeVO" items="${typeSvc.all}" > 
          <option value="${typeVO.typeId}">${typeVO.typeId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/type/type.do" >
       <b>選擇活動類型名稱:</b>
       <select size="1" name="typeId">
         <c:forEach var="typeVO" items="${typeSvc.all}" > 
          <option value="${typeVO.typeId}">${typeVO.typeName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>活動類型管理</h3>

<ul>
  <li><a href='<%=request.getContextPath() %>/back_end/type/addType.jsp'>Add</a> a new Type.</li>
</ul>

</body>
</html>