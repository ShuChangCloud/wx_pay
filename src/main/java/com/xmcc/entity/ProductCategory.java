package com.xmcc.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @company xmcc
 * @create create by qcc on 2019-06-20 16:51
 */

@Entity
@Data
@DynamicUpdate
public class ProductCategory {

    @Id
    private Integer categoryId;

    //类目名字
    private String categoryName;

    //类目编号
    private Integer categoryType;

    //创建时间
    private Date createTime;

    //修改时间
    private Date updateTime;


}
