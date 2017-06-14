package cn.sz.qianfeng.action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.Callable;
/**
 * 客户端写信息到服务器端，然后接收服务器端处理的结果
 * @author gl
 *
 */
public class CallThread implements Callable<String> {

	private Socket socket;
	private String str;
	private String obj;
	public CallThread(Socket socket,String obj){
		this.socket = socket;
		this.obj = obj;
	}
	@Override
	public String call() throws Exception {
		try {
			//客户端写出用户明和密码信息到服务器端
			OutputStream os = socket.getOutputStream();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
			bw.write(obj.toString());
			bw.newLine();
			bw.flush();
			//从服务器端接收结果
			InputStream is = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			return reader.readLine();
			
		} catch (IOException e) {
			//e.printStackTrace();
		}
		return null;
	}

}
