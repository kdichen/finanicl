package com.qian.manager.error;

import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 自定义错误处理
 *
 * @author : ChenQian
 * @date : 2019/4/13 14:01
 */

public class ErrorController extends BasicErrorController {
    public ErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties, List<ErrorViewResolver>
            errorViewResolvers) {
        super(errorAttributes, errorProperties, errorViewResolvers);
    }

    @Override
    protected Map<String, Object> getErrorAttributes(HttpServletRequest request,
                                                     boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(request, includeStackTrace);
        errorAttributes.remove("timestamp");
        errorAttributes.remove("status");
        errorAttributes.remove("error");
        errorAttributes.remove("exception");
        errorAttributes.remove("path");
        String errorCode = (String) errorAttributes.get("message");
        ErrorEnum errorEnum = ErrorEnum.getByCode(errorCode);
        errorAttributes.put("message", errorEnum.getMessage());
        errorAttributes.put("code", errorEnum.getCode());
        errorAttributes.put("canRetry", errorEnum.isCanRetry());
        return errorAttributes;

    }
}
