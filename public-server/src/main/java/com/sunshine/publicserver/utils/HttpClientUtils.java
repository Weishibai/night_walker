package com.sunshine.publicserver.utils;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.sunshine.common.util.JsonUtil;
import com.sunshine.common.vo.InfoCenterData;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.util.List;

public class HttpClientUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);

    public static final Header[] DEFAULT_HEADER = new Header[1];

    static {
        DEFAULT_HEADER[0] = new BasicHeader("Content-Type", "application/json");
    }

    public static String get(String url) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet reuqest = new HttpGet(url);
            LOGGER.info("executing request {}", reuqest.getURI());

            try (CloseableHttpResponse response = httpclient.execute(reuqest)) {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String responseStr = EntityUtils.toString(entity);
                    LOGGER.info("execute response {}", responseStr);
                    return responseStr;
                }
                return StringUtils.EMPTY;
            }

        } catch (Exception e) {
            LOGGER.error("request url {} error", url, e);
            return StringUtils.EMPTY;
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {}
        }
    }

    private static HttpParams buildHttpParams(int connTimeout, int socketTimeout) {
        HttpParams params = new BasicHttpParams();
        params.setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connTimeout);
        params.setIntParameter(CoreConnectionPNames.SO_TIMEOUT, socketTimeout);
        return params;
    }

    public static void main(String[] args) {
        String body = JsonUtil.writeString(InfoCenterData.make("", "BJS", ""));
        post("http://localhost:8080/admin-server/infocenter/query", body, DEFAULT_HEADER, 1000, 1000);
    }


    public static String post(String url, String body, Header[] headers, int connTimeout, int soTimeout) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httpPost = new HttpPost(url);
        try {

            httpPost.setParams(buildHttpParams(connTimeout, soTimeout));
            httpPost.setEntity(new StringEntity(body, Charsets.UTF_8));
            httpPost.setHeaders(headers);
            CloseableHttpResponse response = httpclient.execute(httpPost);

            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String resp = EntityUtils.toString(entity, Charsets.UTF_8);
                    LOGGER.info("post url {} resp {}", url, resp);
                    return resp;
                }
                return StringUtils.EMPTY;
            } finally {
                response.close();
            }
        } catch (Exception e) {
            LOGGER.error("http execute error: ", e);
            return StringUtils.EMPTY;
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {}
        }
    }
}
