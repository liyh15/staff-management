package com.staff.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.HttpStatus;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

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
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String password = request.getParameter("password");
        if ("123456".equals(password)) {
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(HttpStatus.SC_OK);
        } else {
            ctx.setSendZuulResponse(false);
            ctx.getResponse().setContentType("application/json;charset=UTF-8");
            ctx.setResponseBody("{\n" +
                    "  \"code\":601,\n" +
                    "   \"message\":\"密码错误\"\n" +
                    "}");
            ctx.setResponseStatusCode(HttpStatus.SC_OK);
        }
        return null;
    }
}
