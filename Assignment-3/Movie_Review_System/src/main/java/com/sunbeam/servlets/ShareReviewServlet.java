package com.sunbeam.servlets;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sunbeam.daos.ReviewsDao;
import com.sunbeam.daos.SharesDao;
import com.sunbeam.daos.UserDao;
import com.sunbeam.pojos.Reviews;
import com.sunbeam.pojos.Users;

@WebServlet("/sharereview")
public class ShareReviewServlet extends HttpServlet{

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
		out.println("<title>Share Reviews</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form method='post' action='sharereview'>");
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		try(ReviewsDao rdao = new ReviewsDao()){
			Reviews r= rdao.findReview(id);
			out.printf("ID:<input type='number' name='reviewid' readonly value=%d><br>",r.getId());
			
			out.printf("User:<select name='user'>");
			try(UserDao udao = new UserDao()){
				List<Users> list = udao.display();
				for(Users u:list) {
					out.printf("<option value=%s>%s</option>",u.getId(),u.getFirst_name());
				}
				
			}
			out.printf("</select>");
			out.printf("<input type='submit' value='Share'>");
			
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
		

	
		int rid = Integer.parseInt( req.getParameter("reviewid"));
		int uid = Integer.parseInt(req.getParameter("user")); 
		System.out.println(rid);
		System.out.println(uid);
		try(SharesDao sdao = new SharesDao()){
		
			sdao.add(rid, uid);
			
			String title = "Shared Reviews";
			req.setAttribute("title", title);
			
			//resp.sendRedirect("reviews");
			
			RequestDispatcher rd = req.getRequestDispatcher("reviews?site=shared");
			rd.forward(req, resp);
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
		
	}
}
