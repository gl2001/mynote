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
	 * 初始化分页参数
	 * @param users
	 * @return
	 */
	public PageSplit getSplitObj(Users users){
		int cp = 1;//当前页码
		int ps = 5;//每页显示行数
		String column = "userid";//这里因为是查询我的笔记，所以需要指定列明为userid
		String kw = users.getUserid()+"";//这里为当前用户的id
		int count = 0 ;//总行数
		int allpage = 0 ;//总页数
		boolean ishaveData = true;//是否有数据
		
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
	 * 打印从服务器返回的笔记记录信息
	 * @param jsonStr : 格式必须为：{"rows":我的笔记的总行数,"arr":"我的笔记记录的集合"}
	 * @param splitobj : 分页参数
	 * @param user : 用户对象,显示的是该用户的笔记记录
	 * @return
	 */
	public PageSplit printNoteInfo(String jsonStr,PageSplit splitobj,Users user){
		try {
			JSONObject jsonobj = new JSONObject(jsonStr);
			splitobj.setCount(jsonobj.getInt("rows"));
			String arr = jsonobj.getString("arr");
			List<Note> notelist = JSON.parseArray(arr, Note.class);
			System.out.println("编号\t用户\t   笔记名字\t\t\t     创建时间\t\t是否共享\t\t\t简介");
			if(notelist==null||notelist.size()<=0){
				System.out.println("还没有添加过笔记");
				splitobj.setIshaveData(false);
			}else{
				for (int i = 0; i < notelist.size(); i++) {
					Note note = notelist.get(i);
					System.out.print(note.getNid());
					System.out.print("\t");
					System.out.print(user.getRealname());
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
					System.out.print(note.getIsShare().equals("0")?"是":"否");
					System.out.print("\t\t\t");
					if(note.getSummary().length()<=15){
						System.out.print(note.getSummary());
					}else{
						System.out.print(note.getSummary().substring(0, 15));
						System.out.print("...");
					}
					System.out.println();
				}
				System.out.println("********************************************************************************************");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return splitobj;
	}
	
	/**
	 * 分页参数接收
	 * @param splitobj
	 * @return
	 */
	public PageSplit scanfNextPage(PageSplit splitobj,Socket socket){
		System.out.println("请选择页码(1.首页                         2.上一页                        3.下一页                          4.尾页                         5.查看笔记内容                      0.回主菜单)：");
		Scanner input = new Scanner(System.in);
		boolean flag = true;
		while(flag){
			int nextpage = input.nextInt();
			switch (nextpage) {
			case 1:
				if(splitobj.getCp()==1){
					System.out.println("已经是第一页了,请重新选择:");
				}else{
					splitobj.setCp(1);
					flag = false;
				}
				break;
			case 2:
				if(splitobj.getCp()<=1){
					System.out.println("已经是第一页了,请重新选择:");
				}else{
					splitobj.setCp(splitobj.getCp()-1);
					flag = false;
				}
				break;
			case 3:
				if(splitobj.getCp()>=splitobj.getAllpage()){
					System.out.println("已经是最后一页了，请重新选择:");
				}else{
					splitobj.setCp(splitobj.getCp()+1);
					flag = false;
				}
				break;
			case 4:
				if(splitobj.getCp()>=splitobj.getAllpage()){
					System.out.println("已经是最后一页了，请重新选择:");
				}else{
					splitobj.setCp(splitobj.getAllpage());
					flag = false;
				}
				break;
			case 5:
				//查看笔记
				String result = this.readNoteContent(socket);
				//执行笔记的操作
				int mydo = this.scanfNoteLoad();
				if(mydo==4){
					splitobj.setIshaveData(false);
				}else if(mydo==2){
					//完成共享笔记
					
				}else if(mydo==1){
					//完成下载
					boolean issuc = this.downloadNote(result);
					System.out.println(issuc?"下载完成":"下载失败");
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
	 * 根据笔记编号到服务器读取笔记内容，并打印
	 * @param socket
	 */
	public String readNoteContent(Socket socket){
		System.out.println("请输入要阅读的笔记的编号：");
		Scanner input = new Scanner(System.in);
		int nid = input.nextInt();
		Note note = new Note();
		note.setNid(nid);
		String result = new ClientSendTool<Note>(socket).sendRequest(note, 4);//4表示我要查看指定编号的某篇笔记的内容
		try {
			JSONArray arr = new JSONArray(result);
			List<String> list = JSON.parseArray(arr.getJSONArray(1).toString(), String.class);
			System.out.println("******************************************************笔记开始*************************************************");
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
			System.out.println("******************************************************笔记结束*************************************************");
			return result;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * 读取笔记内容后,需要的操作
	 * @return
	 */
	public int scanfNoteLoad(){
		System.out.println("请选择您要的操作( 1.下载                      2.共享本笔记                        3.返回我的笔记列表                       4.返回主菜单)：");
		Scanner input = new Scanner(System.in);
		return input.nextInt();
	}
	
	/**
	 * 文件下载
	 * @param content : 文件内容
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
		System.out.println("请输入您要存放的路径(格式为盘符:\\目录\\文件名.txt,例如：d:\\project\\java基础.txt):");
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
	 * 读取要上传的文件内容
	 */
	public List<String> uploadNote(){
		Scanner input = new Scanner(System.in);
		System.out.println("请输入您要上传的文件路径(格式为盘符:\\目录\\文件名.txt,例如：d:\\project\\java基础.txt):");
		String path = input.next();
		File file = new File(path);
		if(!file.exists()){
			System.out.println("源文件不存在,回到主菜单");
		}else{
			//源文件存在
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
	
	
	public void uploadMyNote(Socket socket,List<String> list,Users user){
		//上传文件的内容,变为一个json数组字符串
		String content = JSON.toJSONString(list);
		
		Scanner input = new Scanner(System.in);
		try {
			System.out.println("请输入笔记名字:");
			String notename = input.next();
			//notename = URLEncoder.encode(notename, "utf-8");
			String createtime = null;
			while(true){
				System.out.println("请输入该笔记记录时间:");
				createtime = input.next();
				if(new DateTool().checkDate(createtime)){
					break;
				}else{
					System.out.println("日期格式错误,请重新输入!");
				}
			}
			System.out.println("请输入笔记简介:");
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
			
			
			String result = new ClientSendTool<Object>(socket).sendJsonRequest(jo.toString(), 5);//5表示上传笔记
			System.out.println(result.equals("true")?"上传成功":"上传失败");
			
		}  catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	
}
