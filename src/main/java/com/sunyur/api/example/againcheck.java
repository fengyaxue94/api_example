package com.sunyur.api.example;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class againcheck {
    /**
     * dev环境智联租户绕过api网关
     */
    private static final String APP_KEY = "110552757";
    private static final String APP_SECRET = "r3jl5714umrdoary346v3btfs7vu2up5";

    /**
     * test环境链家租户绕过api网关
      private static final String APP_KEY = "25647624";
      private static final String APP_SECRET = "371c4f954a0ffa84e3810e5cc96c1f3a";
     */
    public static void main(String[] args) {
//        String body = "[{\"code\":\"1\", \"name\":\"张三\", \"status\":1}]";
        /**
         * body：用于存储参数
         */
        /**
         * 取消订单接口的参数
         * String body = "{\"code\":\"PO1904010023\",\"operatorCode\":\"20358237\"}";
         */
        /**
         * 再来一单接口的参数
         */
        String body = "{\"mallSkus\":[{\"itemSkuId\":\"2673\",\"amount\":\"100.01\"},{\"itemSkuId\":\"2672\",\"amount\":\"100.32\"},{\"itemSkuId\":\"2240\",\"amount\":\"100\"}],\"userCode\":\"ZL43653\"}";

        //String body = "{\"code\":\"00000006num2\",\"name\":\"杭州人力资源test001\",\"simpleName\":\"杭州人力资源test001\",\"creditCode\":\"111111112222000000test_num1\",\"mobile\":\"400-12345679\",\"type\":\"2\",\"status\":\"1\"}";
//        String body = "[{\"code\":\"2019021900017364\", \"status\":\"1\", \"operatorCode\":\"\", \"reason\":\"\"}]";
//        String body = "[{\"currencyDescription\":\"币种同步222\",\"currencyName\":\"人民币测试22\",\"currencyCode\":\"CNY\"}]";


        try {
//            HttpPost httpPost = new HttpPost("http://test.api.sunyur.com/api/purchaser/user/save");
//            HttpPost httpPost = new HttpPost("http://api.sunyur.com/api/purchaser/user/login");
//            HttpPost httpPost = new HttpPost("http://api.sunyur.com/api/purchaser/invoice_order/detail");
            /**
             * 请求处理接口：再来一单
             *
             */
            HttpPost httpPost = new HttpPost("http://dev.api.sunyur.com/api/shoppingcart/again/check");
            /**
             * 加收供应商主体信息的联系电话和状态
             */
            httpPost.setEntity(new StringEntity(body, "UTF-8"));
            //
            /**
             * httpPost.addHeader("X-Ca-Key", APP_KEY);
             * 智联里header部分参数是：X-Ca-AppId
             */
            httpPost.addHeader("X-Ca-AppId", APP_KEY);
            httpPost.addHeader("X-Ca-Timestamp", String.valueOf(new Date().getTime()));
            httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
            Map<String, String> headers = new HashMap<>();
            for(Header header : httpPost.getAllHeaders()){
                headers.put(header.getName(), header.getValue());
            }
            /**
             * path:请求接口地址，和httppost地址保持一致
             */
            String sign = SYAPIUtils.sign(APP_SECRET, "POST", "/api/shoppingcart/again/check", headers, body);
            httpPost.addHeader("X-Ca-Signature", sign);
            HttpClient httpClient = HttpClients.createDefault();
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if(httpResponse.getStatusLine().getStatusCode() == 200){
                System.out.println(EntityUtils.toString(httpResponse.getEntity(), "UTF-8"));
            }else{
                System.out.println(httpResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
