package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        String config = "河南:test;湖北:213;默认:reject";
        String[] configStr = config.split(";");
        System.out.println(configStr.length);
        List<String> idList = new ArrayList<>();
        StringBuffer reteList = new StringBuffer();
        for (int i = 0; i < configStr.length; i++) {
            String channelName = configStr[i].split(":")[1];
            idList.add(channelName);
            reteList.append(configStr[i].split(":")[0] + ",");
        }
        System.out.println(idList);
        System.out.println(reteList.substring(0, reteList.lastIndexOf(",")));

        String[] rateStr = reteList.substring(0, reteList.lastIndexOf(",")).split(",");
        List<String> cityList = new ArrayList<String>(Arrays.asList(rateStr));
        System.out.println("cityList" + cityList);
        int index = cityList.size() - 1;
        System.out.println("index" + index);
        if (cityList.contains("河南")) {
            index = cityList.indexOf("湖北");
        }
        System.out.println("index" + index);

    }


}
