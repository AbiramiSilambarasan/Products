package com.e2e.products.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.e2e.products.model.ColorSwatchesPojo;
import com.e2e.products.model.ProductsObjPojo;
import com.e2e.products.model.ResponseProductsPojo;
import com.e2e.products.service.ProductsService;

/**
 * Controller layer class to controller of MVC
 * 
 * @author Abirami Silambarasan
 * 
 */
@RestController
public class RestApiController {

	@Autowired
	private Environment env;

	@RequestMapping(value = "/user")
	public ResponseProductsPojo ProductList(@RequestParam(value = "pricelabel", required = false) String pricelabel)
			throws JSONException, IOException {

		ResponseProductsPojo resPrdctPojo = new ResponseProductsPojo();
		String urlPath = env.getProperty("urlPath");
		ProductsObjPojo pdtsObjPojo = null;
		ColorSwatchesPojo clrSwtchPojo = null;

		ProductsService prdtService = new ProductsService();
		JSONObject json = prdtService.getProductRequest(urlPath);
		JSONArray result = json.getJSONArray("products");

		ArrayList<ProductsObjPojo> arrayProducts = new ArrayList<ProductsObjPojo>();

		try {

			for (int i = 0; i < result.length(); i++) {
				pdtsObjPojo = new ProductsObjPojo();
				JSONObject result1 = result.getJSONObject(i);
				String productId = result1.getString("productId");

				String title = result1.getString("title");

				JSONObject price = result1.getJSONObject("price");
				String was = price.getString("was");
				String then1 = price.getString("then1");
				String then2 = price.getString("then2");
				String now = "0.00";

				if (i == 1 || i == 20 || i == 21) {
					now = "0.00";
				} else {
					now = price.getString("now");
				}

				long nowRound = prdtService.getRoundLong(now);

				String nowPrice = prdtService.getNowPrice(nowRound);

				if (was.equals("")) {
					was = "0.00";
				} else {
					was = price.getString("was");
				}
				if (then1.equals("")) {
					then1 = "0.00";
				} else {
					then1 = price.getString("then1");
				}
				if (then2.equals("")) {
					then2 = "0.00";
				} else {
					then2 = price.getString("then2");
				}
				String priceLabel = prdtService.getPriceLabel(now, was, then1, then2, pricelabel);
				long wasRound = prdtService.getRoundLong(was);
				long priceRed = wasRound - nowRound;

				JSONArray values1 = result1.getJSONArray("colorSwatches");

				ArrayList<ColorSwatchesPojo> arrayColor = new ArrayList<ColorSwatchesPojo>();
				for (int j = 0; j < values1.length(); j++) {
					clrSwtchPojo = new ColorSwatchesPojo();

					JSONObject productsColor = values1.getJSONObject(j);

					String color = productsColor.getString("color");
					clrSwtchPojo.setColor(color);
					String basicColor = productsColor.getString("basicColor");
					clrSwtchPojo.setRgbColor(env.getProperty(basicColor));
					String skuId = productsColor.getString("skuId");
					clrSwtchPojo.setSkuid(skuId);

					arrayColor.add(clrSwtchPojo);
				}
				pdtsObjPojo.setProductId(productId);
				pdtsObjPojo.setTitle(title);
				pdtsObjPojo.setColorSwatches(arrayColor);
				pdtsObjPojo.setNowPrice(nowPrice);
				pdtsObjPojo.setPriceLabel(priceLabel);
				pdtsObjPojo.setPriceReduction(priceRed);

				Collections.sort(arrayProducts, ProductsObjPojo.priceRed);

				arrayProducts.add(pdtsObjPojo);

			}
			resPrdctPojo.setProducts(arrayProducts);

		} catch (Exception e) {
			System.out.println("Exception in res " + e);
		}

		return resPrdctPojo;
	}
}
