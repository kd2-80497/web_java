/**
 * 
 */
package com.sunbeam.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeam.daos.UserDao;
import com.sunbeam.pojos.Users;

/**
 * @author Ritu Powar
 *
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		Users user = new Users();
		user.setEmail(email);
		user.setPassword(password);
		
		try(UserDao userDao = new UserDao()){
			Users u = userDao.valid(user);
			
			if(u!=null) {
				HttpSession	session = req.getSession();
				session.setAttribute("curUser",u);
				resp.sendRedirect("reviews");
			}else
			{
				
				resp.sendRedirect("invalidLogin.html");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServletException(e);
		} 
		
	}
	
}
