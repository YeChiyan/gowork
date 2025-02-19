package com.cug.cs.overseaprojectinformationsystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cug.cs.overseaprojectinformationsystem.bean.common.ResponseData;
import com.cug.cs.overseaprojectinformationsystem.bean.common.ResponseUtil;
import com.cug.cs.overseaprojectinformationsystem.constant.RedisConstant;
import com.cug.cs.overseaprojectinformationsystem.constant.UserRetCode;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.Member;
import com.cug.cs.overseaprojectinformationsystem.dto.LoginFormDto;
import com.cug.cs.overseaprojectinformationsystem.dto.MemberDto;
import com.cug.cs.overseaprojectinformationsystem.dto.UserLoginResponse;
import com.cug.cs.overseaprojectinformationsystem.exception.ValidateException;
import com.cug.cs.overseaprojectinformationsystem.form.UserLoginRequest;
import com.cug.cs.overseaprojectinformationsystem.mapper.MemberMapper;
import com.cug.cs.overseaprojectinformationsystem.service.ILoginService;
import com.cug.cs.overseaprojectinformationsystem.util.JwtUtil;
import com.cug.cs.overseaprojectinformationsystem.util.RegexUtils;
import com.cug.cs.overseaprojectinformationsystem.util.jwt.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;


import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
@Slf4j
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

   /* @Autowired
    private UserConverterMapper userConverterMapper;*/


    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        // 验证用户名和密码是否正确
        /*Example example = new Example(Member.class);
        example.createCriteria()
                .andEqualTo("username", request.getUserName())
                .andEqualTo("password", DigestUtils.md5DigestAsHex(request.getUserPwd().getBytes()));*/
        String password = request.getUserPwd();
        String userName = request.getUserName();
        Member member = new Member();
        member.setUsername(userName);
        member.setPassword(password);
        // 验证用户名和密码是否正确
        
        List<Member> members = memberMapper.selectByUserNameAndPassword(member);
        
        if (CollectionUtils.isEmpty(members)) {
            throw new ValidateException(UserRetCode.USERORPASSWORD_ERROR.getCode(),
                    UserRetCode.USERORPASSWORD_ERROR.getMessage());
        }

        // 是否需要用  激活表来验证
        Member registeredMember = members.get(0);
        
        // 生成 JWT token
        Map<String, Object> map = new HashMap<>();
        map.put("uid", registeredMember.getId());
        map.put("username", registeredMember.getUsername());
        String token = JwtTokenUtils.builder().msg(JSON.toJSONString(map)).build().creatJwtToken();
        UserLoginResponse loginResponse = new UserLoginResponse();
        // TODO 需要重新封装类
        
        // UserLoginResponse loginResponse = userConverterMapper.converter(registeredMember);
        loginResponse.setToken(token);
        return loginResponse;
    }

    @Override
    public ResponseData sendCode(String phone) {
        System.out.println("sendCode被调用");
        if(!RegexUtils.isCodeInvalid(phone)){
            log.error("手机号不符合要求");
            return new ResponseUtil().setErrorMsg(200,"手机号不符合要求，请重新输入");
//            return ResponseData("手机号不符合要求，请重新输入");
        }
        String code = RandomUtil.randomNumbers(6);//生成 6 位数的验证码
        String CodeKey= RedisConstant.LOGIN_CODE_KEY+phone;
        stringRedisTemplate.opsForValue().set(CodeKey,code,RedisConstant.LOGIN_CODE_TTL, TimeUnit.MINUTES);
        log.debug("用户："+phone+"的验证码为："+code);
        return new ResponseUtil<>().setData(true,"验证码生成成功！！！");
    }

    @Override
    public ResponseData loginWithPassword(LoginFormDto loginForm) {
        return null;
    }

    @Override
    public ResponseData loginWithPhoneCode(LoginFormDto loginForm) {
        //1.验证手机号是否有效
        if(!RegexUtils.isCodeInvalid(loginForm.getPhone())){
            log.error("手机号不符合要求");

            return new ResponseUtil().setErrorMsg(200,"手机号不符合要求，请重新输入");
        }
        //2.从 redis 中获取验证码
        String codeKey=RedisConstant.LOGIN_CODE_KEY+loginForm.getPhone();
        String code = stringRedisTemplate.opsForValue().get(codeKey);
        if(code==null||loginForm.getCode()==null){
            return new ResponseUtil().setErrorMsg(200,"当前用户暂未发送验证码");
        }
        //3.判断验证码是否有效
        if (!StrUtil.equals(code,loginForm.getCode())) {
            return new ResponseUtil().setErrorMsg(200,"验证码输入错误");
        }
        //4.从数据库中找有没有当前用户
        LambdaQueryWrapper<Member> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Member::getPhone,loginForm.getPhone());
        Member member = memberMapper.selectOne(lqw);
        //5.没有的话就是注册
        if(member==null){
            createUserWithPhone(loginForm.getPhone());
        }
        //生成 Jwttoken 保存到 redis中
        String jwtToken = JwtUtil.generateJwtToken(loginForm.getPhone());//这个时候还不知道 username，只能就用 phone 了
//        MemberDto memberDto = new MemberDto();
        MemberDto memberDto = BeanUtil.copyProperties(member, MemberDto.class);// 复制对象属性
//        Map<String, Object> memberMap = BeanUtil.beanToMap(memberDto,);
        Map<String, Object> userMap = BeanUtil.beanToMap(memberDto, new HashMap<>(),
                CopyOptions.create()
                        .setIgnoreNullValue(true)
                        .setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString()));
        String tokenKey = "LOGIN_MEMBER_KEY" + jwtToken;  // tokenKey 使用 member 的唯一标识（如 token）
//        stringRedisTemplate.opsForValue().set(tokenKey,jwtToken);
        stringRedisTemplate.opsForHash().putAll(tokenKey,userMap);
        return new ResponseUtil<>().setData(200,"用户注册成功!!!!!!!!!");
    }

    private Member createUserWithPhone(String phone) {
        Member member = new Member();
        member.setPhone(phone);
        member.setName(RedisConstant.USER_NICK_NAME_PREFIX+RandomUtil.randomString(10));
        memberMapper.insert(member);
        return member;
    }


}
