package com.thoughtworks.capacity.gtb.mvc.Controller;

import com.thoughtworks.capacity.gtb.mvc.Exception.NoAuthorizedException;
import com.thoughtworks.capacity.gtb.mvc.Exception.UserExistingException;
import com.thoughtworks.capacity.gtb.mvc.Service.AccountSercive;
import com.thoughtworks.capacity.gtb.mvc.model.Account;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchProviderException;

@RestController
public class AccountController {

    private final AccountSercive accountSercive;

    public AccountController(AccountSercive accountSercive) {
        this.accountSercive = accountSercive;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Valid Account account) {
        if(accountSercive.userNameExsiting(account)){
                throw new UserExistingException("用户已存在");
        }
        accountSercive.createAccount(account);
    }

    @GetMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public Account login(@RequestBody @Valid Account account) {
        Account result = accountSercive.login(account);
        if(result==null){
            throw new NoAuthorizedException("用户名或密码错误");
        }
        return result;
    }

}