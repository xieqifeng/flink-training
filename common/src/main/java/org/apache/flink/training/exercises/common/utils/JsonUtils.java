package org.apache.flink.training.exercises.common.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.util.*;

public class JsonUtils {

    public static void main(String[] args) {
        Properties properties = AvalonConfig.getProperties("mongoSchema.properties");
        System.out.println(properties.keySet());
        Object o = properties.get("mongo.tr_task.device_image_log");
        System.out.println(o.toString());
        System.out.println(o.toString().substring(660));
        String res = generateJsonType(o.toString());
        System.out.println(res);
        // new FileOutputStream("../../test/",res)
    }

    private static String generateJsonType(String jsonContent) {
        JSONObject obj = new JSONObject(jsonContent);
        Iterator<String> keys = obj.keys();
        JSONArray ja = new JSONArray();
        StringBuilder sb = new StringBuilder();
        String type;
        while (keys.hasNext()) {
            String next = keys.next();
            Object o = obj.get(next);
            JSONObject jo = new JSONObject();
            jo.put("name", next);
            if (o instanceof String) {
                if (DateUtil.parseDate(o.toString())) {
                    type = "date";
                } else {
                    type = "string";
                }
            } else if (o instanceof Integer) {
                type = "int";
            } else if (o instanceof Double) {
                type = "double";
            } else if (o instanceof Object) {
                type = "string";
            } else {
                type = "other";
            }
            jo.put("type", type);
            ja.put(jo);
            sb.append(next).append(" ").append(type).append(",");
        }
        System.out.println(sb.toString());
        return ja.toString();
    }

    // /**
    // * 将JSON数据格式化并保存到文件中
    // *
    // * @param jsonData 需要输出的json数
    // * @param filePath 输出的文件地址
    // * @return
    // */
    // // public static boolean createJsonFile(Object jsonData, String filePath) {
    // String content = JSON.toJSONString(jsonData, SerializerFeature.PrettyFormat,
    // SerializerFeature.WriteMapNullValue,
    // SerializerFeature.WriteDateUseDateFormat);
    // // 标记文件生成是否成功
    // boolean flag = true;
    // // 生成json格式文件
    // try {
    // // 保证创建一个新文件
    // File file = new File(filePath);
    // if (!file.getParentFile().exists()) { // 如果父目录不存在，创建父目录
    // file.getParentFile().mkdirs();
    // }
    // if (file.exists()) { // 如果已存在,删除旧文件
    // file.delete();
    // }
    // file.createNewFile();
    // // 将格式化后的字符串写入文件
    // Writer write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
    // write.write(content);
    // write.flush();
    // write.close();
    // } catch (Exception e) {
    // flag = false;
    // e.printStackTrace();
    // }
    // return flag;
    // }

}
