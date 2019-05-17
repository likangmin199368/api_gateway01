package com.likangmin.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author likangmin
 * @date 2019/5/15-14:35
 */
public class AccessPasswordFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        // 如果前一个过滤器的结果为true，
        // 则说明上一个过滤器成功了，需要进入当前的过滤，
        // 如果前一个过滤器的结果为false，
        // 则说明上一个过滤器没有成功，则无需进行下面的过滤动作了，
        // 直接跳过后面的所有过滤器并返回结果
        RequestContext ctx = RequestContext.getCurrentContext();
        return (boolean) ctx.get("isSuccess");
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        System.out.println(String.format("%s AccessPasswordFilter request to %s", request.getMethod(), request.getRequestURL().toString()));
        String username = request.getParameter("password");
        if(null != username && username.equals("123456")) {
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);
            return null;
        }else{
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("{\"result\":\"password is not correct!\"}");
            ctx.set("isSuccess", false);
            return null;
        }
    }
}
