package com.howbuy.cc.ubt.server.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by xinwei.cheng on 2015/11/18.
 */
public class HdfsService {

    Logger logger = Logger.getLogger(this.getClass());


    public void appendLogFile(String log) throws IOException, URISyntaxException, InterruptedException {
        FileSystem fileSystem =  FileSystem.get(new URI("hdfs://192.168.221.21:9000") , new Configuration() , "cim");
        FSDataOutputStream fsDataOutputStream1 = fileSystem.append(new Path(""));
//        fsDataOutputStream.write("");

    }


    @Test
    public void put() throws IOException, URISyntaxException, InterruptedException {
//        String day = DateTime.now().toString("yyyyMMdd");
        Path path = new Path("ubt");
        FileSystem fileSystem =  FileSystem.get(new URI("hdfs://192.168.221.21:9000") , new Configuration() , "cim");
        fileSystem.copyFromLocalFile(new Path("") , path);
    }


    @Test
    public void testLs() throws URISyntaxException, IOException, InterruptedException {
        FileSystem fs =  FileSystem.get(new URI("hdfs://192.168.221.21:9000") , new Configuration() , "cim");
        try {
            RemoteIterator<LocatedFileStatus> rmIterator = fs.listLocatedStatus(new Path("/"));
            while (rmIterator.hasNext()) {
                Path path = rmIterator.next().getPath();
                if(fs.isDirectory(path)){
                    logger.info("-----------DirectoryName: "+path.getName());
                }
                else if(fs.isFile(path)){
                    logger.info("-----------FileName: "+path.getName());
                }
            }
        }catch (IOException e) {
            logger.error("list fileSysetm object stream.:" , e);
            new RuntimeException(e);
        }
    }


}
