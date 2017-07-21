package com.example;

public class ExamOne {
	public static void main(String[] args) {
		Integer a1 = 100,a2 = 100,a3 =150 ,a4= 150;
		System.out.println(a1==a2);
		System.out.println(a3==a4);
		System.out.println(getBool());
	}
	public static boolean getBool(){
		try {
			return true;
		} catch (Exception e) {
		}finally {
			return false;
		}
	}
}
