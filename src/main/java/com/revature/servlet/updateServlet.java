package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.SuperDAOImpl;
import com.revature.pojo.Super;

@WebServlet("/Update")
public class updateServlet extends HttpServlet{
	
	private SuperDAOImpl sDao = new SuperDAOImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse rsp) throws ServletException, IOException{
		Integer super_id = Integer.decode(req.getParameter("super_id"));
		Super superhuman = sDao.readSuper(super_id);
		Integer alignment = 0;
		
		if (req.getParameter("alias") != null && req.getParameter("alias").trim().length() >= 3) {
			superhuman.setAlias(req.getParameter("alias"));
		}
		
		if (req.getParameter("firstname") != null && req.getParameter("firstname").trim().length() >= 3) {
			if (req.getParameter("firstname").equals("null")) {
				superhuman.setFirstname(null);
			}
			else {
				superhuman.setFirstname(req.getParameter("firstname"));
			}
		}
		
		if (req.getParameter("lastname") != null && req.getParameter("lastname").trim().length() >= 3) {
			if (req.getParameter("lastname").equals("null")) {
				superhuman.setLastname(null);
			}
			else {
				superhuman.setLastname(req.getParameter("lastname"));
			}
		}
		
		if (!req.getParameter("alignment").equals("noChange")) {
			if (req.getParameter("alignment").equals("good")) {
				superhuman.setAlignment(1);;
			}
			else if (req.getParameter("alignment").equals("evil")) {
				superhuman.setAlignment(-1);
			}
			else {
				superhuman.setAlignment(0);;
			}
		}
		
		int eval = sDao.updateSuper(superhuman);
		System.out.println("Updated rows: " + eval);
		
		rsp.sendRedirect("table");
	}
	
}
