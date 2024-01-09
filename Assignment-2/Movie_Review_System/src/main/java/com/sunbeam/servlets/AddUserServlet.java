/**
 * 
 */
package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.daos.UserDao;
import com.sunbeam.pojos.Users;
import com.sunbeam.utils.DateUtil;

/**
 * @author Ritu Powar
 *
 */

@WebServlet("/register")
public class AddUserServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try(UserDao udao = new UserDao()){
			String fname = req.getParameter("fname");
			String lname = req.getParameter("lname");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String mobile = req.getParameter("mobile");
			String birth = req.getParameter("birth");
			Users u = new Users();
			u.setFirst_name(fname);
			u.setLast_name(lname);
			u.setEmail(email);
			u.setPassword(password);
			u.setMobile(mobile);
			u.setBirth(DateUtil.parse(birth));
			udao.adduser(u);
			
			PrintWriter out = resp.getWriter();
			out.println("<html><head><title>Register</title></head><body>");
			out.println("<h3>User Added Successfully!</h3>");
			out.println("<h3>Click here to <a href='index.html'>Login</a></h3>");
			out.println("</body></html>");
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
	}
	
}
