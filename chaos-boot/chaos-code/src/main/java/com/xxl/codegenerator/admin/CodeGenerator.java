package com.xxl.codegenerator.admin;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.xxl.codegenerator.admin.helper.CodeHelper;

import java.util.*;

public class CodeGenerator {


    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        final String projectPath = "C:\\Work\\projects\\javademo\\code";
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setFileOverride(true);
        gc.setAuthor("J.C");
        gc.setOpen(false);
        gc.setSwagger2(true); //实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc1 = CodeHelper.initDataSource(mpg);

        // 包配置
        final PackageConfig pc = new PackageConfig();
        pc.setModuleName(CodeHelper.scanner("模块名"));
        pc.setParent("com.cui.tech");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // code genarete
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("datapath", "com.cui.tech." + pc.getModuleName() + ".data");
                //params.put("author", "jian.Cui");
                this.setMap(params);
            }
        };

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();

        initData(projectPath, pc.getModuleName(), focList);
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setEntity("templates/code/model");
        templateConfig.setXml(null);
        templateConfig.setMapper("templates/code/dao");
        templateConfig.setService("templates/code/service");
        templateConfig.setServiceImpl("templates/code/service_impl");
        templateConfig.setController("templates/code/controller");

        mpg.setTemplate(templateConfig);

        StrategyConfig strategy = CodeHelper.initStrategyConfig(pc);
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
        System.out.println("Done");
    }


    private static void initData(final String projectPath, final String pc_model, List<FileOutConfig> focList) {
        CodeHelper.FileOutConfig("templates/code/src/main/resources/mapper/mybatis.ftl",)
        String mybatisTemplatePath = ;
        focList.add(new FileOutConfig(mybatisTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" +
                        tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        String mtdTemplatePath = "templates/code/model_to_dto.ftl";
        focList.add(new FileOutConfig(mtdTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/java/com/cui/tech/" + pc_model
                        + "/service/impl/" + tableInfo.getEntityName() + "ModelToDto" + StringPool.DOT_JAVA;
            }
        });
        String dtmTemplatePath = "templates/code/src/main/java/api/data/dto_to_model.ftl";
        focList.add(new FileOutConfig(dtmTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/java/com/cui/tech/" + pc_model
                        + "/service/impl/" + tableInfo.getEntityName() + "DtoToModel" + StringPool.DOT_JAVA;
            }
        });

        String cdTemplatePath = "templates/code/src/main/java/api/data/data.ftl";
        focList.add(new FileOutConfig(cdTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/java/com/cui/tech/" + pc_model
                        + "/data/" + tableInfo.getEntityName() + "CreateData" + StringPool.DOT_JAVA;
            }
        });

        String udTemplatePath = "templates/code/update_data.ftl";
        focList.add(new FileOutConfig(udTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/java/com/cui/tech/" + pc_model
                        + "/data/" + tableInfo.getEntityName() + "UpdateData" + StringPool.DOT_JAVA;
            }
        });

        String qTemplatePath = "templates/code/query_data.ftl";
        focList.add(new FileOutConfig(qTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/java/com/cui/tech/" + pc_model
                        + "/data/" + tableInfo.getEntityName() + "QueryData" + StringPool.DOT_JAVA;
            }
        });

        String lqTemplatePath = "templates/code/src/main/java/api/data/list_query_data.ftl";
        focList.add(new FileOutConfig(lqTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/java/com/cui/tech/" + pc_model
                        + "/data/" + tableInfo.getEntityName() + "ListQueryData" + StringPool.DOT_JAVA;
            }
        });

        String rTemplatePath = "templates/code/result.ftl";
        focList.add(new FileOutConfig(rTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/java/com/cui/tech/" + pc_model
                        + "/data/" + tableInfo.getEntityName() + "Result" + StringPool.DOT_JAVA;
            }
        });

        String pomTemplatePath = "templates/code/pom.xml.ftl";
        focList.add(new FileOutConfig(pomTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/pom.xml";
            }
        });

        String applicationTemplatePath = "templates/code/src/main/resources/application.yml.ftl";
        focList.add(new FileOutConfig(applicationTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/application.yml";
            }
        });

        String applicationbaseTemplatePath = "templates/code/src/main/resources/application-base.yml.ftl";
        focList.add(new FileOutConfig(applicationbaseTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/application-base.yml";
            }
        });

        String applicationdevTemplatePath = "templates/code/src/main/resources/application-dev.yml.ftl";
        focList.add(new FileOutConfig(applicationdevTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/application-dev.yml";
            }
        });

        String appTemplatePath = "templates/code/src/main/Application.ftl";
        focList.add(new FileOutConfig(appTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/java/com/cui/tech/" + pc_model + "/" + "Application" + StringPool.DOT_JAVA;
            }
        });

    }


}
