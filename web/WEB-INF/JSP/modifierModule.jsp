<%@ page import="app.data.Groupe" %><%--
  Created by IntelliJ IDEA.
  User: marangep
  Date: 29/01/2020
  Time: 09:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="groupes" type="java.util.List<app.data.Groupe>" scope="request" />
<jsp:useBean id="module" type="app.data.Module" scope="request" />

<link href="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/css/select2.min.css" rel="stylesheet" />

<div>

    <h3 class="ml-4">Modification du module <%= module.getNom()%></h3>

    <div class="container">
        <form method="post">
            <div class="form-group">
                <label for="nom">Nom</label>
                <input type="text" class="form-control" id="nom" name="nom" value="<%=module.getNom()%>">
            </div>
            <div class="form-group">
                <label for="groupe">Groupes</label>
                <select class="form-control" name="groupe" id="groupe" multiple>
                    <%for (Groupe groupe : groupes) {%>
                    <option <%=module.getGroupes().contains(groupe)?"selected":""%> value="<%=groupe.getId()%>"><%=groupe.getNom()%></option>
                    <%}%>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Enregistrer</button>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/select2.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#groupe').select2();
        });
    </script>

</div>