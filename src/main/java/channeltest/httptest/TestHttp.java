package channeltest.httptest;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author o_0sky
 * @date 2019/4/30 17:19
 */
public class TestHttp {
    public static void main(String[] args) throws UnsupportedEncodingException {

        //一般用于直接上传键值对
        HttpPost httpPost = new HttpPost("http://47.100.172.112:10005/materialUpload");
        List<NameValuePair> pairs = new ArrayList<>();
        pairs.add(new BasicNameValuePair("key", "value"));
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, HTTP.UTF_8));


        //一般用于上传json居多，格式多样化
        JSONObject json = new JSONObject();
        json.put("key", "value");
        httpPost.setEntity(new StringEntity(json.toString(), HTTP.UTF_8));


    }


}
