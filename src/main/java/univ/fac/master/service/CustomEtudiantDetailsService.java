package univ.fac.master.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import univ.fac.master.entities.AuthenticationBean;
import univ.fac.master.entities.CustomEtudiantDetails;
import univ.fac.master.entities.Etudiant;
import univ.fac.master.repositories.EtudiantRepository;

public class CustomEtudiantDetailsService implements UserDetailsService {

	@Autowired
	private EtudiantRepository er;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Etudiant etudiant = er.findByEmail(email);
		
		if (etudiant == null) {
			throw new UsernameNotFoundException("Etudiant not found");
		}
		
		return new CustomEtudiantDetails(etudiant);
	}

}
