package com.sunbeam.bean;

import com.sunbeam.daos.UserDao;
import com.sunbeam.pojos.Users;
import com.sunbeam.utils.DateUtil;

public class RegisterBean {
	String fname;
	String lname;
	String email;
	String password;
	String mobile;
	String birth;
	boolean status;
	public RegisterBean() {
		
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public void saveUser() {
		try(UserDao udao = new UserDao()){
			Users u = new Users();
			u.setFirst_name(fname);
			u.setLast_name(lname);
			u.setEmail(email);
			u.setPassword(password);
			u.setMobile(mobile);
			u.setBirth(DateUtil.parse(birth));
			int cnt = udao.adduser(u);
			if(cnt==0) {
				status = false;
			}else status = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
