package org.springblade.modules.performance.service.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.performance.entity.OpKpi;
import org.springblade.modules.performance.mapper.OpKpiMapper;
import org.springblade.modules.performance.service.OpKpiService;

/**
 * @Author 元杰
 * @Date 2022/9/6 9:16
 */

@Service
public class OpKpiServiceImpl extends ServiceImpl<OpKpiMapper, OpKpi> implements OpKpiService {

    @Override
    public List<OpKpi> lambdaQuerydata(String toMonth, String idOrName) {
        List<OpKpi> opKpiList = baseMapper.lambdaQuerydata(toMonth, idOrName);
        return opKpiList;
    }
}


