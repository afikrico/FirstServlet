package com.arifco.first;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(description ="Login Servlet",
			urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7991256153134075718L;
	private final String userId = "arif";
	private final String password ="Miracup1";
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String usr = req.getParameter("user");
		String pwd = req.getParameter("pwd");
		
		log("User "+usr+" password "+pwd);
		
		if(userId.equals(usr) && password.equals(pwd)) {
			HttpSession session = req.getSession();
			session.setAttribute("user", usr);
			Cookie loginCookie = new Cookie("user", usr);
			loginCookie.setMaxAge(30*60);
			res.addCookie(loginCookie);
			res.sendRedirect("LoginSuccess.jsp");
		} else {
			RequestDispatcher rdp = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter pw = res.getWriter();
			pw.println("<font color=red> Either username or password is wrong.</font>");
			rdp.include(req, res);
					
		}
		
	}
}
