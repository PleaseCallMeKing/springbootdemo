package cn.itweknow.sbswagger;

import ch.qos.logback.core.db.ConnectionSource;
import cn.itweknow.sbswagger.model.User;
import org.omg.PortableInterceptor.Interceptor;
import org.springframework.aop.interceptor.DebugInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.management.Query;
import java.util.List;

@SpringBootApplication
public class SbSwaggerApplication {
    //数据库的配置参数
//    static String driver = "com.mysql.jc.jdbc.Driver";
//    static String url = "jdbc:mysql://127.0.0.1:3306/springboot_demo?useUnicode=true&characterEncoding=UTF-8";
//    static String userName = "root";
//    static String password = "root";

    public static void main(String[] args) {
        SpringApplication.run(SbSwaggerApplication.class, args);


//        PageQuery query = new PageQuery();
//        sql.pageQuery("user.queryNewUser", User.class,query);
//        List<User> list = query.getList();


    }

}
