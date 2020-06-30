package com.thoughtworks.capacity.gtb.mvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private Integer id;

    @Length(min = 5,max = 12, message = "用户名不合法")
    private String accountName;

    @Pattern(regexp = "^[0-9a-zA-Z_]{4,11}$", message = "密码必须为5-12位")
    private String password;

    @Email(message = "邮箱名不合法")
    private String emailAddress;

}
