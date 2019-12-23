package com.mixin.demo.ssm.controller;


import com.mixin.demo.ssm.dao.UserDao;
import com.mixin.demo.ssm.entity.UserDomain;
import com.mixin.demo.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Donghua.Chen on 2018/7/25.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity addUser(
            @RequestParam(value = "userName", required = true)
                    String userName,
            @RequestParam(value = "password", required = true)
                    String password,
            @RequestParam(value = "phone", required = false)
                    String phone
    ){
        UserDomain userDomain = new UserDomain();
        userDomain.setUserName(userName);
        userDomain.setPassword(password);
        userDomain.setPhone(phone);
        userService.insert(userDomain);
        return ResponseEntity.ok("添加成功");
    }

    @DeleteMapping("")
    public ResponseEntity deleteUser(@RequestParam(value = "userId", required = true) Integer userId){

        userService.deleteUserById(userId);
        return ResponseEntity.ok("删除成功");
    }

    @PutMapping("")
    public ResponseEntity updateUser(
            @RequestParam(value = "userId", required = true)
                    Integer userId,
            @RequestParam(value = "userName", required = false)
                    String userName,
            @RequestParam(value = "password", required = false)
                    String password,
            @RequestParam(value = "phone", required = false)
                    String phone
    ){
        UserDomain userDomain = new UserDomain();
        userDomain.setUserId(userId);
        userDomain.setUserName(userName);
        userDomain.setPassword(password);
        userDomain.setPhone(phone);
        userService.updateUser(userDomain);
        return ResponseEntity.ok("更新成功");
    }

    @GetMapping("")
    public ResponseEntity getUsers(){
        return ResponseEntity.ok(userService.selectUsers());
    }

    @GetMapping("find")
    public ResponseEntity find(@RequestParam(value = "uid", required = false, defaultValue = "1000") int uid){
        return ResponseEntity.ok(userService.find(uid));
    }

    @GetMapping("selectById")
    public ResponseEntity selectById(@RequestParam(value = "uid", required = false, defaultValue = "1000") int uid){
        return ResponseEntity.ok(userService.selectById(uid));
    }

}
