
public interface IPaymentActivity {
	void onPaymentSuccesful();
	void onPaymentProcess();
	void onPaymentCanceled();
	void onPaymentFailure();
}
