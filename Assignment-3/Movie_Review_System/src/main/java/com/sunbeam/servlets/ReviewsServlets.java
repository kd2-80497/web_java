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

	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Reviews</title>");
		out.println("</head>");
		out.println("<body>");

		HttpSession session = req.getSession();
		Users user = (Users) session.getAttribute("curUser");

		out.println("<h1>Hello " + user.getFirst_name() + " " + user.getLast_name() + "!</h1>");
		out.println("<a  href='reviews?site=all'>All Reviews</a><span></span>");
		out.println("<a href='reviews?site=my'>My Reviews</a><span></span>");
		out.println("<a href='reviews?site=shared'>Shared Reviews</a><span></span><br><br>");
		String site = req.getParameter("site");
		
		
		
		String title = (String) req.getAttribute("title");
		System.out.println(title);
				if(title != null) {
					out.printf("<h3>%s\r\n</h3>", title);
				}
					

		Date date = new Date();
		out.println(date);

		out.println("<table border=1><thead><th>ID</th>");
		out.println("<th>Movie</th>");
		out.println("<th>Ratings</th>");
		out.println("<th>User Id</th>");
		out.println("<th>Action</th>");
		out.println("</thead><tbody>");

		

		if (site==null|| site.equals("all")) {
			try (ReviewsDao rdao = new ReviewsDao()) {

				List<Reviews> rlist = rdao.display();

				for (Reviews reviews : rlist) {
					out.println("<tr>");
					out.printf("<td>%s</td>\r\n", reviews.getId());
					try (MoviesDao mdao = new MoviesDao()) {

						Movies m = mdao.findMovieById(reviews.getMovie_id());
						out.printf("<td>%s</td>\r\n", m.getTitle());

					}
					out.printf("<td>%s</td>\r\n", reviews.getReview());
					out.printf("<td>%s</td>\r\n", reviews.getUser_id());
					if(reviews.getUser_id()==user.getId()) {
						out.printf(
								"<td><a href='editreview?id=%s'><img width=24 height=24 src='edit.png' alt='edit'></a>"
								+ "<a href='deletereview?id=%s'><img width=24 height=24 src='delete.png' alt='delete'></a>"
								+ "<a href='sharereview?id=%s'><img width=24 height=24 src='share.png' alt='share'></a>"
								+ "</td>",
								reviews.getId(),reviews.getId(),reviews.getId());

					}
					
					out.println("</tr>");
				}
			} catch (Exception e) {

				e.printStackTrace();
				throw new ServletException(e);
			}

		} else if (site.equals("my")) {
			try (ReviewsDao rdao = new ReviewsDao()) {

				List<Reviews> mlist = rdao.myReviews(user.getId());

				for (Reviews reviews : mlist) {
					out.println("<tr>");
					out.printf("<td>%s</td>\r\n", reviews.getId());
					try (MoviesDao mdao = new MoviesDao()) {

						Movies m = mdao.findMovieById(reviews.getMovie_id());
						out.printf("<td>%s</td>\r\n", m.getTitle());

					}
					out.printf("<td>%s</td>\r\n", reviews.getReview());
					out.printf("<td>%s</td>\r\n", reviews.getUser_id());

					out.printf(
							"<td><a href='editreview?id=%s'><img width=24 height=24 src='edit.png' alt='edit'></a><span></span>"
							+ "<a href='deletereview?id=%s'><img width=24 height=24 src='delete.png' alt='delete'></a><span></span>"
							+ "<a href='sharereview?id=%s'><img width=24 height=24 src='share.png' alt='share'></a><span></span>"
							+ "</td>",
							reviews.getId(),reviews.getId(),reviews.getId());

					out.println("</tr>");

				}
			} catch (Exception e) {

				e.printStackTrace();
				throw new ServletException(e);
			}

		} else {

			try (ReviewsDao rdao = new ReviewsDao()) {

				List<Reviews> slist = rdao.displaySharedReview(user.getId());

				for (Reviews reviews : slist) {
					out.println("<tr>");
					out.printf("<td>%s</td>\r\n", reviews.getId());
					try (MoviesDao mdao = new MoviesDao()) {

						Movies m = mdao.findMovieById(reviews.getMovie_id());
						out.printf("<td>%s</td>\r\n", m.getTitle());

					}
					out.printf("<td>%s</td>\r\n", reviews.getReview());
					out.printf("<td>%s</td>\r\n", reviews.getUser_id());
					out.println("</tr>");

				}

			} catch (Exception e) {

				e.printStackTrace();
				throw new ServletException(e);
			}

			

		}
		out.println("</tbody></table>");

		out.println("<br><br><a href='addreview'>Add Review</a><span></span>");
		out.println("<a href='logout'>Sign Out</a>");

		out.println("</body>");
		out.println("</html>");
	}
}
