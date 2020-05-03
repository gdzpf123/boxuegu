package com.boxuegu.view;

import android.app.Activity;
import com.boxuegu.R;
import com.boxuegu.adapter.ExercisesAdapter;
import com.boxuegu.bean.ExercisesBean;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class ExercisesView {
    private ListView lv_list;
    private ExercisesAdapter adapter;
    private List<ExercisesBean> ebl;
    private Activity mContext;
    private LayoutInflater mInflater;
    private View mCurrentView;
    public ExercisesView(Activity context){
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }
    private void createView(){
        initView();
    }
    private void initView(){
        mCurrentView = mInflater.inflate(R.layout.main_view_exercises,null);
        lv_list = (ListView) mCurrentView.findViewById(R.id.lv_list);
        adapter = new ExercisesAdapter(mContext);
        initData();
        adapter.setData(ebl);
        lv_list.setAdapter(adapter);
    }
    
    //��������
    private void initData(){
        ebl = new ArrayList<ExercisesBean>();
        for (int i=0;i<10;i++){
            ExercisesBean bean = new ExercisesBean();
            bean.id = (i+1);
            switch (i){
                case 0:
                    bean.title = "��1�� ����(��վ)���";
                    bean.content="����5��";
                    bean.background=(R.drawable.exercises_bg_1);
                    break;
                case 1:
                    bean.title = "��2�� ��ʶWordPress";
                    bean.content="����5��";
                    bean.background=(R.drawable.exercises_bg_2);
                    break;
                case 2:
                    bean.title = "��3�� WordPress����";
                    bean.content="����5��";
                    bean.background=(R.drawable.exercises_bg_3);
                    break;
                case 3:
                    bean.title = "��4�� ��ϤLinuxϵͳ";
                    bean.content="����5��";
                    bean.background=(R.drawable.exercises_bg_4);
                    break;
                case 4:
                    bean.title = "��5�� PHP�����﷨";
                    bean.content="����5��";
                    bean.background=(R.drawable.exercises_bg_1);
                    break;
                case 5:
                    bean.title = "��6�� MySQL���ݿ�";
                    bean.content="����5��";
                    bean.background=(R.drawable.exercises_bg_2);
                    break;
                case 6:
                    bean.title = "��7�� Nginx������";
                    bean.content="����5��";
                    bean.background=(R.drawable.exercises_bg_3);
                    break;
                case 7:
                    bean.title = "��8�� �Ʒ��������";
                    bean.content="����5��";
                    bean.background=(R.drawable.exercises_bg_4);
                    break;
                case 8:
                    bean.title = "��9�� ���������";
                    bean.content="����5��";
                    bean.background=(R.drawable.exercises_bg_1);
                    break;
                case 9:
                    bean.title = "��10�� WordPress����";
                    bean.content="����5��";
                    bean.background=(R.drawable.exercises_bg_2);
                    break;
                default:
                    break;
            }
            ebl.add(bean);
        }
    }
    
    //��ȡ��ǰ�ڵ������Ϸ���ʾ��Ӧ��view
    public View getView(){
        if (mCurrentView == null){
            createView();
        }
        return mCurrentView;
    }
    
    //��ʾ��ǰ�������Ϸ�����Ӧ��view����
    public void showView(){
        if (mCurrentView == null) {
            createView();
        }
        mCurrentView.setVisibility(View.VISIBLE);
    }

}