package cn.annual.ticket.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.annual.ticket.model.LevelInfo;
@Service("levelInfoService")
public class ILevelInfoServiceImpl implements ILevelInfoService {

	@Resource(name="levelInfoDao")
	private ILevelInfoDao iLevelInfoDao;
	@Override
	public int insertCardLevel(LevelInfo levelInfo) {
		// TODO Auto-generated method stub
		return this.iLevelInfoDao.insertCardLevel(levelInfo);
	}

}
