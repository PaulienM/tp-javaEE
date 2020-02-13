<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%-- IMPORT --%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.Integer" %>
<%@ page import="app.data.*" %>

<%-- USEBEAN --%>
<jsp:useBean id="listeEtudiants" type="java.util.Collection<app.data.Etudiant>" scope="request"/>

<div>

    <a href="<%=application.getContextPath()%>/do/creer-etudiant" class="btn btn-success ml-4">Créer un etudiant</a>
    <!-- AFFICHAGE d'un titre  -->
    <h3 class="ml-4">Liste des étudiants</h3>

    <jsp:include page="/WEB-INF/JSP/filtreGroupe.jsp"/>

    <!-- AFFICHAGE des notes des étudiants  -->
    <% if (listeEtudiants.size() != 0) {%>

    <!-- tableau de notes  -->
    <table class="table table-striped table-dark">

        <tr>
            <th>Etudiant</th>
            <th>Groupe</th>
            <th>Edition</th>
        </tr>
        <%
            for (Etudiant etudiant : listeEtudiants) {
        %>
        <tr>
            <td>
                <a href="<%= application.getContextPath()%>/do/etudiant?id=<%=etudiant.getId()%>"><%=etudiant.getPrenom()%> <%=etudiant.getNom()%>
                </a>
            </td>
            <td><%=etudiant.getGroupe() != null ? etudiant.getGroupe().getNom() : "-"%></td>
            <td>
                <div class="btn-group" role="group">
                    <a href="<%=application.getContextPath()%>/do/supprimer-etudiant?id=<%=etudiant.getId()%>" class="btn btn-danger">Supprimer</a>
                    <a href="<%=application.getContextPath()%>/do/modifier-etudiant?id=<%=etudiant.getId()%>" class="btn btn-primary">Modifier</a>
                </div>
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
