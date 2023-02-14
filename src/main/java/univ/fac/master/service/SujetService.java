package univ.fac.master.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import univ.fac.master.entities.Encadrant;
import univ.fac.master.entities.Sujet;
import univ.fac.master.repositories.EncadrantRepository;
import univ.fac.master.repositories.SujetRepository;
@Service
@Primary
public class SujetService {
	@Autowired
	SujetRepository sr;
	
	@Autowired
	EncadrantRepository er;
	
	public Sujet addSujet(Sujet s, Long idEn) {
		Encadrant en =er.findById(idEn).get();
		s.setEncadrant(en);
		return sr.save(s);
	}
	
	public List<Sujet> getAllSujets() {
		return sr.findAll();
	}
	public Optional<Sujet> getSujetById(Long id) {
		return sr.findById(id);
	}
	
	
	public void deleteSujet(Long id) {
	
		sr.deleteById(id);
		
	}
	public Sujet updateSujet(Sujet s) {
		return sr.save(s);
	}
	
	//4
	public List<Sujet>getSujetByEncadrant(Long idEn){
		Encadrant e = new Encadrant();
		e.setId(idEn);
		return sr.findByEncadrant(e);
	}
	
}