package com.qingxu.android.huhudaily.model;

/**
 * Created by QingXu on 2016/9/7.
 */
public class SplashBean {
    /**
     * text : Johannes Andersson
     * img : https://pic1.zhimg.com/v2-2c9774a1c31be8033125427da581e66c.jpg
     */

    private String text;
    private String img;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "SplashBean{" +
                "text='" + text + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
