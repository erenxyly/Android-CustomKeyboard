package com.bthdtm.customkeyboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private CustomKeyboard mCustomKeyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText mEditText = findViewById(R.id.et);
        CustomKeyboardView mCustomKeyboardView = findViewById(R.id.customKeyboardView);
        mCustomKeyboard = new CustomKeyboard(this, mCustomKeyboardView, mEditText);

        mEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCustomKeyboard.showKeyboard();
            }
        });

        try {
            Class<EditText> cls = EditText.class;
            Method setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
            setShowSoftInputOnFocus.setAccessible(true);
            setShowSoftInputOnFocus.invoke(mEditText, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mCustomKeyboard.isShowKeyboard()) {
            mCustomKeyboard.hideKeyboard();
        } else {
            finish();
        }
    }
}
