package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.pojo.Super;
import com.revature.util.ConnectionFactory;

public class SuperDAOImpl implements SuperDAO {

	public Integer createSuper(Super superhuman) {
		int eval = 0;
		Connection conn = ConnectionFactory.getConnection();
		String str = "insert into project1.superhumans (alias, firstname, lastname, alignment) values (?, ?, ?, ?)";
		try {
			PreparedStatement prest = conn.prepareStatement(str);
			prest.setString(1, superhuman.getAlias());
			prest.setString(2, superhuman.getFirstname());
			prest.setString(3, superhuman.getLastname());
			prest.setInt(4, superhuman.getAlignment());
			eval = prest.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return eval;
	}

	public Super readSuper(String alias) {
		Super readSuper = new Super();
		Connection conn = ConnectionFactory.getConnection();
		String sql = "select super_id, alias, firstname, lastname, alignment from project1.superhumans where alias = ?";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			prep = conn.prepareStatement(sql);
			prep.setString(1, alias);
			rs = prep.executeQuery();
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
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return readSuper;
	}

	public ResultSet readAllSuper() {
		Connection conn = ConnectionFactory.getConnection();
		ResultSet rs = null;
		String str = "select * from project1.superhumans";
		PreparedStatement prest;
		try {
			prest = conn.prepareStatement(str);
			rs = prest.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}

	public Integer updateSuper(Super superhuman) {
		int eval = 0;
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement prep = null;
		String sql = "update project1.superhumans set alias = ?, firstname = ?, lastname = ?, alignment = ? "
				+ "where super_id = ?";
		try {
			prep = conn.prepareStatement(sql);
			prep.setString(1, superhuman.getAlias());
			prep.setString(2, superhuman.getFirstname());
			prep.setString(3, superhuman.getLastname());
			prep.setInt(4, superhuman.getAlignment());
			if (superhuman.getID() != (Integer) null) {
				prep.setInt(5, superhuman.getID());
			} else {
				return 0;
			}
			eval = prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return eval;
	}

	public Integer deleteSuper(String alias) {
		int eval = 0;
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement prep = null;
		String sql = "delete from project1.superhumans where alias = ?";
		try {
			prep = conn.prepareStatement(sql);
			prep.setString(1, alias);
			eval = prep.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return eval;
	}

}
