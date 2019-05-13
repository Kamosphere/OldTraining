<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>员工列表</title>
<style>
#employees_table
{
    width: 100%;
	border-collapse:collapse;
	text-align:medium;
	border: 2px solid #000;
}
#employees_table td, #employees_table th
{
	font-size:1em;
	border:2px solid #000;
	text-align: center;
}
#employees_table th
{
	font-weight: normal;
	background-color:#000;
	color:#fff;
}
#employees_table tr
{
    height: 60px;
    width: 100%;
    color: #000;
}
#employees_table tr:nth-child(even)
{
	background-color:#EEE;
	color:#000;
}
</style>

</head>
<body>
<table id="employees_table">
        <tr>
            <th>编号</th>
            <th>姓名</th>
            <th>年龄</th>
            <th>性别</th>
        </tr>
        <c:if test="${employeeList == null || fn:length(employeeList) == 0}">
            <tr>
                <td colspan="4">暂无数据</td>
            </tr>
        </c:if>
        <c:forEach var="employee" items="${employeeList}">
            <tr>
                <td>${employee.id}</td>
                <td>${employee.name}</td>
                <td>${employee.age}</td>
                <td>${employee.gender}</td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
