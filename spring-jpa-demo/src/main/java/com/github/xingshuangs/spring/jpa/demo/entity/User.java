package com.github.xingshuangs.spring.jpa.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.EnumType.STRING;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author xingshuang
 * @since 2019-11-09
 */
@Data
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名
     */
    @Column(unique = true, columnDefinition = "VARCHAR(11) COMMENT '用户名'")
    private String username;

    /**
     * 密码
     */
    @Column(columnDefinition = "VARCHAR(30) COMMENT '密码'")
    private String password;

    /**
     * 真实姓名
     */
    @Column(columnDefinition = "VARCHAR(10) COMMENT '真实姓名'")
    private String realName;

    /**
     * 邮箱
     */
    @Column( columnDefinition = "VARCHAR(50) COMMENT '邮箱'")
    private String email;

    /**
     * 手机号
     */
    @Column(columnDefinition = "VARCHAR(11) COMMENT '手机号'")
    private String mobile;

    /**
     * 性别 FEMALE：女，MALE：男
     */
    @Enumerated(STRING)
    @Column( columnDefinition = "VARCHAR(11) COMMENT '性别 FEMALE：女，MALE：男'")
    private Gender gender;

    /**
     * 状态  LOCK：禁用，UNLOCK：正常
     */
    @Enumerated(STRING)
    @Column( columnDefinition = "VARCHAR(11) COMMENT '状态 LOCK：禁用，UNLOCK：正常'")
    private Status status;

    /**
     * 创建者ID
     */
    @Column(columnDefinition = "bigint COMMENT '创建者ID'")
    private Long createUserId;

    /**
     * 创建时间
     */
    @CreationTimestamp
    @Column(columnDefinition = "DATETIME COMMENT '创建时间'")
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    @UpdateTimestamp
    @Column(columnDefinition = "DATETIME COMMENT '更新时间'")
    private LocalDateTime gmtModified;

    @Getter
    @AllArgsConstructor
    public enum Gender {
        MALE("男"),
        FEMALE("女");
        private String description;
    }

    @Getter
    @AllArgsConstructor
    public enum Status {
        UNLOCK("正常"),
        LOCK("禁用");
        private String description;
    }
}
