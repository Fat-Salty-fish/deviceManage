package com.sinoyd.demo.dingding;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.sinoyd.demo.entity.UserInfo;
import lombok.Getter;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;


/**
 * @Description 使用钉钉接口所需的参数
 * @auther 李忠杰
 * @create 2019-02-26 16:26
 */
@Getter
public class DingDingTools {
    private static final String CORPID = "ding207076782f589c1e35c2f4657eb6378f";
    private static final String APPKEY = "ding4ivs4znpcduq4bdq";         //AppId和AppSecret用于获取access_token
    private static final String APPSECRET = "jD4xFJsfv0f3Jdf1NqWtDBtiHniuKFjMuIbzCRlbHP3r1psruzuo_n5xjxjzamEP";


    /**
     * 钉钉免登陆流程：通过appkey和appsecret参数获取access_token 有效期为2小时 有效期内重复获取返回相同的结果 并且自动续期
     * 前端首次访问时需要携带免登授权码 通过免登授权码以及access_token即可获取员工基本信息
     * 获取员工基本信息之后再通过user_id 和access_token即可获取员工详细信息
     *
     * @return
     */
    public static String getAccessToken() {
        //创建一个httpClient请求实例
        CloseableHttpClient client = HttpClients.createDefault();
        //创建一个httpResponse响应实例
        CloseableHttpResponse response = null;
        try {
            //添加请求参数
            URIBuilder builder = new URIBuilder("https://oapi.dingtalk.com/gettoken");
            builder.addParameter("appkey", APPKEY);
            builder.addParameter("appsecret", APPSECRET);
            URI uri = builder.build();
            //设置请求uri
            HttpGet get = new HttpGet(uri);
            //获取响应报文
            response = client.execute(get);

            //用于返回的token字符串
            String token = null;
            if (response.getStatusLine().getStatusCode() == 200) {
                //获取返回的json数据
                InputStream inputStream = response.getEntity().getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                //获取access_token的字符串
                JSONObject jsonObject = JSON.parseObject(buffer.toString());
                token = jsonObject.getString("access_token");
                return token;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("在获取accesstoken时发生错误");
            return null;
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 传入参数为用户免登码以及之前获取的access_token 使用这两个参数再向钉钉服务器获取用户信息即可
     *
     * @param code
     * @param accessToken
     * @return
     */
    public static String getUserId(String code, String accessToken) {
        if (code == null) {
            System.out.println("code为空");
            throw new NullPointerException("员工code为空 此值必须传入");
        }
        if (accessToken == null) {
            System.out.println("access_token为空");
            throw new NullPointerException("access_token为空 此值必须传入");
        }
        //创建一个httpClient请求实体
        CloseableHttpClient client = HttpClients.createDefault();
        //创建一个httpResponse响应实体
        CloseableHttpResponse response = null;

        try {
            URIBuilder builder = new URIBuilder("https://oapi.dingtalk.com/user/getuserinfo");
            builder.addParameter("access_token", accessToken);
            builder.addParameter("code", code);
            URI uri = builder.build();
            HttpGet get = new HttpGet(uri);
            response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == 200) {
                InputStream inputStream = response.getEntity().getContent();
                String line = "";
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer buffer = new StringBuffer();
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                JSONObject jsonObject = JSON.parseObject(buffer.toString());
                System.out.println(jsonObject);
                String userId = jsonObject.getString("userid");
                return userId;
            }
        } catch (URISyntaxException e) {
            System.out.println("发生了uri错误");
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            System.out.println("发生了client错误");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("发生了io错误");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过上一个接口获取的用户id与之前获得的access_token获取用户的信息
     *
     * @param userID
     * @param accessToken
     * @return
     */
    public static UserInfo getUserInfo(String userID, String accessToken) {
        //创建httpclient请求实体
        CloseableHttpClient client = HttpClients.createDefault();
        //创建httpresponse响应实体
        CloseableHttpResponse response = null;

        try {
            URIBuilder uriBuilder = new URIBuilder("https://oapi.dingtalk.com/user/get");
            uriBuilder.addParameter("access_token", accessToken);
            uriBuilder.addParameter("userid", userID);
            URI uri = uriBuilder.build();
            HttpGet get = new HttpGet(uri);
            response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == 200) {
                InputStream inputStream = response.getEntity().getContent();
                String line = "";
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer stringBuffer = new StringBuffer();
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line);
                }
                JSONObject jsonObject = JSON.parseObject(stringBuffer.toString());
//                System.out.println(jsonObject);
                UserInfo userInfo = JSON.toJavaObject(jsonObject, UserInfo.class);
                return userInfo;
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
