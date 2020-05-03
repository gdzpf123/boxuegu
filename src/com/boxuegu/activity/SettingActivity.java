package com.boxuegu.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.boxuegu.R;

public class SettingActivity extends AppCompatActivity {
    public static  SettingActivity instance = null;
    private TextView tv_main_title;
    private TextView tv_back;
    private RelativeLayout rl_title_bar;
    private RelativeLayout rl_modify_psw;
    private RelativeLayout rl_security_setting;
    private RelativeLayout rl_exit_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //���ý��沼��
        setContentView(R.layout.activity_setting);
        //���ý���Ϊ����
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        instance = this;
        init();
    }

    //��ȡ����ؼ�
    private void init() {
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("����");
        tv_back = (TextView) findViewById(R.id.tv_back);
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#FF9900"));
        rl_modify_psw = (RelativeLayout) findViewById(R.id.rl_modify_psw);
        rl_security_setting = (RelativeLayout) findViewById(R.id.rl_security_setting);
        rl_exit_login = (RelativeLayout) findViewById(R.id.rl_exit_login);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingActivity.this.finish();
            }
        });
        
        //�޸�����ĵ���¼�
        rl_modify_psw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //��ת���޸��������
            	Intent intent = new Intent(SettingActivity.this,ModifyPswActivity.class);
                startActivity(intent);
            }
        });
        
        //�����ܱ��ĵ���¼�
        rl_security_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //��ת�������ܱ�����
            	 Intent intent = new Intent(SettingActivity.this,FindPswActivity.class);
                 intent.putExtra("from","security");
                 startActivity(intent);
            }
        });
        
        //�˳���¼�ĵ���¼�
        rl_exit_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingActivity.this,"�˳���¼�ɹ�",Toast.LENGTH_SHORT).show();
                clearLoginStatus();   //�����¼״̬�͵�¼���û���
                //�˳��ɹ�����˳��ɹ���״̬���ݵ�MainActivity��
                Intent data = new Intent();
                data.putExtra("isLogin",false);
                setResult(RESULT_OK,data);
                SettingActivity.this.finish();
            }
        });
    }

    //���SharedPreferences�еĵ�¼״̬�͵�¼ʱ���û���
    private void clearLoginStatus(){
        SharedPreferences sp = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();  //��ȡ�༭��
        editor.putBoolean("isLogin",false);   //�����¼״̬
        editor.putString("loginUserName","");  //����û���
        editor.commit();  //�ύ�޸�
    }
}
