/*
 * Copyright (c) 2022 Tander, All Rights Reserved.
 */

package com.examplespringBoot.services;

import com.examplespringBoot.dao.UrlDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;

/**
 * Класс URLGeneratorServise
 */

@Service
public class URLGeneratorService {

	@Autowired
	private UrlDao urlDao;

	public String createURL(String longURL, String baseUrl) {

		String resultUrl = baseUrl + "/" + shortUrl(longURL);
		urlDao.addUrl(longURL, resultUrl);

		return resultUrl;
	}

	public String getURL(String longURL) {
		String shortUrl = urlDao.getURL(longURL);
		return shortUrl;
	}


	private static String shortUrl(String longURL) {
		// Вы можете настроить смешанный ключ перед передачей зашифрованного MD5
		String key = "test";
		// Чтобы использовать символы, которые генерируют URL
		String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h",
			"i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
			"u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
			"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
			"U", "V", "W", "X", "Y", "Z"
		};
		// MD5 шифрование входящего URL
		String hex = md5ByHex(key + longURL);

		String resUrl = null;
		// Выполнить битовую операцию И зашифрованных символов в соответствии с набором 8-битных шестнадцатеричных и 0x3FFFFFFF
		String sTempSubString = hex.substring(2 * 8, 2 * 8 + 8);

		// Здесь вам нужно использовать тип long для преобразования, потому что Inteper.parseInt () может обрабатывать только 31 бит, первый бит является знаковым битом, если вы не используете long, он будет вне диапазона
		long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
		String outChars = "";
		for (int j = 0; j < 6; j++) {
			// Выполнить побитовую операцию И между полученным значением и 0x0000003D,
			// чтобы получить индекс символов массива символов
			long index = 0x0000003D & lHexLong;
			// Добавить полученные символы
			outChars += chars[(int) index];
			// Перемещаем 5 бит вправо каждый раз
			lHexLong = lHexLong >> 5;
			// Сохраняем строку в выходном массиве соответствующего индекса
			resUrl = outChars;
		}
		return resUrl;
	}

	/**
	 * MD5 шифрование (32-битная прописная буква)
	 *
	 * @param src
	 * @return
	 */
	public static String md5ByHex(String src) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] b = src.getBytes();
			md.reset();
			md.update(b);
			byte[] hash = md.digest();
			String hs = "";
			String stmp = "";
			for (int i = 0; i < hash.length; i++) {
				stmp = Integer.toHexString(hash[i] & 0xFF);
				if (stmp.length() == 1)
					hs = hs + "0" + stmp;
				else {
					hs = hs + stmp;
				}
			}
			return hs.toUpperCase();
		} catch (Exception e) {
			return "";
		}
	}
}