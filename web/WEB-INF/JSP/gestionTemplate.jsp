<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="content" class="java.lang.String" scope="request"/>

<html>
<head>
    <title><%= application.getInitParameter("title")%>
    </title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <a class="navbar-brand" href="<%= application.getContextPath()%>/do/accueil">Accueil</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
            aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <a class="nav-item nav-link" href="<%= application.getContextPath()%>/do/listeEtudiants">Les étudiants</a>
            <a class="nav-item nav-link" href="<%= application.getContextPath()%>/do/consultationNotes">Consulter les
                notes</a>
            <a class="nav-item nav-link" href="<%= application.getContextPath()%>/do/consultationAbsences">Consulter les
                absences</a>
            <a class="nav-item nav-link" href="<%= application.getContextPath()%>/do/gerer-groupes">Gérer les
                groupes</a>
        </div>
    </div>
</nav>

<div>
    <h1 class="mt-4 ml-2"><%= application.getInitParameter("title")%>
    </h1>
</div>

<jsp:include page="<%=content%>"/>

</body>
</html>
