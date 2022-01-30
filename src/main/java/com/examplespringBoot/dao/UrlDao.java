/*
 * Copyright (c) 2022 Tander, All Rights Reserved.
 */

package com.examplespringBoot.dao;

/**
 * Класс UrlDao
 */
public interface UrlDao {


	void addUrl(String originalURL, String resultUrl);

	String getURL(String longURL);
}