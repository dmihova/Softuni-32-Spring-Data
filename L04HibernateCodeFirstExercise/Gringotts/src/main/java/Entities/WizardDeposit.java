package Entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "wizard_deposits")
public class WizardDeposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;

    @Column(name = "first_name", length=60)
    private String firstName;

    @Column(name = "last_name",nullable=false, length=60)
    private String lastName;

    @Column(length=1000)
    private String notes;

    @Column(nullable=false)
    private int age;

    @Column(name="magic_wand_creator", length=100)
    private String magicWandCreator;

    @Column(name="magic_wand_size" ) // Number in range [1, 215-1]
    private byte magicWandSize;

    @Column(name="deposit_group", length=20)
    private String depositGroup;

    @Column(name="deposit_start_date" )
    private Date depositStartDate;

    @Column(name="deposit_amount" )
    private BigDecimal depositAmount;

    @Column(name="deposit_interest" )
    private double depositInterest;

    @Column(name="deposit_charge" )
    private double depositCharge;

    @Column(name="deposit_expiration_date" )
    private Date depositExpirationDate;

    @Column(name="is_deposit_expired" )
    private boolean isDepositExpired;

    public WizardDeposit() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMagicWandCreator() {
        return magicWandCreator;
    }

    public void setMagicWandCreator(String magicWandCreator) {
        this.magicWandCreator = magicWandCreator;
    }

    public byte getMagicWandSize() {
        return magicWandSize;
    }

    public void setMagicWandSize(byte magicWandSize) {
        this.magicWandSize = magicWandSize;
    }

    public String getDepositGroup() {
        return depositGroup;
    }

    public void setDepositGroup(String depositGroup) {
        this.depositGroup = depositGroup;
    }

    public Date getDepositStartDate() {
        return depositStartDate;
    }

    public void setDepositStartDate(Date depositStartDate) {
        this.depositStartDate = depositStartDate;
    }

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    public double getDepositInterest() {
        return depositInterest;
    }

    public void setDepositInterest(double depositInterest) {
        this.depositInterest = depositInterest;
    }

    public double getDepositCharge() {
        return depositCharge;
    }

    public void setDepositCharge(double depositCharge) {
        this.depositCharge = depositCharge;
    }

    public Date getDepositExpirationDate() {
        return depositExpirationDate;
    }

    public void setDepositExpirationDate(Date depositExpirationDate) {
        this.depositExpirationDate = depositExpirationDate;
    }

    public boolean isDepositExpired() {
        return isDepositExpired;
    }

    public void setDepositExpired(boolean depositExpired) {
        isDepositExpired = depositExpired;
    }
}
