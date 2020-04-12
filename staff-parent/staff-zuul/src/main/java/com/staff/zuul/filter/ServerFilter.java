package com.staff.zuul.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.staff.common.config.ErrorCode;
import com.staff.common.response.BaseResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpStatus;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Configuration
public class ServerFilter extends ZuulFilter {

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
        if (url.contains("/private/")) {
            return true;
        }
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpSession session = request.getSession();
        String result = (String) session.getAttribute("isLogin");
        if ("yes".equals(result)) {
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(HttpStatus.SC_OK);
        } else {
            ctx.setSendZuulResponse(false);
            BaseResponse response = new BaseResponse();
            response.setException(ErrorCode.Status.NO_LOGIN);
            ctx.getResponse().setContentType("application/json;charset=UTF-8");
            ctx.setResponseBody(JSONObject.toJSONString(response));
            ctx.setResponseStatusCode(HttpStatus.SC_OK);
        }
        return null;
    }
}
