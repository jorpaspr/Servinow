
package com.servinow.payment;

import android.content.Intent;

public interface IPaymentActivity {
	void onPaymentSuccesful(Payment.Method method);
	void onPaymentProcess(Payment.Method method);
	void onPaymentCanceled(Payment.Method method);
	void onPaymentFailure(Payment.Method method);
}
