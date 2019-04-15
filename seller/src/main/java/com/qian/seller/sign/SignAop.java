package com.qian.seller.sign;

import com.qian.seller.service.SignService;
import com.qian.util.RsaUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * 验签aop
 *
 * @author : ChenQian
 * @date : 2019/4/15 22:43
 */
@Component
@Aspect
public class SignAop {

    @Autowired
    private SignService signService;

    @Before(value = "execution(* com.qian.seller.controller.*.*(..)) && args(authId,sign,text,..)")
    public void verify(String authId,String sign,SignText text){
        String publicKey = signService.getPublicKey(authId);
        Assert.isTrue(RsaUtil.verify(text.toText(),sign,publicKey),"验签失败");
    }
}
