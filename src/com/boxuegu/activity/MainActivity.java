package com.boxuegu.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com. boxuegu. R;
import com.boxuegu.view.CourseView;
import com.boxuegu.view.ExercisesView;
import com.boxuegu.view.MyInfoView;
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

	//�м�������
    private FrameLayout mBodyLayout;
    //�ײ���������ť
    private LinearLayout mBottomLayout;

    //�ײ���ť
    private View mCourseBtn;
    private View mExercisesBtn;
    private View mMyInfoBtn;
    private TextView tv_course;
    private TextView tv_exercises;
    private TextView tv_myInfo;
    private ImageView iv_course;
    private ImageView iv_exercises;
    private ImageView iv_myInfo;
    private TextView tv_back;
    private TextView tv_main_title;
    private RelativeLayout rl_title_bar;
    //��ת��ϰ��
    private ExercisesView mExerciseView;
    
    private CourseView mCourseView;
    
    //��������
    private MyInfoView mMyInfoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //���ý��沼��
        setContentView(R.layout.activity_main);
        //���ý���Ϊ����
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
        initBottomBar();
        setListener();
        setInitStatus();
    }
    
	/* 
	 * ��ȡ�����ϵ�UI�ؼ�
	 */
    private void init() {
        tv_back = (TextView)findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("WordPress�γ�");
        rl_title_bar = (RelativeLayout)findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#FF9900"));
        tv_back.setVisibility(View.GONE);
        initBodyLayout();
    }
    
    /* 
	 * ��ȡ�ײ��������ϵĿؼ�
	 */
    private void initBottomBar() {
        mBottomLayout = (LinearLayout) findViewById(R.id.main_bottom_bar);
        mCourseBtn=findViewById(R.id.bottom_bar_course_btn);
        mExercisesBtn= findViewById(R.id.bottom_bar_exercises_btn);
        mMyInfoBtn = findViewById(R.id.bottom_bar_myinfo_btn);
        tv_course=(TextView)findViewById(R.id.bottom_bar_text_course);
        tv_exercises=(TextView)findViewById(R.id.bottom_bar_text_exercises);
        tv_myInfo = (TextView)findViewById(R.id.bottom_bar_text_myinfo);
        iv_course =(ImageView)findViewById(R.id.bottom_bar_image_course);
        iv_exercises= (ImageView)findViewById(R.id.bottom_bar_image_exercises);
        iv_myInfo= (ImageView)findViewById(R.id.bottom_bar_image_myinfo);

    }
    private void initBodyLayout() {
        mBodyLayout = (FrameLayout) findViewById(R.id.main_body);
    }

    /* 
	 * �ؼ��ĵ���¼�
	 */
    @Override
    public void onClick(View v){
        switch (v.getId()){
        	//�γ̵ĵ���¼�
            case R.id.bottom_bar_course_btn:
                clearBottomImageState();
                selectDisplayView(0);
                break;
                
            //ϰ��ĵ���¼�
            case R.id.bottom_bar_exercises_btn:
                clearBottomImageState();
                selectDisplayView(1);
                break;
                
             //�ҵĵ���¼�
            case R.id.bottom_bar_myinfo_btn:
                clearBottomImageState();
                selectDisplayView(2);
                if (mMyInfoView == null){
                    mMyInfoView=new MyInfoView(this);
                    mBodyLayout.addView(mMyInfoView.getView());
                }else {
                	mMyInfoView.getView();
                }
                mMyInfoView.showView();
                
                break;
            default:
                break;
        }
    }
    
    /* 
	 * ���õײ�������ť�ĵ�������¼�
	 */
    private void setListener() {
        for (int i = 0; i < mBottomLayout.getChildCount();i++){
            mBottomLayout.getChildAt(i).setOnClickListener(this);
        }
    }

    /* 
	 * ����ײ���ť��ѡ��״̬
	 */
    private void clearBottomImageState() {
        tv_course.setTextColor(Color.parseColor("#666666"));
        tv_exercises.setTextColor(Color.parseColor("#666666"));
        tv_myInfo.setTextColor(Color.parseColor("#666666"));
        iv_course.setImageResource(R.drawable.main_course_icon);
        iv_exercises.setImageResource(R.drawable.main_exercises_icon);
        iv_myInfo.setImageResource(R.drawable.main_my_icon);
        for (int i=0;i<mBottomLayout.getChildCount();i++){
            mBottomLayout.getChildAt(i).setSelected(false);
        }
    }

    /* 
	 * ���õײ���ť��ѡ��״̬
	 */
    public void setSelectedStatus(int index){
        switch (index){
            case 0:
                mCourseBtn.setSelected(true);
                iv_course.setImageResource(R.drawable.main_course_icon_selected);
                tv_course.setTextColor(Color.parseColor("#0097F7"));
                rl_title_bar.setVisibility(View.VISIBLE);
                tv_main_title.setText("WordPress�γ�");
                break;
            case 1:
                mExercisesBtn.setSelected(true);
                iv_exercises.setImageResource(R.drawable.main_exercises_icon_selected);
                tv_exercises.setTextColor(Color.parseColor("#0097F7"));
                rl_title_bar.setVisibility(View.VISIBLE);
                tv_main_title.setText("WordPressϰ��");
                break;
            case 2:
                mMyInfoBtn.setSelected(true);
                iv_myInfo.setImageResource(R.drawable.main_my_icon_selected);
                tv_myInfo.setTextColor(Color.parseColor("#0097F7"));
                rl_title_bar.setVisibility(View.GONE);
        }
    }
    
    /* 
	 * �Ƴ�����Ҫ����ͼ
	 */
    private void removeAllView(){
        for (int i = 0;i<mBodyLayout.getChildCount();i++){
            mBodyLayout.getChildAt(i).setVisibility(View.GONE);
        }
    }

    /* 
	 * ���ý���view�ĳ�ʼ��״̬
	 */
    private void setInitStatus() {
        clearBottomImageState();
        setSelectedStatus(0);
        createView(0);

    }

    /* 
	 * ��ʾ��Ӧ��ҳ��
	 */
    private void selectDisplayView(int index) {
        removeAllView();
        createView(index);
        setSelectedStatus(index);
    }

    /* 
	 * ѡ����ͼ
	 */
    private void createView(int viewIndex) {
        switch (viewIndex){
            case 0:
                //�γ̽���
            	if (mCourseView == null){
                    mCourseView = new CourseView(this);
                    mBodyLayout.addView(mCourseView.getView());
                }else{
                    mCourseView.getView();
                }
                mCourseView.showView();
            	
                break;
            case 1:
               //ϰ�����
            	if (mExerciseView == null){
                    mExerciseView = new ExercisesView(this);
                    mBodyLayout.addView(mExerciseView.getView());
                }else{
                    mExerciseView.getView();
                }
                mExerciseView.showView();
                break;
            case 2:
            	//��������
            	if (mMyInfoView == null){
                    mMyInfoView = new MyInfoView(this);
                    mBodyLayout.addView(mMyInfoView.getView());
                }else {
                    mMyInfoView.getView();
                }
                mMyInfoView.showView();
               //�ҵĽ���
                break;
        }
    }

    //��������
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
        	//�����ý�����¼���洫�ݹ����ĵ�¼״̬
            boolean isLogin = data.getBooleanExtra("isLogin",false);
            if (isLogin){      //��¼�ɹ�ʱ��ʾ�γ̽���
                clearBottomImageState();
                selectDisplayView(0);
            }
            if (mMyInfoView != null){    //��¼�ɹ����˳���¼ʱ����islogin�����ҵĽ���
                mMyInfoView.setLoginParams(isLogin);
            }
        }
    }


    protected long exitTime; //��¼��һ�ε����ʱ��

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if ((System.currentTimeMillis()-exitTime)>2000){
                Toast.makeText(MainActivity.this,"�ٰ�һ���˳�WordPress",Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }else{
                MainActivity.this.finish();
                if (readLoginStatus()){
                    clearLoginStatus();
                }
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    //��ȡSharedPreferences�еĵ�¼״̬
    private boolean readLoginStatus() {
        SharedPreferences sp = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        boolean isLogin = sp.getBoolean("isLogin",false);
        return isLogin;
    }

    //���SharedPreferences�еĵ�¼״̬
    private void clearLoginStatus() {
        SharedPreferences sp = getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();  //��ȡ�༭��
        editor.putBoolean("isLogin",false);   //�����¼״̬
        editor.putString("loginUserName","");  //�����¼ʱ���û���
        editor.commit();    //�ύ�޸�
    }

}