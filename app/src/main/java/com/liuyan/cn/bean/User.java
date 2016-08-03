package com.liuyan.cn.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by apple on 16/8/2.下午6:38
 *
 * @Entity ，就是将我们的java普通类变为一个能够被greenDao识别的数据库类型的实体类
 * @Id 通过这个注解标记的字段Long类型的，这个字段在数据库中表示它就是主键，并且它默认就是自增的
 */
@Entity
public class User {
    @Id
    private Long id;
    private String name;
    private String age;
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1666193281)
    public User(Long id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    @Generated(hash = 586692638)
    public User() {
    }

   


}

