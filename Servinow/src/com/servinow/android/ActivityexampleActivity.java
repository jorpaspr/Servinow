package com.servinow.android;

import com.servinow.payment.IPaymentCallback;
import com.servinow.payment.Payment;

import java.io.Console;
import java.math.BigDecimal;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.paypal.android.MEP.PayPal;
import com.paypal.android.MEP.PayPalActivity;
import com.paypal.android.MEP.PayPalInvoiceData;
import com.paypal.android.MEP.PayPalPayment;
import com.paypal.android.MEP.PaymentAdjuster;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;

public class ActivityexampleActivity extends SherlockFragmentActivity implements IPaymentCallback {
	private Payment payment;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityexample);
        
        //payment = new Payment(this, "igcogi_1352626122_biz@gmail.com", 1000, 21);
        
        //payment.setPaymentMethod();
        
        /*PayPal pp = PayPal.getInstance();
		if (pp == null) {  // Test to see if the library is already initialized
			// This main initialization call takes your Context, AppID, and target server
			pp = PayPal.initWithAppID(this, "APP-80W284485P519543T", PayPal.ENV_SANDBOX);

			// Set the language for the library
			pp.setLanguage("es_ES");
		}
		
		PayPalPayment payment = new PayPalPayment();

		payment.setSubtotal(new BigDecimal(20)); //Subtotal

		payment.setCurrencyType("EUR");

		payment.setRecipient("igcogi_1352626122_biz@gmail.com");

		payment.setPaymentType(PayPal.PAYMENT_TYPE_GOODS);
		
		// PayPalInvoiceData can contain tax and shipping amounts, and an
		// ArrayList of PayPalInvoiceItem that you can fill out.
		// These are not required for any transaction.
		PayPalInvoiceData invoice = new PayPalInvoiceData();

		// Set the tax amount
		invoice.setTax(new BigDecimal(20));

		Intent checkoutIntent = PayPal.getInstance().checkout(payment, this);

		startActivityForResult(checkoutIntent, 1); */
        
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	payment.onActivityResult(requestCode, resultCode, data);
    }

	@Override
	public void onPaymentSuccesful(Payment.Method method) {
		switch (method) {
			case NORMAL:
				Log.i("ExampleActivity", "Cuenta solicitada. Espere al camarero para registrar el pago.");
				break;
			case PAYPAL:
	
				break;
			default:
				break;
		}
		
	}

	@Override
	public void onPaymentProcess(Payment.Method method) {
		switch (method) {
			case NORMAL:
				Log.i("ExampleActivity", "Solicitando cuenta...");
				break;
			case PAYPAL:
	
				break;
			default:
				break;
		}
		
	}

	@Override
	public void onPaymentCanceled(Payment.Method method) {
		switch (method) {
			case NORMAL:
				Log.i("ExampleActivity", "Solicitud de cuenta cancelada.");
				break;
			case PAYPAL:
	
				break;
			default:
				break;
		}
		
	}

	@Override
	public void onPaymentFailure(Payment.Method method) {
		switch (method) {
			case NORMAL:
				Log.i("ExampleActivity", "Fallo al solicitar cuenta.");
				break;
			case PAYPAL:

				break;
			default:
				break;
		}
		
	}
}
