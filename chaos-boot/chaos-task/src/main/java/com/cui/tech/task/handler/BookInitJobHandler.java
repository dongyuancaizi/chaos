package com.cui.tech.task.handler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cui.tech.task.manager.BookTaskManager;
import com.cui.tech.user.api.IUserFacade;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@JobHandler(value = "bookInitJobHandler")
@Component
public class BookInitJobHandler extends IJobHandler {
    @Autowired
    private BookTaskManager bookTaskManager;

    @Reference
    private IUserFacade iUserFacade;
    @Override
    public ReturnT<String> execute(String param) throws Exception {
        XxlJobLogger.log("XXL-JOB, Hello BookInitJobHandler.");
        bookTaskManager.execute();
        return ReturnT.SUCCESS;
    }

}
