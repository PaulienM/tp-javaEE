<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>


<jsp:useBean id="etudiant" class="app.data.Etudiant" scope="request"/>
<jsp:useBean id="nbAbsences" type="java.lang.Integer" scope="request"/>
<jsp:useBean id="note" type="java.lang.Float" scope="request"/>

<div>

    <h3 class="ml-4">Fiche de <%=etudiant.getPrenom()%> <%=etudiant.getNom()%>
    </h3>

    <table class="table table-striped table-dark">
        <tr>
            <td>Groupe</td>
            <td><%=etudiant.getGroupe() != null ? etudiant.getGroupe().getNom() : "-"%>
            </td>
        </tr>
        <tr>
            <td>Moyenne Generale</td>
            <td><%=note%>
            </td>
        </tr>
        <tr>
            <td>Absences</td>
            <td><%=nbAbsences%>
            </td>
        </tr>
    </table>

    <form class="container" action="<%= application.getContextPath()%>/do/modifier-moyenne?id=<%=etudiant.getId()%>" method="post">
        <div class="form-group">
            <label for="moyenne">Moyenne générale</label>
            <input type="number" class="form-control" id="moyenne" name="moyenne" step="any" value="<%=note%>" max="20" min="0">
            <small id="moyenneHelp" class="form-text text-muted">Modifiez la moyenne de l'étudiant</small>
        </div>
        <button type="submit" class="btn btn-primary">Valider</button>
    </form>

</div>