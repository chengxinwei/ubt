package com.howbuy.cc.ubt.mr;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.net.URLDecoder;

/**
 * Created by xinwei.cheng on 2015/10/8.
 */
public class UbtMapper extends Mapper<LongWritable, Text, Text , LongWritable> {

    private final Integer full_x = 1280;
    private final Integer full_y = 960;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] words = line.split("\t");
        if(words.length < 13){
            System.out.println("不合法的信息：" + value.toString());
            return;
        }
        String x = words[3];
        String y = words[4];
        String body_x = words[5];
        String body_y = words[6];

        if(StringUtils.isEmpty(x)|| StringUtils.isEmpty(x) ||
                StringUtils.isEmpty(body_x) || StringUtils.isEmpty(body_y)){
            System.out.println("x:"+ x +",y:" + y + ",body_x:" + body_x + ",body_y:" + body_y );
            return;
        }

        String url = URLDecoder.decode(words[11]);
        Long resultx = Math.round(Integer.parseInt(x) / Double.parseDouble(body_x) * full_x);
        Long resulty = Math.round(Integer.parseInt(y) / Double.parseDouble(body_y) * full_y);

        String[] resultAry = new String[3];
        resultAry[0] = url;
        resultAry[1] = resultx.toString();
        resultAry[2] = resulty.toString();
        context.write(new Text(StringUtils.join(resultAry , " ")) , new LongWritable(1));
    }


    public static void main(String[] args) {
        String str = "20151125160647			1144	298	1366	768		192.168.121.199";

    }
}
