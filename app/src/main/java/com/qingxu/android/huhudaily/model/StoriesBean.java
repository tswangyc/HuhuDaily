package com.qingxu.android.huhudaily.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by QingXu on 2016/9/8.
 */
public class StoriesBean implements Serializable {

    /**
     * images : ["http://pic1.zhimg.com/de407d2cd9b797108010f4eae81da3e8.jpg"]
     * type : 0
     * id : 8771619
     * ga_prefix : 090717
     * title : 知乎好问题 · 什么样的电影算是好电影？
     */
    private int type;
    private int id;
    private String ga_prefix;
    private String title;
    private List<String> images;

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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
