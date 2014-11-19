package cn.annual.ticket.model;


import org.zkoss.zul.DefaultTreeNode;
import org.zkoss.zul.TreeNode;

import cn.annual.ticket.model.PackageData;
import cn.annual.ticket.model.DirectoryTreeNode;
import cn.annual.ticket.model.DirectoryTreeNodeCollection;

public class PackageDataUtil {
	private static DirectoryTreeNode<PackageData> rootAdmin;
	private static DirectoryTreeNode<PackageData> courtOperator;
	private static DirectoryTreeNode<PackageData> policeOperator;
	static {
		rootAdmin = new DirectoryTreeNode<PackageData>(null,
				new DirectoryTreeNodeCollection<PackageData>() {
					private static final long serialVersionUID = 9019022379404376015L;

					{
						add(new DirectoryTreeNode<PackageData>(new PackageData(
								"添加办证人员",null),
								null
								));
						add(new DirectoryTreeNode<PackageData>(new PackageData(
								"设置卡类型",null),
								null

						));
						add(new DirectoryTreeNode<PackageData>(new PackageData(
								"修改密码",null),
								null

						));
						
					}
				}, true); // dist opened
		
		courtOperator = new DirectoryTreeNode<PackageData>(null,
				new DirectoryTreeNodeCollection<PackageData>() {
					private static final long serialVersionUID = 9019022379404376015L;

					{
						add(new DirectoryTreeNode<PackageData>(new PackageData(
								"罚单处理",null),
								new DirectoryTreeNodeCollection<PackageData>() {
									private static final long serialVersionUID = 3541713473898615987L;

									{
										add(new DirectoryTreeNode<PackageData>(
												new PackageData("查看罚单","treeitemc10")));
										/*add(new DefaultTreeNode<PackageData>(
												new PackageData("判决录入", "treeitem11")));*/
										/*add(new DirectoryTreeNode<PackageData>(
												new PackageData("申请资产报废","treeitemc11")));
										add(new DirectoryTreeNode<PackageData>(
												new PackageData("资产转移处理","treeitemc12")));*/
									}
								}
								));
					}
				}, true);
		policeOperator = new DirectoryTreeNode<PackageData>(null,
				new DirectoryTreeNodeCollection<PackageData>() {
					private static final long serialVersionUID = 9019022379404376015L;

					{
						add(new DirectoryTreeNode<PackageData>(new PackageData(
								"罚单管理",null),
								new DirectoryTreeNodeCollection<PackageData>() {
									private static final long serialVersionUID = 3541713473898615987L;

									{
										add(new DirectoryTreeNode<PackageData>(
												new PackageData("违规录入","treeitem10")));
										add(new DirectoryTreeNode<PackageData>(
												new PackageData("查看罚单","treeitem11")));
										/*add(new DirectoryTreeNode<PackageData>(
												new PackageData("资产报废处理","treeitem12")));*/
									}
								}
								));
						
						add(new DirectoryTreeNode<PackageData>(new PackageData(
								"巡查部门管理",null),
								new DirectoryTreeNodeCollection<PackageData>() {
									private static final long serialVersionUID = 3541713473898615987L;

									{
										add(new DirectoryTreeNode<PackageData>(
												new PackageData("添加处理部门","treeitem30")));
										add(new DirectoryTreeNode<PackageData>(
												new PackageData("添加巡查部门","treeitem31")));
										
									}
								}

						));
						
					}
				}, true); // dist opened
	}
	public static DirectoryTreeNode<PackageData> getRootAdmin() {
		return rootAdmin;
	}
	public static DirectoryTreeNode<PackageData> getCourtOperator() {
		return courtOperator;
	}
	public static DirectoryTreeNode<PackageData> getPoliceOperator() {
		return policeOperator;
	}
}
