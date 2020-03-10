package cn.itweknow.sbswagger.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;
import java.util.Date;

/*
 *
 * gen by beetlsql 2020-03-10
 */
@Table(name="springboot_demo.user")
@ApiModel("用户实体")
public class User extends BaseRowModel  implements Serializable {

    /**
     * 用户Id
     */
    @ExcelProperty(value="用户Id",index=0)
    @ApiModelProperty("用户id")
    private Integer id;

    /**
     * 用户名
     */
    @ExcelProperty(value="用户姓名",index=1)
    @ApiModelProperty("用户姓名")
    private String name;

    /**
     * 用户年龄
     */
    @ExcelProperty(value="用户年龄",index=2)
    @ApiModelProperty("用户年龄")
    private Integer age ;

    /**
     * 创建时间
     */
    @ExcelProperty(value="创建时间",index=3)
    @ApiModelProperty("创建时间")
    private Date createDate ;

    public User() {
    }

    public Integer getId(){
        return  id;
    }
    public void setId(Integer id ){
        this.id = id;
    }

    public Integer getAge(){
        return  age;
    }
    public void setAge(Integer age ){
        this.age = age;
    }

    public String getName(){
        return  name;
    }
    public void setName(String name ){
        this.name = name;
    }

    public Date getCreateDate(){
        return  createDate;
    }
    public void setCreateDate(Date createDate ){
        this.createDate = createDate;
    }
}
