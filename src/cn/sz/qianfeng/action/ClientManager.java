package cn.sz.qianfeng.action;

import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.sz.qianfeng.util.ClientSendTool;
import cn.sz.qianfeng.vo.Users;

public class ClientManager {

	private UserClientManager userclient = new UserClientManager();
	private Socket socket;
	public ClientManager(Socket socket){
		this.socket = socket;
	}
	/**
	 * 进入主菜单
	 * @param user
	 */
	public void showMainMenu(Users user){
		System.out.println("欢迎您，"+user.getRealname()+",您可以进行如下操作：");
		Scanner input = new Scanner(System.in);
		boolean type = true;
		while(type){
			System.out.println("--------------主菜单--------------");
			System.out.println("           1：修改密码");
			System.out.println("           2：显示个人信息");
			System.out.println("           3：显示我的笔记");
			System.out.println("           0：退出");
			System.out.println("---------- 请根据需要执行的操作，选择序号输入----");
			int check = input.nextInt();
			switch (check) {
			case 0:
				System.out.println("程序退出，期待您的下次使用，谢谢！！！");
				System.exit(0);
				break;
			case 1:
				//修改密码
				//先从控制台接收新密码并验证原密码和新密码
				userclient.enterForChangePwd(user);
				//通过线程发起请求，由服务器完成修改密码
				this.changePwd(user);
				break;
			case 2:
				//显示用户个人信息
				userclient.showPersonalInfo(user);
				break;
			case 3:
				
				break;
			default:
				System.out.println("选择错误，请重新选择");
				break;
			}
		}
	}
	
	
	public void changePwd(Users users){
		String result = new ClientSendTool<Users>(socket).sendRequest(users, 2);//修改密码时为2
		if(result!=null){
			System.out.println(result.equals("true")?"密码修改成功":"密码修改失败");
		}else{
			System.out.println("操作失败");
		}
	}
}
