package com.cqust.recurtion;

public class SimpleRecurtion {
	
	public static int sum = 0;
	//递归打印0-n
	public static void printN(int n){
		//出口
		if(n > 0)
		printN(n-1);
		
		System.out.println(n+"--");
	}
	//实现数组的累加和
	/**
	 *思想：【.【.【..........】】】
	 *也就是我需要后面的和 
	 */
	public static int addAll(int [] a,int n){
		//当数组已经到了最后一项的时候返回0，也就是出口
		if(a.length == n)
			return 0;
		//余下的n-1的和
		int x = addAll(a,n+1);
		//返回当前的项和余下的n-1的和
		return x + a[n];
	}
	/**
	 *判断字符串是否想等 
	 */
	public static boolean isSameString(String s1,String s2){
		if(s1.length() != s2.length()) return false;
		if(s1.length() == 0 && s2.length() == 0) return true;
		if(s1.charAt(0) != s2.charAt(0)) return false;
		return isSameString(s1.substring(1), s2.substring(1));
	}
	//主方法的入口
	public static void main(String [] args){
//		printN(10);
		System.out.println(addAll(new int []{1,2,3,4,5,6},0));
		System.out.println(isSameString("huang", "huang1"));
		
	}
}
