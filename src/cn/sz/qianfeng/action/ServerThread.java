package cn.sz.qianfeng.action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.apache.catalina.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.sz.qianfeng.vo.Users;

import com.alibaba.fastjson.JSON;

public class ServerThread implements Runnable {

	private Socket socket;
	private String obj;
	public ServerThread(Socket socket){
		this.socket = socket;
		//this.obj = obj;
	}
	
	@Override
	public void run() {
		try {
			
			InputStream is = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			UserServerManager userManager = new UserServerManager();
			while(true){
				//接收客户端发送过来的用户信息
				String info = br.readLine();
				JSONArray arr = new JSONArray(info);
				int status = arr.getJSONObject(0).getInt("status");
				if(status==0){
					//完成登录验证
					Users user = userManager.checkLogin(JSON.parseObject(arr.getJSONObject(1).toString(), Users.class));
					obj = JSON.toJSONString(user);
				}else if(status==1){
					//完成注册
					boolean isregok = userManager.isreg(JSON.parseObject(arr.getJSONObject(1).toString(),Users.class));
					obj = isregok+"";
				}else if(status==2){
					//修改密码
					boolean ischangeok = userManager.changePwd(JSON.parseObject(arr.getJSONObject(1).toString(), Users.class));
					obj = ischangeok+"";
				}
				
				//把验证结果返回客户端
				OutputStream os = socket.getOutputStream();
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
				bw.write(obj==null?"":obj);
				bw.newLine();
				bw.flush();
			}
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
