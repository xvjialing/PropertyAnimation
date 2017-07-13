package com.lytech.xjl.propertyanimation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by xjl on 17-7-13.
 */

public class WaveView extends View{
    private static final String TAG=WaveView.class.getSimpleName();

    private int mWidth,mHeight;
    private Paint mPaint;
    private int waveLength;
    private Path mPath;
    private int mCenterY;
    private int mWaveCount;
    private int offset;

    private ValueAnimator valueAnimator;

    private void init(){
        mPaint=new Paint();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(8);
        mPaint.setColor(Color.LTGRAY);

        waveLength=800;
        mPath=new Path();
    }

    public WaveView(Context context) {
        super(context);
        init();
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth=MeasureSpec.getSize(widthMeasureSpec);
        mHeight=MeasureSpec.getSize(heightMeasureSpec);
        mCenterY=mHeight/2;
        mWaveCount= (int) Math.round(mWidth/waveLength+1.5);   //计算波形个数
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initAnimation();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(-waveLength,mCenterY);
        for (int i = 0; i < mWaveCount; i++) {
            mPath.quadTo(-waveLength * 3 / 4 + i * waveLength + offset,mCenterY + 60,-waveLength / 2 + i * waveLength + offset,mCenterY);
            mPath.quadTo(-waveLength / 4 + i * waveLength + offset,mCenterY - 60,i * waveLength + offset,mCenterY);
        }
        mPath.lineTo(mWidth,mHeight);
        mPath.lineTo(0,mHeight);
        mPath.close();
        canvas.drawPath(mPath,mPaint);
    }

    private void initAnimation() {
        valueAnimator=ValueAnimator.ofInt(0,waveLength);
        valueAnimator.setDuration(5000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                offset = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();
    }
}
