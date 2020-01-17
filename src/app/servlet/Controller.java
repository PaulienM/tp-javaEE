/**
 *
 */
package app.servlet;

/**
 * @author hb
 *
 */
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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
            //MIAM.addModule(MI1);
            //MIAM.addModule(MI4);
            //SIMO.addModule(MI1);

            MI1.addGroupe(MIAM);
            MI4.addGroupe(MIAM);
            MI1.addGroupe(SIMO);

            //GroupeDAO.update(MIAM);
            //GroupeDAO.update(SIMO);

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
        // on passe la main au GET
        doGet(request, response);
    }

    // GET
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

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
        Collection<Etudiant> listeEtudiants = EtudiantDAO.getAll();

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
        int note = etudiant.getMoyenneGenerale();

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

        // Récupérer les étudiants en fonction du filtre groupe
        Collection<Etudiant> listeEtudiants = EtudiantDAO.getAll();

        // Récupérer l'association Etudiant/Note pour affichage
        Map<Etudiant, Integer> listeNotesEtudiants = new HashMap<>();
        for (Etudiant etudiant: listeEtudiants) {
            listeNotesEtudiants.put(etudiant, etudiant.getMoyenneGenerale());
        }

        //
        request.setAttribute("listeNotesEtudiants", listeNotesEtudiants);

        //
        request.setAttribute("content", urlConsultationNotes);
        loadJSP(urlGestionTemplate, request, response);
    }

    // /////////////////////// CONSULTATION ABSENCES
    //
    private void doConsultationAbsences(HttpServletRequest request,
                                        HttpServletResponse response) throws ServletException, IOException {

        // Récupérer les étudiants
        Collection<Etudiant> listeEtudiants = EtudiantDAO.getAll();

        // Récupérer l'association Etudiant/Note pour affichage
        Map<Etudiant, Integer> listeAbsencesEtudiants = new HashMap<>();
        for (Etudiant etudiant: listeEtudiants) {
            listeAbsencesEtudiants.put(etudiant, etudiant.getNbAbsences());
        }

        //
        request.setAttribute("listeAbsencesEtudiants", listeAbsencesEtudiants);

        //
        request.setAttribute("content", urlConsultationAbsences);
        loadJSP(urlGestionTemplate, request, response);
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
