package com.sunbeam.bean;

import com.sunbeam.daos.UserDao;
import com.sunbeam.pojos.Users;

public class LoginBean {
	
	String email;
	String password;
	boolean status;
	public LoginBean() {
		
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public void authenticate() {
		try(UserDao udao = new UserDao()){
			Users u = new Users();
			u.setEmail(email);
			u.setPassword(password);
			System.out.println(u);
			Users user = udao.valid(u);
			System.out.println(user);
			if(user!=null) {
				status = true;
			}else status = false;
			 
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
