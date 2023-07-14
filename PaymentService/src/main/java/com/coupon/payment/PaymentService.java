package com.coupon.payment;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
public class PaymentService {
	
    private static final String KEY = "rzp_test_YkZzDRMXtOpPwc";
    
	private static final String KEY_SECRET = "S7h4ZgyYusfi39LE64wUR7po";
	
	private static final String CURRENCY = "INR";
	
	
	public TransactionDetails createTransaction(double amount) {
		try {
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("amount", amount);
			jsonObject.put("currency", CURRENCY);
			
			RazorpayClient razorpayClient = new RazorpayClient(KEY, KEY_SECRET);
			
			Order order = razorpayClient.orders.create(jsonObject);
			
			return prepareTransactionDetails(order);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	private TransactionDetails prepareTransactionDetails(Order order) {
		String orderId = order.get("id");
		String currency = order.get("currency");
		Integer amount = order.get("amount");
		
		TransactionDetails details = new TransactionDetails(orderId,currency,amount,KEY);
		return details;
	}

}
