package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeam.daos.ReviewsDao;
import com.sunbeam.pojos.Reviews;
import com.sunbeam.pojos.Users;

@WebServlet("/editreview")
public class EditReviewServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Reviews</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form method='POST' action='editreview'>");
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		try(ReviewsDao rdao = new ReviewsDao()){
			Reviews r= rdao.findReview(id);
			out.printf("ID:<input type='number' name='reviewid' readonly value=%d><br>",r.getId());
			//out.printf("Review:<input type='text' name='review' value='%s' /><br>",r.getReview());
			out.printf("Review:<textarea name='review'>%s</textarea><br>",r.getReview());
			out.printf("Rating:<input type='number' name='rating' readonly disabled value=%d><br>",r.getRating());
			out.printf("<input type='submit' value='Update'>");
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		Users u = (Users) session.getAttribute("curUser");
		
		int rid = Integer.parseInt( req.getParameter("reviewid"));
		String review = req.getParameter("review");
		//int rating = Integer.parseInt(req.getParameter("rating"));
		try(ReviewsDao rdao = new ReviewsDao()){
			rdao.edit(rid, review,u.getId());
			
//			RequestDispatcher rd = req.getRequestDispatcher("reviews?site=all");
//			rd.forward(req, resp);
		//405	//it always call doPost therefore 405 error method not supported
			
			String title = "All Reviews";
			req.setAttribute("title", title);
			
			RequestDispatcher rd = req.getRequestDispatcher("reviews?site=all");
			rd.forward(req, resp);
			
			
			//resp.sendRedirect("reviews?site=all");
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
		
	}
}
