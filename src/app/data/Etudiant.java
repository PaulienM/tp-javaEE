package app.data;

import javax.persistence.*;

@Entity
public class Etudiant {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable=false)
	private String prenom;

	@Column(nullable=false)
	private String nom;

	private int nbAbsences;

	@ManyToOne
	private Groupe groupe;
	private int moyenneGenerale;

	public Etudiant() {
		super();
	}

	public Etudiant(Integer id, String prenom, String nom, Groupe groupe) {
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

	public void ajouterAbsence() {
		this.nbAbsences++;
	}

	public void enleverAbsence() {
		this.nbAbsences--;
	}

	public int getMoyenneGenerale() {
		return moyenneGenerale;
	}

	public void setMoyenneGenerale(int moyenneGenerale) {
		if ((moyenneGenerale >= 0) && (moyenneGenerale <= 20)) {
			this.moyenneGenerale = moyenneGenerale;
		}
	}

	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}
}
