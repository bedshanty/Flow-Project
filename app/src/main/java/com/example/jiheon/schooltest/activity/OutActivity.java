package com.example.jiheon.schooltest.activity;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
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

import com.example.jiheon.schooltest.database.DatabaseHelper;
import com.example.jiheon.schooltest.model.DateTime;
import com.example.jiheon.schooltest.network.NetworkService;
import com.example.jiheon.schooltest.R;
import com.example.jiheon.schooltest.RetrofitBuilder;
import com.example.jiheon.schooltest.Utils;
import com.example.jiheon.schooltest.type.OutType;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;
import retrofit2.Call;
import retrofit2.Callback;

public class OutActivity extends FragmentActivity implements DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {

    @BindView(R.id.out_start_date_spinner)  Spinner mStartDateSpinner;
    @BindView(R.id.out_start_time_spinner)  Spinner mStartTimeSpinner;
    @BindView(R.id.out_end_date_spinner)    Spinner mEndDateSpinner;
    @BindView(R.id.out_end_time_spinner)    Spinner mEndTimeSpinner;

    @BindView(R.id.out_radio_group) RadioGroup mRadioGroup;
    @BindView(R.id.out_radio_button1)   RadioButton mRadioButton1;
    @BindView(R.id.out_radio_button2)   RadioButton mRadioButton2;

    @BindView(R.id.out_reason)  EditText mOutReason;

    @BindView(R.id.out_submit_btn)  Button mSubmit;

    private final DatabaseHelper mDatabase = new DatabaseHelper(this);

    private boolean mStart;
    
    private DateTime mStartTime = new DateTime();
    private DateTime mEndTime = new DateTime();

    private NetworkService mService;
    private Call<com.example.jiheon.schooltest.network.jsonTypes.Out.Response.Response> mOutRequest;
    private Call<com.example.jiheon.schooltest.network.jsonTypes.Sleep.Response.Response> mSleepRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out);

        ButterKnife.bind(this);

        mRadioButton1.setChecked(true);
        
        mStartDateSpinner.setOnKeyListener(onKeyStartDateListener);
        mStartTimeSpinner.setOnKeyListener(onKeyStartTimeListener);

        mService = new RetrofitBuilder().getService();
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
        if(mStartTime.getYear() == 0 || mStartTime.getHour() == 0 ||
                mEndTime.getYear() == 0 || mEndTime.getHour() == 0) {
            Toast.makeText(this, "날짜를 선택해주세요.", Toast.LENGTH_SHORT).show();
        } else if(TextUtils.isEmpty(mOutReason.getText().toString())) {
            Toast.makeText(this, "사유를 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else {
            String startDate = Utils.dateFormatConverter(mStartTime);
            String endDate = Utils.dateFormatConverter(mEndTime);

            int selectedGender = mRadioGroup.getCheckedRadioButtonId();
            RadioButton radioButton = findViewById(selectedGender);
            int type = mRadioGroup.indexOfChild(radioButton);

            if(type == 0) {
                mOutRequest = mService.out(mDatabase.getToken(),
                        new com.example.jiheon.schooltest.network.jsonTypes.Out.Request.Request(
                                startDate, endDate, mOutReason.getText().toString()));

                mOutRequest.enqueue(new Callback<com.example.jiheon.schooltest.network.jsonTypes.Out.Response.Response>() {
                    @Override
                    public void onResponse(Call<com.example.jiheon.schooltest.network.jsonTypes.Out.Response.Response> call, retrofit2.Response<com.example.jiheon.schooltest.network.jsonTypes.Out.Response.Response> response) {
                        if (response.body().getStatus() == 200) {
                            mDatabase.insertOut(startDate, endDate, OutType.OUT);
                            Toast.makeText(OutActivity.this, "성공적으로 신청되었습니다.",
                                    Toast.LENGTH_SHORT).show();

                            finish();
                        } else {
                            Toast.makeText(OutActivity.this, response.body().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<com.example.jiheon.schooltest.network.jsonTypes.Out.Response.Response> call, Throwable t) {
                        Toast.makeText(OutActivity.this, R.string.error_server_error,
                                Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                mSleepRequest = mService.sleep(mDatabase.getToken(),
                        new com.example.jiheon.schooltest.network.jsonTypes.Sleep.Request.Request(
                                startDate, endDate, mOutReason.getText().toString()));

                mSleepRequest.enqueue(new Callback<com.example.jiheon.schooltest.network.jsonTypes.Sleep.Response.Response>() {
                    @Override
                    public void onResponse(Call<com.example.jiheon.schooltest.network.jsonTypes.Sleep.Response.Response> call, retrofit2.Response<com.example.jiheon.schooltest.network.jsonTypes.Sleep.Response.Response> response) {
                        if (response.body().getStatus() == 200) {
                            mDatabase.insertOut(startDate, endDate, OutType.SLEEP);
                            Toast.makeText(OutActivity.this, "성공적으로 신청되었습니다.",
                                    Toast.LENGTH_SHORT).show();

                            finish();
                        } else {
                            Toast.makeText(OutActivity.this, response.body().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<com.example.jiheon.schooltest.network.jsonTypes.Sleep.Response.Response> call, Throwable t) {
                        Toast.makeText(OutActivity.this, R.string.error_server_error,
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
}
