//package com.howbuy.cc.ubt.server;
//
//import io.dropwizard.Application;
//import io.dropwizard.setup.Bootstrap;
//import io.dropwizard.setup.Environment;
//
///**
// * Created by xinwei.cheng on 2015/11/17.
// */
//public class UbtApplication extends Application<UbtConfiguration> {
//
//    public static void main(String[] args) throws Exception {
//        new UbtApplication().run("server" , "D:\\workspace\\chengxinweiProject\\basic\\basic-dropwizard\\src\\main\\java\\com\\howbuy\\cc\\basic\\dropwizard\\hello-world.yml");
//    }
//
//    @Override
//    public String getName() {
//        return "hello-world";
//    }
//
//    @Override
//    public void initialize(Bootstrap<UbtConfiguration> bootstrap) {
//        // nothing to do yet
//    }
//
//    @Override
//    public void run(UbtConfiguration configuration,
//                    Environment environment) {
//        environment.jersey().register(new UbtServer());
//    }
//
//}
