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
        <th>Account</th>
        <th>Nom</th>
        <th>Prenom</th>
    </tr>
    <c:forEach var="a" items="${accounts}">
        <tr>
            <td>${a.getUser_id()}</td>
            <td>${a.getNom()}</td>
            <td>${a.getPrenom()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>