package com.howbuy.cc.ubt.server.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.net.URI;

/**
 * Created by xinwei.cheng on 2015/11/18.
 */
public final class HdfsClient {

    private FileSystem fileSystem;

    private volatile static  HdfsClient hdfsClient = null;

    private HdfsClient(){}

    private void init(){

        try {
            fileSystem =  FileSystem.get(new URI("hdfs://192.168.221.21:9000") , new Configuration() , "cim");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HdfsClient getInstance(){
        if(hdfsClient == null){
            synchronized (HdfsClient.class){
                if(hdfsClient == null){
                    hdfsClient  = new HdfsClient();
                    hdfsClient.init();
                }
            }
        }
        return hdfsClient;
    }

    public FileSystem getFileSystem(){
        return fileSystem;
    }

}
