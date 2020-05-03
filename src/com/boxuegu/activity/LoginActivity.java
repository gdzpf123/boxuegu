package com.boxuegu.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.boxuegu.R;
import com.boxuegu.utils.MD5Utils;


public class LoginActivity extends AppCompatActivity {
    private TextView tv_main_title;
    private TextView tv_back,tv_register,tv_find_psw;
    private Button btn_login;
    private String userName,psw,spPsw;
    private EditText et_user_name,et_psw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //����ҳ�沼��
        setContentView(R.layout.activity_login);
      //���ý���Ϊ����
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }

	/** 
	 * ��ȡ����ؼ� 
	 */
    private void init() {
        tv_main_title = (TextView)findViewById(R.id.tv_main_title);
        tv_main_title.setText("��¼");
        tv_back = (TextView)findViewById(R.id.tv_back);
        tv_register = (TextView)findViewById(R.id.tv_register);
        tv_find_psw = (TextView)findViewById(R.id.tv_find_psw);
        btn_login = (Button) findViewById(R.id.btn_login);
        et_user_name = (EditText)findViewById(R.id.et_user_name);
        et_psw = (EditText)findViewById(R.id.et_psw);
        //���ذ�ť�ĵ���¼�
        tv_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                LoginActivity.this.finish();
            }
        });
        
        //����ע��ؼ��ĵ���¼�
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            												//��ת��ע�����
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivityForResult(intent,1);
            }
        });
        
        //�һ�����ؼ��ĵ���¼�
        tv_find_psw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	//��ת���һ��������
            	Intent intent = new Intent(LoginActivity.this, FindPswActivity.class);
                startActivity(intent);
            }
        });
        
        //��¼��ť�ĵ���¼�
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = et_user_name.getText().toString().trim();
                psw = et_psw.getText().toString().trim();
                String md5Psw = MD5Utils.md5(psw);
                spPsw = readPsw(userName);
                //����û���Ϊ��
                if (TextUtils.isEmpty(userName)){
                    Toast.makeText(LoginActivity.this,"�������û���",Toast.LENGTH_LONG).show();
                    return;
                }else  if (TextUtils.isEmpty(psw)){  //�������Ϊ��
                    Toast.makeText(LoginActivity.this,"����������",Toast.LENGTH_LONG).show();
                    return;
                }else if (md5Psw.equals(spPsw)){
                    Toast.makeText(LoginActivity.this,"��¼�ɹ�",Toast.LENGTH_LONG).show();
                    //�����¼״̬�͵�¼���û���
                    saveLoginStatus(true,userName);
                    //�ѵ�¼�ɹ���״̬���ݵ�MainActivity��
                    Intent data = new Intent();
                    data.putExtra("isLogin",true);
                    data.putExtra("userName",userName);
                    setResult(RESULT_OK,data);
                    LoginActivity.this.finish();
                    return;
                }else if (!TextUtils.isEmpty(spPsw) && !md5Psw.equals(spPsw)){
                    Toast.makeText(LoginActivity.this,"������û��������벻һ��",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(LoginActivity.this,"���û���������",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

	/* 
	 * ��SharedPreferences�����û�����ȡ���� 
	*/
    private String readPsw(String userName){
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        return  sp.getString(userName,"");
    }
    
    /* 
	 * �����¼״̬�͵�¼�û�����SharedPreferences��
	*/
    private void saveLoginStatus(boolean status, String userName) {
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit(); //��ȡ�༭��
        editor.putBoolean("isLogin",status);
        editor.putString("loginUserName",userName);  //�����¼���û���
        editor.commit(); //�ύ�޸�
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
        	//��ע����洫�ݹ������û���
            String userName = data.getStringExtra("userName");
            if (!TextUtils.isEmpty(userName)){
                et_user_name.setText(userName);
                //���ù���λ��
                et_user_name.setSelection(userName.length());
            }
        }
    }
}
