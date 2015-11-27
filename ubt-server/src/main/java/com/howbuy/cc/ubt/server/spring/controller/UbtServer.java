package com.howbuy.cc.ubt.server.spring.controller;

import com.howbuy.cc.ubt.server.model.UBT;
import com.howbuy.cc.ubt.server.util.IpUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xinwei.cheng on 2015/9/7.
 */
@RestController
public class UbtServer {

    private Logger logger = Logger.getLogger(this.getClass());

    private String logFileName;
    private int count = 0;

    /**
     * 记录用户行为
     *
     * @param ubt
     * @return
     */
    @RequestMapping("/log")
    public String logUbt(HttpServletRequest request, UBT ubt) throws InterruptedException {
        ubt.setIp(IpUtil.getClientIp(request));
        logger.info(ubt.toString());
        return "ok";
    }




    @PostConstruct
    public void init() throws IOException {
        logger.setAdditivity(false);
        logFileName = "d:/log/ubt.log." + count++;
        FileAppender appender = new FileAppender(new PatternLayout("%d{yyyyMMddHHmmss}%m%n")  , logFileName , true);
        appender.setName(this.getClass().getName());
        appender.activateOptions();
        logger.addAppender(appender);

        put2Hdfs();
    }


    /**
     * 记录用户行为
     * @return
     */
    public void put2Hdfs(){
        Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if(!needPushHdfs(logFileName)){
                        try {
                            Thread.sleep(6000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue;
                    }

                    String oldLogfile = logFileName;
                    updateLogFile();
                    put2Hdfs(oldLogfile);
                    removeLogFile(oldLogfile);

                    try {
                        Thread.sleep(6000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        t.setDaemon(true);
        t.start();
    }


    /**
     * 判断是否需要上传HDFS
     * @param logFile
     * @return
     */
    public boolean needPushHdfs(String logFile){
        File file = new File(logFile);
        if(file.exists()){
            Long length = file.length();
            if(length > 1024 * 10) {
                return true;
            }
        }
        return false;
    }


    /**
     * 上传完HDFS之后删除日志文件
     * @param fileName
     */
    public void removeLogFile(String fileName){
        File file = new File(fileName);
        if(file.exists()){
            if(file.isFile()){
                file.delete();
            }
        }
    }



    /**
     * 更新日志文件的文件名
     */
    public void updateLogFile(){
        FileAppender appender = (FileAppender) logger.getAppender(this.getClass().getName());
        logFileName = "d:/log/ubt.log." + count++;
        appender.setFile(logFileName);
        appender.activateOptions();
    }


    /**
     * 定时发送到HDFS
     * @param fileName
     */
    public void put2Hdfs(final String fileName){

        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.get(new URI("hdfs://192.168.221.21:9000") , new Configuration() , "cim");
            fileSystem.copyFromLocalFile(new Path(fileName), new Path("/home/cim"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }finally {
            if (fileSystem != null) {
                try {
                    fileSystem.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}