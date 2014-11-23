package cn.annual.ticket.service;

import org.springframework.stereotype.Component;

import cn.annual.ticket.model.LevelInfo;
@Component("levelInfoDao")
public interface ILevelInfoDao {
	int insertCardLevel(LevelInfo levelInfo);
}
