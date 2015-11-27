//package com.howbuy.cc.ubt.server.kafka;
//
//import kafka.producer.Partitioner;
//import kafka.utils.VerifiableProperties;
//
///**
// * Created by xinwei.cheng on 2015/11/23.
// */
//public class UbtPartition implements Partitioner {
//
//    public UbtPartition (VerifiableProperties props) {
//
//    }
//
//    public int partition(Object key, int a_numPartitions) {
//        int partition = 0;
//        String stringKey = (String) key;
//        int offset = stringKey.lastIndexOf('.');
//        if (offset > 0) {
//            partition = Integer.parseInt( stringKey.substring(offset+1)) % a_numPartitions;
//        }
//        return partition;
//    }
//
//}
