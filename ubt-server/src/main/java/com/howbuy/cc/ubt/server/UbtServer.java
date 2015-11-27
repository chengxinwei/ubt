//package com.howbuy.cc.ubt.server;
//
//import com.howbuy.cc.ubt.server.model.UBT;
//import com.howbuy.cc.ubt.server.util.IpUtil;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.log4j.Logger;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.MediaType;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.atomic.AtomicInteger;
//
///**
// * Created by xinwei.cheng on 2015/11/17.
// */
//@Path("/ubt")
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
//public class UbtServer {
//
////    private static ArrayBlockingQueue<String> bakUbtQueue = new ArrayBlockingQueue<String>(50000 , true);
//
////    public UbtServer(){
////        initSendThread();
////    }
//
////    private static final AtomicInteger count = new AtomicInteger(0);
//
//    Logger logger = Logger.getLogger(this.getClass());
//
//    /**
//     * 记录用户行为
//     * @param ubt
//     * @return
//     */
//    @POST
//    @Path("/log")
//    public String logUbt(@Context HttpServletRequest request , UBT ubt) throws InterruptedException {
////        count.incrementAndGet();
//        ubt.setIp(IpUtil.getClientIp(request));
////        bakUbtQueue.offer(ubt.toString(), 100, TimeUnit.MILLISECONDS);
//        logger.info(ubt.toString());
//        return "ok";
//    }
//
//
//    /**
//     * 启动发送线程，把list中的数据发送到HDFS中
//     */
////    public void initSendThread(){
////        Thread t = new Thread(){
////            public void run(){
////                int i = 0;
////                while(true){
////                    try {
////                        List<String> sendList = new ArrayList<String>();
////                        while(true){
////                            String ubtStr = bakUbtQueue.poll(1L , TimeUnit.MILLISECONDS);
////                            if(ubtStr == null){
////                                if(sendList.size() > 0) {
////                                    i = i + sendList.size();
////                                    send(sendList);
////                                }
////                                break;
////                            }
////                            sendList.add(ubtStr);
////                            if(sendList.size() > 100){
////                                i = i + sendList.size();
////                                send(sendList);
////                                break;
////                            }
////                        }
////                        System.out.println(i + ":" + count.get());
////                        Thread.sleep(5000);
////                    } catch (Throwable e) {
////                        e.printStackTrace();
////                    }
////                }
////            }
////        };
////
////        t.setDaemon(true);
////        t.start();
////    }
//
//
//    /**
//     * 把数据发送到 HDFS 中
//     * @param sendList
//     */
////    public void send(List<String> sendList){
////        System.out.println(StringUtils.join(sendList.toArray(new String[sendList.size()]) , "\n"));
////        try {
////            Thread.sleep(100);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
////        sendList.clear();
////    }
//
//}