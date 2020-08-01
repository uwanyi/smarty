package com.uwanyi.lottery_draw.service.impl;

import com.uwanyi.lottery_draw.model.SystemLog;
import com.uwanyi.lottery_draw.service.SystemLogService;
import org.springframework.stereotype.Service;

/**
 * created by jasonwang
 * on 2019/7/20 0:21
 */
@Service
public class SystemLogServiceImpl implements SystemLogService{

    public int insert(SystemLog systemLog){
        System.out.println(systemLog);
        return 0;
    }

}
