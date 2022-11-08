package org.springblade.modules.performance.service;

import org.springblade.core.tool.api.R;
import org.springblade.modules.performance.dto.AccountingDto;
import org.springblade.modules.performance.entity.KpiAccounting;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.performance.vo.KpiAccountingVo;

import java.util.List;

/**
 * @Author 元杰
 * @Date 2022/9/14 14:36
 */

public interface KpiAccountingService extends IService<KpiAccounting> {

    R selectAccountingPage(String toMonth);

    R saves(AccountingDto param);

	R savess(AccountingDto param);
}


