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
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.data.Etudiant;
import app.data.GestionFactory;

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
        // Récupération des URLs en paramètre du web.xml
        urlGestionTemplate = getServletConfig().getInitParameter("urlGestionTemplate");
        urlAccueil = getServletConfig().getInitParameter("urlAccueil");
        urlListeEtudiants = getServletConfig().getInitParameter("urlListeEtudiants");
        urlEtudiant = getServletConfig().getInitParameter("urlEtudiant");
        urlConsultationAbsences = getServletConfig().getInitParameter("urlConsultationAbsences");
        urlConsultationNotes = getServletConfig().getInitParameter("urlConsultationNotes");
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
        Collection<Etudiant> listeEtudiants = GestionFactory.getEtudiants();

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
        Etudiant etudiant = GestionFactory.getEtudiantById(idEtudiant);
        int nbAbsences = GestionFactory.getAbsencesByEtudiantId(idEtudiant);
        int note = GestionFactory.getNoteByEtudiantId(idEtudiant);

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
        Collection<Etudiant> listeEtudiants = GestionFactory.getEtudiants();

        // Récupérer l'association Etudiant/Note pour affichage
        Map<Etudiant, Integer> listeNotesEtudiants = GestionFactory.getNoteByEtudiants(listeEtudiants);

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
        Collection<Etudiant> listeEtudiants = GestionFactory.getEtudiants();

        // Récupérer l'association Etudiant/Note pour affichage
        Map<Etudiant, Integer> listeAbsencesEtudiants = GestionFactory.getAbsencesByEtudiants(listeEtudiants);

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
