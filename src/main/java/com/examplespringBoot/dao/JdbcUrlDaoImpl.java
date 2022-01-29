/*
 * Copyright (c) 2022 Tander, All Rights Reserved.
 */

package com.examplespringBoot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;

/**
 * Класс JdbcUrlDao
 */
@Repository
public class JdbcUrlDaoImpl implements UrlDao {
	@Autowired
	private ConnectionManager connectionManager;

	@Override
	public void addUrl(String originalURL, String resultUrl) {
		// добавление в БД данных ориг и коротких ссылок
		String SQL_add_url = "INSERT INTO shorter_url(original_url, sorted_url) VALUES('?', '?')";

		try (Connection connection = connectionManager.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(SQL_add_url)) {
			preparedStatement.setString(1, originalURL);
			preparedStatement.setString(2, resultUrl);
			preparedStatement.execute();
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}
}