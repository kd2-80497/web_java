package com.sunbeam.pojos;

import java.util.Date;
import java.util.Scanner;

import com.sunbeam.daos.UserDao;
import com.sunbeam.utils.DateUtil;

//pojo class of users table

public class Users {
	private int id;
	private String first_name;
	private String last_name;
	private String email;
	private String password;
	private String mobile;
	private Date birth;
	Scanner sc = new Scanner(System.in);

	public Users() {
		// TODO Auto-generated constructor stub
	}

	public Users(int id, String first_name, String last_name, String email, String password, String mobile,
			Date birth) {
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		this.birth = birth;
	}

	
	//Accepting Users with Validation
	public int accept() {
		System.out.print("Enter First Name = ");
		String fn = sc.next();
		int i = 0;
		//first name cannot be empty
		if(fn.length()==0) {
			System.out.println("Name cannot be empty.");
		}
		while(i!= fn.length()) {
			//validating if first name contains number
			if(!Character.isAlphabetic(fn.charAt(i))) {
				System.out.println("Enter only Alphabets.");
				return 0;
			}
			i++;
				
		}
		setFirst_name(fn);
		System.out.print("Enter Last Name = ");
		String ln = sc.next();
		int j = 0;
		
		//last name cannot be empty
		if(ln.length()==0) {
			System.out.println("Name cannot be empty.");
		}
		while(j!= ln.length()) {
			//validating if last name contains number
			if(!Character.isAlphabetic(ln.charAt(j))) {
				System.out.println("Enter only Alphabets.");
				return 0;
			}
			j++;
				
		}

		setLast_name(ln);
		System.out.print("Enter Email = ");
		
		String email = sc.next();
		if(email.length()==0) {
			System.out.println("Email cannot be empty.");
		}
		//validating email id
		if(email.indexOf('@') == -1) {
			System.out.println("Invalid email id");
			return 0;
		}
		
		//validating if email id is duplicate
		try (UserDao udao = new UserDao()) {
			if (!udao.displayEmails().contains(email)) {
				setEmail(email);
			} else {
				
				System.out.println("Duplicate email id found. Plz enter again. ");
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		System.out.print("Enter Password = ");
		sc.nextLine();
		String s=sc.nextLine();
		
		if(s.length()<8) {
			System.out.println("Password should be minimum 8 characters.");
			return 0;
		}
		setPassword(s);
		System.out.print("Enter Mobile = ");
		String m = sc.next();
		if(m.length()!= 10) {
			System.out.println("Mobile should be 10 digit.");
			return 0;
		}
		 i = 0;
		while(i!= 10) {
			if(!Character.isDigit(m.charAt(i))) {
				System.out.println("Enter only numbers.");
				return 0;
			}
			i++;
				
		}
		setMobile(m);
		System.out.print("Birth Date (dd-MM-yyyy): ");
		String dateStr = sc.next();
		setBirth(DateUtil.parse(dateStr));
		return 1;
	}

	/**
	 * @author Ritu And Ujjwal
	 * prn no ritu = 230940820086
	 * prn no ujjwal = 230940820111
	 *
	 */
	
	
	@Override
	public String toString() {
		return id+". "+first_name + " " + last_name + " (" + email+")";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
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

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

}
