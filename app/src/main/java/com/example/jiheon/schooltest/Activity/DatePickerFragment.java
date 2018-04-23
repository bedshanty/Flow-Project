package com.example.jiheon.schooltest.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.jiheon.schooltest.DateTimeHelper;
import com.example.jiheon.schooltest.Model.DateTime;

import butterknife.ButterKnife;

public class DatePickerFragment extends DialogFragment {
        //implements DatePickerDialog.OnDateSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ButterKnife.bind(getActivity());
        final DateTimeHelper dateTimeHelper = new DateTimeHelper();
        final DateTime dateTime = dateTimeHelper.getDateTime();

        return new DatePickerDialog(getActivity(), (OutActivity)getActivity(),
                dateTime.getYear(), dateTime.getMonth(), dateTime.getDay());
    }
}
