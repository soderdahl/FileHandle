package se.kth.payment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaymentBundle {
    private String accountNumber;
    private Date paymentDate;//??
    private String currency;
    private BigDecimal amount;
    private List<Payment> paymentList = new ArrayList();

    public PaymentBundle() {
    }

    public PaymentBundle(String accountNumber, Date paymentDate, String currency, BigDecimal amount, List<Payment> paymentList) {
        this.accountNumber = accountNumber;
        this.paymentDate = paymentDate;
        this.currency = currency;
        this.amount = amount;
        this.paymentList = paymentList;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    @Override
    public String toString() {
        return "PaymentBundle{" +
                "accountNumber='" + accountNumber + '\'' +
                ", paymentDate=" + paymentDate +
                ", currency='" + currency + '\'' +
                ", amount=" + amount +
                ", paymentList=" + paymentList +
                '}';
    }

    /**
     * To check if the total amount is correct by comparing with sum of each amount
     *
     * @param paymentList
     * @param paymentBundle
     * @return
     */
    public boolean controlTotalAmount(List<Payment> paymentList, PaymentBundle paymentBundle) {
        double sumOfAmounts = paymentList.stream().mapToDouble(payment -> payment.getAmount().doubleValue()).sum();
        double totalAmount = paymentBundle.getAmount().doubleValue();
        if (sumOfAmounts == totalAmount) {
            return true;
        }
        System.out.println("Sum of amounts: " + sumOfAmounts);
        System.out.println("Total amount: " + totalAmount);
        return false;
    }
}
