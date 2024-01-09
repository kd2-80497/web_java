package com.sunbeam.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sunbeam.pojos.Reviews;

public class ReviewsDao extends Dao implements AutoCloseable {
	
	/**
	 * @author Ritu And Ujjwal
	 * prn no ritu = 230940820086
	 * prn no ujjwal = 230940820111
	 *
	 */
	
	
	//adding new movie reviews
	
	public int accept(Reviews r,int id)throws Exception{
		String sql="insert into reviews(movie_id,review,rating,user_id,modified) values(?,?,?,?,now())";
		try(PreparedStatement stmt=con.prepareStatement(sql)){
			stmt.setInt(1, r.getMovie_id());
			stmt.setString(2, r.getReview());
			stmt.setInt(3, r.getRating());
			stmt.setInt(4, id);
			return stmt.executeUpdate();
		}
	}
	
	//Displaying movie reviews
	public List<Reviews> display() throws Exception {
		String sql = "select * from reviews";
		List<Reviews> list = new ArrayList<Reviews>();
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Reviews q = new Reviews();
					q.setId(rs.getInt("id"));
					q.setMovie_id(rs.getInt("movie_id"));
					q.setRating(rs.getInt("rating"));
					q.setReview(rs.getString("review"));
					q.setUser_id(rs.getInt("user_id"));
					q.setModified(rs.getDate("modified"));
					list.add(q);
					
				}
			}
		}
		return list;
	}
	
	//editing movie reviews
	public int edit(int id,String s,int user_id) throws Exception{
		String sql="update reviews set review=? where id=? and user_id = ?";
		try(PreparedStatement stmt=con.prepareStatement(sql)){
			stmt.setInt(2, id);
			stmt.setString(1, s);
			stmt.setInt(3, user_id);
			return stmt.executeUpdate();
		}
		
	}
	
	//getting logged in user's reviews
	
	public List<Reviews> myReviews(int n) throws Exception{
		String sql = "select * from reviews where user_id = ?";
		List<Reviews> list = new ArrayList<Reviews>();
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, n);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Reviews q = new Reviews();
					q.setId(rs.getInt("id"));
					q.setMovie_id(rs.getInt("movie_id"));
					q.setRating(rs.getInt("rating"));
					q.setReview(rs.getString("review"));
					q.setUser_id(rs.getInt("user_id"));
					q.setModified(rs.getDate("modified"));
					list.add(q);
					
				}
			}
		}
		return list;
	}
	
	
	//displaying shared reviews of particular user
	public List<Reviews> mySharedReviews(int n,int id) throws Exception{
		String sql = "select * from reviews where user_id = ? and id = ?";
		List<Reviews> list = new ArrayList<Reviews>();
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, n);
			stmt.setInt(2, id);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Reviews q = new Reviews();
					q.setId(rs.getInt("id"));
					q.setMovie_id(rs.getInt("movie_id"));
					q.setRating(rs.getInt("rating"));
					q.setReview(rs.getString("review"));
					q.setUser_id(rs.getInt("user_id"));
					q.setModified(rs.getDate("modified"));
					list.add(q);
					
				}
			}
		}
		return list;
	}
	
	//displaying all shared reviews
	public List<Reviews> displaySharedReview(int id) throws Exception{
		String sql="select distinct id,movie_id,review,rating,reviews.user_id,modified from reviews,(select review_id from shares where user_id=?)prime where review_id=id";
		List<Reviews> rlist = new ArrayList<Reviews>();
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, id);
			try(ResultSet rs = stmt.executeQuery()){
				while(rs.next()){
					Reviews r = new Reviews();
					r.setId(rs.getInt("id"));
					r.setModified(rs.getDate("modified"));
					r.setRating(rs.getInt("rating"));
					r.setMovie_id(rs.getInt("movie_id"));
					r.setReview(rs.getString("review"));
					r.setUser_id(rs.getInt("user_id"));
					rlist.add(r);
				}
			}
		}
		return rlist;
	}
	
	//deleting reviews
	public int delete(int id,int user) throws Exception{
		String s="select * from reviews where id=? and user_id=?";
		try(PreparedStatement st=con.prepareStatement(s)){
			st.setInt(1, id);
			st.setInt(2, user);
			try(ResultSet rs=st.executeQuery()){
				while(rs.next()) {
					Reviews q = new Reviews();
					q.setId(rs.getInt("id"));
					q.setMovie_id(rs.getInt("movie_id"));
					q.setRating(rs.getInt("rating"));
					q.setReview(rs.getString("review"));
					q.setUser_id(rs.getInt("user_id"));
					q.setModified(rs.getDate("modified"));
					System.out.println(q.toString());
				}
			}
		}
		String sql="delete from reviews where id=? and user_id=?";
		try(PreparedStatement stmt=con.prepareStatement(sql)){
			stmt.setInt(1, id);
			stmt.setInt(2, user);
			return stmt.executeUpdate();
			
		}
	}

}
