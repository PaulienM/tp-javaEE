package app.data;

public class Etudiant {
	
	private Integer id;
	private String prenom;
	private String nom;
	private int nbAbsences;
	private int moyenneGenerale;
	private String groupe;
	
	public Etudiant() {
		super();
	}

	public Etudiant(Integer id, String prenom, String nom, String groupe) {
		super();
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.nbAbsences = 0;
		this.moyenneGenerale = 20;
		this.groupe = groupe;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNbAbsences() {
		return nbAbsences;
	}

	public void setNbAbsences(int nbAbsences) {
		this.nbAbsences = nbAbsences;
	}

	public int getMoyenneGenerale() {
		return moyenneGenerale;
	}

	public void setMoyenneGenerale(int moyenneGenerale) {
		this.moyenneGenerale = moyenneGenerale;
	}

	public String getGroupe() {
		return groupe;
	}

	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}
}
