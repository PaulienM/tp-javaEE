<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%-- IMPORT --%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.Integer" %>
<%@ page import="app.data.*" %>

<%-- USEBEAN --%>
<jsp:useBean id="listeAbsencesEtudiants" type="java.util.Map<app.data.Etudiant,java.lang.Integer>" scope="request"/>

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
        </tr>
        <%
            int totalAbsences = 0;
            for (Map.Entry<Etudiant, Integer> entry : listeAbsencesEtudiants.entrySet()) {
                Etudiant etudiant = entry.getKey();
                Integer absences = entry.getValue();
        %>
        <tr>
            <td>
                <a href="<%= application.getContextPath()%>/do/etudiant?id=<%=etudiant.getId()%>"><%=etudiant.getPrenom()%> <%=etudiant.getNom()%>
                </a></td>
            <td><%=etudiant.getGroupe().getNom()%>
            </td>
            <td><%=absences%>
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
