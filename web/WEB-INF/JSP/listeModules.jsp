<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%-- IMPORT --%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.Integer" %>
<%@ page import="app.data.*" %>
<%@ page import="app.data.Module" %>

<%-- USEBEAN --%>
<jsp:useBean id="listeModules" type="java.util.Collection<app.data.Module>" scope="request"/>

<div>

    <a href="<%=application.getContextPath()%>/do/creer-module" class="btn btn-success ml-4">Créer un module</a>
    <!-- AFFICHAGE d'un titre  -->
    <h3 class="ml-4">Liste des modules</h3>

    <jsp:include page="/WEB-INF/JSP/filtreGroupe.jsp"/>

    <!-- AFFICHAGE des notes des étudiants  -->
    <% if (listeModules.size() != 0) {%>

    <!-- tableau de notes  -->
    <table class="table table-striped table-dark">

        <tr>
            <th>Module</th>
            <th>Groupes</th>
            <th>Edition</th>
        </tr>
        <%
            for (Module module : listeModules) {
        %>
        <tr>
            <td>
                <%=module.getNom()%>
            </td>
            <td>
                <%
                    int i = 0;
                    for (Groupe groupe : module.getGroupes()) {
                        i++;
                %>
                        <%=groupe.getNom()%>
                        <%=i<module.getGroupes().size() ? ", " : ""%>
                <%}%>
            </td>
            <td>
                <div class="btn-group">
                    <a href="<%=application.getContextPath()%>/do/supprimer-module?id=<%=module.getId()%>" class="btn btn-danger">Supprimer</a>
                    <a href="<%=application.getContextPath()%>/do/editer-module?id=<%=module.getId()%>" class="btn btn-primary">Modifier</a>
                </div>
            </td>
        </tr>
        <%
            }
        %>
    </table>

    <% } else {%>

    <p class="ml-4">Aucun module</p>
    <%}%>

</div>
