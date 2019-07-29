package cn.charge.ssmv.velocity;

import cn.charge.ssmv.util.EasyuiColumn;
import cn.charge.ssmv.util.FieldVo;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
/** 52行 写要生成的domain层的名字，注意要有domain层，还有生成mapper时的findall记得改成分页(其他mapper拷贝就可以)
 * @Author: wenbing
 * @Date: 2019/1/18 23:50
 * @Version 1.0
 */
public class VelocityMain {
    //模板文件数组定义:顺序是你自己的
    static String[] templateName = {
//            "domain.js", "domain.jsp",
            "DomainController.java"
            , "DomainQuery.java", "DomainServiceImpl.java", "DomainService.java"
    };

    //    static String[] templateName = {
//            "domain.js", "domain.jsp"
//    };
    //项目路径：
//    static final String jsFilePath = "D:/InerlliJ_IDEA/Workspace/FM/file_management/basic-velocity/src/main/webapp/static/js/model/";
//    static final String jspFilePath = "D:/InerlliJ_IDEA/Workspace/FM/file_management/basic-velocity/src/main/webapp/WEB-INF/views/";
    static final String controllerFilePath = "D:/InerlliJ_IDEA/Workspace/FM/file_management/fm-web/src/main/java/cn/charge/ssmv/web/controller/";
    static final String queryFilePath = "D:/InerlliJ_IDEA/Workspace/FM/file_management/fm-common/src/main/java/cn/charge/ssmv/query/";
    static final String serviceImplFilePath = "D:/InerlliJ_IDEA/Workspace/FM/file_management/fm-service/src/main/java/cn/charge/ssmv/service/impl/";
    static final String serviceFilePath = "D:/InerlliJ_IDEA/Workspace/FM/file_management/fm-service/src/main/java/cn/charge/ssmv/service/";

    //生成文件的路径数据定义：这个要和templateName对应起来
    static String[] outFileRootPath = {
//            jsFilePath, jspFilePath,
            controllerFilePath, queryFilePath
            , serviceImplFilePath,serviceFilePath
    };
//
//    static String[] outFileRootPath = {
//            jsFilePath, jspFilePath
//    };

    //可能有多个domain需要生成
    static String[] domain = {""};

    /**
     * 1：定义模板
     * 2：使用Velocity生成模板：
     * 2.1：初始化Velocity：设置加载方式：classpath下加载
     * 2.2：设置Velocity的上下文
     * 2.3:从classpath下读取模板，输出到文件
     *
     * @param args
     */
    public static void main(String[] args) throws ClassNotFoundException {
        for (String domainMame : domain) {
            // domainName = Employee

            Properties p = new Properties();
            // 2.1：初始化Velocity：设置加载方式：classpath下加载
            // 使用classpath加载：org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader
            p.setProperty(Velocity.RESOURCE_LOADER, "class");
            p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            Velocity.init(p);

            //2.2：设置Velocity的上下文
            VelocityContext context = new VelocityContext();
            //  domainName = Employee
            // domainLower= employee
            String domainLower = domainMame.substring(0, 1).toLowerCase() + domainMame.substring(1);
            //使用Department代替模板文件中：Domain
            context.put("Domain", domainMame);  //Department
            context.put("domain", domainLower); //department
            //
            context.put("fieldList",scanDomain(domainMame));

            //遍历模板，每一个模板都生成一个文件
            for (int i=0;i<templateName.length;i++) {
                //2.3:读取模板，输出到文件
                //从classpath下读取模板
                String tempName = templateName[i];

                // i=0==>tempName=domain.js
                String templateFile = "template/" + tempName;
                // templateFile=template\domain.js
                //根据模板的索引读取对应生成文件的路径：
                String outFilePath = outFileRootPath[i];
                // outFilePath=F:\java0830\itsource-parent\crm-web\src\main\webapp\static\js\

                //"F:/java0830/itsource-parent/crm-web/src/main/webapp/WEB-INF/views/";
                boolean views= outFilePath.contains("views")||outFilePath.contains("js");
                if(views){
                    // F:/java0830/itsource-parent/crm-web/src/main/webapp/WEB-INF/views/employee/
                    outFilePath=outFilePath+"/"+domainLower+"/";
                }
                // outFile=outFilePath+domain.js==>outFilePath+employee.js
                String outFile = outFilePath + tempName;
                outFile=outFile.replaceAll("Domain",domainMame).replaceAll("domain",domainLower);


                try {
                    File file = new File(outFile);
                    File parentFile = file.getParentFile();
                    //文件不存在，就创建
                    if (!parentFile.exists()) {
                        parentFile.mkdirs();
                    }
                    //文件输出
                    FileWriter fileWriter = new FileWriter(file);
                    Template template = Velocity.getTemplate(templateFile, "utf-8");
                    template.merge(context, fileWriter);
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
       /* List<FieldVo> voList = scanDomain("Employee");
        for (FieldVo fieldVo : voList) {
            System.out.println(fieldVo);
        }*/
    }

    private static List<FieldVo> scanDomain(String domainMame) throws ClassNotFoundException {
        List<FieldVo> voList=new ArrayList<>();
        // domainMame=Employee
        // cn.itsource.crm.domain.Employee
        String clazzPath="cn.charge.ssmv.domain."+domainMame;
        Class<?> c = Class.forName(clazzPath);
        System.out.println(c);

        //获取字段
        Field[] declaredFields = c.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            //判断这个字段上是否有EasyuiColumn
            if(declaredField.isAnnotationPresent(EasyuiColumn.class)){
                EasyuiColumn easyuiColumn = declaredField.getAnnotation(EasyuiColumn.class);

                //获取到注解的title的值
                String title = easyuiColumn.title();
                String name = declaredField.getName();
                FieldVo fieldVo=new FieldVo();
                fieldVo.setField(name);
                fieldVo.setTitle(title);
                voList.add(fieldVo);

            }
        }
        for (FieldVo fieldVo : voList) {
            System.out.println(fieldVo);
        }

        return voList;
    }
}