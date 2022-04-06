package com.Tw.taxCalculator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Tw.taxCalculator.dto.Item;

@SpringBootTest
public class SalesTaxCalculationStrategyTest {

	@Autowired
	private TaxCalculationStrategy salesTaxCalculationStrategy;

	@Test
	public void shouldReturnZeroTaxWhenBookIsPurchased() {
		Item book = new Item("book", 1, Item.Type.BOOK, new BigDecimal(12), false);

		BigDecimal tax = salesTaxCalculationStrategy.calculate(book);

		assertEquals(new BigDecimal(0), tax);
	}

	@Test
	public void shouldReturnReceiptWith_$1_TaxWhenTaxableItemAt_$10_IsPurchased() {
		Item drums = new Item("Drums", 1, Item.Type.OTHER, new BigDecimal(10), false);

		BigDecimal tax = salesTaxCalculationStrategy.calculate(drums);

		assertEquals(new BigDecimal(1), tax);

	}

	@Test
	public void shouldReturn_$15_WhenTaxableItemkAt_$100_IsPurchased() {
		Item importedDrums = new Item("imported drums", 1, Item.Type.OTHER, new BigDecimal(100), true);

		BigDecimal tax = salesTaxCalculationStrategy.calculate(importedDrums);

		assertEquals(new BigDecimal(15), tax);

	}

	@Test
	public void shouldReturn_$5_WhenUntaxedImportedeItemkAt_$100_IsPurchased() {
		Item importedBook = new Item("imported book", 1, Item.Type.BOOK, new BigDecimal(100), true);

		BigDecimal tax = salesTaxCalculationStrategy.calculate(importedBook);

		assertEquals(new BigDecimal(5), tax);

	}
}
