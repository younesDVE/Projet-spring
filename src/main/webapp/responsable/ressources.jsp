<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="../script.js"></script>
</head>
<body>
<table>
    <tr>
        <th>Code</th>
        <th>Type</th>
        <th>Date Livraison</th>
        <th>Duree garantie</th>
        <th>Marque</th>
        <th>Specification</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="ressource" items="${ressources}">
            <tr>
               <c:forEach var="att" items="${ressource.toList()}">
                   <td>${att}</td>
               </c:forEach>
                <td>
                    <form method="post" action="RessourceAction">
                        <input type="hidden" name="code" value="${ressource.getCode()}">
                        <button type="submit" name="action" value="edite">Modifier</button>
                        <button type="submit" name="action" value="delete">Supprimer</button>
                        <c:if test="${!ressource.isAffacte()}">
                            <button type="submit" name="action" value="affect">Affecter</button>
                        </c:if>
                    </form>
                </td>
            </tr>
        </c:forEach>
</table>
</body>
</html>