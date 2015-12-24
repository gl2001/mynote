package cn.sz.qianfeng.action;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import cn.sz.qianfeng.factory.ServiceFactory;
import cn.sz.qianfeng.vo.Directory;
import cn.sz.qianfeng.vo.Note;
import cn.sz.qianfeng.vo.PageSplit;
import cn.sz.qianfeng.vo.Users;

import com.alibaba.fastjson.JSON;

public class UserServerManager {
	
	public Users checkLogin(Users users){
		if(users==null){
			return null;
		}
		//ȥ���ݿ���֤���û��Ƿ����
		Users user = ServiceFactory.getUsersBizInstance().islogin(users.getLoginname(), users.getPwd());
		if(user!=null){
			return user;
		}
		return null;
	}
	
	/**
	 * ����û�ע�Ṧ��
	 * @param users
	 * @return
	 */
	public boolean isreg(Users users){
		if(users==null){
			return false;
		}
		return ServiceFactory.getUsersBizInstance().doCreate(users);
	}
	
	/**
	 * ��������ݿ��޸����빦��
	 * @param users
	 * @return
	 */
	public boolean changePwd(Users users){
		if(users==null){
			return false;
		}
		return ServiceFactory.getUsersBizInstance().doUpdate(users);
	}
	
	/**
	 * �������������ݿ��ҳ��ѯ�ҵıʼǼ�¼
	 * @param split : ��ҳ������װ��һ������
	 * @return : �����ҵıʼǵļ�¼
	 */
	public String findMyNoteSplit(PageSplit split){
		List<Note> notelist = ServiceFactory.getNoteBizInstance().findall(split.getColumn(), split.getKw(), split.getCp(), split.getPs());
		int count = ServiceFactory.getNoteBizInstance().getCount(split.getColumn(), split.getKw());
		JSONObject obj = null;
		try {
			obj = new JSONObject();
			obj.put("rows", count);
			obj.put("arr", JSON.toJSONString(notelist));
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj.toString();
	}
	
	/**
	 * �����ֵ���Ŀsubject����ѯ��Ӧ���ֵ��¼
	 * @param direct : �ֵ���󣬲�ѯ��ֻʹ�øö����е�subject���ԣ�������û��
	 * @return : �����ֵ���󼯺�
	 */
	public String findDirectBySubject(Directory direct){
		List<Directory> list = ServiceFactory.getDirectoryBizInstance().findall(direct.getSubject());
		return JSON.toJSONString(list);
	}
}
