/**
 * 
 */
package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeam.daos.MoviesDao;
import com.sunbeam.daos.ReviewsDao;
import com.sunbeam.pojos.Movies;
import com.sunbeam.pojos.Users;

/**
 * @author Ritu Powar
 *
 */

@WebServlet("/addreview")
public class NewReviewServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		try(ReviewsDao rdao = new ReviewsDao()){			
			PrintWriter out = resp.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Add Review</title>");
			out.println("</head>");
			
			out.println("<body>");
			
			HttpSession session = req.getSession();
			Users user =(Users) session.getAttribute("curUser");
			out.println("<h3>Hello "+user.getFirst_name()+" "+user.getLast_name()+"!</h3>");
			out.println("<form method='POST' action='addReviewServlet'>");
			out.println("<label>Movie</label>");
			out.println("<select name='movies'>");
			try(MoviesDao mdao = new MoviesDao()){
				
				List<Movies> mlist= mdao.display();
				
				for (Movies movies : mlist) {
					out.printf("<option value="+movies.getId()+">%s</option>",movies.getTitle());
					
				}
				out.println("</select><br>");
				
			
			out.println("<label>Rating</label>");
			out.println("<input name='rating' type='text'></input><br>"); 
			out.println("<label>Review</label>");
			out.println("<textarea name='review'></textarea><br>");
			out.println("<input type='submit' value='Save'></input><br>");
			
			out.println("</form>");
			}
			out.println("</body>");
			out.println("</html>");	
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
