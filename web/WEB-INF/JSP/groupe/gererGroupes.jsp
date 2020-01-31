<%@ page import="app.data.Groupe" %>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<jsp:useBean id="groupes" type="java.util.List<app.data.Groupe>" scope="request"/>

<div>

    <a href="<%=application.getContextPath()%>/do/nouveau-groupe" class="btn btn-success ml-4">Créer un groupe</a>

    <h3 class="ml-4">Gérer les groupes</h3>

    <table class="table table-striped table-dark">
        <tr>
            <th>Groupe</th>
            <th>Nombre d'étudiants</th>
            <th>Editer</th>
        </tr>
        <% for (Groupe groupe : groupes) { %>
        <tr>
            <td><%=groupe.getNom()%></td>
            <td><%=groupe.getEtudiants().size()%></td>
            <td>
                <div class="btn-group" role="group">
                    <a href="<%=application.getContextPath()%>/do/supprimer-groupe?id=<%=groupe.getId()%>" class="btn btn-danger">Supprimer</a>
                    <a href="<%=application.getContextPath()%>/do/modifier-groupe?id=<%=groupe.getId()%>" class="btn btn-primary">Modifier</a>
                </div>
            </td>
        </tr>
        <% } %>
    </table>

</div>