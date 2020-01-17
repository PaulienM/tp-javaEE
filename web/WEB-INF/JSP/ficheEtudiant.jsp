<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>


<jsp:useBean id="etudiant" class="app.data.Etudiant" scope="request"/>
<jsp:useBean id="nbAbsences" type="java.lang.Integer" scope="request"/>
<jsp:useBean id="note" type="java.lang.Integer" scope="request"/>

<div>

    <h3 class="ml-4">Fiche de <%=etudiant.getPrenom()%> <%=etudiant.getNom()%>
    </h3>

    <table class="table table-striped table-dark">
        <tr>
            <td>Groupe</td>
            <td><%=etudiant.getGroupe().getNom()%>
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

</div>