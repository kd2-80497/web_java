package com.sunbeam.daos;

import java.sql.Connection;

import com.sunbeam.utils.DbUtil;

public class Dao implements AutoCloseable {
	protected Connection con;
	public Dao(){
		try {
			con = DbUtil.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void close() {
		try {
			if(con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
