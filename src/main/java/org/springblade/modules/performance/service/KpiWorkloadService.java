package org.springblade.modules.performance.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.tool.api.R;
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

	R updateByOne(KpiWorkload param);

	R updateByList(List<KpiWorkload> kpiFixedList);


}

