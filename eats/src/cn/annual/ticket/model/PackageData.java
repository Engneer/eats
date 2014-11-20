package cn.annual.ticket.model;

public class PackageData {
	private final String path;
	private	final	String	treeitemid;

	public PackageData(String path,	String	treeitemid) {
		this.path = path;
		this.treeitemid	=	treeitemid;
	}
	public String getPath() {
		return path;
	}
	public String getTreeitemid() {
		return treeitemid;
	}
	
}
