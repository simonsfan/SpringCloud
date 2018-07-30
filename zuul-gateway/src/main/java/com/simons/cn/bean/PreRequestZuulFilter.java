package com.simons.cn.bean;

import com.google.gson.Gson;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.simons.cn.util.CommonEnum;
import com.simons.cn.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 说明：自定义pre类型zuul过滤器，实现限过滤白名单IP功能
 * Author：simonsfan
 */
@Slf4j
public class PreRequestZuulFilter extends ZuulFilter {
    /**
     * to classify a filter by type. Standard types in Zuul are "pre" for pre-routing filtering,
     * "route" for routing to an origin, "post" for post-routing filters, "error" for error handling.
     * We also support a "static" type for static responses see  StaticResponseFilter.
     * Any filterType made be created or added and run by calling FilterProcessor.runFilters(type)
     *
     * @return A String representing that type
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * filterOrder() must also be defined for a filter. Filters may have the same  filterOrder if precedence is not
     * important for a filter. filterOrders do not need to be sequential.
     *
     * @return the int order of a filter
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * whether this filter works or not
     *
     * @return true:work ; false:zuulfilter not work
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * concrete zuulfilter logic，defined by yourself
     *
     * @return object which you need
     */
    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        HttpServletResponse response = currentContext.getResponse();
        String remoteIp = getIpAddrAdvanced(request);
        //实际白名单应该做成配置化，我这里写死仅为了模拟场景
        if (!remoteIp.equals("10.200.10.159")) {
            try {
                outPut(response);
//                return CommonResult.success(CommonEnum.FREQUENT.getCode(),CommonEnum.FREQUENT.getMessage(),null);
            } catch (Exception e) {
                log.error("pre-zuulfilter run method exception:{}", e);
            }
        }
        return null;
    }

    /**
     * 获取客户端ip
     * @param request
     * @return
     */
    public static String getIpAddrAdvanced(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (ip != null && ip.indexOf(",") != -1) {
            String[] ipWithMultiProxy = ip.split(",");

            for (int i = 0; i < ipWithMultiProxy.length; ++i) {
                String eachIpSegement = ipWithMultiProxy[i];
                if (!"unknown".equalsIgnoreCase(eachIpSegement)) {
                    ip = eachIpSegement;
                    break;
                }
            }
        }
        return ip;
    }

    /**
     * 输出结果
     * @param response
     * @throws IOException
     */
    public void outPut(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            String message = new Gson().toJson(CommonResult.success(CommonEnum.ILLEALREQUEST.getCode(), CommonEnum.ILLEALREQUEST.getMessage()));
            out.write(message.getBytes("UTF-8"));
        } catch (Exception e) {
            log.error("pre-zuulfilter error:{}", e);
        } finally {
            out.flush();
            out.close();
        }
    }
}
