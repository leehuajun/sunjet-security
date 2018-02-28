package com.sunjet.sunjetsecuritydemo.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.sunjet.sunjetsecuritydemo.dto.User;
import com.sunjet.sunjetsecuritydemo.dto.UserQueryCondition;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    public List<User> query(UserQueryCondition condition, @PageableDefault(page=2,size = 17,sort = "username,asc") Pageable pageable){
//        System.out.println(username);
        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
        System.out.println(ReflectionToStringBuilder.toString(pageable, ToStringStyle.MULTI_LINE_STYLE));

        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getSort());

        List<User> users = Arrays.asList(
                new User("zhangsan","123456"),
                new User("lisi","123456"),
                new User("wangwu","123456")
        );
        return users;
    }

    /**
     * id 只能是数字
     * @param id
     * @return
     */
    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@PathVariable(name = "id") String id){
        User user = new User("tom","");
        return user;
    }

    /**
     * 加入 @Valid 进行属性验证
     * @param user
     * @param errors
     * @return
     */
    @PostMapping
    public User create(@Valid @RequestBody User user, BindingResult errors){

        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error-> System.out.println(error.getDefaultMessage()));
        }

        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());

        user.setId("1");

        return user;
    }

    @PutMapping("/{id:\\d+}")
    public User update(@Valid @RequestBody User user, BindingResult errors){

        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error-> {
//                FieldError fieldError = (FieldError)error;
//                System.out.println(fieldError.getField() + ": " + error.getDefaultMessage());
                System.out.println(error.getDefaultMessage());
            });
        }

        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());

        user.setId("1");

        return user;
    }
}
