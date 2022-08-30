package org.springblade.modules.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.user.entity.OtherPerformance;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author 元杰
 * @Date 2022/8/26 18:51
 */

public interface OtherPerformanceService extends IService<OtherPerformance> {


    IPage<OtherPerformance> selectOtherPerformancePage(IPage<Object> page, String otherPerformanceName);
}

