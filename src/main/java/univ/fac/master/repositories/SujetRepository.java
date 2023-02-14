package univ.fac.master.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import univ.fac.master.entities.Encadrant;
import univ.fac.master.entities.Sujet;


public interface SujetRepository extends JpaRepository<Sujet, Long> {

	//4
	List<Sujet> findByEncadrant(Encadrant e);

}
