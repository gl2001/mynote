package cn.sz.qianfeng.test;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.sz.qianfeng.action.CallThread;
import cn.sz.qianfeng.action.UserClientManager;
import cn.sz.qianfeng.vo.Users;

import com.alibaba.fastjson.JSON;

public class Client {

	//public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) {
		try {
			Socket  socket = new Socket("localhost", 8888);
			System.out.println("欢迎使用【我的笔记】服务");
			UserClientManager manager = new UserClientManager();
			ExecutorService service = Executors.newCachedThreadPool();
			boolean type = true;
			int count = 0 ;
			while(type){
				Scanner input = new Scanner(System.in);
				System.out.println("请选择您要的操作(1.登录         2.注册)：");
				int choice = Integer.valueOf(input.next());
				if(choice==1){
					//登录
					//从控制台输入用户名和密码
					Users users = manager.enterLoginInfo();
					//线程池开启线程来发送验证请求,并把线程执行结果返回
					JSONObject jo = new JSONObject();
					jo.put("status", 0);//0表示登录
					JSONArray arr = new JSONArray();
					arr.put(jo);
					arr.put(new JSONObject(users));
					Future<String> f = service.submit(new CallThread(socket, arr.toString()));
					String userinfo = f.get();
					users = JSON.parseObject(userinfo,Users.class);
					if(users!=null){
						System.out.println("登录成功");
						System.out.println(users.getRealname()+"----"+users.getEmail()+"-----"+users.getNickname());
						type = false;
					}else{
						count++;
						if(count>=3){
							System.out.println("登录失败次数超过三次，程序退出");
							type = false;
						}else{
							System.out.println("登录失败，请重新输入");
						}
					}
					//service.shutdown();
					
				}else if(choice==2){
					System.out.println("您选择的是【注册】");
					Users users = manager.enterForReg();
					JSONObject jo = new JSONObject();
					jo.put("status", 1);//1表示注册
					JSONArray arr = new JSONArray();
					arr.put(jo);
					arr.put(new JSONObject(users));
					Future<String> f = service.submit(new CallThread(socket, arr.toString()));
					System.out.println(f.get().equals("true")?"注册成功":"注册失败");
				}else{
					System.out.println("您的输入有误,请重新输入");
				}
				
				//br.close();
			}
			
			
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
