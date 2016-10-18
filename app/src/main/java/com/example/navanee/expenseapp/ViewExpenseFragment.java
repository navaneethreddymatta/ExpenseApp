package com.example.navanee.expenseapp;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ViewExpenseFragment extends Fragment {
    Expense expense;
    String[] categories;
    TextView det_name;
    TextView det_cat;
    TextView det_amount;
    TextView det_date;
    DecimalFormat df = new DecimalFormat("0.#");
    private OnFragmentInteractionListener mListener;

    public ViewExpenseFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_expense, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        categories = mListener.getCategories();
        expense = mListener.getExpense();
        if(expense != null) {
            det_name = (TextView) getActivity().findViewById(R.id.dt_name);
            det_cat = (TextView) getActivity().findViewById(R.id.dt_category);
            det_amount = (TextView) getActivity().findViewById(R.id.dt_amount);
            det_date = (TextView) getActivity().findViewById(R.id.dt_date);
            det_name.setText(expense.getName());
            det_cat.setText(categories[expense.getCategory()]);
            det_amount.setText("$" + df.format(expense.getAmount()));
            det_date.setText(expense.getcDate());
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
        Expense getExpense();
    }
}
