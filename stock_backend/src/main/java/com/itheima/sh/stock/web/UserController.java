package com.itheima.sh.stock.web;

import com.itheima.sh.stock.pojo.entity.SysUser;
import com.itheima.sh.stock.pojo.vo.LoginReqVo;
import com.itheima.sh.stock.pojo.vo.LoginRespVo;
import com.itheima.sh.stock.pojo.vo.R;
import com.itheima.sh.stock.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author by itheima
 * @Date 2022/6/29
 * @Description 定义用户处理器接口
 */
/*
	@RestController 是@controller和@ResponseBody 的结合
		@Controller 将当前修饰的类注入Spring IOC容器.
		@ResponseBody 它的作用就是指该类中所有的API接口返回的数据，对应的方法返回Map或是其他Object，都会以Json字符串的形式返回给客户端
*/
@RestController
//@RequestMapping注解是一个用来处理请求地址映射的注解
@RequestMapping("/api")
@Log
public class UserController {

    @Autowired
    private UserService userService;

     /**
     * 根据用户名查询用户信息
     * TODO:@GetMapping用于处理请求方法的GET类型
     *  1. @GetMapping("/{userName}") 的{userName}表示路径参数，如果方法getUserByUserName的形参不加注解@PathVariable，
     *  那么前端提交的数据是无法赋值给形参String userName的userName的
     *  2.如果@GetMapping("/{userName}")的userName和形参名String userName的userName变量名一致，可以不在@PathVariable注解
     *  后面加小括号，但是书写小括号加标识符也可以即@PathVariable("userName")
      * @return
      */

    @GetMapping("/{userName}")
    public SysUser getUserByUserName(@PathVariable("userName") String userName){
        log.info("用户访问userName"+userName);
        return userService.getUserByUserName(userName);
    }
    @PostMapping("/login")
    public R<LoginRespVo> login(@RequestBody LoginReqVo vo){
        //调用业务层方法将前端请求对象作为参数传递，接收后端响应的对象
        log.info("访问"+vo);
        R<LoginRespVo> r= userService.login(vo);
        log.info("返回数据r:"+r);
        return r;
    }
}
