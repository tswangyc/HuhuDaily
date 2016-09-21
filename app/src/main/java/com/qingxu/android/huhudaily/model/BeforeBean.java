package com.qingxu.android.huhudaily.model;

import java.util.List;

/**
 * Created by QingXu on 2016/9/10.
 */
public class BeforeBean {


    /**
     * date : 20160909
     * stories : [{"images":["http://pic3.zhimg.com/58982220dec9c4d3b254f44b302c580a.jpg"],"type":0,"id":8603838,"ga_prefix":"090922","title":"小事 · 谢谢你懂我"},{"images":["http://pic3.zhimg.com/8af3b0552a1cbb8a22fe87e38346d242.jpg"],"type":0,"id":8779353,"ga_prefix":"090921","title":"短、节奏轻松、还如此饱满反转，好的这周末就看它了"},{"images":["http://pic3.zhimg.com/1f6f3e06d334475dbc4c503678b2b65a.jpg"],"type":0,"id":8779833,"ga_prefix":"090920","title":"以家庭为单位来收税，这事儿为什么不能成？"},{"images":["http://pic3.zhimg.com/12730f2ed82b5b899ca2594113a9309e.jpg"],"type":0,"id":8779765,"ga_prefix":"090919","title":"多一根「拐杖」，登山就会轻松很多"},{"images":["http://pic3.zhimg.com/c9199912c5cb6d3a6d5fb611db2e0566.jpg"],"type":0,"id":8778503,"ga_prefix":"090918","title":"「每次路过这种建筑门口的长台阶，我就想坐下歇会儿」"},{"images":["http://pic4.zhimg.com/1880ebe956d74c53a778b63e37aa61bf.jpg"],"type":0,"id":8779021,"ga_prefix":"090917","title":"知乎好问题 · 人为什么要睡觉？"},{"images":["http://pic1.zhimg.com/9c8abed7375c7ff3def2edd23b4e8a14.jpg"],"type":0,"id":8779335,"ga_prefix":"090916","title":"终极目标不是赚钱，「社会企业」是一种怎样的存在？"},{"images":["http://pic4.zhimg.com/46e3d3258c1467797a65b0d58d360347.jpg"],"type":0,"id":8779052,"ga_prefix":"090915","title":"什么样的应届生值得招？分享几条通用的经验"},{"images":["http://pic1.zhimg.com/3a5a3d893ed26028b877fbf180c02c28.jpg"],"type":0,"id":8778611,"ga_prefix":"090914","title":"为什么苹果要把耳机接口「设计」没？"},{"images":["http://pic1.zhimg.com/c67815a1f83a039fdc8e9bcec62eabf4.jpg"],"type":0,"id":8769563,"ga_prefix":"090913","title":"外国人名字又长又复杂，但我在其中找到了很多乐趣"},{"images":["http://pic2.zhimg.com/8039a0c0565638f4f297fea5ad0f0031.jpg"],"type":0,"id":8776951,"ga_prefix":"090912","title":"大误 · 七个矮人奉命，囚禁白雪公主"},{"images":["http://pic3.zhimg.com/72abae7c9e4c9dc6460bd3b29703111e.jpg"],"type":0,"id":8778143,"ga_prefix":"090911","title":"金融行业中，常用的 Excel 分析操作技巧有哪些？"},{"images":["http://pic4.zhimg.com/15371d7a0dda53319a05ef1a68cf4d47.jpg"],"type":0,"id":8767983,"ga_prefix":"090910","title":"人才流失与经济落后，地方发展的死循环该怎么解？"},{"images":["http://pic3.zhimg.com/97a32b53a3ef32641956a885f6baa7be.jpg"],"type":0,"id":8776024,"ga_prefix":"090909","title":"「避孕套」只能避孕，「安全套」才保安全？"},{"images":["http://pic4.zhimg.com/519be3aa3ae3d1964b12086a8d21e507.jpg"],"type":0,"id":8770955,"ga_prefix":"090908","title":"大家都听成了「原来你是我的猪大哥」，科学是这么解释的"},{"images":["http://pic3.zhimg.com/0f0d411799d568cccae28035f8ad7846.jpg"],"type":0,"id":8767268,"ga_prefix":"090907","title":"都是收税，间接税和直接税的影响差别不是一般的大"},{"images":["http://pic4.zhimg.com/08a9ae2aaa4b9cdd7bfbc8a6442b6377.jpg"],"type":0,"id":8777165,"ga_prefix":"090907","title":"大脑为什么非要分「两瓣」？"},{"images":["http://pic3.zhimg.com/1e8f378b6dc877e2b80af7d40fb392aa.jpg"],"type":0,"id":8775387,"ga_prefix":"090907","title":"仔细看，苹果产品盒子上的字跟以前有些不一样"},{"images":["http://pic3.zhimg.com/e410f76423921eacdcdca65839f3deea.jpg"],"type":0,"id":8777082,"ga_prefix":"090907","title":"读读日报 24 小时热门 TOP 5 · 苹果发布会让你更质疑库克？"},{"images":["http://pic3.zhimg.com/48e374edfba348ad15ae7d4cb91a9302.jpg"],"type":0,"id":8775162,"ga_prefix":"090906","title":"瞎扯 · 如何正确地吐槽"},{"images":["http://pic3.zhimg.com/cd07e603af107b93d929c1c407c9c332.jpg"],"type":0,"id":8775171,"ga_prefix":"090906","title":"这里是广告 · 在这里，听见未来"}]
     */

    private String date;
    /**
     * images : ["http://pic3.zhimg.com/58982220dec9c4d3b254f44b302c580a.jpg"]
     * type : 0
     * id : 8603838
     * ga_prefix : 090922
     * title : 小事 · 谢谢你懂我
     */

    private List<StoriesBean> stories;

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
}
