/**
 * 
 */
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
import com.sunbeam.pojos.Users;

/**
 * @author Ritu Powar
 *
 */

@WebServlet("/deletereview")
public class DeleteReviewServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		Users user = (Users) session.getAttribute("curUser");
		int user_id = user.getId();
		String id = req.getParameter("id");
		int review_id = Integer.parseInt(id);
		
		try(ReviewsDao rdao = new ReviewsDao()){
			rdao.delete(review_id,user_id);
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
		String title = "All Reviews";
		req.setAttribute("title", title);
		
		 RequestDispatcher rd=req.getRequestDispatcher("reviews?site=all");
		 rd.forward(req, resp);
		
	}
	
	
}
