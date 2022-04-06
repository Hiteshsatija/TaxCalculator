package com.Tw.taxCalculator.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Tw.taxCalculator.dto.Item;
import com.Tw.taxCalculator.service.BillingService;

@RestController
public class ReceiptController {

	@Autowired
	private BillingService billingService;
	
	@PostMapping("/receipt")
	public String getReceipt(@RequestBody Item[] items) {
		
		return billingService.buyItems(Arrays.asList(items)).toPrintable();
	}
}
