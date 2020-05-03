package com.boxuegu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.boxuegu.R;

public class ViewPagerIndicator extends LinearLayout {

    private int mCount; //СԲ��ĸ���
    private int mIndex;     //��ǰСԲ���λ��
    private Context context;

    public ViewPagerIndicator(Context context) {
        this(context,null);
    }

    public ViewPagerIndicator(Context context,AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setGravity(Gravity.CENTER);  //����СԲ�㲼�־���
    }

    //���û�������ǰСԲ��ʱ����СԲ���λ��
    public void setCurrentPosition(int currentIndex){
        mIndex = currentIndex;  //��ǰСԲ��
        removeAllViews();   //�Ƴ������ϴ��ڵ�view
        int pex = 5;
        for (int i=0;i<mCount;i++){
            ImageView imageView = new ImageView(context);
            if (mIndex == i){   //������ǰ����
                //��ɫСԲ��
                imageView.setImageResource(R.drawable.indicator_on);
            }else{
                //��ɫСԲ��
                imageView.setImageResource(R.drawable.indicator_off);
            }
            imageView.setPadding(pex,0,pex,0);
            addView(imageView);
        }
    }

    //����СԲ�����Ŀ
    public void setCount(int count){
        this.mCount = count;
    }
}