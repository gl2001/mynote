package cn.sz.qianfeng.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Scanner;

import cn.sz.qianfeng.factory.ServiceFactory;
import cn.sz.qianfeng.vo.Directory;
import cn.sz.qianfeng.vo.Users;

public class UserClientManager {

	/**
	 * ����̨�����û��������룬Ϊ��¼��֤׼��
	 * @return
	 */
	public Users enterLoginInfo(){
		Scanner input = new Scanner(System.in);
		System.out.println("��ѡ����ǡ���¼��");
		System.out.println("�������û�����");
		String loginname = input.next();
		System.out.println("���������룺");
		String pwd = input.next();

		Users users = new Users();
		users.setLoginname(loginname);
		users.setPwd(pwd);

		return users;
	}
	
	/**
	 * ���������û���Ϣ��Ϊ�û�ע��׼��
	 * @return
	 */
	public Users enterForReg(){
		Users users = null;
		try {
			Scanner input = new Scanner(System.in);
			System.out.println("�������û�����");
			String loginname = input.next();
			System.out.println("���������룺");
			String pwd = input.next();
			System.out.println("��������ʵ����:");
			String realname = input.next();
			realname = URLEncoder.encode(realname, "utf-8");
			System.out.println("�����������ǳƣ�");
			String nickname = input.next();
			nickname = URLEncoder.encode(nickname, "utf-8");
			System.out.println("�����������ֻ�����:");
			String mobile = input.next();
			System.out.println("����������email��");
			String email = input.next();
			System.out.println("���������ı�ҵѧУ��");
			String university = input.next();
			university = URLEncoder.encode(university, "utf-8");
			System.out.println("����������רҵ��");
			String subject = input.next();
			subject = URLEncoder.encode(subject, "utf-8");
			System.out.println("���������ı�ҵʱ��(��ʽΪ2015-09):");
			String graduateTime = input.next();
			
			List<Directory> list = ServiceFactory.getDirectoryBizInstance().findall("area");
			System.out.println("��ѡ�����İ༶���ڵ�����");
			for (int i = 0; i < list.size(); i++) {
				Directory direct = list.get(i);
				String d = URLDecoder.decode(direct.getOption(), "utf-8");
				System.out.println("           "+i+"."+d);
			}
			Directory directory = list.get(input.nextInt());
			
			List<Directory> sublist = ServiceFactory.getDirectoryBizInstance().findall("subject");
			System.out.println("��ѡ�����Ŀγ̣�");
			for (int i = 0; i < sublist.size(); i++) {
				Directory direct = sublist.get(i);
				System.out.println("           "+i+"."+direct.getOption());
			}
			Directory subDirect = list.get(input.nextInt());
			
			System.out.println("������༶��ţ�");
			String cls = input.next();
			cls = directory.getKw()+"-"+subDirect.getKw()+"-"+cls;

			users = new Users();
			users.setLoginname(loginname);
			users.setPwd(pwd);
			users.setRealname(realname);
			users.setNickname(nickname);
			users.setMobile(mobile);
			users.setEmail(email);
			users.setUniversity(university);
			users.setSubject(subject);
			users.setGraduateTime(graduateTime);
			users.setCls(cls);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	/**
	 * ����̨����ԭ���벢��֤�����һ�£��ٽ��������벢ȷ��
	 * @param user
	 * @return ���صĶ����д����userid��pwd,�������null,˵���û������޸�����
	 */
	public Users enterForChangePwd(Users user){
		Scanner input = new Scanner(System.in);
		Users users = null;
		System.out.println("��ѡ����ǡ��޸����롿");
		String newpwd = null;
		boolean flag = true;
		while(flag){
			System.out.println("��������ԭ�������룺");
			String oldpwd = input.next();
			if(oldpwd!=null&&oldpwd.equals(user.getPwd())){
				System.out.println("�����������룺");
				newpwd = input.next();
				if(newpwd!=null&&!newpwd.equals("")){
					System.out.println("��ȷ������������:");
					String repwd = input.next();
					if(repwd!=null&&repwd.equals(newpwd)){
						users = new Users();
						users.setPwd(newpwd);
						users.setUserid(user.getUserid());
						break;
					}else{
						System.out.println("�������벻һ�»����벻��Ϊ�գ�����������");
					}
				}else{
					System.out.println("���벻��Ϊ�գ�����������");
				}
			}else{
				System.out.println("�������ԭ���벻��ȷ����ѡ��(1.��������      2.�˳�):");
				flag = input.next().equals("1")?true:false;
			}
		}
		return users;
	}
	
	/**
	 * ��ʾ�û��ĸ�����Ϣ
	 * @param user
	 */
	public void showPersonalInfo(Users user){
		System.out.println("�ҵ���Ϣ��");
		System.out.println("��¼����"+user.getLoginname());
		System.out.println("��ʵ������"+user.getRealname());
		System.out.println("�ǳƣ�"+user.getNickname());
		System.out.println("�ֻ���"+user.getMobile());
		System.out.println("email:"+user.getEmail());
		System.out.println("��ҵѧУ��"+user.getUniversity());
		System.out.println("רҵ��"+user.getSubject());
		System.out.println("��ҵʱ�䣺"+user.getGraduateTime());
		System.out.println("�༶��"+user.getCls());
	}
	
	
	
}
