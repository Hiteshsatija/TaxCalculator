package com.Tw.taxCalculator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Tw.taxCalculator.dto.Item;
import com.Tw.taxCalculator.dto.Receipt;

@Service
public interface BillingService {

	Receipt buyItems(List<Item> item);
}
