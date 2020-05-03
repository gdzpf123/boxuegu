package com.boxuegu.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.boxuegu.R;
import com.boxuegu.bean.UserBean;
import com.boxuegu.utils.AnalysisUtils;
import com.boxuegu.utils.DBUtils;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_user_name;
    private TextView tv_signature;
    private RelativeLayout rl_signature;
    private TextView tv_sex;
    private RelativeLayout rl_sex;
    private TextView tv_nickName;
    private RelativeLayout rl_nickName;
    private TextView tv_back;
    private TextView tv_main_title;
    private RelativeLayout rl_title_bar;
    private String spUserName;
    //�������
    private static  final int  CHANGE_NICKNAME = 1;//�޸��ǳƵ��Զ��峣��
    private static  final int  CHANGE_SIGNATURE = 2;//�޸�ǩ�����Զ��峣��


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        //���ý���Ϊ����
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        spUserName = AnalysisUtils.readLoginUserName(this);
        init();
        initData();
        setListener();
    }

    //�Զ�����ת���� �������
    private void enterActivityForResult(Class<?> to,int requestCode,Bundle b){
        Intent i = new Intent(this,to);  //to��ʶ��Ҫ��ת���Ľ���
        i.putExtras(b);  //b��ʾ��תʱ���ݵĲ���
        startActivityForResult(i,requestCode);  //requestCode��ʾһ��������

    }
   
    //��ʼ���ؼ�
    private void init() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("��������");
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#FF9900"));
        rl_nickName = (RelativeLayout) findViewById(R.id.rl_nickName);
        tv_nickName = (TextView) findViewById(R.id.tv_nickName);
        rl_sex = (RelativeLayout) findViewById(R.id.rl_sex);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        rl_signature = (RelativeLayout) findViewById(R.id.rl_signature);
        tv_signature = (TextView) findViewById(R.id.tv_signature);
        tv_user_name = (TextView) findViewById(R.id.tv_user_name);
       

    }
    
    //�����ݿ��л�ȡ����
    private  void initData(){
        UserBean bean = null;
        bean  = DBUtils.getInstance(this).getUserInfo(spUserName);
        //�����ж�һ�����ݿ����Ƿ�������
        if(bean ==null){
            bean = new UserBean();
            bean.userName = spUserName; //�û����������޸�
            bean.nickName = "���������ǳ�";
            //Ĭ��Ϊ��
            bean.sex = "��";
            bean.signature = "��������ǩ��";
            //�����û���Ϣ�����ݿ�
            DBUtils.getInstance(this).saveUserInfo(bean);
        }
        setValue(bean);
    }
    
    //Ϊ����ؼ�����ֵ
    public void setValue(UserBean bean) {
        tv_nickName.setText(bean.nickName);
        tv_sex.setText(bean.sex);
        tv_signature.setText(bean.signature);
        tv_user_name.setText(bean.userName);
    }
    
    //���ý���ĵ�������¼�
    private void setListener() {
        tv_back.setOnClickListener(this);
        rl_nickName.setOnClickListener(this);
        rl_sex.setOnClickListener(this);
        rl_signature.setOnClickListener(this);
    }

    //�ؼ��ĵ���¼�
    @Override
    public void onClick(View v) {
        switch (v.getId()){
           //���ؼ��ĵ���¼�
            case R.id.tv_back:
                this.finish();
                break;
                
            //�ǳƵĵ���¼�
            case R.id.rl_nickName:   
                String name = tv_nickName.getText().toString();//��ȡ�ǳƿؼ��ϵ�����
                Bundle bdName = new Bundle();
                bdName.putString("content",name);  //���ݽ����ϵ��ǳ�����
                bdName.putString("title","�ǳ�");  //���ݽ���ı���
                bdName.putInt("flag",1);  //flag ����1��ʾ���ǳ�
                //��ת�����������޸Ľ���
                enterActivityForResult(ChangeUserInfoActivity.class,CHANGE_NICKNAME,bdName);
                break;
                
            //�Ա�ĵ���¼� 
            case R.id.rl_sex:
                String sex = tv_sex.getText().toString();
                sexDialog(sex);
                break;
                
            //ǩ���ĵ���¼� 
            case R.id.rl_signature: 
                String signature = tv_signature.getText().toString();//��ȡǩ���ؼ��ϵ�����
                Bundle bdSignature = new Bundle();
                bdSignature.putString("content",signature);//���ݽ����ϵ�ǩ������
                bdSignature.putString("title","ǩ��"); //���ݽ���ı���
                bdSignature.putInt("flag",2);//flag ����2��ʾ��ǩ��
              //��ת�����������޸Ľ���
                enterActivityForResult(ChangeUserInfoActivity.class,CHANGE_SIGNATURE,bdSignature);
                break;
            default:
                break;
        }
    }

    //�޸��Ա�ĵ�����
    private void sexDialog(String sex) {
        int sexFlag = 0;
        if("��".equals(sex)){
            sexFlag = 0;
        }else if("Ů".equals(sex)){
            sexFlag = 1;
        }
        final String items[] = {"��","Ů"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("�Ա�"); //���ñ���
        builder.setSingleChoiceItems(items, sexFlag, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(UserInfoActivity.this,items[which],Toast.LENGTH_SHORT).show();;
                setSex(items[which]);
            }
        });
        builder.show();
    }
    
    //���½����ϵ��Ա�����
    private void setSex(String sex) {
        tv_sex.setText(sex);
        //�������ݿ��е��Ա�����
        DBUtils.getInstance(this).updateUserInfo("sex",sex,spUserName);
    }
  

    //�����޸��Ժ�ش����ݵ�����  �������
    private String new_info;  //��������
    @Override
    protected  void  onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        switch (requestCode){
            case CHANGE_NICKNAME:
                if(data!=null){
                    new_info = data.getStringExtra("nickName");//�Ӹ������Ͻ���ش�����������
                    if(TextUtils.isEmpty(new_info)||new_info==null){
                        return;
                    }
                    tv_nickName.setText(new_info);
                    //�������ݿ��е��س��ֶ�
                    DBUtils.getInstance(UserInfoActivity.this).updateUserInfo("nickName", new_info,spUserName);
                }

                break;
            case CHANGE_SIGNATURE:

                if(data!=null){
                    new_info = data.getStringExtra("signature");//�Ӹ������Ͻ���ش�����������
                    if(TextUtils.isEmpty(new_info)||new_info==null){
                        return;
                    }
                    tv_signature.setText(new_info);
                    //�������ݿ��е�ǩ���ֶ�
                    DBUtils.getInstance(UserInfoActivity.this).updateUserInfo("signature", new_info,spUserName);
                }

                break;
        }

    }

}
