
package com.servinow.payment;

import android.content.Intent;

public interface IPaymentCallback {
	void onPaymentSuccesful(Payment.Method method);
	void onNormalPaymentProcess();
	void onPaymentCanceled(Payment.Method method);
	void onPaymentFailure(Payment.Method method);
}
