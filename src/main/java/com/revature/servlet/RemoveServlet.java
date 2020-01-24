package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.SuperDAOImpl;

@WebServlet("/Remove")
public class RemoveServlet extends HttpServlet {
	
	private SuperDAOImpl sDao = new SuperDAOImpl();
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse rsp) throws ServletException, IOException {
		Integer id = Integer.decode(req.getParameter("super_id"));
		sDao.deleteSuper(id);
		
		rsp.sendRedirect("table");
	
	}
}