<%@ page import="app.data.Groupe" %>
<%@ page import="app.data.GroupeDAO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: marangep
  Date: 28/01/2020
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Groupe> groupes = GroupeDAO.getAll();
%>

<nav class="nav">
    <% for (Groupe groupe : groupes) { %>
    <a class="nav-link" href="<%=request.getAttribute("javax.servlet.forward.request_uri")%>?idGroupe=<%=groupe.getId()%>"><%= groupe.getNom()%></a>
    <% } %>
    <a class="nav-link" href="<%=request.getAttribute("javax.servlet.forward.request_uri")%>">Tous les groupes</a>
</nav>