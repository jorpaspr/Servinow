import java.math.BigDecimal;

import com.paypal.android.MEP.PayPal;
import com.paypal.android.MEP.PayPalActivity;
import com.paypal.android.MEP.PayPalInvoiceData;
import com.paypal.android.MEP.PayPalPayment;

import android.app.Activity;
import android.content.Intent;


public class Payment {
	public static enum Method {
		NORMAL,
		PAYPAL
	};
	
	private Activity activity;
	private Payment.Method paymentMethod;
	
	public Payment(Activity activity) {
		this.activity = activity;
	}
	
	public void setPaymentMethod(){
		//Load a dialog in order to select the payment method
		
	}
	
	public void pay(String emailRecipient, String order, float tax){
		switch (paymentMethod) {
		case PAYPAL:
			usePaypalMethod(emailRecipient, order, tax);
			break;
		case NORMAL:
			useNormalMethod(order);
			break;
		default:
			break;
		}
	}
	
	private void usePaypalMethod(String emailRecipient, String order, float tax){
		PayPal pp = PayPal.getInstance();
		if (pp == null) {  // Test to see if the library is already initialized
			// This main initialization call takes your Context, AppID, and target server
			pp = PayPal.initWithAppID(activity, "APP-80W284485P519543T", PayPal.ENV_SANDBOX);

			// Set the language for the library
			pp.setLanguage("en_US");
		}
		
		PayPalPayment payment = new PayPalPayment();

		payment.setSubtotal(new BigDecimal(tax)); //Subtotal

		payment.setCurrencyType("USD");

		payment.setRecipient(emailRecipient);

		payment.setPaymentType(PayPal.PAYMENT_TYPE_GOODS);
		
		// PayPalInvoiceData can contain tax and shipping amounts, and an
		// ArrayList of PayPalInvoiceItem that you can fill out.
		// These are not required for any transaction.
		PayPalInvoiceData invoice = new PayPalInvoiceData();

		// Set the tax amount
		invoice.setTax(new BigDecimal(tax));

		Intent checkoutIntent = PayPal.getInstance().checkout(payment, activity);

		activity.startActivityForResult(checkoutIntent, 1); 
	}
	
	private void useNormalMethod(String order){
		
		
	}
	
	public void onActivityResults(int requestCode, int resultCode, Intent data) {

		   switch(resultCode) {

		      case Activity.RESULT_OK:
		    	  	((IPaymentActivity) activity).onPaymentSuccesful();
		          break;

		       case Activity.RESULT_CANCELED:
		    	   ((IPaymentActivity) activity).onPaymentCanceled();
		           break;

		       case PayPalActivity.RESULT_FAILURE:
		    	   ((IPaymentActivity) activity).onPaymentFailure();
		  }

		}
}
