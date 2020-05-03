package com.boxuegu.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import com.boxuegu.R;
import com.boxuegu.utils.AnalysisUtils;
import com.boxuegu.utils.MD5Utils;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;




public class ModifyPswActivity extends AppCompatActivity {
    private TextView tv_main_title;
    private TextView tv_back;
    private EditText et_original_psw, et_new_psw, et_new_psw_again;
    private Button btn_save;
    private String originalPsw, newPsw, newPswAgain;
    private String userName;
    @Override
    protected void onCreate(Bundle savedIntanceState){
        super.onCreate(savedIntanceState);
        //���ý��沼��
        setContentView(R.layout.activity_modify_psw);
        //���ý���Ϊ����
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
        userName = AnalysisUtils.readLoginUserName(this);
    }

    //��ȡ����ؼ���������ؿؼ��ĵ���¼�
    private void init(){
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("�޸�����");
        tv_back = (TextView) findViewById(R.id.tv_back);
        et_original_psw = (EditText) findViewById(R.id.et_original_psw);
        et_new_psw = (EditText) findViewById(R.id.et_new_psw);
        et_new_psw_again = (EditText) findViewById(R.id.et_new_psw_again);
        btn_save = (Button) findViewById(R.id.btn_save);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModifyPswActivity.this.finish();
            }
        });
        
        //���水ť�ĵ���¼�
        btn_save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getEditString();
                if (TextUtils.isEmpty(originalPsw)) {
                    Toast.makeText(ModifyPswActivity.this, "������ԭʼ����",
                            Toast.LENGTH_SHORT).show();
                    return;
                }else if (!MD5Utils.md5(originalPsw).equals(readPsw())) {
                    Toast.makeText(ModifyPswActivity.this, "�����������ԭʼ���벻һ��",
                            Toast.LENGTH_SHORT).show();
                    return;
                }else if (MD5Utils.md5(newPsw).equals(readPsw())) {
                    Toast.makeText(ModifyPswActivity.this, "�������������ԭʼ�����벻��һ��",
                            Toast.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(newPsw)) {
                    Toast.makeText(ModifyPswActivity.this, "������������",
                            Toast.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(newPswAgain)) {
                    Toast.makeText(ModifyPswActivity.this, "���ٴ�����������",
                            Toast.LENGTH_SHORT).show();
                    return;
                }else if (!newPsw.equals(newPswAgain)) {
                    Toast.makeText(ModifyPswActivity.this, "��������������벻һ��",
                            Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(ModifyPswActivity.this,"���������óɹ�",
                            Toast.LENGTH_SHORT).show();
                    modifyPsw(newPsw);
                    Intent intent = new Intent(ModifyPswActivity.this, LoginActivity.class);
                    startActivity(intent);
                    SettingActivity.instance.finish();   //�ر����ý���
                    ModifyPswActivity.this.finish();  //�رձ�����
                }
            }
        });
    }

    //��ȡ�ؼ��ϵ��ַ���
    private void getEditString(){
        originalPsw = et_original_psw.getText().toString().trim();
        newPsw = et_new_psw.getText().toString().trim();
        newPswAgain=et_new_psw_again.getText().toString().trim();
    }
    
    //�޸ĵ�¼�ɹ�ʱ������SharedPreferences�е�����
    private void modifyPsw(String newPsw){
        String md5Psw = MD5Utils.md5(newPsw);
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();  //��ȡ�༭��
        editor.putString(userName,md5Psw);   //����������
        editor.commit();   //�ύ�޸�
    }

    //��SharedPreferences�ж�ȡԭʼ����
    private String readPsw(){
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        String spPsw = sp.getString(userName, "");
        return spPsw;
    }
}