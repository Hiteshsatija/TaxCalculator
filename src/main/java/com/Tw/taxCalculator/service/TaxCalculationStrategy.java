package com.Tw.taxCalculator.service;

import java.math.BigDecimal;

import com.Tw.taxCalculator.dto.Item;

public interface TaxCalculationStrategy {

	BigDecimal calculate (Item item);
}
