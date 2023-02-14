package univ.fac.master.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import univ.fac.master.entities.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
	@Query("SELECT e FROM Etudiant e WHERE e.email = ?1")
	public Etudiant findByEmail(String email);
	@Query("SELECT e FROM Etudiant e WHERE e.verificationCode = ?1")
    public Etudiant findByVerificationCode(String code);
	
	@Query("UPDATE Etudiant e SET e.enabled = true WHERE e.id = ?1")
	@Modifying
	public void enabled(Integer id);
}
