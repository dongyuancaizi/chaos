package com.xxl.codegenerator.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.xxl.codegenerator.admin.core.CodeGeneratorTool;
import com.xxl.codegenerator.admin.core.model.ClassInfo;
import com.xxl.codegenerator.admin.model.ReturnT;
import com.xxl.codegenerator.admin.util.FreemarkerTool;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * sso server (for web)
 *
 * @author xuxueli 2017-08-01 21:39:47
 */
@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Resource
    private FreemarkerTool freemarkerTool;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/codeGenerate")
    @ResponseBody
    public ReturnT<Map<String, String>> codeGenerate(String tableSql) {

        try {

            if (StringUtils.isBlank(tableSql)) {
                return new ReturnT<Map<String, String>>(ReturnT.FAIL_CODE, "表结构信息不可为空");
            }

            // parse table
            ClassInfo classInfo = CodeGeneratorTool.processTableIntoClassInfo(tableSql);

            // code genarete
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("classInfo", classInfo);
            params.put("projcet", "okya");
            params.put("author", "J.C");
            params.put("superControllerClass", "BaseController");
            params.put("superServiceClassPackage", "com.cui.tech.chaos.web.BaseController");

            SimpleDateFormat sdf = new SimpleDateFormat();
            params.put("date", sdf.format(new Date()));

            Map<String, Object> table = new HashMap<String, Object>();
            table.put("comment", classInfo.getClassComment());
            table.put("entityPath", classInfo.getTableName());
            table.put("controllerName", classInfo.getClassName() + "Controller");
            table.put("serviceName", "I" + classInfo.getClassName() + "Service");
            table.put("serviceImplName", classInfo.getClassName() + "ServiceImpl");
            table.put("mapperName", classInfo.getClassName() + "Mapper");
            table.put("name", classInfo.getTableName());
            List<Map> map = classInfo.getFieldList().stream().map(item -> {
                Map m = new HashMap();
                m.put("name", item.getColumnName());
                m.put("comment", item.getFieldComment());
                m.put("propertyType", item.getFieldClass());
                m.put("propertyName", item.getFieldName());
                return m;
            }).collect(Collectors.toList());

            table.put("fields", map);

            params.put("table", table);
            params.put("entity", classInfo.getClassName());


            Map<String, String> packageStr = new HashMap<String, String>();
            packageStr.put("ModuleName", "okya");
            packageStr.put("Controller", "com.cui.tech.okya.controller");
            packageStr.put("Service", "com.cui.tech.okya.service");
            packageStr.put("Entity", "com.cui.tech.okya.entity");
            packageStr.put("Mapper", "com.cui.tech.okya.mapper");

            packageStr.put("ServiceImpl", "com.cui.tech.okya.service.impl");

            params.put("package", packageStr);


            // result
            Map<String, String> result = new HashMap<String, String>();

            result.put("controller_code", freemarkerTool.processString("xxl-code-generator/controller.ftl", params));
            result.put("service_code", freemarkerTool.processString("xxl-code-generator/IService.ftl", params));
            result.put("service_impl_code", freemarkerTool.processString("xxl-code-generator/service_impl.ftl", params));

            result.put("dao_code", freemarkerTool.processString("xxl-code-generator/mapper.ftl", params));
            result.put("mybatis_code", freemarkerTool.processString("xxl-code-generator/mybatis.ftl", params));

            result.put("model_code", freemarkerTool.processString("xxl-code-generator/model.ftl", params));

            // 计算,生成代码行数
            int lineNum = 0;
            for (Map.Entry<String, String> item : result.entrySet()) {
                if (item.getValue() != null) {
                    lineNum += StringUtils.countMatches(item.getValue(), "\n");
                }
            }
            logger.info("生成代码行数：{}", lineNum);

            return new ReturnT<Map<String, String>>(result);
        } catch (IOException | TemplateException e) {
            logger.error(e.getMessage(), e);
            return new ReturnT<Map<String, String>>(ReturnT.FAIL_CODE, "表结构解析失败");
        }

    }

    @RequestMapping("/codeGenerateAll")
    @ResponseBody
    public ReturnT<Map<String, String>> codeGenerateAll(String tableName, String moduleName) {

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        final String projectPath = "C:\\Work\\projects\\code";
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setFileOverride(true);
        gc.setAuthor("Jian.cui");
        gc.setOpen(false);
        gc.setSwagger2(true); //实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://119.23.57.4:3306/okya?useUnicode=true&useSSL=false&characterEncoding=utf8");
        //dsc.setUrl("jdbc:mysql://47.111.6.183:3306/hey?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("okya");
        dsc.setPassword("SiKe!2#4%6");
        //dsc.setUsername("root");
        //dsc.setPassword("panpan");
        mpg.setDataSource(dsc);

        // 包配置
        final PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName);
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


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass("com.cui.tech.chaos.model.BaseModel");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类

        strategy.setSuperControllerClass("com.cui.tech.chaos.web.BaseController");
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("id");
        strategy.setInclude(tableName.split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
        System.out.println("Done");
        return new ReturnT<Map<String, String>>(null);
    }

    private void initData(final String projectPath, final String pc_model, List<FileOutConfig> focList) {
        // 自定义配置会被优先输出
        // 如果模板引擎是 freemarker
        String mybatisTemplatePath = "templates/code/src/main/resources/mapper/mybatis.ftl";
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
