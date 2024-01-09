/**
 * 
 */
package com.sunbeam.interfaces;

/**
 * @author Ritu Powar
 *
 */
import java.util.List;

import com.sunbeam.pojos.Reviews;

public interface ReviewsDaoInf {
	public int accept(Reviews r,int id)throws Exception;
	public List<Reviews> display() throws Exception;
	public int edit(int id,String s,int user_id) throws Exception;
	public List<Reviews> myReviews(int n) throws Exception;
	public List<Reviews> mySharedReviews(int n,int id) throws Exception;
	public List<Reviews> displaySharedReview(int id) throws Exception;
	public int delete(int id,int user) throws Exception;
}