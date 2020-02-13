<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%-- IMPORT --%>
<%@ page import="java.util.*" %>
<%@ page import="app.data.*" %>

<%-- USEBEAN --%>
<jsp:useBean id="listeNotesEtudiants" type="java.util.Map<app.data.Etudiant,java.lang.Float>" scope="request"/>
<jsp:useBean id="listeEtudiants" type="java.util.Collection<app.data.Etudiant>" scope="request"/>


<div>

    <!-- AFFICHAGE d'un titre  -->
    <h3 class="ml-4">Moyennes générales</h3>

    <jsp:include page="/WEB-INF/JSP/filtreGroupe.jsp"/>

    <!-- AFFICHAGE des notes des étudiants  -->
    <% if (listeNotesEtudiants.size() != 0) {%>

    <!-- tableau de notes  -->
    <table class="table table-striped table-dark">
        <tr>
            <th>Etudiant</th>
            <th>Groupe</th>
            <th>Note</th>
        </tr>

        <%
            float sommeMoyenneGenerale = 0;
            for (Etudiant etudiant : listeEtudiants) {
                float note = listeNotesEtudiants.get(etudiant);
        %>
        <tr>
            <td>
                <a href="<%= application.getContextPath()%>/do/etudiant?id=<%=etudiant.getId()%>"><%=etudiant.getPrenom()%> <%=etudiant.getNom()%>
                </a></td>
            <td><%=etudiant.getGroupe() != null ? etudiant.getGroupe().getNom() : "-"%>
            </td>
            <td><%=note%>
            </td>
        </tr>
        <%

                // Calcul de la somme totale des notes pour calcul de la moyenne
                sommeMoyenneGenerale = sommeMoyenneGenerale + note;
            }
        %>
    </table>

    <p class="ml-4">Moyenne des étudiants : <%= sommeMoyenneGenerale / listeNotesEtudiants.size()%>
    </p>

    <% } else {%>

    <p class="ml-4">Aucun étudiant</p>
    <%}%>

</div>
