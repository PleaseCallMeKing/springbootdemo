package cn.itweknow.sbswagger.controller;

import cn.itweknow.sbswagger.model.User;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.beetl.sql.core.*;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.ext.DebugInterceptor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ganchaoyang
 * @date 2019/3/1013:55
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户相关接口", description = "提供用户相关的Rest API")
public class UserController {

    //数据库的配置参数
    static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://127.0.0.1:3306/springboot_demo?useUnicode=true&characterEncoding=UTF-8";
    static String userName = "root";
    static String password = "root";

    private static SQLManager getSqlManager(){
        ConnectionSource source = ConnectionSourceHelper.getSimple(driver, url, userName, password);
        DBStyle mysql = new MySqlStyle();
// sql语句放在classpagth的/sql 目录下
        SQLLoader loader = new ClasspathLoader("/sql");
// 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion，下划线风格的，
        UnderlinedNameConversion nc = new  UnderlinedNameConversion();
// 最后，创建一个SQLManager,DebugInterceptor 不是必须的，但可以通过它查看sql执行情况
        SQLManager sqlManager = new SQLManager(mysql,loader,source,nc,new Interceptor[]{new DebugInterceptor()});
        return sqlManager;
    }

    @ApiOperation("新增用户接口")
    @PostMapping("/add")
    public int addUser(@RequestBody User user) {
        //使用内置的生成的sql 新增用户，如果需要获取主键，可以传入KeyHolder
        return UserController.getSqlManager().insert(user);
    }

    @ApiOperation("通过id查找用户接口")
    @GetMapping("/find/{id}")
    public User findById(@PathVariable("id") int id) {
        return UserController.getSqlManager().unique(User.class,id);
    }

    @ApiOperation("更新用户信息接口")
    @PutMapping("/update")
    public int update(@RequestBody User user) {
        return UserController.getSqlManager().updateTemplateById(user);
    }

    @ApiOperation("分页查找用户接口")
    @GetMapping("/pageQuery")
    public List<User> findById() {
        PageQuery query = new PageQuery();
        UserController.getSqlManager().pageQuery("user.queryNewUser", User.class,query);
        List<User> list = query.getList();
        return list;
    }

    @ApiOperation("删除用户接口")
    @DeleteMapping("/delete/{id}")
    public int delete(@PathVariable("id") int id) {
        User delUser = new User();
        delUser.setId(id);
     return  UserController.getSqlManager().deleteObject(delUser);
    }


    @ApiOperation("excel导出用户")
    @RequestMapping("/expor")
    public String exporExcel(HttpServletResponse response) throws IOException {
        ServletOutputStream out = response.getOutputStream();
        String fileName = new String((new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
                .getBytes(), "UTF-8");
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename="+fileName+".xlsx");

        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
        Sheet sheet = new Sheet(1, 0);
        List<List<String>> head = new ArrayList<List<String>>();
        List<String> headCoulumn1 = new ArrayList<String>();
        List<String> headCoulumn2 = new ArrayList<String>();
        headCoulumn1.add("第一列");
        headCoulumn2.add("第二列");
        head.add(headCoulumn1);
        head.add(headCoulumn2);
        Table table = new Table(1);
        table.setHead(head);
        sheet.setSheetName("下载数据");

        //获取数据
        PageQuery query = new PageQuery();
        UserController.getSqlManager().pageQuery("user.queryNewUser", User.class,query);
        List<User> list = query.getList();

        writer.write(list, sheet, table);
        writer.finish();
        out.flush();


        return "index";
    }

}
