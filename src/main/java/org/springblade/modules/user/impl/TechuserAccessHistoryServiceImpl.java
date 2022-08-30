package org.springblade.modules.user.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.user.entity.TechuserAccessHistory;
import org.springblade.modules.user.entity.TechuserDict;
import org.springblade.modules.user.mapper.TechuserAccessHistoryMapper;
import org.springblade.modules.user.service.TechuserAccessHistoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class TechuserAccessHistoryServiceImpl extends ServiceImpl<TechuserAccessHistoryMapper, TechuserAccessHistory> implements TechuserAccessHistoryService {
	@Override
	public void instLogin(TechuserDict user) {
		TechuserAccessHistory techuserAccessHistory = new TechuserAccessHistory();
		techuserAccessHistory.setTechuserCode(user.getTechuserCode());
		techuserAccessHistory.setDeviceCode(user.getDeviceCode());
		techuserAccessHistory.setOpType("auth/login");
		techuserAccessHistory.setOpTime(DateUtil.date());
		this.baseMapper.insert(techuserAccessHistory);
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void instLogout(TechuserDict user) {
		//筛选出当天同一设备的 登录的集合
		TechuserAccessHistory history = this.lambdaQuery()
			.eq(TechuserAccessHistory::getTechuserCode, user.getTechuserCode())
			.eq(TechuserAccessHistory::getOpType, "auth/login")
			//TODO  设备类型
//			.eq(TechuserAccessHistory::getDeviceCode,user.getDeviceCode())
			.gt(TechuserAccessHistory::getOpTime, DateUtil.beginOfDay(new Date()))
			.orderByDesc(TechuserAccessHistory::getOpTime).list().get(0);

		Date now = DateUtil.date();
		Long min = now.getTime() - history.getOpTime().getTime();
		//登出的信息插入
		TechuserAccessHistory techuserAccessHistory = new TechuserAccessHistory();
		techuserAccessHistory.setTechuserCode(user.getTechuserCode());
		techuserAccessHistory.setDeviceCode(user.getDeviceCode());
		techuserAccessHistory.setOpType("auth/logout");
		techuserAccessHistory.setOpTime(DateUtil.date());
		techuserAccessHistory.setMin(min);
		this.baseMapper.insert(techuserAccessHistory);
	}

}






