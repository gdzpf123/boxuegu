package com.boxuegu.bean;

public class ExercisesBean {
    public int id;          //ÿ��ϰ��ID
    public String title;//ÿ��ϰ�����
    public String content;//ÿ��ϰ�����Ŀ
    public int background;//ÿ��ϰ��ǰ�ߵ����б���
    public int subjectId;//ÿ��ϰ���ID
    public String subject;//ÿ��ϰ������
    public String a;//ÿ��ϰ���Aѡ��
    public String b;//ÿ��ϰ���Bѡ��
    public String c;//ÿ��ϰ���Cѡ��
    public String d;//ÿ��ϰ���Dѡ��
    public int answer;//ÿ��ϰ�����ȷ��
    
	/*
	 * selectΪ 0,1,2,3,4���ĺ��壺
	 * 0ʱ��ʾ��ѡѡ���ǶԵ� 
	 * 1��ʾѡ���е�A�Ǵ���� 
	 * 2��ʾѡ���е�B�Ǵ���� 
	 * 3��ʾѡ���е�C�Ǵ���� 
	 * 4��ʾѡ���е�D�Ǵ����
	 */
    public int select;
}