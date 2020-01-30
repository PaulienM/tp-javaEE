<%@ page import="app.data.Groupe" %><%--
  Created by IntelliJ IDEA.
  User: marangep
  Date: 29/01/2020
  Time: 09:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="groupes" type="java.util.List<app.data.Groupe>" scope="request" />
<jsp:useBean id="etudiant" type="app.data.Etudiant" scope="request" />

<div>

    <h3 class="ml-4">Modification de <%= etudiant.getPrenom() + " " + etudiant.getNom()%></h3>

    <div class="container">
        <form method="post">
            <div class="form-group">
                <label for="prenom">Prenom</label>
                <input type="text" class="form-control" id="prenom" name="prenom" value="<%=etudiant.getPrenom()%>">
            </div>
            <div class="form-group">
                <label for="nom">Nom</label>
                <input type="text" class="form-control" id="nom" name="nom" value="<%=etudiant.getNom()%>">
            </div>
            <div class="form-group">
                <label for="groupe">Groupe</label>
                <select class="form-control" name="groupe" id="groupe">
                <%for (Groupe groupe : groupes) {%>
                    <option value="<%=groupe.getId()%>" <%=groupe == etudiant.getGroupe() ? "selected" : false%>><%=groupe.getNom()%></option>
                <%}%>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Enregistrer</button>
        </form>
    </div>

</div>