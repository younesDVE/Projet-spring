<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>hello</h1>
<h5>${constat.getId()}</h5>
<h5>${constat.getObjet()}</h5>
<h5>${constat.getEmail()}</h5>


<c:if test="${result < 0}">
    <form method="post" action="reparer">
        <input type="hidden" name="id" value="${constat.getId() }">
        <button type="submit" name="action" value="delete" class="btn btn-success">reparer</button>
    </form>
</c:if>
<c:if test="${result > 0}">
    <form method="post" action="garantie">
        <input type="hidden" name="id" value="${constat.getId() }">
        <button type="submit" name="action" value="delete" class="btn btn-success">changer ressource</button>
    </form>
</c:if>




</body>
</html>