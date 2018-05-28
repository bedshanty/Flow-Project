package kr.hs.dgsw.flow.activity;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;

import kr.hs.dgsw.flow.DateTimeHelper;
import kr.hs.dgsw.flow.model.DateTime;

import butterknife.ButterKnife;
import kr.hs.dgsw.flow.DateTimeHelper;
import kr.hs.dgsw.flow.model.DateTime;

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
