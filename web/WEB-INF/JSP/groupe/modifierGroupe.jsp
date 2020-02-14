<%@ page import="app.data.Module" %><%--
  Created by IntelliJ IDEA.
  User: marangep
  Date: 31/01/2020
  Time: 08:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="groupe" type="app.data.Groupe" scope="request" />

<div>

    <h3 class="ml-4">Modification de <%= groupe.getNom()%></h3>

    <div class="container">
        <form method="post">
            <div class="form-group">
                <label for="nom">Nom du groupe</label>
                <input type="text" class="form-control" id="nom" name="nom" value="<%=groupe.getNom()%>">
            </div>
            <button type="submit" class="btn btn-primary">Enregistrer</button>
        </form>

        <h3>Modules</h3>
        <ul>
            <% for (Module module : groupe.getModules()) { %>
            <li><%=module.getNom()%></li>
            <%}%>
        </ul>
    </div>

</div>