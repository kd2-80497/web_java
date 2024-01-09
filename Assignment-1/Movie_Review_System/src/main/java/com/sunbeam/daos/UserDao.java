package com.sunbeam.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sunbeam.pojos.Users;
import com.sunbeam.utils.DateUtil;

public class UserDao extends Dao implements AutoCloseable{

	
	
	/**
	 * @author Ritu And Ujjwal
	 * prn no ritu = 230940820086
	 * prn no ujjwal = 230940820111
	 *
	 */
	
	
	//adding new user into database
	public int adduser(Users u) throws Exception {
		String sql="insert into users(first_name,last_name,email,password,mobile,birth) values (?,?,?,?,?,?)";
		try(PreparedStatement stmt=con.prepareStatement(sql)){
			
				stmt.setString(1,u.getFirst_name());
				stmt.setString(2,u.getLast_name());
				stmt.setString(3,u.getEmail());
				stmt.setString(4,u.getPassword());
				stmt.setString(5,u.getMobile());
				stmt.setDate(6,DateUtil.utilToSqlDate(u.getBirth()));
				int n=stmt.executeUpdate();
				return n;
		}
	}
	
	//for unique email validation
	public List<String> displayEmails() throws Exception {
		List<String> list = new ArrayList<String>();
		String sql = "select email from users";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
		try (ResultSet rs = stmt.executeQuery()) {
		while (rs.next()) {
		String str = rs.getString("email");
		list.add(str);
		}
		}
		}
		return list;
		}

//displaying users
	public List<Users> display() throws Exception{
		List<Users> list=new ArrayList<Users>(); 
		String sql="select * from users";
		try(PreparedStatement stmt=con.prepareStatement(sql)){
			try(ResultSet rs=stmt.executeQuery()){
				while(rs.next()) {
					Users u=new Users();
					u.setId(rs.getInt("id"));
					u.setFirst_name(rs.getString("first_name"));
					u.setLast_name(rs.getString("last_name"));
					u.setEmail(rs.getString("email"));
					u.setPassword(rs.getString("password"));
					u.setMobile(rs.getString("mobile"));
					u.setBirth(DateUtil.sqlToUtilDate(rs.getDate("birth")));
					list.add(u);
				}
			}
		}
		return list;
	}
	
	//validating users
	public List<Users> valid(Users u) throws Exception{
		List<Users> list=new ArrayList<Users>(); 
		String sql="select * from users where email=? and password=?";
		try(PreparedStatement stmt=con.prepareStatement(sql)){
			stmt.setString(1, u.getEmail());
			stmt.setString(2, u.getPassword());
			try(ResultSet rs=stmt.executeQuery()){
				while(rs.next()) {
					Users us=new Users();
					us.setId(rs.getInt("id"));
					us.setFirst_name(rs.getString("first_name"));
					us.setLast_name(rs.getString("last_name"));
					us.setEmail(rs.getString("email"));
					us.setPassword(rs.getString("password"));
					us.setMobile(rs.getString("mobile"));
					us.setBirth(DateUtil.sqlToUtilDate(rs.getDate("birth")));
					list.add(us);
				}
			}
		}
		return list;
	}
	

	//resetting user password
	public void update(String n,int x)throws Exception {
		String sql="update users set password=? where id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(2, x);
			stmt.setString(1, n);
			stmt.executeUpdate();
		}
	}
	
	
	/**
	 * @author Ritu And Ujjwal
	 * prn no ritu = 230940820086
	 * prn no ujjwal = 230940820111
	 *
	 */
	
	
	
	
	//editing user profile
	
	public int editProfile(String fn,String ln,String m,String p,Date dob,int n)throws Exception{
		String sql="update users set first_name=?,last_name=?,mobile=?,password=?,birth=? where id=?";
		try(PreparedStatement stmt=con.prepareStatement(sql)){
			stmt.setString(1, fn);
			stmt.setString(2, ln);
			stmt.setString(3, m);
			stmt.setString(4, p);
			stmt.setDate(5, DateUtil.utilToSqlDate(dob));
			stmt.setInt(6, n);
			return stmt.executeUpdate();
		}
	}

}