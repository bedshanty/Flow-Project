package kr.hs.dgsw.flow.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;

import kr.hs.dgsw.flow.DateTimeHelper;
import kr.hs.dgsw.flow.model.DateTime;

import butterknife.ButterKnife;
import kr.hs.dgsw.flow.DateTimeHelper;
import kr.hs.dgsw.flow.model.DateTime;

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
