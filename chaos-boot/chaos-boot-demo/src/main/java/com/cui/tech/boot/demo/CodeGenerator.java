package com.cui.tech.boot.demo;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class CodeGenerator {

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        final String projectPath = "C:\\Work\\projects\\code";
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setFileOverride(true);
        gc.setAuthor("J.C");
        gc.setOpen(false);
        gc.setSwagger2(true); //实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        PackageConfig pc = CodeHelper.initPackageConfig();
        mpg.setPackageInfo(pc);
        mpg.setDataSource(CodeHelper.initDataSource(mpg));

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("p", "com.cui.tech." + pc.getModuleName());
                params.put("a", pc.getModuleName().substring(0, 1).toUpperCase() + pc.getModuleName().substring(1));
                this.setMap(params);
            }
        };

        CodeHelper.projectPath = projectPath;
        CodeHelper.pcmodel = pc.getModuleName();
        cfg.setFileOutConfigList(CodeHelper.initData());
        mpg.setCfg(cfg);

        mpg.setTemplate(CodeHelper.initTemplateConfig());
        mpg.setStrategy(CodeHelper.initStrategyConfig(pc));
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
        System.out.println("Done");
    }


}
