package univ.fac.master.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import univ.fac.master.entities.Encadrant;

import univ.fac.master.entities.Sujet;
import univ.fac.master.service.SujetService;

@RestController
@CrossOrigin(origins ="http://localhost:4200/")
public class SujetController {
	
	@Autowired
	SujetService ss;
	
	@GetMapping("api/sujet")
	public List<Sujet> getAllSujets(){
		List<Sujet>  s =ss.getAllSujets();
		System.out.print(s);
		return ss.getAllSujets();
	}
	@GetMapping("api/sujet/{id}")
	public Optional<Sujet> getSujetById(@PathVariable Long id){
		return ss.getSujetById(id);
	}
	@PostMapping("api/sujet/{idEn}")
	public Sujet addSujet(@RequestBody Sujet s , @PathVariable Long idEn ){
	
		return ss.addSujet(s,idEn);
	}
	@DeleteMapping("api/sujet/{id}")
	public void deleteSujet(@PathVariable Long id){
		ss.deleteSujet(id);
	}
	@PutMapping("api/sujet")
	public Sujet updateSujet(@RequestBody Sujet s){
		return ss.updateSujet(s);
	}
	///q6
		@GetMapping("sujet/{id}/encadrant")
		public Encadrant sujetEncadrant(@PathVariable Long id){
			Encadrant e=ss.getSujetById(id).get().getEncadrant();
			//System.out.print(e);
				return  ss.getSujetById(id).get().getEncadrant();
		}
		//4
		@GetMapping("Sujet/{idEn}/encadrant")
			public List<Sujet> getSujetByEncadrant (@PathVariable Long idEn){
				return ss.getSujetByEncadrant(idEn);
			}
			
		

}
