package com.revature.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.SuperDAOImpl;
import com.revature.pojo.Super;

@WebServlet("/Add")
public class AddServlet extends HttpServlet {
	
	private SuperDAOImpl sDao = new SuperDAOImpl();
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse rsp) throws ServletException, IOException {
		String alias = req.getParameter("alias");
		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		String alignTemp = req.getParameter("alignment");
		int alignment = 0;
		if (alignTemp.toUpperCase() == "GOOD") {
			alignment = 1;
		}
		else if (alignTemp.toUpperCase() == "EVIL") {
			alignment = -1;
		}
		
		Super superhuman = new Super(alias, firstname, lastname, alignment);
		sDao.createSuper(superhuman);
		
		rsp.sendRedirect("table");
		
	}
}
