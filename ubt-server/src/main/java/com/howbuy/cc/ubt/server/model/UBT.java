package com.howbuy.cc.ubt.server.model;

import org.apache.commons.lang3.StringUtils;

import java.net.URLDecoder;

/**
 * Created by xinwei.cheng on 2015/11/18.
 */
public class UBT {

    private Integer x;
    private Integer y;
    private Integer screen_x;
    private Integer screen_y;
    private Integer body_x;
    private Integer body_y;
    private String url;
    private String href;
    private String type;
    private String id;
    private String name;
    private String text;
    private String ip;

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getScreen_x() {
        return screen_x;
    }

    public void setScreen_x(Integer screen_x) {
        this.screen_x = screen_x;
    }

    public Integer getScreen_y() {
        return screen_y;
    }

    public void setScreen_y(Integer screen_y) {
        this.screen_y = screen_y;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getBody_x() {
        return body_x;
    }

    public void setBody_x(Integer body_x) {
        this.body_x = body_x;
    }

    public Integer getBody_y() {
        return body_y;
    }

    public void setBody_y(Integer body_y) {
        this.body_y = body_y;
    }

    @Override
    public String toString(){
        String[] ary = new String[13];
        ary[0] = id;
        ary[1] = name;
        ary[2] = String.valueOf(x);
        ary[3] = String.valueOf(y);
        ary[4] = String.valueOf(screen_x);
        ary[5] = String.valueOf(screen_y);
        ary[6] = String.valueOf(body_x);
        ary[7] = String.valueOf(body_y);
        ary[8] = type;
        ary[9] = ip;
        ary[10] = url;
        ary[11] = href;
        ary[12] = text.replace("\n" , "");
        return "\t" + StringUtils.join(ary , "\t");

    }
}
