package org.springblade.modules.performance.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.performance.mapper.KpiWorkloadMapper;
import org.springblade.modules.performance.entity.KpiWorkload;
import org.springblade.modules.performance.service.KpiWorkloadService;
 /**
 * @Author 元杰
 * @Date 2022/9/7 14:57
 */

@Service
public class KpiWorkloadServiceImpl extends ServiceImpl<KpiWorkloadMapper, KpiWorkload> implements KpiWorkloadService{

}
