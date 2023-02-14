package univ.fac.master.entities;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomEtudiantDetails implements UserDetails {

	private Etudiant etudiant;
	
	public CustomEtudiantDetails(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
	@Override
	public boolean isEnabled() {
	    return etudiant.isEnabled();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return etudiant.getPassword();
	}

	@Override
	public String getUsername() {
		return etudiant.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/*@Override
	public boolean isEnabled() {
		return true;
	}*/
	
	public String getFullName() {
		return etudiant.getPrenom() + " " + etudiant.getNom();
	}
	
	public Long getId() {
		return etudiant.getId();
	}

}
