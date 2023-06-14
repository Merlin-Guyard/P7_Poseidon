package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.sql.Timestamp;


@Entity
@Table(name = "trade")
public class Trade {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="tradeId")
    int tradeId;

    @NotEmpty(message = "Field cannot be empty")
    @Column(name="account")
    String account;

    @NotEmpty(message = "Field cannot be empty")
    @Column(name="type")
    String type;

    @Positive(message = "Enter a positive number above 0")
    @Column(name="buyQuantity")
    double buyQuantity;

    @Column(name="sellQuantity")
    double sellQuantity;

    @Column(name="buyPrice")
    double buyPrice;

    @Column(name="sellPrice")
    double sellPrice;

    @Column(name="benchmark")
    String benchmark;

    @Column(name="tradeDate")
    Timestamp tradeDate;

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

    @Column(name="sourceListId")
    String sourceListId;

    @Column(name="side")
    String side;

    public Trade() {
    }

    public Trade(String account, String type, double buyQuantity) {
        this.account = account;
        this.type = type;
        this.buyQuantity = buyQuantity;
    }

    public Trade(String tradeAccount, String type) {
    }

    public int getTradeId() {
        return tradeId;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
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

    public double getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(double buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    public double getSellQuantity() {
        return sellQuantity;
    }

    public void setSellQuantity(double sellQuantity) {
        this.sellQuantity = sellQuantity;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public Timestamp getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Timestamp tradeDate) {
        this.tradeDate = tradeDate;
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

    public String getSourceListId() {
        return sourceListId;
    }

    public void setSourceListId(String sourceListId) {
        this.sourceListId = sourceListId;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }
}
