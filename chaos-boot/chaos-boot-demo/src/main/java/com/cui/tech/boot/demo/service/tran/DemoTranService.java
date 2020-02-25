package com.cui.tech.boot.demo.service.tran;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cui.tech.boot.demo.api.service.IVersionManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author Jian.cui
 * @date 2019/12/28 10:24
 */
@Slf4j
@Service
public class DemoTranService {
    @Reference
    private IVersionManageService iVersionManageService;

    @Transactional(rollbackFor = {Exception.class})
    public boolean tran() {
        try {
            log.info("");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("", e);
            //手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
