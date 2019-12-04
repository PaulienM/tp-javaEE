<%@ page import="app.data.GestionFactory" %>
<%@ page import="java.util.Collection" %>
<%@ page import="app.data.Etudiant" %><%--
  Created by IntelliJ IDEA.
  User: marangep
  Date: 03/12/2019
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Etudiants</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  </head>
  <body>
  <table class="table table-striped table-dark">
    <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Prenom</th>
      <th scope="col">Nom</th>
    </tr>
    </thead>
    <tbody>
    <%
      Collection<Etudiant> etudiants = GestionFactory.getEtudiants();
      for (Etudiant etudiant: etudiants) {
    %>
    <tr>
      <th scope="row"><%= etudiant.getId() %></th>
      <td><%= etudiant.getPrenom() %></td>
      <td><%= etudiant.getNom() %></td>
    </tr>
    <%
      }
    %>
    </tbody>
  </table>

  </body>
</html>
