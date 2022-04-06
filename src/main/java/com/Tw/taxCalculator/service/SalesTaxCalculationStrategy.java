package com.Tw.taxCalculator.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.Tw.taxCalculator.dto.Item;
/**
 * This class contains the logic of calculating tax for every item.
 * @author hiteshsatija
 *
 */
@Service
public class SalesTaxCalculationStrategy implements TaxCalculationStrategy {

	private BigDecimal salesTax = new BigDecimal(10);
	private BigDecimal importTax = new BigDecimal(5);

	private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

	@Override
	public BigDecimal calculate(Item item) {

		BigDecimal taxes = BigDecimal.ZERO;

		if (item.isStandardTaxApplicable()) {
			taxes = taxes.add(item.getPrice().multiply(salesTax).divide(ONE_HUNDRED));
		}

		if (item.isImportTaxApplicable()) {
			taxes = taxes.add(item.getPrice().multiply(importTax).divide(ONE_HUNDRED));
		}

		return taxes;
	}

}
