package com.thoughtworks.capacity.gtb.mvc.Service;

import com.thoughtworks.capacity.gtb.mvc.model.Account;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccountSercive {

    private Map<Integer, Account> accontMap=new HashMap<>();

    public AccountSercive(){
        accontMap.put(1,new Account(1,"yzh960522","Deary0628","1124928067@qq.com"));
    }

    public void createAccount(Account account) {
        int id = (int)(1+Math.random()*(100-1+1));
        while(accontMap.containsKey(id)){
            id = (int)(1+Math.random()*(100-1+1));
        }
        account.setId(id);
        accontMap.put(id,account);
    }

    public boolean userNameExsiting(Account account){
        for (Account value : accontMap.values()) {
            if(value.getAccountName().equals(account.getAccountName())){
            return true;}
        }
        return false;
    }

    public Account login(Account account){
        for (Account value : accontMap.values()) {
            if(value.getAccountName().equals(account.getAccountName())&&value.getPassword().equals(account.getPassword())){
                return value;
            }
        }
        return null;
    }
}
