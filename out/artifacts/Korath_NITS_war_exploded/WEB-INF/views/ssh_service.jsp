<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SSH Service</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/sshCommand" method="POST"  >
    <fieldset>
        <legend>SSH Terminal:</legend>
        Host:<br>
        <input type="text" name="host" value="${host}"><br>
        Username:<br>
        <input type="text" name="username" value="${username}"><br>
        Password:<br>
        <input type="password" name="password" value="${password}"><br>
        Command:<br>
        <input type="text" name="command" value=""><br>
        <br>
        <input type="submit" value="Connect">
    </fieldset>
</form>

<br>

<fieldset>
    <legend>Output Log:</legend>
<textarea style="width: 100%; height: 50%">
<c:if test="${not empty response.lines}">
    <c:forEach items="${response.lines}" var="line">
       ${line}
    </c:forEach>
</c:if>
</textarea>
</fieldset>


</body>
</html>
