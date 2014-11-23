package cn.annual.ticket.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.annual.ticket.model.Users;

@Component("userdao")
public interface IUsersDao {

	public List<Map>	getUserList(Users users);
	public int			insertUsers(Users users);
}
