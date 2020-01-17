package app.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestionFactory {

	// AJOUT des groupes
	private static final String MIAM = "MIAM";
	private static final String SIMO = "SIMO";
	private static final String MESSI = "MESSI";

	//
	private static final List<String> listGroupes = Arrays.asList(MIAM, SIMO, MESSI);
	private static final HashMap<Integer, Etudiant> listEtudiants = intializeListEtudiants();
	private static final HashMap<Integer, Integer> listEtudiantAbsence = intializelistEtudiantAbsence();
	private static final HashMap<Integer, Integer> listEtudiantNote = intializelistEtudiantNote();

	// Initialisation des étudiants
	private static HashMap<Integer, Etudiant> intializeListEtudiants() {

		// Création des étudiants
		Etudiant etu1 = new Etudiant(0, "Brunet-Manquat", "Francis", MIAM);
		Etudiant etu2 = new Etudiant(1, "Martin", "Philippe", SIMO);

		HashMap<Integer, Etudiant> listEtudiantsTemp = new HashMap<>();
		listEtudiantsTemp.put(etu1.getId(), etu1);
		listEtudiantsTemp.put(etu2.getId(), etu2);

		return listEtudiantsTemp;
	}

	// Initialisation des absences
	private static final HashMap<Integer, Integer> intializelistEtudiantAbsence() {

		// Association etudiant id -> absences
		HashMap<Integer, Integer> listEtudiantAbsenceTemp = new HashMap<>();
		listEtudiantAbsenceTemp.put(listEtudiants.get(0).getId(), 0);
		listEtudiantAbsenceTemp.put(listEtudiants.get(1).getId(), 7);

		return listEtudiantAbsenceTemp;
	}

	// Initialisation des notes
	private static final HashMap<Integer, Integer> intializelistEtudiantNote() {

		// Association etudiant id -> absences
		HashMap<Integer, Integer> listEtudiantNoteTemp = new HashMap<>();
		listEtudiantNoteTemp.put(listEtudiants.get(0).getId(), 20);
		listEtudiantNoteTemp.put(listEtudiants.get(1).getId(), 15);

		return listEtudiantNoteTemp;
	}

	// Retourne l'ensemble des groupes
	public static List<String> getGroupes() {
		return listGroupes;
	}

	// Donne l'ensemble des etudiants
	public static Collection<Etudiant> getEtudiants() {
		return listEtudiants.values();
	}

	// Retourne un étudiant à l'aide de son id
	// Donne l'ensemble des etudiants
	public static Etudiant getEtudiantById(int id) {
		return listEtudiants.get(id);
	}

	// Donne le nombre d'absences d'un etudiant à l'aide de son id
	public static Integer getAbsencesByEtudiantId(int id) {
		return listEtudiantAbsence.get(id);
	}

	// Donne le nombre d'absences d'un etudiant à l'aide de son id
	public static void setAbsencesByEtudiantId(int id, int nbabsences) {
		listEtudiantAbsence.put(id, nbabsences);
	}

	// Donne la note d'un etudiant à l'aide de son id
	public static Integer getNoteByEtudiantId(int id) {
		return listEtudiantNote.get(id);
	}

	// Donne les notes des étudiants
	public static Map<Etudiant, Integer> getNoteByEtudiants(Collection<Etudiant> etudiants) {

		HashMap<Etudiant, Integer> temp = new HashMap<>();
		for (Etudiant etudiant : etudiants) {
			temp.put(etudiant, getNoteByEtudiantId(etudiant.getId()));
		}

		return temp;
	}

	// Donne les absences des étudiants
	public static Map<Etudiant, Integer> getAbsencesByEtudiants(Collection<Etudiant> etudiants) {

		HashMap<Etudiant, Integer> temp = new HashMap<>();
		for (Etudiant etudiant : etudiants) {
			temp.put(etudiant, getAbsencesByEtudiantId(etudiant.getId()));
		}

		return temp;
	}

	// Retourne l'ensemble des etudiants d'un groupe donné
	public static Collection<Etudiant> getEtudiantsByGroupe(String groupe) {
		ArrayList<Etudiant> listEtudiantsParGroupe = new ArrayList<Etudiant>();
		for (Etudiant etudiant : getEtudiants()) {
			if (etudiant.getGroupe().equals(groupe)) {
				listEtudiantsParGroupe.add(etudiant);
			}
		}
		return listEtudiantsParGroupe;
	}
}
