package com.github.xingshuangs.user.demo.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author xingshuang
 * @date 2020/5/28
 */
@Data
public class LoginInfoDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
