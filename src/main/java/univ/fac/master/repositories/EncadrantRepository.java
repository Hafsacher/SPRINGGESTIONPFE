package univ.fac.master.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import univ.fac.master.entities.Encadrant;

public interface EncadrantRepository extends JpaRepository<Encadrant, Long> {
	@Query("SELECT e FROM Encadrant e WHERE e.email = ?1")
	public Encadrant findByEmail(String email);
	@Query("SELECT e FROM Encadrant e WHERE e.verificationCode = ?1")
    public Encadrant findByVerificationCode(String code);
	
	@Query("UPDATE Encadrant e SET e.enabled = true WHERE e.id = ?1")
	@Modifying
	public void enabled(Integer id);
}