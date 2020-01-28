<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%-- IMPORT --%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.Integer" %>
<%@ page import="app.data.*" %>

<%-- USEBEAN --%>
<jsp:useBean id="listeAbsencesEtudiants" type="java.util.Map<app.data.Etudiant,java.lang.Integer>" scope="request"/>
<jsp:useBean id="listeEtudiants" type="java.util.Collection<app.data.Etudiant>" scope="request"/>

<!--% ou en JAVA
Map<Etudiant,Integer> listeAbsencesEtudiants = (Map<Etudiant,Integer>)request.getAttribute("listeAbsencesEtudiants");
%-->

<div>

    <!-- AFFICHAGE en fonction du groupe ou de ALL  -->
    <h3 class="ml-4">Consultation des absences</h3>

    <!-- AFFICHAGE des notes des étudiants  -->
    <% if (listeAbsencesEtudiants.size() != 0) {%>

    <!-- tableau de notes  -->
    <table class="table table-striped table-dark">
        <tr>
            <th>Etudiant</th>
            <th>Groupe</th>
            <th>Nombre d'absences</th>
            <th>Gérer les absence</th>
        </tr>
        <%
            int totalAbsences = 0;
            for (Etudiant etudiant : listeEtudiants) {
                Integer absences = listeAbsencesEtudiants.get(etudiant);
        %>
        <tr>
            <td>
                <a href="<%= application.getContextPath()%>/do/etudiant?id=<%=etudiant.getId()%>"><%=etudiant.getPrenom()%> <%=etudiant.getNom()%>
                </a></td>
            <td><%=etudiant.getGroupe().getNom()%>
            </td>
            <td><%=absences%>
            </td>
            <td>
                <div class="btn-group" role="group">
                    <a class="btn btn-secondary" href="<%= application.getContextPath()%>/do/ajouter-absence?id=<%=etudiant.getId()%>">+</a>
                    <a class="btn btn-secondary<%=absences < 1 ? " disabled":""%>" href="<%= application.getContextPath()%>/do/enlever-absence?id=<%=etudiant.getId()%>">-</a>
                </div>
            </td>
        </tr>
        <%
                totalAbsences = totalAbsences + absences;
            }
        %>
    </table>

    <p class="ml-4">Nombre total d'absences : <%= totalAbsences%>
    </p>

    <% } else {%>

    <p class="ml-4">Aucun étudiant</p>
    <%}%>

</div>
