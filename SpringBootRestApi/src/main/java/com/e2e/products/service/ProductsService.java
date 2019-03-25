package com.e2e.products.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Service layer class to do internal functionality
 * 
 * @author Abirami Silambarasan
 * 
 */
public class ProductsService {

	/**
	 * getProductRequest method to get data from rest api link
	 * 
	 * @param urlPath
	 *            has rest api link value
	 * 
	 */
	public JSONObject getProductRequest(String urlPath) throws JSONException, IOException {
		URL url;
		HttpURLConnection conn;
		JSONObject json = null;
		try {
			url = new URL(urlPath);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			StringBuilder sb = new StringBuilder();
			String output;
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
			json = new JSONObject(sb.toString());
			conn.disconnect();
		} catch (Exception e) {
			System.out.println("Exception in getProductRequest" + e);
		}
		return json;
	}

	/**
	 * getProductRequest method
	 * 
	 * @param nowRound
	 * 
	 * 
	 */
	public String getNowPrice(long nowRound) {
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		String nowPrice = "", format = "";
		try {
			if (nowRound < 10) {
				format = decimalFormat.format(nowRound);
				nowPrice = "£" + format;
			} else {
				nowPrice = "£" + nowRound;
			}
		} catch (Exception e) {
			System.out.println("Exception in getNowPrice method. " + e);
		}
		return nowPrice;
	}

	/**
	 * getProductRequest method to do condition check
	 * 
	 * @param String
	 *            now, String was, String then1, String then2, String showwasnow
	 * 
	 * 
	 */
	public String getPriceLabel(String now, String was, String then1, String then2, String showwasnow) {
		String priceLabel = "";
		String then = "", ShowWasNow = "", ShowWasThenNow = "", ShowPercDscount = "";
		ShowWasNow = was + "," + now;
		if (!then2.equals("")) {
			then = then2;
		} else if (!then1.equals("")) {
			then = then1;
		} else {
			then = "";
		}
		if (!then.equals("")) {
			ShowWasThenNow = was + "," + then + "," + now;
		} else {
			ShowWasThenNow = was + "," + now;
		}
		ShowPercDscount = "5% off" + "-" + now;
		switch (showwasnow) {
		case "ShowWasNow":
			priceLabel = ShowWasNow;
			break;
		case "ShowWasThenNow":
			priceLabel = ShowWasThenNow;
			break;
		case "ShowPercDscount":
			priceLabel = ShowPercDscount;
			break;
		default:
			priceLabel = ShowWasNow;
			break;
		}
		return priceLabel;
	}

	/**
	 * getRoundLong method to roundup the input
	 * 
	 * @param now
	 *            has the value
	 * 
	 * 
	 */
	public long getRoundLong(String now) {
		double nowString = Double.parseDouble(now);
		long nowRound = Math.round(nowString);
		return nowRound;
	}

}
