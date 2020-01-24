package com.revature.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.pojo.Super;

public interface SuperDAO {
	
	public Integer createSuper(Super superhuman);
	
	public ResultSet readAllSuper();
	
	public Super readSuper(String alias);
	
	public Integer updateSuper(Super superhuman);
	
	public Integer deleteSuper(Integer super_id);
	
	public ResultSet getAlignments();
	
}
