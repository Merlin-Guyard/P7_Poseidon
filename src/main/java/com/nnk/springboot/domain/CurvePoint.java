package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

import java.sql.Timestamp;

@Entity
@Table(name = "curvepoint")
public class CurvePoint {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    int id;

    @Positive(message = "Enter a positive number above 0")
    @Column(name="curveId")
    int curveId;

    @Column(name="asOfDate")
    Timestamp asOfDate;

    @Positive(message = "Enter a positive number above 0")
    @Column(name="term")
    double term;

    @Positive(message = "Enter a positive number above 0")
    @Column(name="valueCP")
    double valueCP;

    @Column(name="creationDate")
    Timestamp creationDate;

    public CurvePoint() {
    }

    public CurvePoint(int curveId, double term, double valueCP) {
        this.curveId = curveId;
        this.term = term;
        this.valueCP = valueCP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurveId() {
        return curveId;
    }

    public void setCurveId(int curveId) {
        this.curveId = curveId;
    }

    public Timestamp getAsOfDate() {
        return asOfDate;
    }

    public void setAsOfDate(Timestamp asOfDate) {
        this.asOfDate = asOfDate;
    }

    public double getTerm() {
        return term;
    }

    public void setTerm(double term) {
        this.term = term;
    }

    public double getValueCP() {
        return valueCP;
    }

    public void setValueCP(double curvePointValue) {
        this.valueCP = curvePointValue;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }
}
