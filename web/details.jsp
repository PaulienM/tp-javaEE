<%@ page import="app.data.Etudiant" %>
<%@ page import="app.data.GestionFactory" %>
<%--
  Created by IntelliJ IDEA.
  User: marangep
  Date: 03/12/2019
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="etudiant" class="app.data.Etudiant" scope="request"/>
<jsp:useBean id="absence" class="java.lang.Integer" scope="request"/>

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <title><%= etudiant.getNom() %>
    </title>
</head>
<body>
<div class="container">
    <h1 class="mt-4">
        #<jsp:getProperty name="etudiant" property="id"/>
        <jsp:getProperty name="etudiant" property="prenom"/>
        <jsp:getProperty name="etudiant" property="nom"/>
    </h1>
    <p>Cet étudiant a été absent <%= absence %> fois</p>
    <a href="index.jsp">Retour à la liste des étudiants</a>
</div>
</body>
</html>
