package univ.fac.master.entities;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomEncadrantDetails implements UserDetails {

private Encadrant encadrant;
	
	public CustomEncadrantDetails(Encadrant encadrant) {
		this.encadrant = encadrant;
	}
	@Override
	public boolean isEnabled() {
	    return encadrant.isEnabled();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return encadrant.getPassword();
	}

	@Override
	public String getUsername() {
		return encadrant.getEmail();
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
		return encadrant.getPrenom() + " " + encadrant.getNom();
	}

}
