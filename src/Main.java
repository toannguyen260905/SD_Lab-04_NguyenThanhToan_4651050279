public class Main {
    public static void main(String[] args) {
        PaymentService service = new PaymentService(new CreditCardPayment());
        service.pay(500000);

        service.setPaymentStrategy(new PayPalPayment());
        service.pay(200000);

        service.setPaymentStrategy(new EWalletPayment());
        service.pay(100000);

        service.setPaymentStrategy(new BankTransferPayment());
        service.pay(1000000);
    }
}
