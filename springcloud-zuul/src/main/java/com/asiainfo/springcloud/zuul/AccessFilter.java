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
 * @Description: TODO
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
        Object accessToken = request.getParameter("token");
        if (StringUtils.isEmpty(accessToken)) {
            logger.error("token is empty!");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("token is empty!");
            return null;
        }
        logger.info("token = {}", accessToken);
        return null;
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
