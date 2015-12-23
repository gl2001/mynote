package cn.sz.qianfeng.test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

import cn.sz.qianfeng.action.ServerThread;

public class Server {

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(8888);
			//Set<Socket> set = new HashSet<Socket>();
			Socket socket = null;
			while(true){
				socket = server.accept();
				//set.add(socket);
				new Thread(new ServerThread(socket)).start();
				
				
				
				
				
			}
			
			
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
