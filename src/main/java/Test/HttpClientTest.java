package Test;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import util.GsonUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URI;

@Slf4j
public class HttpClientTest {
    @Data
    public  static  class OrderInfo{
        String openid;
        String orderId;
    }
    @Test
    public void postJsonTimeOutTest() {

//        String url = "http://199.33.222.2:90";
        String url = "http://localhost/sell/buyer/order/detail2";
        HttpClientTest.OrderInfo orderInfo = new HttpClientTest.OrderInfo();
        orderInfo.setOpenid("505050");
        orderInfo.setOrderId("154321220133679135289");
        String json = GsonUtil.objectToJson(orderInfo);
        System.out.println(json+"=============");
        String charset = "utf-8";
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost();
        httpPost.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
        httpPost.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 50000);

        httpPost.setURI(URI.create(url));
        StringEntity entity = null;
        try {
            entity = new StringEntity(json);
            entity.setContentEncoding(charset);
            entity.setContentType("application/json");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpPost.setEntity(entity);
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpPost);
            HttpEntity responseEntity = httpResponse.getEntity();
            log.info("responseContent-type=[{}]",responseEntity.getContentType().getName()+":"+responseEntity.getContentType().getValue());
            String responseStr = EntityUtils.toString(responseEntity,charset);
            log.info(responseStr);
        }catch (HttpHostConnectException e){
            System.out.println("HttpHostConnectException");
            e.printStackTrace();
        } catch (ConnectTimeoutException e){
            log.info("SocketTimeoutException");
            e.printStackTrace();
        }
        catch (SocketTimeoutException e) {
            System.out.println("SocketTimeoutException");
            e.printStackTrace();
        }catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        }
    }

    @Test
    public void GsonUtiltest() {
        HttpClientTest.OrderInfo orderInfo = new HttpClientTest.OrderInfo();
        orderInfo.setOpenid("505050");
        orderInfo.setOrderId("154321220133679135289");
        String s = GsonUtil.objectToJson(orderInfo);
        System.out.println(s);

        HttpClientTest.OrderInfo orderInfo1 = GsonUtil.jsonToObject(s, HttpClientTest.OrderInfo.class);
        System.out.println(orderInfo1);
    }
}
