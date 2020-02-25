package com.cui.tech.boot.demo.service.facade;

import com.alibaba.dubbo.config.annotation.Service;
import com.cui.tech.boot.demo.api.facade.IOkyaFacade;

/**
 * @author J.C
 * @date 2020/1/10 11:58
 */
@Service
public class OkyaFacade implements IOkyaFacade {
    @Override
    public void ii() {
        System.out.println("ii");
    }
}
