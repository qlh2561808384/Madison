package com.longshao.madison.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/*
 @Configuration  ：通过该注解来表明该类是一个Spring的配置，相当于一个xml；
 @PropertySource : 通过该注解可以制定读取的配置文件，并且通过@Value来取值；
 @Data ： 不用手动添加get set方法，但是如果项目中其他类中使用getset方法，如果报错，原因是idea中没有添加Lombok插件，添加上插件便可以解决；
 @Component : 把普通pojo实例化到spring容器中，相当于配置文件中的<bean id="" class=""/>）；
 @ConfigurationProperties ：把配置文件的信息，读取并自动封装成实体类，这样子，我们在代码里面使用就轻松方便多了，
                            这时候，我们就可以使用@ConfigurationProperties，它可以把同类的配置信息自动封装成实体类；
*/

@Component
@Configuration
@ConfigurationProperties(prefix = "userinfo", ignoreUnknownFields = false)
@PropertySource(value = "classpath:userConfig/remote.yml",encoding = "utf-8")
@Data public class UserInfo {
     //赋值没赋上
     private String names;
     private Integer age;
     private String content;

  /*  public Integer getAge() {
        return age;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }*/
}
