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
								"��Ӱ�֤��Ա",null),
								null
								));
						add(new DirectoryTreeNode<PackageData>(new PackageData(
								"���ÿ�����",null),
								null

						));
						add(new DirectoryTreeNode<PackageData>(new PackageData(
								"�޸�����",null),
								null

						));
						
					}
				}, true); // dist opened
		
		courtOperator = new DirectoryTreeNode<PackageData>(null,
				new DirectoryTreeNodeCollection<PackageData>() {
					private static final long serialVersionUID = 9019022379404376015L;

					{
						add(new DirectoryTreeNode<PackageData>(new PackageData(
								"��������",null),
								new DirectoryTreeNodeCollection<PackageData>() {
									private static final long serialVersionUID = 3541713473898615987L;

									{
										add(new DirectoryTreeNode<PackageData>(
												new PackageData("�鿴����","treeitemc10")));
										/*add(new DefaultTreeNode<PackageData>(
												new PackageData("�о�¼��", "treeitem11")));*/
										/*add(new DirectoryTreeNode<PackageData>(
												new PackageData("�����ʲ�����","treeitemc11")));
										add(new DirectoryTreeNode<PackageData>(
												new PackageData("�ʲ�ת�ƴ���","treeitemc12")));*/
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
								"��������",null),
								new DirectoryTreeNodeCollection<PackageData>() {
									private static final long serialVersionUID = 3541713473898615987L;

									{
										add(new DirectoryTreeNode<PackageData>(
												new PackageData("Υ��¼��","treeitem10")));
										add(new DirectoryTreeNode<PackageData>(
												new PackageData("�鿴����","treeitem11")));
										/*add(new DirectoryTreeNode<PackageData>(
												new PackageData("�ʲ����ϴ���","treeitem12")));*/
									}
								}
								));
						
						add(new DirectoryTreeNode<PackageData>(new PackageData(
								"Ѳ�鲿�Ź���",null),
								new DirectoryTreeNodeCollection<PackageData>() {
									private static final long serialVersionUID = 3541713473898615987L;

									{
										add(new DirectoryTreeNode<PackageData>(
												new PackageData("��Ӵ�����","treeitem30")));
										add(new DirectoryTreeNode<PackageData>(
												new PackageData("���Ѳ�鲿��","treeitem31")));
										
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
