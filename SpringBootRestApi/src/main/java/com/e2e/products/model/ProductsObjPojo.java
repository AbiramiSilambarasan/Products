package com.e2e.products.model;

/**
 * Model class for getter and setter methods
 * 
 * @author Abirami Silambarasan
 * 
 */
import java.util.ArrayList;
import java.util.Comparator;

public class ProductsObjPojo {
	public String productId;
	public String title;
	public String nowPrice;
	public String priceLabel;
	public long priceReduction;

	public ArrayList<ColorSwatchesPojo> colorSwatches;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNowPrice() {
		return nowPrice;
	}

	public void setNowPrice(String nowPrice) {
		this.nowPrice = nowPrice;
	}

	public String getPriceLabel() {
		return priceLabel;
	}

	public void setPriceLabel(String priceLabel) {
		this.priceLabel = priceLabel;
	}

	public long getPriceReduction() {
		return priceReduction;
	}

	public void setPriceReduction(long priceReduction) {
		this.priceReduction = priceReduction;
	}

	public ArrayList<ColorSwatchesPojo> getColorSwatches() {
		return colorSwatches;
	}

	public void setColorSwatches(ArrayList<ColorSwatchesPojo> arrayColor) {
		this.colorSwatches = arrayColor;
	}

	/* Comparator for sorting the list by price Reduction */
	public static Comparator<ProductsObjPojo> priceRed = new Comparator<ProductsObjPojo>() {

		public int compare(ProductsObjPojo p1, ProductsObjPojo p2) {

			int pRed1 = (int) p1.getPriceReduction();
			int pRed2 = (int) p2.getPriceReduction();

			return pRed2 - pRed1;
		}

	};

}
