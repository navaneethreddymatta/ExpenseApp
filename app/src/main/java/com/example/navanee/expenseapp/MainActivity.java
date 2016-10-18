package com.example.navanee.expenseapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExpenseAppFragment.OnFragmentInteractionListener,AddExpenseFragment.OnFragmentInteractionListener,ViewExpenseFragment.OnFragmentInteractionListener{
    ArrayList<Expense> expenses = new ArrayList<Expense>();
    String categories[] = {"Groceries","Invoice","Transportation","Shopping","Rent","Trips","Utilities","Other"};
    Expense selectedExpense;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupFragment();
    }

    @Override
    public ArrayList<Expense> onFragmentInteraction() {
        return expenses;
    }

    @Override
    public String[] getCategories() {
        return categories;
    }

    @Override
    public Expense getExpense() {
        return selectedExpense;
    }

    @Override
    public void sendExpense(Expense expense) {
        expenses.add(expense);
        showExpenseTable();
    }

    public void setupFragment() {
        getSupportActionBar().setTitle(R.string.app_name);
        ExpenseAppFragment eFragment = new ExpenseAppFragment();
        getFragmentManager().beginTransaction()
                .add(R.id.mainLayout,eFragment,"mainView")
                .commit();
    }

    @Override
    public void moveToAdd() {
        getSupportActionBar().setTitle(R.string.app_name_add);
        AddExpenseFragment aFragment = new AddExpenseFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.mainLayout,aFragment,"addView")
                .addToBackStack(null)
                .commit();
        aFragment.setDataToFragment(categories);
    }

    public void displaySelectedExpense(Expense expense) {
        getSupportActionBar().setTitle(R.string.app_name_view);
        ViewExpenseFragment vFragment = new ViewExpenseFragment();
        selectedExpense = expense;
        getFragmentManager().beginTransaction()
                .replace(R.id.mainLayout,vFragment,"detailView")
                .addToBackStack(null)
                .commit();
    }

    public void showExpenseTable() {
        ExpenseAppFragment eFragment = (ExpenseAppFragment) getFragmentManager().findFragmentByTag("mainView");
    }
}
