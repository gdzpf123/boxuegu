package com.boxuegu.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


//SQLiteHelper��̳���SQLiteOpenHelper��
public class SQLiteHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;  //���ݿ�İ汾
    public static final String DB_NAME = "wordpress.db";   //���ݿ������
    public static final String U_USERINFO = "userinfo";   //��������
    
    public static final String U_VIDEO_PLAY_LIST = "videoplaylist";  //��Ƶ�����б�

    public SQLiteHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    
    //�������ݿ�
    @Override
    public void onCreate(SQLiteDatabase db){
        //�����û���Ϣ��
        db.execSQL("CREATE TABLE IF NOT EXISTS " + U_USERINFO + "( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "userName VARCHAR,"  //�û���
                + "nickName VARCHAR,"  //�ǳ�
                + "sex VARCHAR,"       //�Ա�
                + "signature VARCHAR"  //ǩ��
                + ")" );
        
      //������Ƶ���ż�¼
        db.execSQL("CREATE TABLE IF NOT EXISTS " + U_VIDEO_PLAY_LIST + "( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "userName VARCHAR,"//�û���
                + "chapterId INT," //�½�id
                + "videoId INT,"//С��id
                + "videoPath VARCHAR,"
                + "title VARCHAR,"  //�½�����
                + "secondTitle VARCHAR" // ��Ƶ����
                + ")");
   
        
    }
    
    
  

    //���ݿ����� �汾������ �������ô˷���
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF NOT EXISTS " + U_USERINFO);
        db.execSQL("DROP TABLE IF NOT EXISTS " + U_VIDEO_PLAY_LIST);
        onCreate(db);

    }
}
