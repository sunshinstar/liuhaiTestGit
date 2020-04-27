package channeldemo.zqsx;


import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZQSXTest {

    final static String SECRET_KEY = "518c14c851b4454314f1ae0b4dfecf4f";

    public static void main(String[] args) {
        send();
      // templateList();
       //templateStatus();
       //createTemplate();
    }

    /**
     * 数字短信类型 ID 数字短信类型
     * 2 生活
     * 3 电商活动
     * 4 日报
     * 5 清单
     * 6 动漫
     * 7 头条
     * 8 热点
     * 9 娱乐
     * 10 体育
     * 11 财经
     * 12 科技 13 NBA
     * 14 段子
     * 15 图片
     * 16 汽车
     * 17 时尚
     * 18 轻松一刻
     * 19 军事
     * 20 历史
     * 21 房产
     * 22 彩票
     * 23 原创
     * 24 电台
     * 25 财政
     * 26 论坛
     * 27 博客
     * 28 社会
     * 29 电影
     * 30 中国足球
     * 31 国际足球 32 CBA
     * 33 跑步
     * 34 手机
     * 35 数码
     * 36 移动互联
     * 37 公开课
     * 38 家居
     * 39 旅游
     * 40 健康
     * 41 读书
     * 42 酒香
     * 43 教育
     * 44 亲子
     * 45 情感
     */
    private static void createTemplate() {
        Map<String, Object> map = new HashMap<>(16);
        map.put("username", "qmy-dengqiuya");
        map.put("type", "2");
        map.put("title", "美国队长3预告片 精彩影视尽在优酷");

        Long timeMillis = System.currentTimeMillis();
        map.put("timestamp", timeMillis.toString());

        List params = new ArrayList();

        List<VideoMessageCreateEntity> listOne = new ArrayList<>();
        String content = "影片于2016年5月6日在大陆上映《美国队长3：内战》是由美国漫威影业公司出品的科幻动作片，由安东尼·罗素、乔·罗素兄弟联合执导，克里斯·埃文斯、小罗伯特·唐尼、斯嘉丽·约翰逊、塞巴斯蒂安·斯坦领衔主演。 \n" +
                "  该片讲述了奥创事件后引发的一系列政治问题导致复仇者之间内部矛盾激化的故事。";
        VideoMessageCreateEntity entity = new VideoMessageCreateEntity("天气预报.txt", content);
        listOne.add(entity);

        List<VideoMessageCreateEntity> listTwo = new ArrayList<>();
        VideoMessageCreateEntity one = new VideoMessageCreateEntity("one.jpg", new File("E:\\1.jpg"));
        listTwo.add(one);

        content = "此条信息是测试样刊，如接收错误请删除。";
        VideoMessageCreateEntity two = new VideoMessageCreateEntity("two.txt", content);
        listTwo.add(two);

        content = "影片于2016年5月6日在大陆上映。";
        VideoMessageCreateEntity three = new VideoMessageCreateEntity("three.txt", content);
        listTwo.add(three);

        content = "《美国队长3：内战》是由美国漫威影业公司出品的科幻动作片，由安东尼·罗素、乔·罗素兄弟联合执导，克里斯·埃文斯、小罗伯特·唐尼、斯嘉丽·约翰逊、塞巴斯蒂安·斯坦领衔主演。";
        VideoMessageCreateEntity four = new VideoMessageCreateEntity("four.txt", content);
        listTwo.add(four);

        content = "该片讲述了奥创事件后引发的一系列政治问题导致复仇者之间内部矛盾激化的故事。";
        VideoMessageCreateEntity five = new VideoMessageCreateEntity("five.txt", content);
        listTwo.add(five);

        VideoMessageCreateEntity six = new VideoMessageCreateEntity("six.mp4", new File("E:\\1.mp4"));
        listTwo.add(six);

        VideoMessageCreateEntity seven = new VideoMessageCreateEntity("seven.txt", "剧情介绍：");
        listTwo.add(seven);

        content = "保护人类的。但是在一些政治角力的背后，政府中有人认为是有必要控制一下这些超级英雄的超自然能力和他们的行动了。于是，一项管控措施出台。这个措施就是要求复联按照政府的要求来行动。任务的开展、进程和结束，都要由政府主导。这个管控措施在复联中引起了极大的争议。意见最极端、最两极分化不可调和的，就是钢铁侠和美国队长之间的问题，于是，这两个同盟者之间的矛盾就此爆发了出来。而复联的“内战”也不可避免的爆发。";
        VideoMessageCreateEntity eight = new VideoMessageCreateEntity("eight.txt", content);
        listTwo.add(eight);

        content = "更多精彩内容尽在优酷：www.youku.com";
        VideoMessageCreateEntity nine = new VideoMessageCreateEntity("nine.txt", content);
        listTwo.add(nine);

        VideoMessageCreateEntity ten = new VideoMessageCreateEntity("ten.jpg", new File("E:\\1.jpg"));
        listTwo.add(ten);

        params.add(listOne);
        params.add(listTwo);
        map.put("param", JSON.toJSONString(params));

        MapSort.sortMap(SECRET_KEY, "/api/dyCheckSave", map);

        String result = HttpUtil.post("http://api.cnyoung.com.cn/api/dyCheckSave", JSON.toJSONString(map));
        System.out.println(result);
    }

    private static void templateStatus() {
        Map<String, Object> map = new HashMap<>(16);
        map.put("username", "qmy-dengqiuya");
        map.put("id", "81973");

        Long timeMillis = System.currentTimeMillis();
        map.put("timestamp", timeMillis.toString());
        MapSort.sortMap(SECRET_KEY, "/api/view", map);
        String result = HttpUtil.post("http://api.cnyoung.com.cn/api/view", JSON.toJSONString(map));
        System.out.println(JSON.parseObject(result).getJSONObject("desc"));
    }

    private static void templateList() {
        Map<String, Object> map = new HashMap<>(16);
        map.put("username", "qmy-dengqiuya");
        map.put("pageStart", "1");
        map.put("pageSize", "20");

        Long timeMillis = System.currentTimeMillis();
        map.put("timestamp", timeMillis.toString());
        MapSort.sortMap(SECRET_KEY, "/api/list", map);
        String result = HttpUtil.post("http://api.cnyoung.com.cn/api/list", JSON.toJSONString(map));
        System.out.println(JSON.parseObject(result).getJSONArray("desc"));
    }

    private static void send() {

        Map<String, Object> map = new HashMap<>(16);
        map.put("username", "qmy-dengqiuya");
        map.put("sequenceNumber", IdUtil.simpleUUID()+"-"+"81956");
        map.put("userNumber", "17681874926");
        map.put("id", "81956");
        Long timeMillis = System.currentTimeMillis();
        map.put("timestamp", timeMillis.toString());
        MapSort.sortMap(SECRET_KEY, "/api/send", map);

        String result = HttpUtil.post("http://api.cnyoung.com.cn/api/send", JSON.toJSONString(map));
        System.out.println(result);
    }
}
