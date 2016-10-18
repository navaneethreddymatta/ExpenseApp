package com.example.navanee.expenseapp;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ExpenseAppFragment extends Fragment {

    ImageView addExp;
    String categories[];
    ArrayList<Expense> expenses = new ArrayList<Expense>();
    ListView expList;
    private OnFragmentInteractionListener mListener;

    public ExpenseAppFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expense_app, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        ArrayList<Expense> onFragmentInteraction();
        String[] getCategories();
        void moveToAdd();
        void displaySelectedExpense(Expense expense);
    }

    @Override
    public void onStart() {
        super.onStart();
        categories = mListener.getCategories();
        expenses = mListener.onFragmentInteraction();
        expList = (ListView) getActivity().findViewById(R.id.expensesListView);
        showExpenseTable();
        addExp = (ImageView) getActivity().findViewById(R.id.addExpenseButton);
        addExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.moveToAdd();
            }
        });
        expList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                expenses.remove(position);
                showExpenseTable();
                Toast.makeText(getActivity(),"Expense Deleted",Toast.LENGTH_LONG).show();
                return true;
            }
        });
        expList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Expense exp = expenses.get(position);
                mListener.displaySelectedExpense(exp);
            }
        });
    }

    public void showExpenseTable() {
        if(expenses.size() == 0) {
            getActivity().findViewById(R.id.expensesListView).setVisibility(View.GONE);
            getActivity().findViewById(R.id.noExpensesHint).setVisibility(View.VISIBLE);
        } else {
            getActivity().findViewById(R.id.noExpensesHint).setVisibility(View.GONE);
            getActivity().findViewById(R.id.expensesListView).setVisibility(View.VISIBLE);
            ExpenseAdapter adapter = new ExpenseAdapter(getActivity(),R.layout.expense_row,expenses);
            expList.setAdapter(adapter);
        }
    }
}
