package com.Tw.taxCalculator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Tw.taxCalculator.dto.Item;
import com.Tw.taxCalculator.dto.Receipt;

@SpringBootTest
public class BillingServiceTest {

	@Autowired
	private BillingService billingService;

	@Test
	public void ShouldReturnReceiptWithTotal_$12_and_tax_$0_WhenBookAt_$12_IsPurchased() {
		Item book = new Item("book", 1,Item.Type.BOOK,new BigDecimal(12), false);
		
		List<Item> items = new ArrayList<>();
		
		items.add(book);
		Receipt receipt = billingService.buyItems(items);
		assertEquals(new BigDecimal(0).setScale(2,RoundingMode.HALF_UP), receipt.getTax());
		assertEquals(new BigDecimal(12).setScale(2,RoundingMode.HALF_UP), receipt.getTotal());
	}
	
	@Test
	public void ShouldIncludeMultipleItemsAndTaxInTotalAmount() {
		Item book = new Item("book", 1,Item.Type.BOOK,new BigDecimal(12), false);
		Item drums = new Item("Drums", 2,Item.Type.OTHER,new BigDecimal(10), false);
		List<Item> items = new ArrayList<>();
		
		items.add(book);
		items.add(drums);
		Receipt receipt = billingService.buyItems(items);
		
		assertEquals(new BigDecimal(2).setScale(2,RoundingMode.HALF_UP), receipt.getTax());
		assertEquals(new BigDecimal(34).setScale(2,RoundingMode.HALF_UP), receipt.getTotal());
	}
	
	@Test
	public void ShouldReturnReceiptWith_$15_TaxWhenTaxableItemkAt_$100_IsPurchased() {
		Item book = new Item("book", 1,Item.Type.BOOK,new BigDecimal(12.49), false);
		Item musicCD = new Item("music CD", 1,Item.Type.OTHER,new BigDecimal(14.99), false);
		Item chocolate = new Item("chocolate", 1,Item.Type.FOOD,new BigDecimal(0.85), false);	
		List<Item> items = new ArrayList<>();
		items.add(book);
		items.add(musicCD);
		items.add(chocolate);
		
		Receipt receipt = billingService.buyItems(items);
		
		assertEquals(new BigDecimal(1.50).setScale(2,RoundingMode.HALF_UP), receipt.getTax());
		assertEquals(new BigDecimal(29.83).setScale(2,RoundingMode.HALF_UP), receipt.getTotal());
	}
}
