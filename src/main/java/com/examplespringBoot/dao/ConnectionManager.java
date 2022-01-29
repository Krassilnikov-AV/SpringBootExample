/*
 * Copyright (c) 2022 Tander, All Rights Reserved.
 */

package com.examplespringBoot.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;

/**
 * Класс InitionalConnect
 */
@Service
class ConnectionManager {
	@Value("${db.url}")
	String dbUrl;
	@Value("${db.username}")
	String dbUsername;
	@Value("${db.password}")
	String dbPassword;
	@Value("${db.driverClassName}")
	String driverClassName;

	public Connection getConnection() {
		try {
			Class.forName(driverClassName);
			return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		} catch (SQLException | ClassNotFoundException e) {
			throw new IllegalStateException(e);
		}
	}
}