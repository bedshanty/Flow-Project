/*
package com.example.jiheon.schooltest.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jiheon.schooltest.Network.AsyncTasks.SchoolParser;
import com.example.jiheon.schooltest.R;

public class SchoolActivity extends AppCompatActivity implements SchoolParser.SchoolResponse {

    final private String API_URL = "www.career.go.kr/cnet/openapi/getOpenApi.json?apiKey=";

    Button mButton;
    TextView mTextView;
    EditText mEditView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);

        mButton = findViewById(R.id.schoolBtn);
        mTextView = findViewById(R.id.schoolText);
        mEditView = findViewById(R.id.schoolEdit);

        final SchoolParser sp = new SchoolParser();
        sp.delegate = this;

        mButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
SchoolParser schoolParser = new SchoolParser();

                schoolParser.execute("달성");


                try {
                    sp.execute("aa");
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void processFinish(String output) {
        Log.i("SUCCESS", output);
        Toast.makeText(this, output, Toast.LENGTH_SHORT).show();
    }
}
*/
