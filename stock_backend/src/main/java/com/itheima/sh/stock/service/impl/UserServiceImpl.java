package com.itheima.sh.stock.service.impl;

import com.google.common.base.Strings;
import com.itheima.sh.stock.mapper.SysUserMapper;
import com.itheima.sh.stock.pojo.entity.SysUser;
import com.itheima.sh.stock.pojo.vo.LoginReqVo;
import com.itheima.sh.stock.pojo.vo.LoginRespVo;
import com.itheima.sh.stock.pojo.vo.R;
import com.itheima.sh.stock.pojo.vo.ResponseCode;
import com.itheima.sh.stock.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author by itheima
 * @Date 2022/6/29
 * @Description 定义user服务实现
 */
@Log
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 根据用户名称查询用户信息
     * @param userName 用户名称
     * @return
     */
    @Override
    public SysUser getUserByUserName(String userName) {
        SysUser user=sysUserMapper.findByUserName(userName);
        return user;
    }

    @Override
    public R<LoginRespVo> login(LoginReqVo vo) {
        //判断输入参数的合法性
        /**
         *    TODO:
         *      1.Strings.isNullOrEmpty(vo.getUsername())  判断用户名是否为空，如果用户名为空则返回true
         */
        if (vo==null || Strings.isNullOrEmpty(vo.getUsername()) || Strings.isNullOrEmpty(vo.getPassword())){
            log.info("vo:"+vo);
            /*
                    TODO:说明数据非法
                        1. ResponseCode.DATA_ERROR表示DATA_ERROR(0,"参数异常")===》将0赋值给ResponseCode中的code，
                            "参数异常"赋值给ResponseCode中的message
                        2.ResponseCode.DATA_ERROR获取的是ResponseCode枚举类对象，ResponseCode.DATA_ERROR.getMessage()
                        表示使用ResponseCode对象调用ResponseCode枚举类中的getMessage()方法获取错误信息即上述的
                        DATA_ERROR(0,"参数异常")的错误信息===》"参数异常"
                        3.R.error(ResponseCode.DATA_ERROR.getMessage())===》R.error("参数异常")===》
                            public static <T> R<T> error(String msg){//String msg="参数异常"
                                return new R<T>(ERROR_CODE,msg);==>return new R<T>(0,"参数异常")
                            }
             */
            log.info("说明数据非法，抛出DATA_ERROR");
            return R.error(ResponseCode.DATA_ERROR.getMessage());
        }
        //根据用户名查询用户信息
        log.info("查询信息");
        SysUser user=sysUserMapper.findByUserName(vo.getUsername());

        //判断用户是否存在，存在则密码校验比对
        /**
         *  TODO:
         *      1.user==null 表示没有查到用户
         *      2.如果查询到用户了，那么user是不等于null的，user==null的结果就是false
         *      3.user==null || !passwordEncoder.matches(vo.getPassword(),user.getPassword()) 根据||的特点就会继续向后执行
         *      !passwordEncoder.matches(vo.getPassword(),user.getPassword())===》vo.getPassword()表示前端传递的密码，
         *      user.getPassword()表示从数据库中查询的密码，passwordEncoder.matches(vo.getPassword(),user.getPassword())
         *      表示输入的验证码和查询数据库的验证码是否匹配，如果匹配返回true，不匹配返回false
         */
        if (user==null || !passwordEncoder.matches(vo.getPassword(),user.getPassword())){
            /**
             *  TODO:
             *      1.没有查询到用户或者密码不匹配
             *      2.ResponseCode.USERNAME_OR_PASSWORD_ERROR===>USERNAME_OR_PASSWORD_ERROR(0,"用户名或密码错误")
             *      3.
             *      public static <T> R<T> error(ResponseCode res){
             *         return new R<T>(res.getCode(),res.getMessage());
             *     }
             */
            log.info("没有查询到用户或者密码不匹配");
            return R.error(ResponseCode.USERNAME_OR_PASSWORD_ERROR);
        }
        log.info("通过校验");
        //组装登录成功数据
        LoginRespVo respVo = new LoginRespVo();
        //属性名称与类型必须相同，否则copy不到
//        respVo.setId(user.getId());
//        respVo.setNickName(user.getNickName());
        //我们发现respVo与user下具有相同的属性，所以直接复制即可
        BeanUtils.copyProperties(user,respVo);
        //返回查询的响应对象
        log.info("R.ok(respVo)："+R.ok(respVo));
        return  R.ok(respVo);
    }
}