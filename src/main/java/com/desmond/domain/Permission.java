package com.desmond.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Permission implements Serializable {
    private static final long serialVersionUID = -54979041104113736L;

    private Long id;
    /**
    * 权限名
    */
    private String name;

    /**
    * 菜单状态（0正常 1停用）
    */
    private String status;
    /**
    * 权限代码
    */
    private String code;
    
    private Date createTime;
    
    private Date updateTime;
    /**
    * 备注
    */
    private String remark;
}