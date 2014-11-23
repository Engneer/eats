package cn.annual.ticket.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import cn.annual.ticket.model.Users;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource(name="userdao")
	private IUsersDao iUsersDao;
	@Override
	public List<Map> getUserList(Users users) {
		// TODO Auto-generated method stub
		return this.iUsersDao.getUserList(users);
	}
	@Override
	public int insertUsers(Users users) {
		// TODO Auto-generated method stub
		return this.iUsersDao.insertUsers(users);
	}

}
