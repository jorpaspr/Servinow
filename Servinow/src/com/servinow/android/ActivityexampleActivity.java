package com.servinow.android;

import com.servinow.android.QRSystem.QRResultCallback;
import com.servinow.android.domain.LineaPedido;
import com.servinow.android.domain.Pedido;
import com.servinow.android.domain.Producto;
import com.servinow.android.domain.Restaurant;
import com.servinow.android.restaurantCacheSyncSystem.CacheRestaurantSystem;
import com.servinow.payment.IPaymentCallback;
import com.servinow.payment.Payment;
import com.servinow.payment.Payment.Method;

import java.io.Console;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.j256.ormlite.dao.ForeignCollection;
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
import android.widget.Toast;

public class ActivityexampleActivity extends SherlockFragmentActivity implements IPaymentCallback {
	private Payment payment;
	private CacheRestaurantSystem startRestDEBUG;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityexample);
        
        
        //Restaurant rest = new Restaurant();
        //rest.setEmailPayPalAccount("igcogi_1352626122_biz@gmail.com");
        //rest.setTax(21);
        
        List<Pedido> pedidos = new LinkedList<Pedido>();
        
        Pedido pedido = new Pedido();
        
        List<LineaPedido> orderLines = new LinkedList<LineaPedido>();
        
        Producto p1 = new Producto();
        p1.setPrecio(5);
        Producto p2 = new Producto();
        p2.setPrecio(9);
        Producto p3 = new Producto();
        p3.setPrecio(2);
        
        LineaPedido lp1 = new LineaPedido();
        lp1.setCantidad(2);
        lp1.setProducto(p1);
        orderLines.add(lp1);
        
        LineaPedido lp2 = new LineaPedido();
        lp2.setCantidad(1);
        lp2.setProducto(p2);
        orderLines.add(lp2);
        
        LineaPedido lp3 = new LineaPedido();
        lp3.setCantidad(2);
        lp3.setProducto(p3);
        orderLines.add(lp3);
        
        pedido.setLineas(orderLines);
        
        pedidos.add(pedido);
        
        //payment = new Payment(this, rest, pedidos);
  		//payment.setPaymentMethod();        
        
        
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
    public void onPaymentSuccesful(Method method) {
    	switch(method){
    	case NORMAL:
    		break;
    	case PAYPAL:
    		Intent i = new Intent(ActivityexampleActivity.this, MainActivity.class);
        	startActivity(i);

        	finish();
    		break;
    	}

    }

    @Override
    public void onPaymentProcess(Method method) {
    	switch(method){
	    	case NORMAL:
	    		Toast.makeText(this, R.string.activity_ticket_normalpaymentinprocess, Toast.LENGTH_LONG).show();
	    		break;
	    	case PAYPAL:
	    		break;
    	}
    }

    @Override
    public void onPaymentCanceled(Method method) {
  	  Toast.makeText(this, R.string.activity_ticket_paymentcancelled, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPaymentFailure(Method method) {
  	  Toast.makeText(this, R.string.activity_ticket_paymentfailure, Toast.LENGTH_LONG).show();
    }
}
