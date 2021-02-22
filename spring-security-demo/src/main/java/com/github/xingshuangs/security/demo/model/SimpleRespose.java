package com.github.xingshuangs.security.demo.model;


/**
 * @author xingshuang
 * @date 2019/9/18
 */
public class SimpleRespose {

    private Object content;

    public SimpleRespose(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
