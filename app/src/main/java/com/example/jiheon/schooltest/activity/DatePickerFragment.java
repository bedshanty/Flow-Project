package com.example.jiheon.schooltest.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.jiheon.schooltest.DateTimeHelper;
import com.example.jiheon.schooltest.model.DateTime;

import butterknife.ButterKnife;

public class DatePickerFragment extends DialogFragment {
        //implements DatePickerDialog.OnDateSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ButterKnife.bind(getActivity());
        final DateTimeHelper dateTimeHelper = new DateTimeHelper();
        final DateTime dateTime = dateTimeHelper.getDateTime();

        return new DatePickerDialog(getActivity(), (LeaveActivity)getActivity(),
                dateTime.getYear(), dateTime.getMonth(), dateTime.getDay());
    }
}
