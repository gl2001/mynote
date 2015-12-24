package cn.sz.qianfeng.action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.sz.qianfeng.util.ClientSendTool;
import cn.sz.qianfeng.util.DateTool;
import cn.sz.qianfeng.vo.Note;
import cn.sz.qianfeng.vo.PageSplit;
import cn.sz.qianfeng.vo.Users;

import com.alibaba.fastjson.JSON;

public class NoteClientManager {
	
	/**
	 * ��ʼ����ҳ����
	 * @param users
	 * @return
	 */
	public PageSplit getSplitObj(Users users){
		int cp = 1;//��ǰҳ��
		int ps = 5;//ÿҳ��ʾ����
		String column = "userid";//������Ϊ�ǲ�ѯ�ҵıʼǣ�������Ҫָ������Ϊuserid
		String kw = users.getUserid()+"";//����Ϊ��ǰ�û���id
		int count = 0 ;//������
		int allpage = 0 ;//��ҳ��
		boolean ishaveData = true;//�Ƿ�������
		
		PageSplit split = new PageSplit();
		split.setCp(cp);
		split.setPs(ps);
		split.setColumn(column);
		split.setKw(kw);
		split.setCount(count);
		split.setAllpage(allpage);
		split.setIshaveData(ishaveData);
		
		return split;
	}
	
	/**
	 * ��ӡ�ӷ��������صıʼǼ�¼��Ϣ
	 * @param jsonStr : ��ʽ����Ϊ��{"rows":�ҵıʼǵ�������,"arr":"�ҵıʼǼ�¼�ļ���"}
	 * @param splitobj : ��ҳ����
	 * @return
	 */
	public PageSplit printNoteInfo(String jsonStr,PageSplit splitobj){
		try {
			JSONObject jsonobj = new JSONObject(jsonStr);
			splitobj.setCount(jsonobj.getInt("rows"));
			splitobj.setAllpage((splitobj.getCount()-1)/splitobj.getPs()+1);
			String arr = jsonobj.getString("arr");
			List<Note> notelist = JSON.parseArray(arr, Note.class);
			System.out.println("**********************************************��"+splitobj.getCp()+"ҳ������"+splitobj.getAllpage()+"ҳ************************************************************");
			System.out.println("���\t�û�\t   �ʼ�����\t\t\t     ����ʱ��\t\t�Ƿ���\t\t\t���");
			if(notelist==null||notelist.size()<=0){
				System.out.println("��û����ӹ��ʼ�");
				splitobj.setIshaveData(false);
			}else{
				for (int i = 0; i < notelist.size(); i++) {
					Note note = notelist.get(i);
					System.out.print(note.getNid());
					System.out.print("\t");
					System.out.print(note.getUser().getRealname());
					System.out.print("\t");
					if(note.getNotename().length()<=10){
						System.out.print(note.getNotename());
						for (int j = 0; j < 13-note.getNotename().length(); j++) {
							System.out.print("  ");
						}
					}else{
						System.out.print(note.getNotename().substring(0, 10));
						System.out.print("...");
					}
					//System.out.print(note.getNotename());
					System.out.print("\t");
					System.out.print(note.getCreateTime());
					for (int j = 0; j < 20-note.getCreateTime().length(); j++) {
						System.out.print("  ");
					}
					System.out.print("\t  ");
					System.out.print(note.getIsShare().equals("0")?"��":"��");
					System.out.print("\t\t\t");
					if(note.getSummary().length()<=15){
						System.out.print(note.getSummary());
					}else{
						System.out.print(note.getSummary().substring(0, 15));
						System.out.print("...");
					}
					System.out.println();
				}
				System.out.println("**********************************************************************************************************************");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return splitobj;
	}
	
	/**
	 * ��ҳ��������
	 * @param splitobj
	 * @param key : 0��ʾ�ҵıʼǷ�ҳ��1��ʾ����ʼǷ�ҳ
	 * @return
	 */
	public PageSplit scanfNextPage(PageSplit splitobj,Socket socket,int key){
		System.out.println("��ѡ��ҳ��(1.��ҳ                         2.��һҳ                        3.��һҳ                          4.βҳ                         5.�鿴�ʼ�����                      0.�����˵�)��");
		Scanner input = new Scanner(System.in);
		boolean flag = true;
		while(flag){
			int nextpage = input.nextInt();
			switch (nextpage) {
			case 1:
				if(splitobj.getCp()==1){
					System.out.println("�Ѿ��ǵ�һҳ��,������ѡ��:");
				}else{
					splitobj.setCp(1);
					flag = false;
				}
				break;
			case 2:
				if(splitobj.getCp()<=1){
					System.out.println("�Ѿ��ǵ�һҳ��,������ѡ��:");
				}else{
					splitobj.setCp(splitobj.getCp()-1);
					flag = false;
				}
				break;
			case 3:
				if(splitobj.getCp()>=splitobj.getAllpage()){
					System.out.println("�Ѿ������һҳ�ˣ�������ѡ��:");
				}else{
					splitobj.setCp(splitobj.getCp()+1);
					flag = false;
				}
				break;
			case 4:
				if(splitobj.getCp()>=splitobj.getAllpage()){
					System.out.println("�Ѿ������һҳ�ˣ�������ѡ��:");
				}else{
					splitobj.setCp(splitobj.getAllpage());
					flag = false;
				}
				break;
			case 5:
				//�鿴�ʼ�
				String result = this.readNoteContent(socket);
				//ִ�бʼǵĲ���
				int mydo = this.scanfNoteLoad(key);
				if(mydo==4){
					splitobj.setIshaveData(false);
				}else if(mydo==2){
					//��ɹ���ʼ�
					if(key==1){
						System.out.println("�ⲻ�����ıʼǣ��������޸����Ĺ�������");
					}else{
						this.shareNote(socket,result);
					}
				}else if(mydo==1){
					//�������
					boolean issuc = this.downloadNote(result);
					System.out.println(issuc?"�������":"����ʧ��");
				}
				flag = false;
				break;
			case 0:
				flag = false;
				splitobj.setIshaveData(false);
				break;
			}
		}
		return splitobj;
	}
	
	
	
	/**
	 * ���ݱʼǱ�ŵ���������ȡ�ʼ����ݣ�����ӡ
	 * @param socket
	 */
	public String readNoteContent(Socket socket){
		System.out.println("������Ҫ�Ķ��ıʼǵı�ţ�");
		Scanner input = new Scanner(System.in);
		int nid = input.nextInt();
		Note note = new Note();
		note.setNid(nid);
		String result = new ClientSendTool<Note>(socket).sendRequest(note, 4);//4��ʾ��Ҫ�鿴ָ����ŵ�ĳƪ�ʼǵ�����
		try {
			JSONArray arr = new JSONArray(result);
			List<String> list = JSON.parseArray(arr.getJSONArray(1).toString(), String.class);
			System.out.println("******************************************************�ʼǿ�ʼ*************************************************");
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
			System.out.println("******************************************************�ʼǽ���*************************************************");
			return result;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * ��ȡ�ʼ����ݺ�,��Ҫ�Ĳ���
	 * @return
	 */
	public int scanfNoteLoad(int key){
		System.out.println("��ѡ����Ҫ�Ĳ���( 1.����                     "+(key==0?" 2.(����/ȡ������)���ʼ�":"")+"         3.���رʼ��б�                       4.�������˵�)��");
		Scanner input = new Scanner(System.in);
		return input.nextInt();
	}
	
	/**
	 * �ļ�����
	 * @param content : �ļ�����
	 */
	public boolean downloadNote(String result){
		
		List<String> content = null;
		try {
			JSONArray arr = new JSONArray(result);
			content = JSON.parseArray(arr.getJSONArray(1).toString(), String.class);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		
		Scanner input = new Scanner(System.in);
		System.out.println("��������Ҫ��ŵ�·��(��ʽΪ�̷�:\\Ŀ¼\\�ļ���.txt,���磺d:\\project\\java����.txt):");
		String path = input.next();
		File file = new File(path);
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		OutputStream os = null;
		BufferedWriter bw =  null;
		try {
			os = new FileOutputStream(file);
			bw = new BufferedWriter(new OutputStreamWriter(os));
			for (int i = 0; content!=null&&i < content.size(); i++) {
				bw.write(content.get(i));
				bw.newLine();
				bw.flush();
			}
			
			bw.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * ��ȡҪ�ϴ����ļ�����
	 */
	public List<String> uploadNote(){
		Scanner input = new Scanner(System.in);
		System.out.println("��������Ҫ�ϴ����ļ�·��(��ʽΪ�̷�:\\Ŀ¼\\�ļ���.txt,���磺d:\\project\\java����.txt):");
		String path = input.next();
		File file = new File(path);
		if(!file.exists()){
			System.out.println("Դ�ļ�������,�ص����˵�");
		}else{
			//Դ�ļ�����
			InputStream is = null;
			BufferedReader br = null;
			List<String> content = new ArrayList<String>();
			try {
				is = new FileInputStream(file);
				br = new BufferedReader(new InputStreamReader(is));
				String msg = "";
				while((msg=br.readLine())!=null){
					content.add(msg);
				}
				br.close();
				return content;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * ִ���ϴ�
	 * @param socket
	 * @param list
	 * @param user
	 */
	public void uploadMyNote(Socket socket,List<String> list,Users user){
		//�ϴ��ļ�������,��Ϊһ��json�����ַ���
		String content = JSON.toJSONString(list);
		
		Scanner input = new Scanner(System.in);
		try {
			System.out.println("������ʼ�����:");
			String notename = input.next();
			//notename = URLEncoder.encode(notename, "utf-8");
			String createtime = null;
			while(true){
				System.out.println("������ñʼǼ�¼ʱ��:");
				createtime = input.next();
				if(new DateTool().checkDate(createtime)){
					break;
				}else{
					System.out.println("���ڸ�ʽ����,����������!");
				}
			}
			System.out.println("������ʼǼ��:");
			String summary = input.next();
			//summary = URLEncoder.encode(summary, "utf-8");
			
			Note note = new Note();
			note.setUserid(user.getUserid());
			note.setNotename(notename);
			note.setCreateTime(createtime);
			note.setIsShare("0");
			note.setSummary(summary);
			note.setReadTimes(0);
			note.setLikeTimes(0);
			
			/*JSONArray arr = new JSONArray();
			arr.put(new JSONObject(note));
			arr.put(new JSONArray(list));*/
			
			JSONObject jo = new JSONObject();
			jo.put("note", new JSONObject(note));
			jo.put("list", new JSONArray(list));
			
			
			String result = new ClientSendTool<Object>(socket).sendJsonRequest(jo.toString(), 5);//5��ʾ�ϴ��ʼ�
			System.out.println(result.equals("true")?"�ϴ��ɹ�":"�ϴ�ʧ��");
			
		}  catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �����ҵıʼ�
	 * @param result
	 */
	public void shareNote(Socket socket,String result){
		/**
		 * //result�����ݣ�
		 * [{"summary":"sasdfds�������","createTime":"2015-10-20","nid":3,"notename":"jdk������������",
		 * "path":"D:\\qianfeng\\work\\yearEndCheck\\project\\mynotefile\\zhangsan\\jdk������������.txt","userid":7,"likeTimes":0,"isShare":"0","readTimes":0},
		 * ["JAVA_HOME=D:\\Program-Files\\Java\\jdk1.6.0_10","CLASSPATH=.;D:\\Program-Files\\Java\\jdk1.6.0_10\\lib;","PATH=D:\\Program-Files\\Java\\jdk1.6.0_10\\bin"]]
		 */
		try {
			JSONArray arr = new JSONArray(result);
			JSONObject jsonobj = arr.getJSONObject(0);
			Note note = JSON.parseObject(jsonobj.toString(), Note.class);
			if(note.getIsShare().equals("0")){
				//�Ѿ��ǹ�����
				note.setIsShare("1");
			}else{
				note.setIsShare("0");
			}
			// ���������������
			String res = new ClientSendTool<Note>(socket).sendRequest(note, 7);// �������������ʼ�Ϊ7
			if (res.equals("true")) {
				System.out.println("����˱ʼǵķ������ã�лл���Ĳ������ñʼ����ڴ��ڡ�"+(note.getIsShare().equals("0")?"����":"������")+"��״̬");
			} else {
				System.out.println("�������ʧ�ܣ������²���");
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public void findShareNote(Socket socket,Users user){
		System.out.println("��ѡ����ǡ���������ʼǡ�");
		System.out.println("��ѡ�������һ������չ����(1.�ʼ�����                  2.�ʼ�ʱ��                     3.�ʼǼ��):");
		Scanner input = new Scanner(System.in);
		int check = input.nextInt();
		System.out.println("��ѡ���˸��ݡ�"+(check==1?"�ʼ�����":(check==2?"�ʼ�ʱ��":"�ʼǼ��"))+"��");
		System.out.println("�����������ؼ��֣�");
		String kw = input.next();
		if(check==2){
			while(!new DateTool().checkDate(kw)){
				System.out.println("�������벻��ȷ������������,����'exit'�����˵�:");
				System.out.println("�����������ؼ��֣�");
				kw = input.next();
				if(kw.equals("exit")){
					return;
				}
			}
		}
		PageSplit splitobj = this.getSplitObj(user);
		splitobj.setColumn(check==1?"notename":(check==2?"createtime":"summary"));
		splitobj.setKw(kw);
		while(splitobj.getIshaveData()){
			String res = new ClientSendTool<PageSplit>(socket).sendRequest(splitobj, 3);// ��ҳ��ѯ�ʼ�Ϊ3
			splitobj = this.printNoteInfo(res, splitobj);
			int allpage = (splitobj.getCount()-1)/splitobj.getPs()+1;//��ҳ��
			splitobj.setAllpage(allpage);
			if(splitobj.getCp()<=allpage&&splitobj.getCp()>=1){
				splitobj = this.scanfNextPage(splitobj,socket,1);
			}
		}
		
	}
}
