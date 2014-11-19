package cn.annual.ticket.model;

import java.util.Date;

public class UserInfo {
	private int 	id;
	private int		number;
	private String 	name;
	private String 	sex;
	private String	identify;
	private int 	level;
	private String 	phone;
	private Date	handletime;
	private String 	imageurl;
	private int 	isupdate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIdentify() {
		return identify;
	}
	public void setIdentify(String identify) {
		this.identify = identify;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getHandletime() {
		return handletime;
	}
	public void setHandletime(Date handletime) {
		this.handletime = handletime;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public int getIsupdate() {
		return isupdate;
	}
	public void setIsupdate(int isupdate) {
		this.isupdate = isupdate;
	}

	
}
