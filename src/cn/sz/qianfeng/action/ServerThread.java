package cn.sz.qianfeng.action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.sz.qianfeng.vo.Directory;
import cn.sz.qianfeng.vo.Note;
import cn.sz.qianfeng.vo.PageSplit;
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
			NoteServerManager noteManager = new NoteServerManager();
			while(true){
				//���տͻ��˷��͹������û���Ϣ
				String info = br.readLine();
				JSONArray arr = new JSONArray(info);
				int status = arr.getJSONObject(0).getInt("status");
				if(status==0){
					//��ɵ�¼��֤
					Users user = userManager.checkLogin(JSON.parseObject(arr.getJSONObject(1).toString(), Users.class));
					obj = JSON.toJSONString(user);
				}else if(status==1){
					//���ע��
					boolean isregok = userManager.isreg(JSON.parseObject(arr.getJSONObject(1).toString(),Users.class));
					obj = isregok+"";
				}else if(status==2){
					//�޸�����
					boolean ischangeok = userManager.changePwd(JSON.parseObject(arr.getJSONObject(1).toString(), Users.class));
					obj = ischangeok+"";
				}else if(status==3){
					//��ҳ��ѯ�ҵıʼ�
					obj = userManager.findMyNoteSplit(JSON.parseObject(arr.getJSONObject(1).toString(), PageSplit.class));
				}else if(status==4){
					//����ָ���ıʼǱ�ţ���ѯ��Ӧ�ıʼ�����
					obj = noteManager.readNoteContent(JSON.parseObject(arr.getJSONObject(1).toString(),Note.class));
				}else if(status==5){
					//�ϴ��ʼ�
					String jsonStr = arr.getJSONObject(1).toString();
					JSONObject jsonobj = new JSONObject(jsonStr);
					List<String> content = JSON.parseArray(jsonobj.getJSONArray("list").toString(), String.class);
					boolean iscreateok = noteManager.uploadMyNote(JSON.parseObject(jsonobj.getJSONObject("note").toString(), Note.class), content);
					obj = iscreateok + "";
				}else if(status==6){
					//��ѯ�ֵ��
					obj = userManager.findDirectBySubject(JSON.parseObject(arr.getJSONObject(1).toString(),Directory.class));
				}
				
				//����֤������ؿͻ���
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
