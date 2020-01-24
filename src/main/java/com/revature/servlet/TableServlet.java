package com.revature.servlet;

import com.revature.pojo.*;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.SuperDAOImpl;

public class TableServlet extends HttpServlet {
	
	SuperDAOImpl sDao = new SuperDAOImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse rsp) throws ServletException, IOException {

		PrintWriter pw = rsp.getWriter();
		List<Super> supers = new ArrayList<Super>();
		ResultSet rs = sDao.readAllSuper();

		try {
			while (rs.next()) {
				supers.add(new Super(rs.getInt("super_id"), rs.getString("alias"), rs.getString("firstname"),
						rs.getString("lastname"), rs.getInt("alignment")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		printTable(pw, supers);
	}

	public void printTable(PrintWriter pw, List<Super> supers) {
		String alignment = null;

		pw.write("<title>Superhuman Registry</title>");
		pw.write("<h3>Superhuman Table</h3>");
		pw.write("<table style='width:100%'><tr>");
		pw.write("<th><a href='addSuper.html'>ADD Superhuman</a></th>");
		pw.write("<th><a href='removeSuper.html'>REMOVE Superhuman</a></th>");
		pw.write("<th><a href='updateSuper.html'>UPDATE Superhuman</a></th>");
		pw.write("</tr></table>");
		pw.write("<table style='width:100%'>");
		pw.write("<tr>");
		pw.write("<th>ALIAS</th>");
		pw.write("<th>FIRST NAME</th>");
		pw.write("<th>LAST NAME</th>");
		pw.write("<th>ALIGNMENT</th>");
		pw.write("<th>ID</th>");
		pw.write("</tr>");

		for (Super person : supers) {
			if (person.getAlignment() == 1) {
				alignment = "Good";
			}
			else if (person.getAlignment() == -1) {
				alignment = "Evil";
			}
			else {
				alignment = "Neutral";
			}
			
			pw.write("<tr>");
			pw.write("<td>" + person.getAlias() + "</td>");
			pw.write("<td>" + person.getFirstname() + "</td>");
			pw.write("<td>" + person.getLastname() + "</td>");
			pw.write("<td>" + alignment + "</td>");
			pw.write("<td>" + person.getID() + "</td>");
			pw.write("</tr>");
		}

		pw.write("</table>");

		

	}
}
