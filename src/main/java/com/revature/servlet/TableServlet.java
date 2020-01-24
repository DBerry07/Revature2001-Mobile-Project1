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
		String firstname = null;
		String lastname = null;
		Integer i = 1;

		pw.write("<head><link rel='stylesheet' href='styles.css'></head>");
		pw.write(
				"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">");
		pw.write("<title>Superhuman Registry</title>");
		pw.write("<body><div class='container'>");
		pw.write("<h2 class='text-center'>SUPERHUMAN REGISTRY</h2>");
		pw.write("<div class='container'><div class='row text-align-center'>");
		pw.write(
				"<div class='col-md-4 text-center'><button class='btn btn-success btn-block btn-lg' onclick=\"window.location.href = 'addSuper.html';\"><b>ADD</b> <i>Superhuman</i></button></div>");
		pw.write(
				"<div class='col-md-4 text-center'><button class='btn btn-danger btn-block btn-lg' onclick=\"window.location.href = 'removeSuper.html';\"><b>REMOVE</b> <i>Superhuman</i></button></div>");
		pw.write(
				"<div class='col-md-4 text-center'><button class='btn btn-warning btn-block btn-lg' onclick=\"window.location.href = 'updateSuper.html';\"><b>UPDATE</b> <i>Superhuman</i></button></div>");
		pw.write("</div></div>");
		pw.write("<table style='width:100%' class='table table-bordered table-striped'>");
		pw.write("<tr>");
		pw.write("<td><b>ALIAS</b></td>");
		pw.write("<td><b>FIRST NAME</b></td>");
		pw.write("<td><b>LAST NAME</b></td>");
		pw.write("<td><b>ALIGNMENT</b></td>");
		pw.write("<td><b>ID</b></td>");
		pw.write("</tr><br><br>");

		for (Super person : supers) {
			if (i > 0) {
				i = 0;
			} else {
				i = 1;
			}
			firstname = null;
			lastname = null;
			if (person.getAlignment() == 1) {
				alignment = "GOOD";
			} else if (person.getAlignment() == -1) {
				alignment = "EVIL";
			} else {
				alignment = "NEUTRAL";
			}

			if (person.getFirstname() == null) {
				firstname = "---";
			} else {
				firstname = person.getFirstname().toUpperCase();
			}

			if (person.getLastname() == null) {
				lastname = "---";
			} else {
				lastname = person.getLastname().toUpperCase();
			}

			pw.write("<tr>");
			pw.write("<td>" + person.getAlias().toUpperCase() + "</td>");
			pw.write("<td>" + firstname + "</td>");
			pw.write("<td>" + lastname + "</td>");
			pw.write("<td>" + alignment + "</td>");
			pw.write("<td>" + person.getID() + "</td>");
			pw.write("</tr>");
		}

		pw.write("</table></div></body>");

	}
}
