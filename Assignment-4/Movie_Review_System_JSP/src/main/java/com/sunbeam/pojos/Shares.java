package com.sunbeam.pojos;

//pojo class of shares table

public class Shares {
private int review_id;
private int user_id;
public int getReview_id() {
	return review_id;
}
public void setReview_id(int review_id) {
	this.review_id = review_id;
}
public int getUser_id() {
	return user_id;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
public Shares(int review_id, int user_id) {
	super();
	this.review_id = review_id;
	this.user_id = user_id;
}


}

/**
 * @author Ritu And Ujjwal
 * prn no ritu = 230940820086
 * prn no ujjwal = 230940820111
 *
 */
