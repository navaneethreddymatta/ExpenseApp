package com.example.navanee.expenseapp;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddExpenseFragment extends Fragment {

    TextView expNameField;
    Spinner expCategoryField;
    TextView expAmountField;
    String categories[];
    int catValue = 0;

    private OnFragmentInteractionListener mListener;

    public AddExpenseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_expense, container, false);
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
    public void onStart() {
        super.onStart();
        expNameField = (TextView) getActivity().findViewById(R.id.editName);
        expCategoryField = (Spinner) getActivity().findViewById(R.id.editCategory);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,categories);
        expCategoryField.setAdapter(adapter);
        expAmountField = (TextView) getActivity().findViewById(R.id.editAmount);
        expCategoryField.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               catValue = i;
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });

        getActivity().findViewById(R.id.addBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            try{
                String expenseName = expNameField.getText().toString();
                Float expenseAmt = Float.parseFloat(expAmountField.getText().toString());
                if(expenseName == "") {
                    Toast.makeText(getActivity(),"Enter Expense Name",Toast.LENGTH_LONG).show();
                } else if(expenseAmt <= 0) {
                    Toast.makeText(getActivity(),"Enter Expense Amount",Toast.LENGTH_LONG).show();
                } else if (expenseName != "" && expenseAmt > 0 && catValue != -1) {
                    String dtVal = String.valueOf(android.text.format.DateFormat.format("MM/dd/yyyy", new java.util.Date()));
                    Expense thisExpense = new Expense(expenseName, catValue, expenseAmt,dtVal);
                    mListener.sendExpense(thisExpense);
                    if(getFragmentManager().getBackStackEntryCount() > 0)
                        getFragmentManager().popBackStack();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(getActivity(),"Enter Valid Amount",Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(getActivity(),"Enter Valid Values",Toast.LENGTH_LONG).show();
            }
            }
        });
        getActivity().findViewById(R.id.cancelBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(getFragmentManager().getBackStackEntryCount() > 0)
                getFragmentManager().popBackStack();
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        ArrayList<Expense> onFragmentInteraction();
        void sendExpense(Expense expense);
    }
    public void setDataToFragment(String[] expCategories) {
        categories = expCategories;
    }
}
