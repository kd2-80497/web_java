package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		HttpSession session = req.getSession();
		session.invalidate();
		resp.setContentType("text/html");
		
	 PrintWriter out=resp.getWriter();
		out.println("<html><head><title>Logout</title></head><body>");
		out.println("<h3>Thank you for using our App...<a href='index.html'>Login Again</a></h3></body></html>");
		
	}
}
