package com.xmedic.troll.components;

import java.util.Observer;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class CountDown extends CountDownTimer {
	
	private TextView target;
	private OnCounterFinishListener onFinishListener;

	public CountDown(long millisInFuture, long countDownInterval, TextView target) {
		super(millisInFuture, countDownInterval);
		this.target = target;
	}

	@Override
	public void onFinish() {
		target.setText("00:00");
		onFinishListener.finished();
	}

	@Override
	public void onTick(long millisUntilFinished) {
		long left =  millisUntilFinished/1000;
		target.setText(((left >= 10) ? "00:" : "00:0") + left);
		if(left < 10) {
			target.setTextColor(Color.RED);
		} else {
			target.setTextColor(Color.parseColor("#ffff00"));
		}
	}

	public void setOnFinishListener(OnCounterFinishListener onFinishListener) {
		this.onFinishListener = onFinishListener;
	}
	
	
	public static abstract class OnCounterFinishListener {
		public abstract void finished();
	}
}
