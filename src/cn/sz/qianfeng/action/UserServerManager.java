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
		//去数据库验证该用户是否存在
		Users user = ServiceFactory.getUsersBizInstance().islogin(users.getLoginname(), users.getPwd());
		if(user!=null){
			return user;
		}
		return null;
	}
	
	/**
	 * 完成用户注册功能
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
	 * 完成在数据库修改密码功能
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
	 * 服务器端在数据库分页查询我的笔记记录
	 * @param split : 分页参数封装的一个对象
	 * @return : 返回我的笔记的记录
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
	 * 根据字典项目subject来查询对应的字典记录
	 * @param direct : 字典对象，查询中只使用该对象中的subject属性，其他都没用
	 * @return : 返回字典对象集合
	 */
	public String findDirectBySubject(Directory direct){
		List<Directory> list = ServiceFactory.getDirectoryBizInstance().findall(direct.getSubject());
		return JSON.toJSONString(list);
	}
}
