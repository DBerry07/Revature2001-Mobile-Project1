package com.revature.test;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.revature.dao.SuperDAOImpl;
import com.revature.pojo.Super;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test {

	private SuperDAOImpl dao = new SuperDAOImpl();
	private Super aSuper = new Super("Tester", "John", "Doe", 0);

	@org.junit.Test
	public void test1ReadAll() {
		ResultSet rs = null;
		rs = dao.readAllSuper();
		assertNotNull(rs);
	}

	@org.junit.Test
	public void test2InsertSuper() {
		int eval = 0;
		Super newSuper = aSuper;
		eval = dao.createSuper(newSuper);
		assertEquals(eval, 1);

	}

	@org.junit.Test
	public void test3FindSuper() {
		String alias = aSuper.getAlias();
		Super newSuper = null;
		newSuper = dao.readSuper(alias);
		assertEquals(aSuper.getAlias(), newSuper.getAlias());
	}

	@org.junit.Test
	public void test4DeleteSuper() {
		int eval = 0;
		eval = dao.deleteSuper(aSuper.getAlias());
		assertEquals(1, eval);
	}

}
