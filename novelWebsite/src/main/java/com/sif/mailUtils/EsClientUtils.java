package com.sif.mailUtils;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

/**
 * @Program: NovelWebOnBigDataStructure
 * @Package: com.sif.mailUtils
 * @Description: es 客户端工具类
 * Created by felahong on 2020/2/25 16:26-
 */

public class EsClientUtils {

    private static Client client;
    private static final String novelIndex = "novel_all";
    private static final String novelDetailIndex = "novel_detail";

    /**
     * @Description 获取es 客户端
     * @Author felahong 2020/2/25 16:33
     **/
    public static Client getClient(){

        if(client != null){
            return client;
        }

        Settings settings = Settings.builder()
                .put("cluster.name", "esapplication")   //设置ES实例的名称
                .build();

        try{
            // es 向外提供服务的端口是9300
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("hadoop001"), 9300));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return client;
    }

    public static String getNovelIndex() {
        return novelIndex;
    }

    public static String getNovelDetailIndex() {
        return novelDetailIndex;
    }





}