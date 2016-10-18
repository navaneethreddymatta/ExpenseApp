package com.example.navanee.expenseapp;

/**
 * Created by navanee on 17-10-2016.
 */

public class Expense {
    private String name;
    private int category;
    private float amount;
    private String cDate;

    public Expense(String name, int category, float amount, String cDate) {
        this.name = name;
        this.category = category;
        this.amount = amount;
        this.cDate = cDate;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getcDate() {
        return cDate;
    }

    public void setcDate(String cDate) {
        this.cDate = cDate;
    }
}
