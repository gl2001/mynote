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
			System.out.println("           4：上传我的笔记");
			System.out.println("           0：退出");
			System.out.println("---------- 请根据需要执行的操作，选择序号输入----");
			int check = input.nextInt();
			switch (check) {
			case 0:
				//0.程序退出
				System.out.println("程序退出，期待您的下次使用，谢谢！！！");
				System.exit(0);
				break;
			case 1:
				//1.修改密码
				//先从控制台接收新密码并验证原密码和新密码
				userclient.enterForChangePwd(user);
				//通过线程发起请求，由服务器完成修改密码
				this.changePwd(user);
				break;
			case 2:
				//2.显示用户个人信息
				userclient.showPersonalInfo(user);
				break;
			case 3:
				//3.查看我的笔记
				this.showNoteSplit(user);
				break;
			case 4:
				//上传笔记
				//先读取要上传笔记的内容
				List<String> content = noteclient.uploadNote();
				//完成笔记上传
				noteclient.uploadMyNote(socket, content, user);
				break;
			default:
				System.out.println("选择错误，请重新选择");
				break;
			}
		}
	}
	
	/**
	 * 客户端发送请求给服务器，来修改密码,并接收服务器的反馈信息
	 * @param users
	 */
	public void changePwd(Users users){
		String result = new ClientSendTool<Users>(socket).sendRequest(users, 2);//修改密码时为2
		if(result!=null){
			System.out.println(result.equals("true")?"密码修改成功":"密码修改失败");
		}else{
			System.out.println("操作失败");
		}
	}
	
	/**
	 * 我的笔记分页显示
	 * @param users
	 */
	public void showNoteSplit(Users users){
		PageSplit splitobj = noteclient.getSplitObj(users);
		while(splitobj.getIshaveData()){
			String result = new ClientSendTool<PageSplit>(socket).sendRequest(splitobj, 3);//我的笔记分页显示为3
			splitobj = noteclient.printNoteInfo(result, splitobj,users);
			int rows = splitobj.getCount();//总行数
			int allpage = (rows-1)/splitobj.getPs()+1;//总页数
			splitobj.setAllpage(allpage);
			if(splitobj.getCp()<=allpage&&splitobj.getCp()>=1){
				splitobj = noteclient.scanfNextPage(splitobj,socket);
				
			}
		}
	}
	
	/**
	 * 去服务器端查询字典表中的集合
	 * @param socket
	 * @param sub : 字典表中的项目字段
	 * @return
	 */
	public List<Directory> findDirectBySubject(Socket socket,String sub){
		Directory direct = new Directory();
		direct.setSubject(sub);
		String result = new ClientSendTool<Directory>(socket).sendRequest(direct, 6);//查询字典表为3
		return JSON.parseArray(result, Directory.class);
	}
}
