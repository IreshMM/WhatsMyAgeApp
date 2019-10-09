package com.ireshmdev.whatsmyage.view.customview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

import org.apache.commons.lang3.StringUtils;

public class PaddedEditText extends AppCompatEditText {
    private int padding;

    public PaddedEditText(Context context) {
        super(context);
    }

    public PaddedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PaddedEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public void setText(String text) {
        String paddedText = StringUtils.leftPad(text, padding, '0');
        super.setText(paddedText);
    }
}
