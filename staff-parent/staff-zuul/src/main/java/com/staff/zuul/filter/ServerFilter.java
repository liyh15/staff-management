package com.staff.zuul.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.staff.common.config.CookieUtil;
import com.staff.common.config.ErrorCode;
import com.staff.common.request.CheckLoginRequest;
import com.staff.common.response.BaseResponse;
import com.staff.common.response.CheckLoginResponse;
import com.staff.zuul.feign.ServerControllerFeign;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Configuration
public class ServerFilter extends ZuulFilter {

    @Autowired
    private ServerControllerFeign serverControllerFeign;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String url = request.getRequestURL().toString();
        // 拦截需要走网关的，private表示需要拦截的
        if (url.contains("public") || url.contains("js")) {
            return false;
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        CheckLoginRequest checkLoginRequest = new CheckLoginRequest();
        String count = CookieUtil.getCookieByName(request, "count");
        String sessionId = CookieUtil.getCookieByName(request, "lyhsessionId");
        if (StringUtils.isBlank(count) || StringUtils.isBlank(sessionId)) {
            ctx.setSendZuulResponse(false);
            try {
                ctx.getResponse().sendRedirect("/public/view/loginView");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        checkLoginRequest.setCount(count);
        checkLoginRequest.setSessionId(sessionId);
        CheckLoginResponse response = serverControllerFeign.checkLogin(checkLoginRequest);
        if ("1".equals(response.getIsLogin())) {
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(HttpStatus.SC_OK);
        } else {
            ctx.setSendZuulResponse(false);
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setException(ErrorCode.Status.NO_LOGIN);
            ctx.getResponse().setContentType("application/json;charset=UTF-8");
            ctx.setResponseBody(JSONObject.toJSONString(baseResponse));
            ctx.setResponseStatusCode(HttpStatus.SC_OK);
        }
        return null;
    }
}
