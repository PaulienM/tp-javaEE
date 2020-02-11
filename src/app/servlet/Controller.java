/**
 *
 */
package app.servlet;

/**
 * @author hb
 *
 */

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.data.*;
import app.data.Module;

@SuppressWarnings("serial")
public class Controller extends HttpServlet {

    // private String urlTest;
    private String urlGestionTemplate;
    private String urlAccueil;
    private String urlListeEtudiants;
    private String urlEtudiant;
    private String urlConsultationAbsences;
    private String urlConsultationNotes;
    private String urlNouvelEtudiant;
    private String urlModifierEtudiant;
    private String urlGererGroupes;
    private String urlNouveauGroupe;
    private String urlModifierGroupe;


    // INIT
    public void init() throws ServletException {
        GestionFactory.open();
        // Récupération des URLs en paramètre du web.xml
        urlGestionTemplate = getServletConfig().getInitParameter("urlGestionTemplate");
        urlAccueil = getServletConfig().getInitParameter("urlAccueil");
        urlListeEtudiants = getServletConfig().getInitParameter("urlListeEtudiants");
        urlEtudiant = getServletConfig().getInitParameter("urlEtudiant");
        urlConsultationAbsences = getServletConfig().getInitParameter("urlConsultationAbsences");
        urlConsultationNotes = getServletConfig().getInitParameter("urlConsultationNotes");
        urlNouvelEtudiant = getServletConfig().getInitParameter("urlNouvelEtudiant");
        urlModifierEtudiant = getServletConfig().getInitParameter("urlModifierEtudiant");
        urlGererGroupes = getServletConfig().getInitParameter("urlGererGroupes");
        urlNouveauGroupe = getServletConfig().getInitParameter("urlNouveauGroupe");
        urlModifierGroupe = getServletConfig().getInitParameter("urlModifierGroupe");

        if ((GroupeDAO.getAll().size() == 0) && (EtudiantDAO.getAll().size() == 0)) {

            // Creation des groupes
            Groupe MIAM = GroupeDAO.create("miam");
            Groupe SIMO = GroupeDAO.create("SIMO");
            Groupe MESSI = GroupeDAO.create("MESSI");

            // Creation des étudiants
            EtudiantDAO.create("Francis", "Brunet-Manquat", MIAM);
            EtudiantDAO.create("Philippe", "Martin", MIAM);
            EtudiantDAO.create("Mario", "Cortes-Cornax", MIAM);
            EtudiantDAO.create("Françoise", "Coat", SIMO);
            EtudiantDAO.create("Laurent", "Bonnaud", MESSI);
            EtudiantDAO.create("Sébastien", "Bourdon", MESSI);
            EtudiantDAO.create("Mathieu", "Gatumel", SIMO);

            // Creation des groupes
            Module MI1 = ModuleDAO.create("MI1");
            Module MI4 = ModuleDAO.create("MI4");

            // Liés groupe et module
            MIAM.addModule(MI1);
            MIAM.addModule(MI4);
            SIMO.addModule(MI1);

            MI1.addGroupe(MIAM);
            MI4.addGroupe(MIAM);
            MI1.addGroupe(SIMO);

            GroupeDAO.update(MIAM);
            GroupeDAO.update(SIMO);

            ModuleDAO.update(MI1);
            ModuleDAO.update(MI4);

        }
    }

    @Override
    public void destroy() {
        super.destroy();
        GestionFactory.close();
    }

    // POST
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        // On récupère le path
        String action = request.getPathInfo();
        if (action.equals("/modifier-moyenne")) {
            doChangerMoyenne(request, response);
        } else {
            // on passe la main au GET
            doGet(request, response);
        }
    }

    // GET
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        // On récupère le path
        String action = request.getPathInfo();
        if (action == null) {
            action = "/accueil";
        }

        // Exécution action
        if (action.equals("/accueil")) {
            doAcceuil(request, response);
        } else if (action.equals("/listeEtudiants")) {
            doListeEtudiants(request, response);

        } else if (action.equals("/etudiant")) {
            doEtudiant(request, response);

        } else if (action.equals("/consultationAbsences")) {

            doConsultationAbsences(request, response);
        } else if (action.equals("/consultationNotes")) {
            doConsultationNotes(request, response);
        } else if (action.equals("/ajouter-absence")) {

            doAjouterAbsence(request, response);
        } else if (action.equals("/enlever-absence")) {

            doEnleverAbsence(request, response);
        } else if (action.equals("/creer-etudiant")) {

            doNouvelEtudiant(request, response);
        } else if (action.equals("/modifier-etudiant")) {

            doModifierEtudiant(request, response);
        } else if (action.equals("/supprimer-etudiant")) {

            doSupprimerEtudiant(request, response);
        } else if (action.equals("/gerer-groupes")){

            doGererGroupes(request, response);
        } else if (action.equals("/nouveau-groupe")){

            doNouveauGroupe(request, response);
        } else if (action.equals("/modifier-groupe")){

            doModifierGroupe(request, response);
        } else if (action.equals("/supprimer-groupe")){

            doSupprimerGroupe(request, response);
        } else {
            // Autres cas
            doAcceuil(request, response);
        }
    }

    // /////////////////////// ACCUEIL
    //
    private void doAcceuil(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {

        // Inclusion du content dans le template
        request.setAttribute("content", urlAccueil);
        loadJSP(urlGestionTemplate, request, response);
    }

    // /////////////////////// Liste des étudiants
    //
    private void doListeEtudiants(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {

        // Récupérer les étudiants
        Collection<Etudiant> listeEtudiants;
        String idGroupe = request.getParameter("idGroupe");
        if (idGroupe != null) {
            Groupe groupe = GroupeDAO.retrieveById(Integer.parseInt(idGroupe));
            listeEtudiants = EtudiantDAO.getAllByGroupe(groupe);
        } else {
            listeEtudiants = EtudiantDAO.getAll();
        }

        // Mettre les étudians en attibuts de request
        request.setAttribute("listeEtudiants", listeEtudiants);

        //
        request.setAttribute("content", urlListeEtudiants);
        loadJSP(urlGestionTemplate, request, response);
    }

    ///////////////////////// Détails étudiant
    //
    private void doEtudiant(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupérer le parametre id, l'objet Etudiant associé, le nombre d'absences et la moyenne
        int idEtudiant = Integer.parseInt(request.getParameter("id"));
        Etudiant etudiant = EtudiantDAO.retrieveById(idEtudiant);
        int nbAbsences = etudiant.getNbAbsences();
        float note = etudiant.getMoyenneGenerale();

        // Mettre l'objet étudiant en attribut pour affichage par la vue
        // correspondant
        request.setAttribute("etudiant", etudiant);
        request.setAttribute("nbAbsences", nbAbsences);
        request.setAttribute("note", note);

        //
        request.setAttribute("content", urlEtudiant);
        loadJSP(urlGestionTemplate, request, response);
    }


    // /////////////////////// CONSULTATION NOTES
    //
    private void doConsultationNotes(HttpServletRequest request,
                                     HttpServletResponse response) throws ServletException, IOException {

        // Récupérer les étudiants
        Collection<Etudiant> listeEtudiants;
        String idGroupe = request.getParameter("idGroupe");
        if (idGroupe != null) {
            Groupe groupe = GroupeDAO.retrieveById(Integer.parseInt(idGroupe));
            listeEtudiants = EtudiantDAO.getAllByGroupe(groupe);
        } else {
            listeEtudiants = EtudiantDAO.getAll();
        }

        // Récupérer l'association Etudiant/Note pour affichage
        Map<Etudiant, Float> listeNotesEtudiants = new HashMap<>();
        for (Etudiant etudiant : listeEtudiants) {
            listeNotesEtudiants.put(etudiant, etudiant.getMoyenneGenerale());
        }

        //
        request.setAttribute("listeNotesEtudiants", listeNotesEtudiants);
        //Pour les avoir dans l'ordre
        request.setAttribute("listeEtudiants", listeEtudiants);

        //
        request.setAttribute("content", urlConsultationNotes);
        loadJSP(urlGestionTemplate, request, response);
    }

    // /////////////////////// CONSULTATION ABSENCES
    //
    private void doConsultationAbsences(HttpServletRequest request,
                                        HttpServletResponse response) throws ServletException, IOException {

        // Récupérer les étudiants
        Collection<Etudiant> listeEtudiants;
        String idGroupe = request.getParameter("idGroupe");
        if (idGroupe != null) {
            Groupe groupe = GroupeDAO.retrieveById(Integer.parseInt(idGroupe));
            listeEtudiants = EtudiantDAO.getAllByGroupe(groupe);
        } else {
            listeEtudiants = EtudiantDAO.getAll();
        }

        // Récupérer l'association Etudiant/Note pour affichage
        Map<Etudiant, Integer> listeAbsencesEtudiants = new HashMap<>();
        for (Etudiant etudiant : listeEtudiants) {
            listeAbsencesEtudiants.put(etudiant, etudiant.getNbAbsences());
        }

        //
        request.setAttribute("listeAbsencesEtudiants", listeAbsencesEtudiants);
        //Pour les avoir dans l'ordre
        request.setAttribute("listeEtudiants", listeEtudiants);
        //
        request.setAttribute("content", urlConsultationAbsences);
        loadJSP(urlGestionTemplate, request, response);
    }


    private void doAjouterAbsence(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {
        //On récupère l'étudiant
        int idEtudiant = Integer.parseInt(request.getParameter("id"));
        Etudiant etudiant = EtudiantDAO.retrieveById(idEtudiant);

        etudiant.ajouterAbsence();

        EtudiantDAO.update(etudiant);

        response.sendRedirect(request.getContextPath() + "/do/consultationAbsences");
    }

    private void doEnleverAbsence(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {
        //On récupère l'étudiant
        int idEtudiant = Integer.parseInt(request.getParameter("id"));
        Etudiant etudiant = EtudiantDAO.retrieveById(idEtudiant);

        etudiant.enleverAbsence();

        EtudiantDAO.update(etudiant);

        response.sendRedirect(request.getContextPath() + "/do/consultationAbsences");
    }

    private void doChangerMoyenne(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {
        int idEtudiant = Integer.parseInt(request.getParameter("id"));
        Etudiant etudiant = EtudiantDAO.retrieveById(idEtudiant);

        float moyenne = Float.parseFloat(request.getParameter("moyenne"));

        etudiant.setMoyenneGenerale(moyenne);

        EtudiantDAO.update(etudiant);

        response.sendRedirect(request.getContextPath() + "/do/etudiant?id=" + etudiant.getId());
    }

    private void doNouvelEtudiant(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String groupeId = request.getParameter("groupe");

        if (nom != null && prenom != null && groupeId != null) {
            Groupe groupe = GroupeDAO.retrieveById(Integer.parseInt(groupeId));
            EtudiantDAO.create(prenom, nom, groupe);

            response.sendRedirect(request.getContextPath() + "/do/listeEtudiants");
            return;
        }

        List<Groupe> groupes = GroupeDAO.getAll();

        request.setAttribute("groupes", groupes);

        request.setAttribute("content", urlNouvelEtudiant);
        loadJSP(urlGestionTemplate, request, response);
    }

    private void doModifierEtudiant(HttpServletRequest request,
                                    HttpServletResponse response) throws ServletException, IOException {

        Etudiant etudiant = EtudiantDAO.retrieveById(Integer.parseInt(request.getParameter("id")));
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String groupeId = request.getParameter("groupe");

        if (nom != null && prenom != null && groupeId != null) {
            Groupe groupe = GroupeDAO.retrieveById(Integer.parseInt(groupeId));

            etudiant.setGroupe(groupe);
            etudiant.setPrenom(prenom);
            etudiant.setNom(nom);

            EtudiantDAO.update(etudiant);

            response.sendRedirect(request.getContextPath() + "/do/listeEtudiants");
            return;
        }
        List<Groupe> groupes = GroupeDAO.getAll();

        request.setAttribute("groupes", groupes);
        request.setAttribute("etudiant", etudiant);
        request.setAttribute("content", urlModifierEtudiant);
        loadJSP(urlGestionTemplate, request, response);
    }

    private void doSupprimerEtudiant(HttpServletRequest request,
                                     HttpServletResponse response) throws ServletException, IOException {
        Etudiant etudiant = EtudiantDAO.retrieveById(Integer.parseInt(request.getParameter("id")));
        EtudiantDAO.remove(etudiant);

        response.sendRedirect(request.getContextPath() + "/do/listeEtudiants");
    }

    private void doGererGroupes(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {
        List<Groupe> groupes = GroupeDAO.getAll();

        request.setAttribute("groupes", groupes);
        request.setAttribute("content", urlGererGroupes);

        loadJSP(urlGestionTemplate, request, response);
    }

    private void doNouveauGroupe(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");

        if (nom != null) {
            GroupeDAO.create(nom);

            response.sendRedirect(request.getContextPath() + "/do/gerer-groupes");
            return;
        }

        request.setAttribute("content", urlNouveauGroupe);
        loadJSP(urlGestionTemplate, request, response);
    }

    private void doModifierGroupe(HttpServletRequest request,
                                    HttpServletResponse response) throws ServletException, IOException {

        Groupe groupe = GroupeDAO.retrieveById(Integer.parseInt(request.getParameter("id")));
        String nom = request.getParameter("nom");

        if (nom != null) {
            groupe.setNom(nom);

            GroupeDAO.update(groupe);

            response.sendRedirect(request.getContextPath() + "/do/gerer-groupes");
            return;
        }

        request.setAttribute("groupe", groupe);
        request.setAttribute("content", urlModifierGroupe);
        loadJSP(urlGestionTemplate, request, response);
    }

    /**
     * Route :
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void doSupprimerGroupe(HttpServletRequest request,
                                     HttpServletResponse response) throws ServletException, IOException {
        Groupe groupe = GroupeDAO.retrieveById(Integer.parseInt(request.getParameter("id")));
        GroupeDAO.remove(groupe);

        response.sendRedirect(request.getContextPath() + "/do/gerer-groupes");
    }

    /**
     * Charge la JSP indiquée en paramètre
     *
     * @param url
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void loadJSP(String url, HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

        // L'interface RequestDispatcher permet de transférer le contrôle à une
        // autre servlet
        // Deux méthodes possibles :
        // - forward() : donne le contrôle à une autre servlet. Annule le flux
        // de sortie de la servlet courante
        // - include() : inclus dynamiquement une autre servlet
        // + le contrôle est donné à une autre servlet puis revient à la servlet
        // courante (sorte d'appel de fonction).
        // + Le flux de sortie n'est pas supprimé et les deux se cumulent

        ServletContext sc = getServletContext();
        System.out.println(sc.getContextPath());
        RequestDispatcher rd = sc.getRequestDispatcher(url);
        rd.forward(request, response);
    }

}
