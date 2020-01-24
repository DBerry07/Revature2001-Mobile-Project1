package com.revature.util;

import java.sql.Driver;
//import org.apache.log4j.Logger;

import com.revature.pojo.Super;

public class Logger {

		static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getRootLogger();
		
		public static void logSQL(String sql) {
			log.debug("SQL Statement: ");
			log.debug("   " + sql);
			return;
		}
		public static void logSuper(Super superhuman) {
			log.debug("Pulled Super:");
			log.debug("	ID: " + superhuman.getID());
			log.debug("	Alias: " + superhuman.getAlias());
			log.debug("	Firstname: " + superhuman.getFirstname());
			log.debug(" Lastname: " + superhuman.getLastname());
			log.debug(" Alignment: " + superhuman.getAlignment());
		}
		public static void closed() {
			log.debug("CONNECTION CLOSED");
		}
		public static void result(Object obj) {
			log.debug("RESULT: " + obj);
		}
		public static void log(String str) {
			log.debug(str);
	}

}
