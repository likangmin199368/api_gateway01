package com.likangmin.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author likangmin
 * @date 2019/5/15-14:36
 */
public class AccessTokenFilter extends ZuulFilter {
    // 在请求被处理之后，会进入该过滤器
    @Override
    public String filterType() {
        return "post";
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
        System.out.println(String.format("%s AccessTokenFilter request to %s", request.getMethod(),
                request.getRequestURL().toString()));
        ctx.setSendZuulResponse(true);
        ctx.setResponseStatusCode(200);
        ctx.setResponseBody("{\"name\":\"chhliu\"}");// 输出最终结果
        return null;
    }
}
