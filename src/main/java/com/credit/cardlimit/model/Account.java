package com.credit.cardlimit.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
//    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long accountId;

    private long customerId;
    private double accountLimit;
    private double perTransactionLimit;
    private double lastAccountLimit;
    private double lastPerTransactionLimit;

    @Temporal(TemporalType.TIMESTAMP)
    private Date accountLimitUpdateTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date perTransactionLimitUpdateTime;

    public Account()
    {

    }

    public Account(Long accountId, long customerId, double accountLimit, double perTransactionLimit,
                   double lastAccountLimit, double lastPerTransactionLimit, Date accountLimitUpdateTime,
                   Date perTransactionLimitUpdateTime)
    {
        this.accountId = accountId;
        this.customerId = customerId;
        this.accountLimit = accountLimit;
        this.perTransactionLimit = perTransactionLimit;
        this.lastAccountLimit = lastAccountLimit;
        this.lastPerTransactionLimit = lastPerTransactionLimit;
        this.accountLimitUpdateTime = accountLimitUpdateTime;
        this.perTransactionLimitUpdateTime = perTransactionLimitUpdateTime;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
    public double getAccountLimit() {
        return accountLimit;
    }
    public void setAccountLimit(double accountLimit) {
        this.accountLimit = accountLimit;
    }
    public double getPerTransactionLimit() {
        return perTransactionLimit;
    }
    public void setPerTransactionLimit(double perTransactionLimit) {
        this.perTransactionLimit = perTransactionLimit;
    }
    public double getLastAccountLimit() {
        return lastAccountLimit;
    }
    public void setLastAccountLimit(double lastAccountLimit) {
        this.lastAccountLimit = lastAccountLimit;
    }
    public double getLastPerTransactionLimit() {
        return lastPerTransactionLimit;
    }
    public void setLastPerTransactionLimit(double lastPerTransactionLimit) {
        this.lastPerTransactionLimit = lastPerTransactionLimit;
    }
    public Date getAccountLimitUpdateTime() {
        return accountLimitUpdateTime;
    }
    public void setAccountLimitUpdateTime(Date accountLimitUpdateTime) {
        this.accountLimitUpdateTime = accountLimitUpdateTime;
    }
    public Date getPerTransactionLimitUpdateTime() {
        return perTransactionLimitUpdateTime;
    }
    public void setPerTransactionLimitUpdateTime(Date perTransactionLimitUpdateTime) {
        this.perTransactionLimitUpdateTime = perTransactionLimitUpdateTime;
    }

    @Override
    public String toString() {
        return "Account [accountId=" + accountId + ", customerId=" + customerId + ", accountLimit=" + accountLimit
                + ", perTransactionLimit=" + perTransactionLimit + ", lastAccountLimit=" + lastAccountLimit
                + ", lastPerTransactionLimit=" + lastPerTransactionLimit + ", accountLimitUpdateTime="
                + accountLimitUpdateTime + ", perTransactionLimitUpdateTime=" + perTransactionLimitUpdateTime + "]";
    }


}
