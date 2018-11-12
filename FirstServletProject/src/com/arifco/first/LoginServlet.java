package com.arifco.first;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description ="Login Servlet",
			urlPatterns = {"/LoginServlet"},
			initParams = {@WebInitParam(name ="user", value="arif"),
						  @WebInitParam(name ="password", value="miracup1")
			})
public class LoginServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7991256153134075718L;

	public void init() throws ServletException {
		if(getServletContext().getInitParameter("dbUser").equals("mysql_user") &&
				getServletContext().getInitParameter("dbUserPwd").equals("mysql_pwd")) {
			getServletContext().setAttribute("DB_Success", true);
		} else {
			throw new ServletException("DB connection error");
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String usr = req.getParameter("user");
		String pwd = req.getParameter("pwd");
		
		String userId = getServletConfig().getInitParameter("user");
		String password = getServletConfig().getInitParameter("password");
		
		log("User "+usr+" password "+pwd);
		
		if(userId.equals(usr) && password.equals(pwd)) {
			res.sendRedirect("LoginSuccess.jsp");
		} else {
			RequestDispatcher rdp = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter pw = res.getWriter();
			pw.println("<font color=red> Either username or password is wrong.</font>");
			rdp.include(req, res);
					
		}
		
	}
}
