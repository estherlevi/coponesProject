package app.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.exceptions.CouponSystemException;
import app.core.login.ClientType;
import app.core.login.LoginManager;
import app.core.services.ClientService;
import app.core.services.JwtUtil;

@CrossOrigin
@RestController
public class LoginController {
	
	@Autowired
	private LoginManager loginManager;
	@Autowired
	private JwtUtil jwt;

	@PostMapping(	
	  path = "/login",
	  consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	
	public String login(@RequestParam String email, @RequestParam String password,@RequestParam ClientType clientType) {
		
		try {
			ClientService clientService;
				clientService = loginManager.login(email, password, clientType);
			
			
			
			if (clientService!=null)
				
			{
			String token = jwt.generateToken(clientService,email);
			System.out.println(token);
			return token;
			
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"no login recognized");
	} catch (CouponSystemException e) {
		
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}		
	
}