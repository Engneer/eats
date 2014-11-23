package cn.annual.ticket.viewmodel;

import java.util.Dictionary;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Center;
import org.zkoss.zul.Div;

public class CommonManagerViewModel {
	@Wire
	private Center 	blcenter;
	private boolean camera_show=true;
	private String 	bl_center_content_id;//adminManager。zul页面center元素中的当前显示页面的id值
	@Wire
	private Div		center_div;
	@Command
	public void regAnnualTicket(@BindingParam("param")String temp){
		System.out.println(temp);
		removeDirectlyChild();
		bl_center_content_id	=	temp;
		Executions.createComponents("regAnnualTicket.zul", center_div, null);
//		Div div=(Div) center_div.getFellow(temp);
//		div.setVisible(camera_show);
		//Executions.sendRedirect("regAnnualTicket.zul");
		
	}
	@Command
	public void checkAnnualTicket(@BindingParam("param")String temp){
		removeDirectlyChild();
		bl_center_content_id	=	temp;
		Executions.createComponents("checkAnnualTicket.zul", center_div, null);
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
