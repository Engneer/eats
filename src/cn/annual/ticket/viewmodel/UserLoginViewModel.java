package cn.annual.ticket.viewmodel;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;

import cn.annual.ticket.model.RandomStringGenerator;
import cn.annual.ticket.model.Users;
import cn.annual.ticket.service.IUserService;
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class) 
public class UserLoginViewModel {
	@WireVariable
	private IUserService	userService;
	private Users 	sysUsers	=	new Users();
	private RandomStringGenerator	rs	=	new RandomStringGenerator(4);
	private List<Map>	userList;
	private String	randString=rs.getRandomString();

	public Users getSysUsers() {
		return sysUsers;
	}

	public void setSysUsers(Users sysUsers) {
		this.sysUsers = sysUsers;
	}
	
	 public String getRandString() {
		return randString;
	}

	@NotifyChange("randString")
	@Command
	public void	regenerate(){
		this.randString = rs.getRandomString();
	}
	@Command
	 public void login(@BindingParam("acount")String name,@BindingParam("pd")String password,@BindingParam("rand")String randtemp){
		 sysUsers.setName(name);
		 sysUsers.setPassword(MD5(password));
		 System.out.println(sysUsers);
		 userList	=	userService.getUserList(sysUsers);
		 if(randString.equals(randtemp)){
			 if(userList.size()==1){
				 if((int)userList.get(0).get("level") ==1){
					if((boolean) userList.get(0).get("islogin")){
						 Clients.showNotification("已经登录未注销，请退出后重新登录！", "error", null, "before_center", 0);
					}else{
						 Executions.sendRedirect("adminManager.zul");
					}
				 }else{
					 Executions.sendRedirect("commonManager.zul");
				 }
			 }else{
				 Clients.showNotification("用户名或者密码错误！", "error", null, "before_center", 0);
			 }
		 }else {
			 Clients.showNotification("验证码错误！", "error", null, "before_center", 0);
		}
	 }
	public String MD5(String key){
		String mdString=null;
		try {
			MessageDigest mDigest=MessageDigest.getInstance("MD5");
			mDigest.update(key.getBytes());
			mdString= new BigInteger(1,mDigest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return mdString;
	}
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
			Selectors.wireComponents(view, this, false);
			Selectors.wireEventListeners(view, this);
	}
}
