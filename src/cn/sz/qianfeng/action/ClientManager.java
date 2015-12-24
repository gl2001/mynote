package cn.sz.qianfeng.action;

import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Future;

import com.alibaba.fastjson.JSON;

import cn.sz.qianfeng.util.ClientSendTool;
import cn.sz.qianfeng.vo.Directory;
import cn.sz.qianfeng.vo.PageSplit;
import cn.sz.qianfeng.vo.Users;

public class ClientManager {

	private UserClientManager userclient = new UserClientManager();
	private NoteClientManager noteclient = new NoteClientManager();
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
			System.out.println("           4���ϴ��ҵıʼ�");
			System.out.println("           0���˳�");
			System.out.println("---------- �������Ҫִ�еĲ�����ѡ���������----");
			int check = input.nextInt();
			switch (check) {
			case 0:
				//0.�����˳�
				System.out.println("�����˳����ڴ������´�ʹ�ã�лл������");
				System.exit(0);
				break;
			case 1:
				//1.�޸�����
				//�ȴӿ���̨���������벢��֤ԭ�����������
				userclient.enterForChangePwd(user);
				//ͨ���̷߳��������ɷ���������޸�����
				this.changePwd(user);
				break;
			case 2:
				//2.��ʾ�û�������Ϣ
				userclient.showPersonalInfo(user);
				break;
			case 3:
				//3.�鿴�ҵıʼ�
				this.showNoteSplit(user);
				break;
			case 4:
				//�ϴ��ʼ�
				//�ȶ�ȡҪ�ϴ��ʼǵ�����
				List<String> content = noteclient.uploadNote();
				//��ɱʼ��ϴ�
				noteclient.uploadMyNote(socket, content, user);
				break;
			default:
				System.out.println("ѡ�����������ѡ��");
				break;
			}
		}
	}
	
	/**
	 * �ͻ��˷�������������������޸�����,�����շ������ķ�����Ϣ
	 * @param users
	 */
	public void changePwd(Users users){
		String result = new ClientSendTool<Users>(socket).sendRequest(users, 2);//�޸�����ʱΪ2
		if(result!=null){
			System.out.println(result.equals("true")?"�����޸ĳɹ�":"�����޸�ʧ��");
		}else{
			System.out.println("����ʧ��");
		}
	}
	
	/**
	 * �ҵıʼǷ�ҳ��ʾ
	 * @param users
	 */
	public void showNoteSplit(Users users){
		PageSplit splitobj = noteclient.getSplitObj(users);
		while(splitobj.getIshaveData()){
			String result = new ClientSendTool<PageSplit>(socket).sendRequest(splitobj, 3);//�ҵıʼǷ�ҳ��ʾΪ3
			splitobj = noteclient.printNoteInfo(result, splitobj,users);
			int rows = splitobj.getCount();//������
			int allpage = (rows-1)/splitobj.getPs()+1;//��ҳ��
			splitobj.setAllpage(allpage);
			if(splitobj.getCp()<=allpage&&splitobj.getCp()>=1){
				splitobj = noteclient.scanfNextPage(splitobj,socket);
				
			}
		}
	}
	
	/**
	 * ȥ�������˲�ѯ�ֵ���еļ���
	 * @param socket
	 * @param sub : �ֵ���е���Ŀ�ֶ�
	 * @return
	 */
	public List<Directory> findDirectBySubject(Socket socket,String sub){
		Directory direct = new Directory();
		direct.setSubject(sub);
		String result = new ClientSendTool<Directory>(socket).sendRequest(direct, 6);//��ѯ�ֵ��Ϊ3
		return JSON.parseArray(result, Directory.class);
	}
}
