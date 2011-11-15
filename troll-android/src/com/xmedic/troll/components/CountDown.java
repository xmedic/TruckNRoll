package com.xmedic.troll.components;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.format.DateUtils;
import android.widget.TextView;

import com.xmedic.troll.R;

public class CountDown extends CountDownTimer {
	
	private final TextView target;
	private OnCounterFinishListener onFinishListener;

    private final static String timeOut = "00:00";
    private final static String timeFormat = "00:%s";
    private final static String timeFormatFraction = "00:0%s";
    
    private final static int WARNING_LIMIT = 10;

	public CountDown(final long millisInFuture, final long countDownInterval, final TextView target) {
		super(millisInFuture, countDownInterval);
		this.target = target;
	}

	@Override
	public void onFinish() {
        target.setText(timeOut);
		onFinishListener.finished();
	}

	@Override
	public void onTick(final long millisUntilFinished) {
        long left = millisUntilFinished / DateUtils.SECOND_IN_MILLIS;
        target.setText(String.format(
                (left >= WARNING_LIMIT) ? timeFormat : timeFormatFraction, left));

        if (left < WARNING_LIMIT) {
			target.setTextColor(Color.RED);
		} else {
            target.setTextColor(R.color.time_left_ok);
		}
	}

	public void setOnFinishListener(final OnCounterFinishListener onFinishListener) {
		this.onFinishListener = onFinishListener;
	}
	
	public static abstract class OnCounterFinishListener {
		public abstract void finished();
	}
}
