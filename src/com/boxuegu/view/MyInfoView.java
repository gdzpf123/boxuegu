package com.boxuegu.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.boxuegu.R;
import com.boxuegu.activity.LoginActivity;
import com.boxuegu.activity.PlayHistoryActivity;
import com.boxuegu.activity.SettingActivity;
import com.boxuegu.activity.UserInfoActivity;
import com.boxuegu.utils.AnalysisUtils;


public class MyInfoView {
    public ImageView iv_head_icon;
    private LinearLayout ll_head;
    private RelativeLayout rl_course_history,rl_setting;
    private TextView tv_user_name;
    private Activity mContext;
    private LayoutInflater mInflater;
    private View mCurrentView;
    public MyInfoView(Activity context){
    	//Ϊ֮��layoutת��Ϊviewʱ��
        mContext= context;
        mInflater= LayoutInflater.from(mContext);
    }
    private void createView(){
        initView();
    }

	/* 
	 * ��ȡ����ؼ�
	 */
    private void initView() {
    	//���ò����ļ�
        mCurrentView = mInflater.inflate(R.layout.main_view_myinfo,null);
        ll_head = (LinearLayout)mCurrentView.findViewById(R.id.ll_head);
        iv_head_icon = (ImageView) mCurrentView.findViewById(R.id.iv_head_icon);
        rl_course_history = (RelativeLayout) mCurrentView.findViewById(R.id.rl_course_history);
        rl_setting = (RelativeLayout) mCurrentView.findViewById(R.id.rl_setting);
        tv_user_name = (TextView) mCurrentView.findViewById(R.id.tv_user_name);
        mCurrentView.setVisibility(View.VISIBLE);
        setLoginParams(readLoginStatus());
        ll_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	//�ж��Ƿ��¼
                if (readLoginStatus()){
                    //�ѵ�¼��ת���������Ͻ���
                	Intent intent = new Intent(mContext, UserInfoActivity.class);
                    mContext.startActivity(intent);
                }else{
                	//δ��¼��ת����¼����
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    mContext.startActivityForResult(intent,1);
                }
            }
        });
        rl_course_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (readLoginStatus()){
                    //��ת�����ż�¼����
                	Intent intent = new Intent(mContext, PlayHistoryActivity.class);
                    mContext.startActivity(intent);
                }else{
                    Toast.makeText(mContext,"����δ��¼�����ȵ�¼",Toast.LENGTH_SHORT).show();
                }
            }
        });
        rl_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (readLoginStatus()){
                	//��ת�����ý��� �������
                	 Intent intent = new Intent(mContext, SettingActivity.class);
                     mContext.startActivityForResult(intent,1);
                }else{
                    Toast.makeText(mContext,"����δ��¼�����ȵ�¼",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    
	/* 
	 * ��¼�ɹ��������ҵĽ���
	 */
    public void setLoginParams(boolean isLogin){
        if (isLogin){
            tv_user_name.setText(AnalysisUtils.readLoginUserName(mContext));
        }
        else{
            tv_user_name.setText("�����¼");
        }
    }
    
    /* 
	 * ��ȡ��ǰ�ڵ������Ϸ���Ӧ��view
	 */
    public View getView(){
        if (mCurrentView==null){
            createView();
        }return mCurrentView;
    }
    
    /* 
	 * ��ʾ��ǰ�������Ϸ�����Ӧ��view����
	 */
    public void showView(){
        if (mCurrentView==null){
            createView();
        }
        mCurrentView.setVisibility(View.VISIBLE);
    }

    /* 
	 * ��SharedPreferences�ж�ȡ��¼״̬
	 */
    private boolean readLoginStatus(){
        SharedPreferences sp = mContext.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        boolean isLogin = sp.getBoolean("isLogin",false);
        return isLogin;
    }

}
