package com.sunbeam.servlets;

import java.io.IOException;

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


@WebServlet("/addReviewServlet")
public class AddReviewServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try(ReviewsDao rdao = new ReviewsDao()){
			
			String movies=req.getParameter("movies");
			int movie_id = Integer.parseInt(movies);
			int rating = Integer.parseInt(req.getParameter("rating")) ;
			String review = req.getParameter("review");
			Reviews r = new Reviews();
			r.setRating(rating);
			r.setReview(review);
			r.setMovie_id(movie_id);
			
			HttpSession session = req.getSession();
			Users user =  (Users) session.getAttribute("curUser");
			rdao.accept(r,user.getId());
			String title = "My Reviews";
			req.setAttribute("title", title);
			
//			resp.sendRedirect("reviews?site=my");
			
			RequestDispatcher rd = req.getRequestDispatcher("reviews?site=my");
			rd.forward(req, resp);
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
