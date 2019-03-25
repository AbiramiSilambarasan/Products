package com.e2e.products.model;

/**
 * Model class for getter and setter methods
 * 
 * @author Abirami Silambarasan
 * 
 */
import java.util.ArrayList;

public class ResponseProductsPojo {

	public ArrayList<ProductsObjPojo> Products;

	public ArrayList<ProductsObjPojo> getProducts() {
		return Products;
	}

	public void setProducts(ArrayList<ProductsObjPojo> products) {
		Products = products;
	}

}
