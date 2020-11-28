package com.dlw.demo.controller;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.DeleteRowsEventData;
import com.github.shyiko.mysql.binlog.event.EventData;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;
import com.github.shyiko.mysql.binlog.event.WriteRowsEventData;
import com.github.shyiko.mysql.binlog.event.deserialization.EventDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 测试binlog
 * @author diaoliwei
 * @date 2020/11/25 15:00
 */
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    /**
     * binlog 测试
     * @param args
     * @Author: diaoliwei
     * @return: void
     */
    public static void main(String[] args) {
        BinaryLogClient client = new BinaryLogClient("127.0.0.1", 3306, "root", "123456"); // 数据库地址、端口号、用户名、密码
        EventDeserializer eventDeserializer = new EventDeserializer();
        eventDeserializer.setCompatibilityMode(
                EventDeserializer.CompatibilityMode.DATE_AND_TIME_AS_LONG,
                EventDeserializer.CompatibilityMode.CHAR_AND_BINARY_AS_BYTE_ARRAY
        );

        client.setEventDeserializer(eventDeserializer);
        client.registerEventListener(event -> {
            EventData data = event.getData();
            event.getHeader();
            if (data instanceof UpdateRowsEventData) {
                log.warn("update==========>>>>>");
                List<Map.Entry<Serializable[], Serializable[]>> list = ((UpdateRowsEventData) data).getRows();
                updateFormat(list);
            } else if (data instanceof WriteRowsEventData) {
                log.warn("insert==========>>>>>");
                List<Serializable[]> list = ((WriteRowsEventData) data).getRows();
                deleteOrInsertFormat(list);
            } else if (data instanceof DeleteRowsEventData) {
                log.warn("delete==========>>>>>");
                List<Serializable[]> list = ((DeleteRowsEventData) data).getRows();
                deleteOrInsertFormat(list);
            }
        });
        try {
            client.connect();
        } catch (IOException e) {
            log.error("====TestController=====main()=====error:{}", e);
        }

    }

    private static void deleteOrInsertFormat(List<Serializable[]> list){
        Iterator var2 = list.iterator();
        while (var2.hasNext()){
            Object[] objects = (Object[])var2.next();
            List<String> beforeList = new ArrayList<>();
            for(Object obj : objects){
                if(obj instanceof Integer){
                    beforeList.add(obj+"");
                }else if(obj instanceof String){
                    beforeList.add(obj+"");
                }else if(obj instanceof byte[]){
                    beforeList.add(new String((byte[]) obj));
                }else {
                    beforeList.add(obj+"");
                }
            }
            log.info("====insert or delete:{}", beforeList);
        }
    }

    private static void updateFormat(List<Map.Entry<Serializable[], Serializable[]>> list){
        Iterator var2 = list.iterator();
        while(var2.hasNext()) {
            log.info("-----rows-----");
            Map.Entry row = (Map.Entry)var2.next();
            Object[] beforeData = (Object[])row.getKey();
            List<String> beforeList = new ArrayList<>();
            for(Object obj : beforeData){
                if(obj instanceof Integer){
                    beforeList.add(obj+"");
                }else if(obj instanceof Double){
                    beforeList.add(obj+"");
                }else if(obj instanceof Long){
                    beforeList.add(obj+"");
                }else if(obj instanceof byte[]){
                    beforeList.add(new String((byte[]) obj));
                }else {
                    beforeList.add(obj+"");
                }
            }
            log.info("before-data:{}", beforeList);
            Object[] afterData =(Object[])row.getValue();
            List<String> afterList = new ArrayList<>();
            for(Object obj : afterData){
                if(obj instanceof Integer){
                    afterList.add(obj + "");
                }else if(obj instanceof Double){
                    beforeList.add(obj + "");
                }else if(obj instanceof Long){
                    beforeList.add(obj + "");
                }else if(obj instanceof byte[]){
                    afterList.add(new String((byte[]) obj));
                }else {
                    afterList.add(obj + "");
                }
            }
            log.info("after-data:{}", afterList);
        }
    }

}
