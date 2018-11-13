package com.arifco.first;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(description="log out servlet.",
			urlPatterns = {"/LogoutServlet"})
public class LogoutServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		Cookie[] cookies = req.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("JSESSIONID")) {
					System.out.println("JSESSIONID = "+cookie.getValue());
					break;
				}
			}
		}
		// invalidate the session if exists
		HttpSession session = req.getSession(false);
		System.out.println("User = "+session.getAttribute("user"));
		if(session != null){
			session.invalidate();
		}
		res.sendRedirect("login.html");
		/**
		 * code for cookies
		 * res.setContentType("text/html");
		Cookie loginCookie = null;
		Cookie[] cookies = req.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("user")) {
					loginCookie = cookie;
					break;
				}
			}
		}
		if(loginCookie != null) {
			loginCookie.setMaxAge(0);
			res.addCookie(loginCookie);
		}
		res.sendRedirect("login.html");*/
	}
	
}
