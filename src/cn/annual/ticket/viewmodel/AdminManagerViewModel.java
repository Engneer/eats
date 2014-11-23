package cn.annual.ticket.viewmodel;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import org.apache.poi.hssf.record.UseSelFSRecord;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Center;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.DefaultTreeModel;
import org.zkoss.zul.Div;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.TreeModel;
import org.zkoss.zul.TreeNode;
import org.zkoss.zul.Treeitem;

import cn.annual.ticket.model.LevelInfo;
import cn.annual.ticket.model.PackageData;
import cn.annual.ticket.model.PackageDataUtil;
import cn.annual.ticket.model.Users;
import cn.annual.ticket.service.ILevelInfoService;
import cn.annual.ticket.service.IUserService;
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class) 
public class AdminManagerViewModel {

	@Wire
	private Center 	blcenter;
	@Wire
	private Div		center_div;
	/**
	 * addRegMember.zul视图页面的控件标示
	 */
	@Wire
	 Textbox		user_count;
	@Wire
	private Combobox		cblevel;
	private TreeModel<TreeNode<PackageData>> tree_model;
	private String 	bl_center_content_id;//adminManager。zul页面center元素中的当前显示页面的id值
	private Users	users	=	new Users();
	private LevelInfo	levelInfo	=new LevelInfo();
	
	
	@WireVariable
	private IUserService	userService;
	@WireVariable
	private ILevelInfoService	levelInfoService;
	@Init
	public void init(){
		setTree_model(new DefaultTreeModel<PackageData>(PackageDataUtil.getRootAdmin()));
	}
	public TreeModel<TreeNode<PackageData>> getTree_model() {
		return tree_model;
	}

	public void setTree_model(TreeModel<TreeNode<PackageData>> tree_model) {
		this.tree_model = tree_model;
	}
	
	@Command
	public void addRegMember(@BindingParam("param")String temp){
		System.out.println("执行添加办证人员错做");
		removeDirectlyChild();
		bl_center_content_id	=	temp;
		Executions.createComponents("addRegMember.zul", center_div, null);
	}
	@Command
	public void addCardType(@BindingParam("param")String temp){
		System.out.println("执行添加卡类型做");
		System.out.println(bl_center_content_id);
		removeDirectlyChild();
		bl_center_content_id	=	temp;
		Executions.createComponents("addCardType.zul", center_div, null);
	}
	@Command
	public void changePassWord(@BindingParam("param")String temp){
		System.out.println("执行更改密码");
		System.out.println(bl_center_content_id);
		removeDirectlyChild();
		bl_center_content_id	=	temp;
		Executions.createComponents("changepd.zul", center_div, null);
	}
	@Command
	public void insertRegMember(@BindingParam("name")String user_name,@BindingParam("level")String level){
		users.setIslogin(0);
		users.setName(user_name);
		users.setPassword(MD5("123456"));
		users.setLevel(Integer.valueOf(level.substring(0, 1)));
		System.out.println(users);
		if (userService.insertUsers(users)==1) {
			 Clients.showNotification("数据添加成功！！", "info", null, "before_center", 0);
			 Textbox textbox=(Textbox) center_div.getFellowIfAny("user_count");
			 Combobox combobox=(Combobox)center_div.getFellowIfAny("cblevel");
			 if(textbox !=null && combobox!=null){
				 	textbox.setValue("");
				 	combobox.setSelectedItem(null);
			 }else {
				 Clients.showNotification("页面组件错误，请维护！！", "error", null, "before_center", 0);
			}
		}else {
			 Clients.showNotification("数据添加失败！！", "error", null, "before_center", 0);
		}
		
	}
	@Command
	public void insertCardType(){
		Textbox textbox=(Textbox) center_div.getFellowIfAny("card_type_name");
		Intbox intbox=(Intbox) center_div.getFellowIfAny("card_account");
		Intbox intbox_1=(Intbox)center_div.getFellowIfAny("take_in_number");
		if(textbox!=null&intbox!=null&intbox_1!=null){
			levelInfo.setAccount(intbox.getValue());
			levelInfo.setLevelname(textbox.getValue());
			levelInfo.setNumber(intbox_1.getValue());
			System.out.println(levelInfo);
			if(levelInfoService.insertCardLevel(levelInfo)==1){
				 Clients.showNotification("数据添加成功！！", "info", null, "before_center", 0);
				 textbox.setValue(null);
				 intbox.setValue(null);
				 intbox_1.setValue(null);
			}else {
				 Clients.showNotification("数据添加失败！！", "error", null, "before_center", 0);
			}
		}else {
			 Clients.showNotification("页面组件错误，请维护！！", "error", null, "before_center", 0);
		}
	}
	public void removeDirectlyChild(){
		if(bl_center_content_id != null){
			Div div	=	(Div) center_div.getFellow(bl_center_content_id);
			center_div.removeChild(div);
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
