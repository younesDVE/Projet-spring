<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>hello</h1>
<h5>${panne.getId()}</h5>
<h5>${panne.getCode()}</h5>
<h5>${panne.getDate()}</h5>
<h5>${panne.getOrdre()}</h5>
<h5>${panne.getFrequence()}</h5>
<h5>${panne.getExplication()}</h5>

<c:if test="${panne.getOrdre()== 'GG'}">
<form method="post" action="DeletePanne">
    <input type="hidden" name="id" value="${panne.getId()}">
    <button type="submit" name="action" value="delete" class="btn btn-success">reparer</button>
</form>
</c:if>
    <c:if test="${panne.getOrdre()== 'rr'}">
<form method="post" action="ConsulterPanne">
    <input type="hidden" name="id" value="${panne.getId()}">
    <button type="submit" name="action" value="delete" class="btn btn-success">genere constat</button>
</form>
    </c:if>


</body>
</html>