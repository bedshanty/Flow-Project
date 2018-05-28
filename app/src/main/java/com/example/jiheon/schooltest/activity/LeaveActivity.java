package com.example.jiheon.schooltest.activity;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.jiheon.schooltest.DateTimeHelper;
import com.example.jiheon.schooltest.database.DatabaseManager;
import com.example.jiheon.schooltest.model.DateTime;
import com.example.jiheon.schooltest.model.Leave;
import com.example.jiheon.schooltest.network.NetworkService;
import com.example.jiheon.schooltest.R;
import com.example.jiheon.schooltest.RetrofitBuilder;
import com.example.jiheon.schooltest.Utils;
import com.example.jiheon.schooltest.type.LeaveType;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;
import retrofit2.Call;
import retrofit2.Callback;

public class LeaveActivity extends FragmentActivity implements DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {

    @BindView(R.id.leave_root) ConstraintLayout mLeaveRoot;
    @BindView(R.id.out_start_date_spinner)  Spinner mStartDateSpinner;
    @BindView(R.id.out_start_time_spinner)  Spinner mStartTimeSpinner;
    @BindView(R.id.out_end_date_spinner)    Spinner mEndDateSpinner;
    @BindView(R.id.out_end_time_spinner)    Spinner mEndTimeSpinner;

    @BindView(R.id.out_radio_group) RadioGroup mRadioGroup;
    @BindView(R.id.out_radio_button1)   RadioButton mRadioButton1;
    @BindView(R.id.out_radio_button2)   RadioButton mRadioButton2;

    @BindView(R.id.out_reason)  EditText mOutReason;

    @BindView(R.id.out_submit_btn)  Button mSubmit;

    private boolean mStart;
    
    private DateTime mStartTime;
    private DateTime mEndTime;

    private Calendar mStartCal;
    private Calendar mEndCal;

    private NetworkService mService;
    private Call<com.example.jiheon.schooltest.network.networkModel.Out.Response.Response> mOutRequest;
    private Call<com.example.jiheon.schooltest.network.networkModel.Sleep.Response.Response> mSleepRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);

        ButterKnife.bind(this);

        mStartTime = new DateTime();
        mEndTime = new DateTime();

        mStartCal = Calendar.getInstance();
        mEndCal = Calendar.getInstance();

        mRadioButton1.setChecked(true);

        mStartDateSpinner.setOnKeyListener(onKeyStartDateListener);
        mStartTimeSpinner.setOnKeyListener(onKeyStartTimeListener);
        mStartDateSpinner.setOnKeyListener(onKeyEndDateListener);
        mStartTimeSpinner.setOnKeyListener(onKeyEndTimeListener);

        mService = new RetrofitBuilder().getService();

        DateTime currentDateTime = Utils.getCurrentDateTime();

        mStartTime.setYear(currentDateTime.getYear());
        mStartTime.setMonth(currentDateTime.getMonth());
        mStartTime.setDay(currentDateTime.getDay());

        mStartTime.setHour(currentDateTime.getHour());
        mStartTime.setMin(currentDateTime.getMin());

        String date = String.valueOf(currentDateTime.getYear()) + "년 "
                + String.valueOf(currentDateTime.getMonth()) + "월 "
                + String.valueOf(currentDateTime.getDay()) + "일";

        ArrayList<String> tempDateArray = new ArrayList<>();
        tempDateArray.add(date);

        ArrayAdapter<String> tempDateAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_item, tempDateArray);

        mStartDateSpinner.setAdapter(tempDateAdapter);

        String time = String.valueOf(currentDateTime.getHour()) + "시 "
                + String.valueOf(currentDateTime.getMin()) + "분";

        ArrayList<String> tempTimeArray = new ArrayList<>();
        tempTimeArray.add(time);

        ArrayAdapter<String> tempTimeAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_item, tempTimeArray);

        mStartTimeSpinner.setAdapter(tempTimeAdapter);
    }

    @OnTouch(R.id.out_start_date_spinner)
    public boolean onTouchStartDate(View view, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            mStart = true;
            showDatePickerDialog(view);
        }

        return true;
    }

    Spinner.OnKeyListener onKeyStartDateListener = (view, keyCode, keyEvent) -> {
        if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
            mStart = true;
            showDatePickerDialog(view);
            return true;
        }
        else {
            return false;
        }
    };

    @OnTouch(R.id.out_start_time_spinner)
    public boolean onTouchStartTime(View view, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            mStart = true;
            showTimePickerDialog(view);
        }

        return true;
    };

    Spinner.OnKeyListener onKeyStartTimeListener = (view, keyCode, keyEvent) -> {
        if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
            mStart = true;
            showTimePickerDialog(view);
            return true;
        }
        else {
            return false;
        }
    };

    @OnTouch(R.id.out_end_date_spinner)
    public boolean onTouchEndDate(View view, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            mStart = false;
            showDatePickerDialog(view);
        }

        return true;
    }

    Spinner.OnKeyListener onKeyEndDateListener = (view, keyCode, keyEvent) -> {
        if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
            mStart = false;
            showDatePickerDialog(view);
            return true;
        }
        else {
            return false;
        }
    };

    @OnTouch(R.id.out_end_time_spinner)
    public boolean onTouchEndTime(View view, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            mStart = false;
            showTimePickerDialog(view);
        }

        return true;
    };

    Spinner.OnKeyListener onKeyEndTimeListener = (view, keyCode, keyEvent) -> {
        if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
            mStart = false;
            showTimePickerDialog(view);
            return true;
        }
        else {
            return false;
        }
    };

    @OnClick(R.id.out_submit_btn)
    public void onClickSubmit(View v) {
        if(isDateBlank()) {
            Snackbar.make(mLeaveRoot, "날짜를 선택해주세요.", Toast.LENGTH_SHORT).show();
        } else if(TextUtils.isEmpty(mOutReason.getText().toString())) {
            Snackbar.make(mLeaveRoot, "사유를 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else {
            final String startDate = Utils.dateFormatConverter(mStartTime);
            final String endDate = Utils.dateFormatConverter(mEndTime);

            final int selectedGender = mRadioGroup.getCheckedRadioButtonId();
            final RadioButton radioButton = findViewById(selectedGender);
            final int type = mRadioGroup.indexOfChild(radioButton);

            final String reason = mOutReason.getText().toString();

            if(type == 0) {
                mOutRequest = mService.out(DatabaseManager.selectToken(),
                        new com.example.jiheon.schooltest.network.networkModel.Out.Request.Request(
                                startDate, endDate, mOutReason.getText().toString()));

                mOutRequest.enqueue(new Callback<com.example.jiheon.schooltest.network.networkModel.Out.Response.Response>() {
                    @Override
                    public void onResponse(Call<com.example.jiheon.schooltest.network.networkModel.Out.Response.Response> call, retrofit2.Response<com.example.jiheon.schooltest.network.networkModel.Out.Response.Response> response) {
                        if(response.errorBody() != null) {
                            try {
                                Snackbar.make(mLeaveRoot, new JSONObject(response.errorBody().string())
                                        .getString("message"), Snackbar.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else if (response.body().getStatus() == 200) {
                            DatabaseManager.insertLeave(new Leave(startDate, endDate, reason, LeaveType.OUT, false));
                            Snackbar.make(mLeaveRoot, "성공적으로 신청되었습니다.",
                                    Toast.LENGTH_SHORT).show();

                            finish();
                        } else {
                            Snackbar.make(mLeaveRoot, response.body().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<com.example.jiheon.schooltest.network.networkModel.Out.Response.Response> call, Throwable t) {
                        Snackbar.make(mLeaveRoot, R.string.error_server_error,
                                Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                mSleepRequest = mService.sleep(DatabaseManager.selectToken(),
                        new com.example.jiheon.schooltest.network.networkModel.Sleep.Request.Request(
                                startDate, endDate, reason));

                mSleepRequest.enqueue(new Callback<com.example.jiheon.schooltest.network.networkModel.Sleep.Response.Response>() {
                    @Override
                    public void onResponse(Call<com.example.jiheon.schooltest.network.networkModel.Sleep.Response.Response> call, retrofit2.Response<com.example.jiheon.schooltest.network.networkModel.Sleep.Response.Response> response) {
                        if(response.errorBody() != null) {
                            try {
                                Snackbar.make(mLeaveRoot, new JSONObject(response.errorBody().toString())
                                        .getString("message"), Snackbar.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else if (response.body().getStatus() == 200) {
                            DatabaseManager.insertLeave(new Leave(startDate, endDate, reason, LeaveType.SLEEP, false));
                            Snackbar.make(mLeaveRoot, "성공적으로 신청되었습니다.",
                                    Toast.LENGTH_SHORT).show();

                            finish();
                        } else {
                            Snackbar.make(mLeaveRoot, R.string.error_server_error,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<com.example.jiheon.schooltest.network.networkModel.Sleep.Response.Response> call, Throwable t) {
                        Snackbar.make(mLeaveRoot, R.string.error_server_error,
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
     }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "Date");
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "Time");
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        String date = String.valueOf(year) + "년 " + String.valueOf(month) + "월 " +
                String.valueOf(day) + "일";

        ArrayList<String> tempArray = new ArrayList<>();
        tempArray.add(date);

        ArrayAdapter<String> sa = new ArrayAdapter<>(this,
                R.layout.spinner_item, tempArray);

        if(mStart) {
            mStartDateSpinner.setAdapter(sa);

            mStartTime.setYear(year);
            mStartTime.setMonth(month);
            mStartTime.setDay(day);
        } else {
            mEndDateSpinner.setAdapter(sa);

            mEndTime.setYear(year);
            mEndTime.setMonth(month);
            mEndTime.setDay(day);
        }

        if(!isDateBlank()) {
            mStartCal.set(mStartTime.getYear(), mStartTime.getMonth() - 1, mStartTime.getDay());
            mEndCal.set(mEndTime.getYear(), mEndTime.getMonth() - 1, mEndTime.getDay());

            int compare = mEndCal.compareTo(mStartCal);

            if(compare < 0) {
                mRadioButton1.setChecked(false);
                mRadioButton2.setChecked(true);
            } else if(compare == 0) {
                mRadioButton1.setChecked(true);
                mRadioButton2.setChecked(false);
            }
        }
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        String date = String.valueOf(hour) + "시 " + String.valueOf(minute) + "분";

        ArrayList<String> tempArray = new ArrayList<>();
        tempArray.add(date);

        ArrayAdapter<String> sa = new ArrayAdapter<>(this,
                R.layout.spinner_item, tempArray);

        if(mStart) {
            mStartTimeSpinner.setAdapter(sa);

            mStartTime.setHour(hour);
            mStartTime.setMin(minute);
        } else {
            mEndTimeSpinner.setAdapter(sa);

            mEndTime.setHour(hour);
            mEndTime.setMin(minute);
        }
    }

    /**
     * 사용자가 날짜를 모두 입력했는지 확인
     * @return 빈 칸이 있으면 true, 모두 입력했으면 false
     */
    public boolean isDateBlank() {
        return mStartTime.getMonth() < 0 || mStartTime.getHour() < 0 ||
                mEndTime.getMonth() < 0 || mEndTime.getHour() < 0;
    }
}
