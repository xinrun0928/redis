package com.zhang.springboot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author zhangxinrun(OS - > zhang)
 * @Date 2021/4/17 18:29
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Accessors(chain = true)
public class User implements Serializable {
    private String id;
    private String name;
    private Integer age;
    private Date bir;
}
