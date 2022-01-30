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
		String SQL_select = "SELECT shorted_url FROM shorter_url WHERE original_url=?";
		String SQL_add_url = "INSERT INTO shorter_url(original_url, shorted_url) VALUES(?, ?)";

		try(Connection connection = connectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_select)) {
		preparedStatement.executeQuery();	//
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try (Connection connection = connectionManager.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(SQL_add_url)) {
			preparedStatement.setString(1, originalURL);
			preparedStatement.setString(2, resultUrl);
			preparedStatement.execute();
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}
// SQL вызов с базы короткой URL
//	query оригинальной  URL
	public String getLinkURL() {
		String SQL_shortURL="SELECT ";
		return null;
	}
	@Override
	public String getURL(String longURL) {

		return null;
	}
}