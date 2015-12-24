package cn.sz.qianfeng.util;

public class DateTool {

	private static String [] timer = new String[2];
	
	static{
		timer [0] = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";
		
		//timer [1] = "\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}";
		
		timer [1] = "\\d{4}-\\d{2}-\\d{2}";
		
		//timer [3] = "\\d{4}/\\d{2}/\\d{2}";
	}
	
	
	public boolean checkDate(String time){
		for (int i = 0; i < timer.length; i++) {
			if(time.matches(timer[i])){
				return true;
			}
		}
		return false;
	}
}
