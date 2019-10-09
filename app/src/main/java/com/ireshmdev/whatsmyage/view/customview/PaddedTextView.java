package com.ireshmdev.whatsmyage.view.customview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import org.apache.commons.lang3.StringUtils;

public class PaddedTextView extends AppCompatTextView {
    private int padding;

    public PaddedTextView(Context context, int padding) {
        super(context);
        this.padding = padding;
    }

    public PaddedTextView(Context context) {
        super(context);
    }

    public PaddedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PaddedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
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
