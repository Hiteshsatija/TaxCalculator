package com.Tw.taxCalculator.dto;

import java.math.BigDecimal;

public class Item {

	public enum Type {

		FOOD, MEDICAL_PRODUCT, BOOK, OTHER
	}

	private String name;

	private int quantity;

	private boolean isImported;

	private Type type;

	private BigDecimal price;
	
	public Item() {
		
	}

	public Item(String name, int quantity, Type type, BigDecimal price, boolean isImported) {

		this.name = name;
		this.quantity = quantity;
		this.type = type;
		this.price = price;
		this.isImported = isImported;

	}

	public boolean isImported() {
		return isImported;
	}

	public void setIsImported(boolean isImported) {
		this.isImported = isImported;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public boolean isStandardTaxApplicable() {
		return type == Type.OTHER;
	}

	public boolean isImportTaxApplicable() {
		return isImported;
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", quantity=" + quantity + ", isImported=" + isImported + ", type=" + type
				+ ", price=" + price + "]";
	}


}