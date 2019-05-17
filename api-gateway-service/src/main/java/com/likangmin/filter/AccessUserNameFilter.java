package com.likangmin.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author likangmin
 * @date 2019/5/15-14:33
 */
public class AccessUserNameFilter extends ZuulFilter {
    // 前置过滤器
    @Override
    public String filterType() {
        return "pre";
    }
    // 优先级为0，数字越大，优先级越低
    @Override
    public int filterOrder() {
        return 0;
    }
    // 是否执行该过滤器，此处为true，说明需要过滤
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        System.out.println(String.format("%s AccessUserNameFilter request to %s", request.getMethod(), request.getRequestURL().toString()));
        System.out.println(request.getMethod());
        System.out.println(request.getRequestURL().toString());
        String username = request.getParameter("username");// 获取请求的参数
        if(null != username && username.equals("chhliu")) {// 如果请求的参数不为空，且值为chhliu时，则通过
            ctx.setSendZuulResponse(true);// 对该请求进行路由
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);// 设值，让下一个Filter看到上一个Filter的状态
            return null;
        }else{
            ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
            ctx.setResponseStatusCode(401);// 返回错误码
            ctx.setResponseBody("{\"result\":\"username is not correct!\"}");// 返回错误内容
            ctx.set("isSuccess", false);
            return null;
        }
    }
}
