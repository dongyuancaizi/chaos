package com.cui.tech.boot.demo.service.task;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cui.tech.boot.demo.api.facade.IOkyaFacade;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Component;

@JobHandler(value = "demoJobHandler")
@Component
public class DemoJobHandler extends IJobHandler {
    @Reference
    private IOkyaFacade iOkyaFacade;

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        XxlJobLogger.log("XXL-JOB, Hello DemoJobHandler.");
        iOkyaFacade.ii();
        return SUCCESS;
    }

}
