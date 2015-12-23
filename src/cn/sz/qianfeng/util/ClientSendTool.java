package cn.sz.qianfeng.util;

import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.sz.qianfeng.action.CallThread;
import cn.sz.qianfeng.vo.Users;

public class ClientSendTool<K> {

	private ExecutorService service = Executors.newCachedThreadPool();//准备线程池
	private Socket socket;
	public ClientSendTool(Socket socket){
		this.socket = socket;
	}
	
	public String sendRequest(K vo,Integer status){
		try {
			JSONArray arr = new JSONArray();
			JSONObject jo = new JSONObject();
			jo.put("status", status);
			arr.put(jo);
			arr.put(new JSONObject(vo));
			Future<String> f = service.submit(new CallThread(socket, arr.toString()));
			return f.get();
			
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String sendJsonRequest(String json,Integer status){
		try {
			JSONArray arr = new JSONArray();
			JSONObject jo = new JSONObject();
			jo.put("status", status);
			arr.put(jo);
			arr.put(new JSONObject(json));
			Future<String> f = service.submit(new CallThread(socket, arr.toString()));
			return f.get();
			
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
