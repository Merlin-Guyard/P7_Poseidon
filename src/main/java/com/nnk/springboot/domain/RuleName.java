package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "rulename")
public class RuleName {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    int id;

    @NotEmpty(message = "Field cannot be empty")
    @Column(name="name")
    String name;

    @NotEmpty(message = "Field cannot be empty")
    @Column(name="description")
    String description;

    @Column(name="json")
    String json;

    @NotEmpty(message = "Field cannot be empty")
    @Column(name="template")
    String template;

    @Column(name="sqlStr")
    String sqlStr;

    @Column(name="sqlPart")
    String sqlPart;

    public RuleName() {
    }

    public RuleName(String ruleName, String description, String json, String template, String sql, String sqlPart) {
    }

    public RuleName(String name, String description, String template) {
        this.name = name;
        this.description = description;
        this.template = template;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getSqlStr() {
        return sqlStr;
    }

    public void setSqlStr(String sqlStr) {
        this.sqlStr = sqlStr;
    }

    public String getSqlPart() {
        return sqlPart;
    }

    public void setSqlPart(String sqlPart) {
        this.sqlPart = sqlPart;
    }
}
