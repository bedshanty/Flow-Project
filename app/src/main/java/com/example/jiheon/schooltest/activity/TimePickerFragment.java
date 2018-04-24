package com.example.jiheon.schooltest.activity;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.jiheon.schooltest.DateTimeHelper;
import com.example.jiheon.schooltest.model.DateTime;

import butterknife.ButterKnife;

public class TimePickerFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ButterKnife.bind(getActivity());
        final DateTimeHelper dateTimeHelper = new DateTimeHelper();
        final DateTime dateTime = dateTimeHelper.getDateTime();

        return new TimePickerDialog(getActivity(), (LeaveActivity)getActivity(),
                dateTime.getHour(), dateTime.getMin(), true);
    }
}
