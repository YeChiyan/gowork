package com.cug.cs.overseaprojectinformationsystem.service.impl;


import com.cug.cs.overseaprojectinformationsystem.constant.UserRetCode;
import com.cug.cs.overseaprojectinformationsystem.dal.entitys.ImageResult;
import com.cug.cs.overseaprojectinformationsystem.dto.KaptchaCodeResponse;
import com.cug.cs.overseaprojectinformationsystem.exception.ValidateException;
import com.cug.cs.overseaprojectinformationsystem.form.KaptchaCodeRequest;
import com.cug.cs.overseaprojectinformationsystem.service.IKaptchaService;
import com.cug.cs.overseaprojectinformationsystem.util.VerifyCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @description: TODO 整合了redis，将登录验证码的值放入到redis
 * @author: ShengHui
 * @since  2023/9/12 7:39
 * @version: 1.0
 */
@Slf4j
@Service
public class KaptchaServiceImpl implements IKaptchaService {

    @Autowired
    RedissonClient redissonClient;

    private final String KAPTCHA_UUID = "kaptcha_uuid";


    @Override
    public KaptchaCodeResponse getKaptchaCode() {
        KaptchaCodeResponse response = new KaptchaCodeResponse();
        ImageResult capText = null;
        try {
            // 生成了两个东西，一个是验证码图片，一个是验证码的码值
            capText = VerifyCodeUtils.VerifyCode(140, 43, 4);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ValidateException(UserRetCode.GENERATE_VERIFY_CODE_FAIL.getCode(),
                    UserRetCode.GENERATE_VERIFY_CODE_FAIL.getMessage());

        }
        // 将验证码图片，放入response
        response.setImageCode(capText.getImg());
        String uuid = UUID.randomUUID().toString();
        RBucket rBucket = redissonClient.getBucket(KAPTCHA_UUID + uuid);
        // 将验证码的码值放入redis
        rBucket.set(capText.getCode());
        log.info("产生的验证码:{},uuid:{}", capText.getCode(), uuid);
        rBucket.expire(120, TimeUnit.SECONDS);
        response.setUuid(uuid);
        return response;
    }

    @Override
    public void validateKaptchaCode(KaptchaCodeRequest request) {
            String redisKey = KAPTCHA_UUID + request.getUuid();
            RBucket<String> rBucket = redissonClient.getBucket(redisKey);
            String code = rBucket.get();
            log.info("请求的redisKey={},请求的code={},从redis获得的code={}", redisKey, request.getCode(), code);
            if (StringUtils.isBlank(code) || !request.getCode().equalsIgnoreCase(code)) {
                throw new ValidateException(UserRetCode.KAPTCHA_CODE_ERROR.getCode(),
                        UserRetCode.KAPTCHA_CODE_ERROR.getMessage());
            }
    }
}
