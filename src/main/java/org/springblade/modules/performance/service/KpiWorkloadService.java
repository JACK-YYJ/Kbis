package org.springblade.modules.performance.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.performance.entity.KpiFixed;
import org.springblade.modules.performance.entity.KpiWorkload;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/9/7 14:57
 */

public interface KpiWorkloadService extends IService<KpiWorkload> {


    IPage<KpiWorkload> selectWorkloadPage(IPage<Object> page, String toMonth, String idOrName);

	void updateByOne(KpiWorkload param);

	void updateByList(List<KpiWorkload> kpiFixedList);
}

