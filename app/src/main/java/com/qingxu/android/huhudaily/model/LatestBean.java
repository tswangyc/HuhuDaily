package com.qingxu.android.huhudaily.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by QingXu on 2016/9/7.
 */
public class LatestBean implements Serializable {

    /**
     * date : 20160907
     * stories : [{"images":["http://pic1.zhimg.com/de407d2cd9b797108010f4eae81da3e8.jpg"],"type":0,"id":8771619,"ga_prefix":"090717","title":"知乎好问题 · 什么样的电影算是好电影？"},{"images":["http://pic4.zhimg.com/ba3595cfaa07afde7716179a7984a13f.jpg"],"type":0,"id":8764921,"ga_prefix":"090716","title":"学法律或法学给你带来了什么好处？"},{"images":["http://pic1.zhimg.com/6015c0b7e9c5269fd292d88e64d2e8c8.jpg"],"type":0,"id":8742994,"ga_prefix":"090715","title":"全家人围着宝宝转，带孩子何必搞得这么累"},{"images":["http://pic2.zhimg.com/9586f9d386c621880fd583ed3b3fb371.jpg"],"type":0,"id":8771380,"ga_prefix":"090714","title":"让病人画一个钟，医生可以确诊什么？"},{"images":["http://pic1.zhimg.com/da8cf045451d0498c55230bce9934428.jpg"],"type":0,"id":8770355,"ga_prefix":"090713","title":"全球最赚钱联赛即将开幕，激烈紧张的橄榄球又一次让美国人疯狂"},{"images":["http://pic4.zhimg.com/3a0849d4711e41c8b083049ecff4f5e7.jpg"],"type":0,"id":8770258,"ga_prefix":"090712","title":"大误 · 瞬移能力者的赚钱方法"},{"images":["http://pic1.zhimg.com/a3a7e3c6dfdea24f00216ab5478e9ab8.jpg"],"type":0,"id":8770760,"ga_prefix":"090711","title":"「咖啡糖」为什么那么黄？"},{"images":["http://pic2.zhimg.com/a9db5ead941dd07d5c87ccc4fbfe2131.jpg"],"type":0,"id":8765702,"ga_prefix":"090710","title":"一半的广告费还是白花了，互联网也改变不了这局面"},{"images":["http://pic1.zhimg.com/39db48682d073485567e92708c8d5308.jpg"],"type":0,"id":8769055,"ga_prefix":"090709","title":"可随性洒脱，可缠绵交错，长大后，我懂了她的情味"},{"images":["http://pic2.zhimg.com/7d164a0f96ef8fa49dc80bf0cf7e2ca9.jpg"],"type":0,"id":8764866,"ga_prefix":"090708","title":"「预计本市菜价下个月要上涨 7.35%」，经济学家还算得挺准"},{"images":["http://pic4.zhimg.com/7344f69217ee6a6355bffb0217bf8f57.jpg"],"type":0,"id":8769642,"ga_prefix":"090707","title":"天文学家是怎么选择「这颗星星就由我来守护」的？"},{"images":["http://pic4.zhimg.com/bb592b35e999607554c5b16d8dd1887f.jpg"],"type":0,"id":8769340,"ga_prefix":"090707","title":"斯坦福发布了 AI 百年报告，未来人类生活可能如此改变"},{"images":["http://pic2.zhimg.com/794ece04ccf75588342b4c5aa6d00721.jpg"],"type":0,"id":8767774,"ga_prefix":"090707","title":"为什么肯德基麦当劳都在忙着「卖身」？"},{"images":["http://pic4.zhimg.com/fb40514d7d7b176aaad856971f23138f.jpg"],"type":0,"id":8769552,"ga_prefix":"090707","title":"读读日报 24 小时热门 TOP 5 · 「白银案」的 13 条新信息"},{"images":["http://pic4.zhimg.com/29cacc760c76bc2e28bbd19bc3a4c67f.jpg"],"type":0,"id":8765676,"ga_prefix":"090706","title":"瞎扯 · 如何正确地吐槽"},{"images":["http://pic2.zhimg.com/23538b878f7852a3ed94b0c4eca61ab1.jpg"],"type":0,"id":8768051,"ga_prefix":"090706","title":"这里是广告 · 25 岁，你的人生才刚刚开始"}]
     * top_stories : [{"image":"http://pic4.zhimg.com/7d5803ebe51575215f66d7515127df0f.jpg","type":0,"id":8771619,"ga_prefix":"090717","title":"知乎好问题 · 什么样的电影算是好电影？"},{"image":"http://pic1.zhimg.com/a1d243a1c2a723a131e1952337c1fc04.jpg","type":0,"id":8770355,"ga_prefix":"090713","title":"全球最赚钱联赛即将开幕，激烈紧张的橄榄球又一次让美国人疯狂"},{"image":"http://pic2.zhimg.com/dd108396f7a52f915d2a3f21614cb681.jpg","type":0,"id":8769340,"ga_prefix":"090707","title":"斯坦福发布了 AI 百年报告，未来人类生活可能如此改变"},{"image":"http://pic4.zhimg.com/cf960142d4d0e88dcd1419366921f5d7.jpg","type":0,"id":8767774,"ga_prefix":"090707","title":"为什么肯德基麦当劳都在忙着「卖身」？"},{"image":"http://pic4.zhimg.com/a8bede507a1cb2b971b3786e4d42fec3.jpg","type":0,"id":8769552,"ga_prefix":"090707","title":"读读日报 24 小时热门 TOP 5 · 「白银案」的 13 条新信息"}]
     */

    private String date;

    private List<StoriesBean> stories;

    private List<TopStoriesBean> top_stories;

    @Override
    public String toString() {
        return "LatestBean{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                ", top_stories=" + top_stories +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

}
