package fans.umamusume.www.common.kit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import fans.umamusume.www.common.Config;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpPost {
    /**
     * 向指定的 URL发送远程POST方法的请求
     * <p>
     * <p>
     * ，
     *
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, JSON json) {
        //PrintWriter out = null;
        OutputStreamWriter out = null;
        BufferedReader in = null;
        JSONObject jsonObject = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl
                    .openConnection();
            // 设置通用的请求属性
            conn.setRequestMethod("POST");
            // 发送POST请求必须设置下面的属性
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求属性
            conn.setRequestProperty("Content-Type",
                    "application/json; charset=utf-8");
            conn.connect();
            // 获取URLConnection对象对应的输出流
            //out = new PrintWriter(conn.getOutputStream());
            out = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
            // 发送请求参数
            //out.print(JSON.toJSONString(json));
            out.write(JSON.toJSONString(json));
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line = "";
            while ((line = in.readLine()) != null) {
                result += line;
            }
            // 将返回结果转换为字符串
            //jsonObject = JSONObject.parseObject(result);
        } catch (Exception e) {
            /*throw new RuntimeException("远程通路异常" + e.toString());*/
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static String doPost(String url, Map<String, Object> _params) {
        String charset = "utf-8";
        boolean pretty = false;
        StringBuffer response = new StringBuffer();
        org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();
        PostMethod method = new PostMethod(url);
        if (_params != null) {
            for (Map.Entry<String, Object> entry : _params.entrySet()) {
                method.setParameter(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        // 设置Http Post数据  方法二
//        if(_params != null) {
//            NameValuePair[] pairs = new NameValuePair[_params.size()];//纯参数了，键值对
//            int i = 0;
//            for (Map.Entry<String, Object> entry : _params.entrySet()) {
//                pairs[i] = new NameValuePair(entry.getKey(), String.valueOf(entry.getValue()));
//                i++;
//            }
//            method.addParameters(pairs);
//        }
        try {
            client.executeMethod(method);
            if (method.getStatusCode() == HttpStatus.SC_OK) {
                // 读取为 InputStream，在网页内容数据量大时候推荐使用
                BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(),
                        charset));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (pretty)
                        response.append(line).append(System.getProperty("line.separator"));
                    else
                        response.append(line);
                }
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("执行HTTP Post请求" + url + "时，发生异常！");
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }
        return response.toString();
    }

    public static Boolean recaptcha(String recaptcha) {
        String url = "https://www.google.com/recaptcha/api/siteverify";
        Map<String, Object> para = new HashMap<>();
        para.put("secret", Config.RECAPTCHA);
        para.put("response", recaptcha);
            /*JSONObject request = new JSONObject();
            request.put("secret", Config.RECAPTCHA);
            request.put("response", recaptcha);*/
        String result = doPost(url, para);
        JSONObject response = JSON.parseObject(result);
        return response.getBoolean("success");
    }


    public static String sendPost(String url, String json) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl
                    .openConnection();
            // 设置通用的请求属性
            conn.setRequestMethod("POST");
            // 发送POST请求必须设置下面的属性
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求属性
            conn.setRequestProperty("Content-Type",
                    "application/json; charset=utf-8");
            conn.connect();
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(json);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line = "";
            while ((line = in.readLine()) != null) {
                result += line;
            }
            // 将返回结果转换为字符串
            //jsonObject = JSONObject.parseObject(result);
        } catch (Exception e) {
            throw new RuntimeException("远程通路异常" + e.toString());
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}