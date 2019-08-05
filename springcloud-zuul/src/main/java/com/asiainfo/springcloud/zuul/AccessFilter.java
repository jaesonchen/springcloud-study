package com.asiainfo.springcloud.zuul;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**   
 * @Description: zuul 过滤器
 * 
 * @author chenzq  
 * @date 2019年5月19日 下午6:48:13
 * @version V1.0
 * @Copyright: Copyright(c) 2019 jaesonchen.com Inc. All rights reserved. 
 */
@Component
public class AccessFilter extends ZuulFilter {

    final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Override
    public Object run() throws ZuulException {
        
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        logger.info("method = {}, requestURL = {}", request.getMethod(), request.getRequestURL());
        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)) {
            logger.error("token is empty!");
            // 过滤该请求，不对其进行路由
            ctx.setSendZuulResponse(false);
            // 返回错误码
            ctx.setResponseStatusCode(401);
            // 返回错误提示
            ctx.setResponseBody("token is empty!");
            // 设置context属性，可以在下一个filter的shouldFilter()里读取，以判断是否需要进行filter逻辑判断
            ctx.set("isSuccess", false);
            return null;
        } else {
            logger.info("token = {}", token);
            // 对该请求进行路由
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);
            // 增加request Header
            ctx.addZuulRequestHeader("X-AUTH-ID", token);
            ctx.set("isSuccess", true);
            return null;
        }
    }
    
    @Override
    public boolean shouldFilter() {
        return true;
    }

    // pre / route / post / error
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }
}