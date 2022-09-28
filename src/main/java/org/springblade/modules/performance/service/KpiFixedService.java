package org.springblade.modules.performance.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.tool.api.R;
import org.springblade.modules.performance.entity.KpiFixed;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/9/7 14:57
 */

public interface KpiFixedService extends IService<KpiFixed> {

	IPage<KpiFixed> selectfixedPage(IPage<Object> page, String toMonth, String idOrName);

	void updateByOne(KpiFixed param);

	R updateByList(List<KpiFixed> kpiFixedList);

	List<KpiFixed> selectToMonth(String format);

	R computeByList(List<KpiFixed> kpiFixedList);
}
