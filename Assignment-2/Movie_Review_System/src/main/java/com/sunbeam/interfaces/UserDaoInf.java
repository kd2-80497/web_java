/**
 * 
 */
package com.sunbeam.interfaces;

import java.util.Date;
import java.util.List;

import com.sunbeam.pojos.Users;

/**
 * @author Ritu Powar
 *
 */
public interface UserDaoInf {
	public int adduser(Users u) throws Exception;
	public List<String> displayEmails() throws Exception;
	public List<Users> display() throws Exception;
	public Users valid(Users u) throws Exception;
	public void update(String n,int x)throws Exception;
	public int editProfile(String fn,String ln,String m,String p,Date dob,int n)throws Exception;
	
}