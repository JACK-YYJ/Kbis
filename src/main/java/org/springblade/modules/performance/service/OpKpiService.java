package org.springblade.modules.performance.service;

import org.springblade.modules.performance.entity.OpKpi;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/9/6 9:16
 */

public interface OpKpiService extends IService<OpKpi> {


    List<OpKpi> lambdaQuerydata(String toMonth, String idOrName);

}


