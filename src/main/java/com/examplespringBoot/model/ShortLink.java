/*
 * Copyright (c) 2022 Tander, All Rights Reserved.
 */

package com.examplespringBoot.model;

/**
 * Класс ShortLink
 */


public class ShortLink {
	Integer id;
	String originalUrl;
	String shortUrl;

	public ShortLink(Integer id, String originalUrl, String shortUrl) {
		this.id = id;
		this.originalUrl = originalUrl;
		this.shortUrl = shortUrl;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public String getOriginalUrl() {
		return originalUrl;
	}


	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}


	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
}

