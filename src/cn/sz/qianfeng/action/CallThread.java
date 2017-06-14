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
 * �ͻ���д��Ϣ���������ˣ�Ȼ����շ������˴���Ľ��
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
			//�ͻ���д���û�����������Ϣ����������
			OutputStream os = socket.getOutputStream();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
			bw.write(obj.toString());
			bw.newLine();
			bw.flush();
			//�ӷ������˽��ս��
			InputStream is = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			return reader.readLine();
			
		} catch (IOException e) {
			//e.printStackTrace();
		}
		return null;
	}

}
