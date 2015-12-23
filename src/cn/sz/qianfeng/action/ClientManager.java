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
	 * �������˵�
	 * @param user
	 */
	public void showMainMenu(Users user){
		System.out.println("��ӭ����"+user.getRealname()+",�����Խ������²�����");
		Scanner input = new Scanner(System.in);
		boolean type = true;
		while(type){
			System.out.println("--------------���˵�--------------");
			System.out.println("           1���޸�����");
			System.out.println("           2����ʾ������Ϣ");
			System.out.println("           3����ʾ�ҵıʼ�");
			System.out.println("           0���˳�");
			System.out.println("---------- �������Ҫִ�еĲ�����ѡ���������----");
			int check = input.nextInt();
			switch (check) {
			case 0:
				System.out.println("�����˳����ڴ������´�ʹ�ã�лл������");
				System.exit(0);
				break;
			case 1:
				//�޸�����
				//�ȴӿ���̨���������벢��֤ԭ�����������
				userclient.enterForChangePwd(user);
				//ͨ���̷߳��������ɷ���������޸�����
				this.changePwd(user);
				break;
			case 2:
				//��ʾ�û�������Ϣ
				userclient.showPersonalInfo(user);
				break;
			case 3:
				
				break;
			default:
				System.out.println("ѡ�����������ѡ��");
				break;
			}
		}
	}
	
	
	public void changePwd(Users users){
		String result = new ClientSendTool<Users>(socket).sendRequest(users, 2);//�޸�����ʱΪ2
		if(result!=null){
			System.out.println(result.equals("true")?"�����޸ĳɹ�":"�����޸�ʧ��");
		}else{
			System.out.println("����ʧ��");
		}
	}
}
