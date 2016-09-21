package com.qingxu.android.huhudaily.model;

import java.io.Serializable;

/**
 * Created by QingXu on 2016/9/8.
 */
public class TopStoriesBean implements Serializable {
    /**
     * image : http://pic4.zhimg.com/7d5803ebe51575215f66d7515127df0f.jpg
     * type : 0
     * id : 8771619
     * ga_prefix : 090717
     * title : 知乎好问题 · 什么样的电影算是好电影？
     */
    private String image;
    private int type;
    private int id;
    private String ga_prefix;
    private String title;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
