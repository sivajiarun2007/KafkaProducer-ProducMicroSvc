package com.appsdeveloperblog.ws.products.model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Product {

	private String name;
	private BigDecimal price;
	private BigInteger quantity;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigInteger getQuantity() {
		return quantity;
	}

	public void setQuantity(BigInteger quantity) {
		this.quantity = quantity;
	}

}
