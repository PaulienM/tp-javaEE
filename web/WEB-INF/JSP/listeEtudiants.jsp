<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%-- IMPORT --%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.Integer" %>
<%@ page import="app.data.*" %>

<%-- USEBEAN --%>
<jsp:useBean id="listeEtudiants" type="java.util.Collection<app.data.Etudiant>" scope="request"/>

<div>

    <!-- AFFICHAGE d'un titre  -->
    <h3 class="ml-4">Liste des étudiants</h3>

    <!-- AFFICHAGE des notes des étudiants  -->
    <% if (listeEtudiants.size() != 0) {%>

    <jsp:include page="/WEB-INF/JSP/filtreGroupe.jsp"/>

    <!-- tableau de notes  -->
    <table class="table table-striped table-dark">

        <tr>
            <th>Etudiant</th>
            <th>Groupe</th>
        </tr>
        <%
            for (Etudiant etudiant : listeEtudiants) {
        %>
        <tr>
            <td>
                <a href="<%= application.getContextPath()%>/do/etudiant?id=<%=etudiant.getId()%>"><%=etudiant.getPrenom()%> <%=etudiant.getNom()%>
                </a>
            </td>
            <td><%=etudiant.getGroupe().getNom()%>
            </td>
        </tr>
        <%
            }
        %>
    </table>

    <% } else {%>

    <p class="ml-4">Aucun étudiant</p>
    <%}%>

</div>
