package com.Tw.taxCalculator.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Tw.taxCalculator.dto.Item;
import com.Tw.taxCalculator.dto.Receipt;

@Service
public class BillingServiceImpl implements BillingService {

	@Autowired
	private TaxCalculationStrategy salesTaxCalculationStrategy;

	@Override
	public Receipt buyItems(List<Item> items) {
		BigDecimal total = items.stream().map(item -> item.getPrice().multiply(new BigDecimal(item.getQuantity())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal totalTax = items.stream()
				.map(item -> salesTaxCalculationStrategy.calculate(item).multiply(new BigDecimal(item.getQuantity())))
				.reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);
		return new Receipt(items, total.add(totalTax).setScale(2, RoundingMode.HALF_UP), totalTax);
	}

}
