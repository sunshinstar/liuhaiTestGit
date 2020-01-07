package testdemo.emptyNumber.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.ContentResponseHandler;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * HTTP工具类
 * @author JiangPengFei
 * @version $Id: mayun-new, v 0.1 2018/12/20 14:07 JiangPengFei Exp $$
 */
public class HttpUtils {

	private final static Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * post请求(用于key-value格式的参数)
	 * (此Request 会复用连接数)
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, Map<String, String> params) throws Exception{
		String result = null;
		List<NameValuePair> paramList = new ArrayList<>();
		for (Iterator iter = params.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String value = String.valueOf(params.get(name));
			paramList.add(new BasicNameValuePair(name, value));
		}
		result = Request.Post(url).body(new UrlEncodedFormEntity(paramList, "utf-8"))
				.socketTimeout(150000).connectTimeout(300000).execute().returnContent().asString(Charset.forName("utf-8"));
		return result;
    }

	/**
	 * post请求，JSON请求
	 * (此Request 会复用连接数)
	 * @param url
	 * @param jsonStr
	 * @return
	 */
	public static String doPostJson(String url,String jsonStr) throws Exception{
		String result = null;
		result = Request.Post(url).body(new StringEntity(jsonStr, "utf-8"))
				.addHeader("Content-Type", "application/json;charset=UTF-8")
				.socketTimeout(60000).connectTimeout(60000).execute().returnContent().asString(Charset.forName("utf-8"));
		return result;
	}

    /**
     * post请求
     *
     * @param url
     *            功能和操作
     * @param body
     *            要post的数据
     * @return
     * @throws IOException
     */
    public static String post(String url, String body) {

        String result = "";
        try {
            OutputStreamWriter out = null;
            BufferedReader in = null;
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();

            // 设置连接参数
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(20000);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 提交数据
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(body);
            out.flush();

            // 读取返回数据
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line = "";
            // 读第一行不加换行符
            boolean firstLine = true;
            while ((line = in.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                } else {
                    result += System.lineSeparator();
                }
                result += line;
            }
			out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 登录语音平台 并获取JSESSIONID (废弃)
     * @param url
     * @param params
     * @return
     */
    public static String loginVoice(String url, Map<String, String> params){
        String result = null;
        List<NameValuePair> paramList = new ArrayList<>();
        for (Iterator iter = params.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String value = String.valueOf(params.get(name));
            paramList.add(new BasicNameValuePair(name, value));
        }
        try {
            Response response = Request.Post(url).body(new UrlEncodedFormEntity(paramList, "utf-8")).socketTimeout(15000).connectTimeout(30000).execute();
            HttpResponse httpResponse = response.returnResponse();

            Content content = new ContentResponseHandler().handleResponse(httpResponse);
            JSONObject jsonObject = JSON.parseObject(content.asString(Charset.forName("utf-8")));
            if(jsonObject.get("respCode").equals("00000")){
                result = httpResponse.getFirstHeader("Set-Cookie").getElements()[0].getValue();
            }

        } catch (Exception e) {
            LOGGER.error("登录语音平台异常",e);
            e.printStackTrace();
        }
        return result;
    }

}
