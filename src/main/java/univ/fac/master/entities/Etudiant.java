package univ.fac.master.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "etudiants")
public class Etudiant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true, length = 45)
	private String email;
	
	@Column(nullable = false, length = 64)
	private String password;
	
	@Column(name = "nom", nullable = false, length = 20)
	private String nom;
	
	@Column(name = "prenom", nullable = false, length = 20)
	private String prenom;
	
	@Column(name = "codeApogee", nullable = false, length = 20)
	private String codeApogee;
	
	@Column(name = "filiere", nullable = false, length = 20)
	private String filiere;
	
	 @Column(name = "verification_code", length = 64)
	 private String verificationCode;
	     
	 private boolean enabled;

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getCodeApogee() {
		return codeApogee;
	}

	public void setCodeApogee(String codeApogee) {
		this.codeApogee = codeApogee;
	}

	public String getFiliere() {
		return filiere;
	}

	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}
	
	public Etudiant(String nom, String prenom,String codeApogee,String filiere,String email,String password) {
		super();
		
		        this.nom=nom;
		        this.prenom=prenom;
		        this.codeApogee=codeApogee;
		        this.filiere=filiere;
		        this.email=email;
		        this.password=password;
	}
	public Etudiant() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
	
	
}
