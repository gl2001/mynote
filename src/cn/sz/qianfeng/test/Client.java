package cn.sz.qianfeng.test;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.sz.qianfeng.action.CallThread;
import cn.sz.qianfeng.action.ClientManager;
import cn.sz.qianfeng.action.UserClientManager;
import cn.sz.qianfeng.vo.Directory;
import cn.sz.qianfeng.vo.Users;

import com.alibaba.fastjson.JSON;

public class Client {

	//public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) {
		try {
			Socket  socket = new Socket("localhost", 8888);
			System.out.println("��ӭʹ�á��ҵıʼǡ�����");
			UserClientManager manager = new UserClientManager();
			ClientManager client = new ClientManager(socket);
			ExecutorService service = Executors.newCachedThreadPool();
			int count = 0 ;
			while(count<3){
				Scanner input = new Scanner(System.in);
				System.out.println("��ѡ����Ҫ�Ĳ���(1.��¼         2.ע��)��");
				int choice = Integer.valueOf(input.next());
				if(choice==1){
					//��¼
					//�ӿ���̨�����û���������
					Users users = manager.enterLoginInfo();
					//�̳߳ؿ����߳���������֤����,�����߳�ִ�н������
					JSONObject jo = new JSONObject();
					jo.put("status", 0);//0��ʾ��¼
					JSONArray arr = new JSONArray();
					arr.put(jo);
					arr.put(new JSONObject(users));
					Future<String> f = service.submit(new CallThread(socket, arr.toString()));
					String userinfo = f.get();
					users = JSON.parseObject(userinfo,Users.class);
					if(users!=null){
						System.out.println("��¼�ɹ�");
						new ClientManager(socket).showMainMenu(users);
						break;
					}else{
						count++;
						if(count>=3){
							System.out.println("��¼ʧ�ܴ����������Σ������˳�");
							System.exit(0);
						}else{
							System.out.println("��¼ʧ�ܣ�����������");
						}
					}
					//service.shutdown();
					
				}else if(choice==2){
					System.out.println("��ѡ����ǡ�ע�᡿");
					//������Ҫ�Ȳ�ѯ�ֵ�
					//�Ȼ�ȡ�ֵ���е�����Ķ��󼯺�
					List<Directory> directlist = client.findDirectBySubject(socket, "area");
					//�ٻ�ȡ�ֵ���еĿ�Ŀ����
					List<Directory> sublist = client.findDirectBySubject(socket, "subject");
					Users users = manager.enterForReg(directlist,sublist);
					JSONObject jo = new JSONObject();
					jo.put("status", 1);//1��ʾע��
					JSONArray arr = new JSONArray();
					arr.put(jo);
					arr.put(new JSONObject(users));
					Future<String> f = service.submit(new CallThread(socket, arr.toString()));
					System.out.println(f.get().equals("true")?"ע��ɹ�":"ע��ʧ��");
				}else{
					System.out.println("������������,����������");
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
