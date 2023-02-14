package univ.fac.master.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import univ.fac.master.entities.CustomEncadrantDetails;
import univ.fac.master.entities.Encadrant;
import univ.fac.master.repositories.EncadrantRepository;


public class CustomEncadrantDetailsService implements UserDetailsService {

	@Autowired
	private EncadrantRepository er;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Encadrant encadrant = er.findByEmail(email);
		if (encadrant == null) {
			throw new UsernameNotFoundException("Encadrant not found");
		}
		return new CustomEncadrantDetails(encadrant);
	}
}