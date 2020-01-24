package com.revature.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.PropertyConfigurator;

import com.revature.util.Logger;
import com.revature.pojo.Super;
import com.revature.util.ConnectionFactory;

public class SuperDAOImpl implements SuperDAO {
	
	private static String properties = "./log4j.properties"; 

	public void getProps() {
		Class cls = this.getClass();
		ClassLoader cLoad = cls.getClassLoader();
		InputStream prop = cLoad.getResourceAsStream(properties);
		PropertyConfigurator.configure(prop);
	}
	
	public Integer createSuper(Super superhuman) {
		getProps();
		PropertyConfigurator.configure(properties);
		int eval = 0;
		Connection conn = ConnectionFactory.getConnection();
		Logger.log("CONNECTION: " + conn);
		String str = "insert into project1.superhumans (alias, firstname, lastname, alignment) values (?, ?, ?, ?)";
		try {
			PreparedStatement prest = conn.prepareStatement(str);
			prest.setString(1, superhuman.getAlias());
			prest.setString(2, superhuman.getFirstname());
			prest.setString(3, superhuman.getLastname());
			prest.setInt(4, superhuman.getAlignment());
			Logger.log("INSERT QUERY");
			Logger.logSQL(prest.toString());
			eval = prest.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				Logger.log("CONNECTION CLOSED");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return eval;
	}

	public Super readSuper(String alias) {
		getProps();
		Super readSuper = new Super();
		Connection conn = ConnectionFactory.getConnection();
		Logger.log("CONNECTION: " + conn);
		String sql = "select super_id, alias, firstname, lastname, alignment from project1.superhumans where alias = ?";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			prep = conn.prepareStatement(sql);
			prep.setString(1, alias);
			Logger.log("READ QUERY");
			Logger.log(prep.toString());
			rs = prep.executeQuery();
			Logger.log("RESULT: " + rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (rs.next()) {
				readSuper = new Super(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
				System.out.println(readSuper.getAlias());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				Logger.log("CONNECTION CLOSED");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return readSuper;
	}
	
	public Super readSuper(Integer super_id) {
		getProps();
		Super readSuper = new Super();
		Connection conn = ConnectionFactory.getConnection();
		Logger.log("CONNECTION: " + conn);
		String sql = "select super_id, alias, firstname, lastname, alignment from project1.superhumans where super_id = ?";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			prep = conn.prepareStatement(sql);
			prep.setInt(1, super_id);
			Logger.logSQL(prep.toString());
			rs = prep.executeQuery();
			Logger.log("RESULT: " + rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (rs.next()) {
				readSuper = new Super(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
				System.out.println(readSuper.getAlias());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				Logger.log("CONNECTION CLOSED");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return readSuper;
	}

	public ResultSet readAllSuper() {
		getProps();
		Connection conn = ConnectionFactory.getConnection();
		Logger.log("CONNECTION: " + conn);
		ResultSet rs = null;
		PreparedStatement prep = null;
		String str = "select super_id, alias, firstname, lastname, alignment from project1.superhumans";
		try {
			prep = conn.prepareStatement(str);
			Logger.logSQL(prep.toString());
			rs = prep.executeQuery();
			Logger.log("RESULT: " + rs);
		} catch (Exception e) {
			System.out.println("Error with connection!");
		} finally {
			try {
				conn.close();
				Logger.log("CONNECTION CLOSED");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}

	public Integer updateSuper(Super superhuman) {
		getProps();
		int eval = 0;
		Connection conn = ConnectionFactory.getConnection();
		Logger.log("CONNECTION: " + conn);
		PreparedStatement prep = null;
		String sql = "update project1.superhumans set alias = ?, firstname = ?, lastname = ?, alignment = ? "
				+ "where super_id = ?";
		try {
			prep = conn.prepareStatement(sql);
			prep.setString(1, superhuman.getAlias());
			prep.setString(2, superhuman.getFirstname());
			prep.setString(3, superhuman.getLastname());
			prep.setInt(4, superhuman.getAlignment());
			prep.setInt(5, superhuman.getID());
			Logger.log("UPDATE QUERY");
			Logger.logSQL(prep.toString());
			eval = prep.executeUpdate();
			Logger.log("RESULT: " + eval);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				Logger.log("CONNECTION CLOSED");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return eval;
	}

	public Integer deleteSuper(Integer super_id) {
		getProps();
		int eval = 0;
		Connection conn = ConnectionFactory.getConnection();
		Logger.log("CONNECTION: " + conn);
		PreparedStatement prep = null;
		String sql = "delete from project1.superhumans where super_id = ?";
		try {
			prep = conn.prepareStatement(sql);
			prep.setInt(1, super_id);
			Logger.logSQL(prep.toString());
			eval = prep.executeUpdate();
			Logger.log("RESULTS: " + eval);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				Logger.log("CONNECTION CLOSED");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return eval;
	}

	public ResultSet getAlignments() {
		String sql = "select * from project1.alignment";
		Connection conn = ConnectionFactory.getConnection();
		Logger.log("CONNECTION: " + conn);
		ResultSet rs = null;
		PreparedStatement prep = null;
		try {
			prep = conn.prepareStatement(sql);
			Logger.logSQL(prep.toString());
			rs = prep.executeQuery();
 		} catch (Exception e) {
 			e.printStackTrace();
 		} finally {
 			try {
				conn.close();
				Logger.closed();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 			
 		}
		return rs;
	}
}
