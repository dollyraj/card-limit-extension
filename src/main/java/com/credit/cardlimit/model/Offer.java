package com.credit.cardlimit.model;


import com.credit.cardlimit.enums.LimitType;
import com.credit.cardlimit.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.util.Date;



@Entity
public class Offer {

    @Id
    private Long limitId;

    @Column(nullable=false)
    private double newLimit;

    @Temporal(TemporalType.TIMESTAMP)
    private Date offerActivationTime;

    @Column(nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date offerExpiryTime;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private LimitType limitType;

    @Enumerated(EnumType.STRING)
    private Status status=null;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    @JsonIgnore
    private Account account;

    public Offer() {

    }

    public Offer(Long limitId, double newLimit, Date offerActivationTime, Date offerExpiryTime, LimitType limitType,
                 Status status, Account account)
    {
        this.limitId = limitId;
        this.newLimit = newLimit;
        this.offerActivationTime = offerActivationTime;
        this.offerExpiryTime = offerExpiryTime;
        this.limitType = limitType;
        this.status = status;
        this.account = account;
    }

    public LimitType getLimitType() {
        return limitType;
    }

    public void setLimitType(LimitType limitType) {
        this.limitType = limitType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getLimitId() {
        return limitId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setLimitId(Long limitId) {
        this.limitId = limitId;
    }

    public double getNewLimit() {
        return newLimit;
    }

    public void setNewLimit(double newLimit) {
        this.newLimit = newLimit;
    }

    public Date getOfferActivationTime() {
        return offerActivationTime;
    }

    public void setOfferActivationTime(Date offerActivationTime) {
        this.offerActivationTime = offerActivationTime;
    }

    public Date getOfferExpiryTime() {
        return offerExpiryTime;
    }

    public void setOfferExpiryTime(Date offerExpiryTime) {
        this.offerExpiryTime = offerExpiryTime;
    }

    @Override
    public String toString() {
        return "Offer [limitId=" + limitId + ", newLimit=" + newLimit + ", offerActivationTime=" + offerActivationTime
                + ", offerExpiryTime=" + offerExpiryTime + ", limitType=" + limitType + ", status=" + status
                + ", account=" + account + "]";
    }


}

