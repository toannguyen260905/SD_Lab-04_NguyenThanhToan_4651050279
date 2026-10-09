public class PaymentService {
    private PaymentStrategy paymentStrategy;

    public PaymentService(PaymentStrategy paymentStrategy) {
        setPaymentStrategy(paymentStrategy);
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        if (paymentStrategy == null) {
            throw new IllegalArgumentException("Phương thức thanh toán không được để trống");
        }
        this.paymentStrategy = paymentStrategy;
    }

    public void pay(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("Số tiền thanh toán phải là số hữu hạn lớn hơn 0");
        }
        paymentStrategy.pay(amount);
    }
}
