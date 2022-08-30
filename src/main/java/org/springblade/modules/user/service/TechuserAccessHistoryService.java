package org.springblade.modules.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.user.entity.TechuserAccessHistory;
import org.springblade.modules.user.entity.TechuserDict;

public interface TechuserAccessHistoryService extends IService<TechuserAccessHistory> {

	void instLogin(TechuserDict user);

	void instLogout(TechuserDict user);

}






