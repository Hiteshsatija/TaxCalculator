package com.Tw.taxCalculator.dto;

import java.math.BigDecimal;
import java.util.List;

public class Receipt {
	private List<Item> items;

	private BigDecimal total;

	private BigDecimal tax;

	public Receipt(List<Item> items, BigDecimal total, BigDecimal tax) {
		this.items = items;
		this.total = total;
		this.tax = tax;
	}

	public List<Item> getItems() {
		return items;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public String toPrintable() {
		StringBuilder stringBuilder = new StringBuilder("Receipt:\nitems:\n");
		items.forEach(item -> stringBuilder.append(item.getQuantity()).append(" ").append(item.getName()).append(" : $").append(item.getPrice()).append("\n"));
		stringBuilder.append("Sales Tax : ").append(tax).append("\n").append("Tolal : $").append(total);
		return stringBuilder.toString();
	}

	@Override
	public String toString() {
		return "Receipt:\n" + " items:\n" + items + ", total=" + total + ", tax=" + tax + "]";
	}

}
