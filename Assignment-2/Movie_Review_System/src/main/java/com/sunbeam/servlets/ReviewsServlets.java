package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
import com.sunbeam.pojos.Reviews;
import com.sunbeam.pojos.Users;


@WebServlet("/reviews")
public class ReviewsServlets extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try(ReviewsDao rdao = new ReviewsDao()){
			 
			List<Reviews> rlist = rdao.display();
					
			HttpSession session = req.getSession();
			Users user = (Users) session.getAttribute("curUser");
			PrintWriter out = resp.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Reviews</title>");
			out.println("</head>");
			
			out.println("<body>");
			out.println("<h1>Hello "+user.getFirst_name()+" "+user.getLast_name()+"!</h1>");
			out.println("<a href='reviews'>All Reviews</a>");
			out.println("<a href=''>My Reviews</a>");
			out.println("<a href=''>Shared Reviews</a>");
			out.println("<h3>All Reviews</h3>");
			Date date = new Date();
			out.println(date);
			out.println("<table border=1><thead><th>ID</th>");
			out.println("<th>Movie</th>");
			out.println("<th>Ratings</th>");
			out.println("<th>User Id</th>");
			out.println("<th>Action</th>");
			out.println("</thead><tbody>");
			
			for (Reviews reviews : rlist) {
				out.println("<tr>");
				out.printf("<td>%s</td>\r\n", reviews.getId());
				try(MoviesDao mdao = new MoviesDao()){
					
					Movies m=mdao.findMovieById(reviews.getMovie_id());
					out.printf("<td>%s</td>\r\n",m.getTitle());
					
				}
				out.printf("<td>%s</td>\r\n", reviews.getReview());
				out.printf("<td>%s</td>\r\n", reviews.getUser_id());
				out.println("</tr>");
				
			}


			
			out.println("</tbody></table>");
			
			out.println("<a href='addreview'>Add Review</a>");
			out.println("<a href='logout'>Sign Out</a>");
			
			out.println("</body>");
			out.println("</html>");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new ServletException(e);
		}
		
		
		
		
	}
}
