package univ.fac.master.entities;

public class AuthenticationBean {

    private String message;
    private Long id;

    public AuthenticationBean(String message) {
        this.message = message;
        
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
  
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
    public String toString() {
        return String.format("HelloWorldBean [message=%s]", message);
    }
}
