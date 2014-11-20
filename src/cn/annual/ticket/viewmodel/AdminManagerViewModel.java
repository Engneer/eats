package cn.annual.ticket.viewmodel;

import java.util.HashMap;

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
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Center;
import org.zkoss.zul.DefaultTreeModel;
import org.zkoss.zul.Div;
import org.zkoss.zul.Tab;
import org.zkoss.zul.TreeModel;
import org.zkoss.zul.TreeNode;
import org.zkoss.zul.Treeitem;

import cn.annual.ticket.model.PackageData;
import cn.annual.ticket.model.PackageDataUtil;

public class AdminManagerViewModel {

	@Wire
	private Center 	blcenter;
	@Wire
	private Div		center_div;
	private TreeModel<TreeNode<PackageData>> tree_model;
	private String 	bl_center_content_id;//adminManager。zul页面center元素中的当前显示页面的id值
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
	public void removeDirectlyChild(){
		if(bl_center_content_id != null){
			Div div	=	(Div) center_div.getFellow(bl_center_content_id);
			center_div.removeChild(div);
		}
	}
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
			Selectors.wireComponents(view, this, false);
			Selectors.wireEventListeners(view, this);
	}
}
