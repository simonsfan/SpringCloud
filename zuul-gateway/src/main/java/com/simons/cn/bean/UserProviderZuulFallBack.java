package com.simons.cn.bean;

import com.google.gson.Gson;
import com.simons.cn.util.CommonEnum;
import com.simons.cn.util.CommonResult;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

@Component
public class UserProviderZuulFallBack implements ZuulFallbackProvider {

    private static final String USER_PROVIDER_SERVICE = "user-provider";

    @Override
    public String getRoute() {
        return USER_PROVIDER_SERVICE; //返回你需要为哪个微服务提供回退
    }

    @Override
    public ClientHttpResponse fallbackResponse() {
        return new ClientHttpResponse() {
            /**
             * 返回Http状态码标识
             */
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            /**
             * 返回Http状态码对应数字:200，详见HttpStatus
             */
            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            /**
             *返回Http状态码对应中译:OK，详见HttpStatus
             */
            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.OK.getReasonPhrase();
            }

            @Override
            public void close() {

            }

            /**
             *body响应体
             */
            @Override
            public InputStream getBody() throws IOException {
                String message = new Gson().toJson(CommonResult.success(CommonEnum.SERVICE_NOT_AVAILABLE.getCode(), CommonEnum.SERVICE_NOT_AVAILABLE.getMessage()));
                return new ByteArrayInputStream(message.getBytes("UTF-8"));
            }

            /**
             * 设置HttpHeaders
             */
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                //    MediaType mediaType = new MediaType("application/json;charset=utf-8");  //这是错误写法
                MediaType mediaType = new MediaType("application", "json", Charset.forName("utf-8"));
                headers.setContentType(mediaType);
                return headers;
            }
        };
    }
}
