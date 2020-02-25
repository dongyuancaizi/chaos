package com.cui.tech.task.manager;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cui.tech.user.api.IUserFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookTaskManager {
    @Reference
    private IUserFacade iUserFacade;

    public void executeGroundbook() {
        log.info("开始执行[书籍上架]定时任务");
        log.info("结束执行[书籍上架]定时任务");
    }

    public void execute() {
        log.info("开始执行[书籍信息全量更新]定时任务");
        iUserFacade.test();
        log.info("结束执行[书籍信息全量更新]定时任务");
    }

    public void executeUnshelfbook() {
        log.info("开始执行[书籍下架]定时任务");
        log.info("结束执行[书籍下架]定时任务");
    }

    public void executeHotspotBook() {
        log.info("开始执行[热门书籍信息更新]定时任务");
        log.info("结束执行[热门书籍信息更新]定时任务");
    }

    public void executeReloadChoiceBookCount() {
        //log.info("开始执行[清理精选书籍数量缓存]定时任务");
        //log.info("结束执行[清理精选书籍数量缓存]定时任务");
    }


}
