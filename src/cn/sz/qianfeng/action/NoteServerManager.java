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
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.sz.qianfeng.factory.ServiceFactory;
import cn.sz.qianfeng.vo.Note;
import cn.sz.qianfeng.vo.Users;

public class NoteServerManager {
	//服务器上所有笔记内容文件的根目录
	private static final String FILEPATH = "D:\\qianfeng\\work\\yearEndCheck\\project\\mynotefile\\";
	private static final String ENDNAME = ".txt";
	
	/**
	 * 根据笔记编号读取笔记内容,并把笔记记录和内容变为json数组
	 * @param note
	 * @return
	 */
	public String readNoteContent(Note note){
		List<String> all = new ArrayList<String>();
		try {
			note = ServiceFactory.getNoteBizInstance().findById(note.getNid());
			File file = new File(note.getPath());
			InputStream is = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String msg = "";
			while((msg=br.readLine())!=null){
				all.add(msg);
			}
			
			JSONArray arr = new JSONArray();
			arr.put(new JSONObject(note));
			arr.put(new JSONArray(all));
			
			return arr.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * 把客户端传递过来的笔记信息存入数据库,并把笔记内容存到硬盘上
	 * @param note : 笔记信息
	 * @param list ： 笔记内容
	 * @return
	 */
	public boolean uploadMyNote(Note note,List<String> list){
		Users user = ServiceFactory.getUsersBizInstance().findById(note.getUserid());
		String path = this.FILEPATH+user.getLoginname()+"\\"+note.getNotename()+this.ENDNAME;
		note.setPath(path);
		File file = new File(path);
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		//先把note对象存入数据库
		ServiceFactory.getNoteBizInstance().doCreate(note);
		//需要把文件内容存到硬盘上
		OutputStream os = null;
		BufferedWriter bw = null;
		try {
			os = new FileOutputStream(file);
			bw = new BufferedWriter(new OutputStreamWriter(os));
			for (int i = 0; i < list.size(); i++) {
				bw.write(list.get(i));
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
}
