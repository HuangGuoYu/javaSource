package com.cqust.recurtion;

public class SimpleRecurtion {
	
	public static int sum = 0;
	//�ݹ��ӡ0-n
	public static void printN(int n){
		//����
		if(n > 0)
		printN(n-1);
		
		System.out.println(n+"--");
	}
	//ʵ��������ۼӺ�
	/**
	 *˼�룺��.��.��..........������
	 *Ҳ��������Ҫ����ĺ� 
	 */
	public static int addAll(int [] a,int n){
		//�������Ѿ��������һ���ʱ�򷵻�0��Ҳ���ǳ���
		if(a.length == n)
			return 0;
		//���µ�n-1�ĺ�
		int x = addAll(a,n+1);
		//���ص�ǰ��������µ�n-1�ĺ�
		return x + a[n];
	}
	/**
	 *�ж��ַ����Ƿ���� 
	 */
	public static boolean isSameString(String s1,String s2){
		if(s1.length() != s2.length()) return false;
		if(s1.length() == 0 && s2.length() == 0) return true;
		if(s1.charAt(0) != s2.charAt(0)) return false;
		return isSameString(s1.substring(1), s2.substring(1));
	}
	//�����������
	public static void main(String [] args){
//		printN(10);
		System.out.println(addAll(new int []{1,2,3,4,5,6},0));
		System.out.println(isSameString("huang", "huang1"));
		
	}
}
