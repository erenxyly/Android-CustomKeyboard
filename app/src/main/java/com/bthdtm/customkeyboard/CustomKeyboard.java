package com.bthdtm.customkeyboard;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

/**
 * Created by 徐杨 on 2018/2/2.
 * 邮箱：544066591@qq.com
 */

public class CustomKeyboard {

    public EditText mEditText;
    public CustomKeyboardView mCustomKeyboardView;
    public Keyboard mKeyboard;

    public CustomKeyboard(Context context, CustomKeyboardView customKeyboardView, EditText editText) {
        mEditText = editText;
        mKeyboard = new Keyboard(context, R.xml.keyboard);
        mCustomKeyboardView = customKeyboardView;
        mCustomKeyboardView.setKeyboard(mKeyboard);
        mCustomKeyboardView.setPreviewEnabled(false);

        KeyboardView.OnKeyboardActionListener actionListener = new KeyboardView.OnKeyboardActionListener() {
            @Override
            public void onPress(int i) {

            }

            @Override
            public void onRelease(int i) {

            }

            @Override
            public void onKey(int i, int[] ints) {
                Editable editable = mEditText.getText();
                int index = mEditText.getSelectionStart();
                switch (i) {
                    case Keyboard.KEYCODE_DELETE:
                        if (editable != null && editable.length() > 0) {
                            if (index > 0) {
                                editable.delete(index - 1, index);
                            }
                        }
                        break;
                    case -10:
                        hideKeyboard();
                        break;
                    default:
                        editable.insert(index, Character.toString((char) i));
                        break;
                }
            }

            @Override
            public void onText(CharSequence charSequence) {

            }

            @Override
            public void swipeLeft() {

            }

            @Override
            public void swipeRight() {

            }

            @Override
            public void swipeDown() {

            }

            @Override
            public void swipeUp() {

            }
        };
        mCustomKeyboardView.setOnKeyboardActionListener(actionListener);
    }

    public void showKeyboard() {
        if (mCustomKeyboardView.getVisibility() != View.VISIBLE) {
            mCustomKeyboardView.setVisibility(View.VISIBLE);
        }
    }

    public void hideKeyboard() {
        if (mCustomKeyboardView.getVisibility() == View.VISIBLE) {
            mCustomKeyboardView.setVisibility(View.GONE);
        }
    }

    public boolean isShowKeyboard() {
        return mCustomKeyboardView.getVisibility() == View.VISIBLE;
    }
}
