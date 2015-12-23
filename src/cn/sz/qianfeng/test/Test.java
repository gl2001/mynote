package cn.sz.qianfeng.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Test {

	public static void main(String[] args) {
		try {
			String s1 = "深圳";
			String s2 = "上海";
			String s3 = "北京";
			String s4 = "java面向对象知识点总结";
			String msg1 = URLEncoder.encode(s1, "utf-8");
			String msg2 = URLEncoder.encode(s2, "utf-8");
			String msg3 = URLEncoder.encode(s3, "utf-8");
			String msg4 = URLEncoder.encode(s4, "utf-8");
			
			System.out.println("msg1:"+msg1);
			System.out.println("msg2:"+msg2);
			System.out.println("msg3:"+msg3);
			System.out.println("msg4:"+msg4);
			
			
			
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
