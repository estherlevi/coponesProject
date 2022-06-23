package app.core.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import app.core.services.JwtUtil;
import io.jsonwebtoken.Claims;

public class LoginFilter implements Filter {
	
	private JwtUtil jwtUtil;

	public LoginFilter(JwtUtil jwtUtil) {
		super();
		this.jwtUtil = jwtUtil;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String token = req.getHeader("token");
		System.out.println("===== LOGIN FILTER TOKEN: " + token);

		if (token == null && req.getMethod().equals("OPTIONS")) {
			System.out.println("this is preflight request: " + req.getMethod());
			chain.doFilter(request, response);
			return;
		}

		try {
		//	clientDetails clientDetails = jwtUtil.extractClient(token);
			Claims clientDetails = jwtUtil.extractAllClaims(token);
			
			System.out.println("===== LOGIN FILTER: " + clientDetails);
			chain.doFilter(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
			resp.sendError(HttpStatus.UNAUTHORIZED.value(), "not logged in");
		}

	}

}
