package Entities;

import jakarta.persistence.*;

@Entity
@Table(name ="bank_accounts")
public class BankAccount extends BillingDetail{
    @Enumerated(EnumType.STRING)
    private final BillingType billingType;

    @Column(name = "bank_name", nullable = false)
    private String bankName;

    @Column(name = "swift_code", nullable = false)
    private String swiftCode;

    public BankAccount() {
        super();
        this.billingType = BillingType.BANK_ACCOUNT;
    }

    public BankAccount(String bankName, String swiftCode) {
        this();
        this.bankName = bankName;
        this.swiftCode = swiftCode;
     }

    public BillingType getBillingType() {
        return billingType;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }
}