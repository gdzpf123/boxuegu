package com.boxuegu.activity;

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
import com.boxuegu.utils.AnalysisUtils;
import com.boxuegu.utils.MD5Utils;

public class FindPswActivity extends AppCompatActivity {

	//formΪsecurityʱ�������ܱ�������ת����������ʹӵ�¼������ת������
    private String from;
    private TextView tv_main_title;
    private TextView tv_back;
    private Button btn_validate;
    private EditText et_validate_name;
    private TextView tv_reset_psw;
    private EditText et_user_name;
    private TextView tv_user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //���ý��沼��
        setContentView(R.layout.activity_find_psw);
        //���ý���Ϊ����
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        from = getIntent().getStringExtra("from");
        init();
    }

    //��ȡ����ؼ���������Ӧ�ؼ��ĵ���¼�
    private void init() {
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_back = (TextView) findViewById(R.id.tv_back);
        et_validate_name = (EditText) findViewById(R.id.et_validate_name);
        btn_validate = (Button) findViewById(R.id.btn_validate);
        tv_reset_psw = (TextView) findViewById(R.id.tv_reset_psw);
        et_user_name = (EditText) findViewById(R.id.et_user_name);
        tv_user_name = (TextView) findViewById(R.id.tv_user_name);
        if ("security".equals(from)){
            tv_main_title.setText("�����ܱ�");
        }else{
            tv_main_title.setText("�һ�����");
            tv_user_name.setVisibility(View.VISIBLE);
            et_user_name.setVisibility(View.VISIBLE);
        }
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FindPswActivity.this.finish();
            }
        });
        btn_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String validateName = et_validate_name.getText().toString().trim();
                if ("security".equals(from)){  //�����ܱ�
                    if (TextUtils.isEmpty(validateName)){
                        Toast.makeText(FindPswActivity.this,"������Ҫ��֤������",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }else{
                        Toast.makeText(FindPswActivity.this,"�ܱ����óɹ�",
                                Toast.LENGTH_SHORT).show();
                        //�����ܱ���SharedPreferences��
                        saveSecurity(validateName);
                        FindPswActivity.this.finish();
                    }
                }else{  //�һ�����
                    String userName = et_user_name.getText().toString().trim();
                    String sp_security = readSecurity(userName);
                    if (TextUtils.isEmpty(userName)){
                        Toast.makeText(FindPswActivity.this,"�����������û���",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }else if (!isExistUserName(userName)){
                        Toast.makeText(FindPswActivity.this,"��������û���������",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }else if (TextUtils.isEmpty(validateName)){
                        Toast.makeText(FindPswActivity.this,"������Ҫ��֤������",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }if (!validateName.equals(sp_security)){
                        Toast.makeText(FindPswActivity.this,"������ܱ�����ȷ",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }else{
                    	//������ܱ���ȷ�����¸��û�����һ�����룺123456
                        tv_reset_psw.setVisibility(View.VISIBLE);
                        tv_reset_psw.setText("��ʼ���룺123456");
                        savePsw(userName);
                    }
                }
            }
        });
    }

    //�����ʼ��������
    private void savePsw(String userName){
        String md5Psw = MD5Utils.md5("123456");  //����MD5����
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();  //��ȡ�༭��
        editor.putString(userName,md5Psw);
        editor.commit();   //�ύ�޸�
    } 
    
    
    private void saveSecurity(String validateName){
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();  //��ȡ�༭��
         //�����Ӧ�˻����ܱ�
        editor.putString(AnalysisUtils.readLoginUserName(this)+"_security",validateName);
        editor.commit();  //�ύ�޸�
    } 

  //��SharedPreferences�ж�ȡ�ܱ�
    private String readSecurity(String userName){
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        String security = sp.getString(userName+"_security","");
        return security;
    }
    
    //��SharedPreferences�и����û�������û������ж��Ƿ�������û�
    private boolean isExistUserName(String userName){
        boolean hasUserName = false;
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        String spPsw = sp.getString(userName,"");
        if (!TextUtils.isEmpty(spPsw)){
            hasUserName=true;
        }
        return hasUserName;
    }
}