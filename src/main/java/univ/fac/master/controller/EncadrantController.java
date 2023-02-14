package univ.fac.master.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import univ.fac.master.entities.AuthenticationBean;
import univ.fac.master.entities.Encadrant;
import univ.fac.master.entities.Etudiant;
import univ.fac.master.repositories.EncadrantRepository;
import univ.fac.master.repositories.EtudiantRepository;
import univ.fac.master.service.EncadrantService;
import univ.fac.master.service.EtudiantService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class EncadrantController {

	@Autowired
	private EncadrantRepository er;
	
	@Autowired
	private EncadrantService es;
	
	/*@GetMapping("")
	public String viewHomePage() {
		return "index";
	}*/
	
	@GetMapping("/register_encadrant")
	public String showRegistrationForm(Model model) {
		model.addAttribute("encadrant", new Encadrant());
		
		return "signup_encadrant";
	}
	
	@GetMapping(path = "/basicauthe")
    public AuthenticationBean basicauth() {
		//Long id = cd.getId();
		//System.out.print(id);
		
        return new AuthenticationBean("You are authenticated");
    }
	
	@PostMapping("process_encadrant")
	public Encadrant processRegister(@RequestBody Encadrant encadrant, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
		/*BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(etudiant.getPassword());
		etudiant.setPassword(encodedPassword);*/
		es.register(encadrant, getSiteURL(request));
		
		Encadrant e=er.save(encadrant);
		
		return e;
	}
	
	@GetMapping("encadrant/{email}")
	public Encadrant getEncadrantByEmail(@PathVariable String email){
		return es.getEncadrantByEmail(email);
	}
	
    private String getSiteURL(HttpServletRequest request) {
	      String siteURL = request.getRequestURL().toString();
	      return siteURL.replace(request.getServletPath(), "");
	}
    
    @GetMapping("/verify_encadrant")
    public String verifyUser(@Param("code") String code) {
        if (es.verify(code)) {
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }

	
	@GetMapping("/encadrants")
	public String listEncadrants(Model model) {
		List<Encadrant> listEncadrants = er.findAll();
		model.addAttribute("listEncadrants", listEncadrants);
		
		return "encadrants";
	}
	
}

