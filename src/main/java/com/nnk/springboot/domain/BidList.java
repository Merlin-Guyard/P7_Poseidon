package com.nnk.springboot.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.sql.Timestamp;

@Entity
@Table(name = "bidlist")
public class BidList {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="bidListId")
    int bidListId;

    @NotEmpty(message = "Field cannot be empty")
    @Column(name="account")
    String account;

    @NotEmpty(message = "Field cannot be empty")
    @Column(name="type")
    String type;

    @Positive(message = "Enter a positive number above 0")
    @Column(name="bidQuantity")
    double bidQuantity;

    @Column(name="askQuantity")
    double askQuantity;

    @Column(name="bid")
    double bid;

    @Column(name="ask")
    double ask;

    @Column(name="benchmark")
    String benchmark;

    @Column(name="bidListDate")
    Timestamp bidListDate;

    @Column(name="commentary")
    String commentary;

    @Column(name="security")
    String security;

    @Column(name="status")
    String status;

    @Column(name="trader")
    String trader;

    @Column(name="book")
    String book;

    @Column(name="creationName")
    String creationName;

    @Column(name="creationDate")
    Timestamp creationDate;

    @Column(name="revisionName")
    String revisionName;

    @Column(name="revisionDate")
    Timestamp revisionDate;

    @Column(name="dealName")
    String dealName;

    @Column(name="dealType")
    String dealType;

    @Column(name="side")
    String side;

    public BidList() {
    }

    public BidList(String account, String type, double bidQuantity) {
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }

    public int getBidListId() {
        return bidListId;
    }

    public void setBidListId(int bidListId) {
        this.bidListId = bidListId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBidQuantity() {
        return bidQuantity;
    }

    public void setBidQuantity(double bidQuantity) {
        this.bidQuantity = bidQuantity;
    }

    public double getAskQuantity() {
        return askQuantity;
    }

    public void setAskQuantity(double askQuantity) {
        this.askQuantity = askQuantity;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public double getAsk() {
        return ask;
    }

    public void setAsk(double ask) {
        this.ask = ask;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public Timestamp getBidListDate() {
        return bidListDate;
    }

    public void setBidListDate(Timestamp bidListDate) {
        this.bidListDate = bidListDate;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getCreationName() {
        return creationName;
    }

    public void setCreationName(String creationName) {
        this.creationName = creationName;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public String getRevisionName() {
        return revisionName;
    }

    public void setRevisionName(String revisionName) {
        this.revisionName = revisionName;
    }

    public Timestamp getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(Timestamp revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }
}
