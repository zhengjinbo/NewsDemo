package com.zhengjinbo.newsdemo.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhengjinbo on 17/7/31.
 */

public class TweetListBean implements Serializable {

    /**
     * tweetlist : [{"author":"bouyeijiang","id":14822255,"portrait":"https://static.oschina.net/uploads/user/438/877346_50.jpeg?t=1501390488000","authorid":877346,"body":".net高并发socket通信库升级了，支持同步连接和接收 \n<a href=\"https://www.oschina.net/p/Bouyei-NetProviderFactory\" target=\"_blank\" rel=\"nofollow\">https://www.oschina.net/p/Bouyei-NetProviderFactory<\/a>","pubDate":"2017-07-31 20:31:42","commentCount":0},{"imgBig":"https://static.oschina.net/uploads/space/2017/0731/203151_OQ7m_2720166.jpg","author":"局长","id":14822252,"portrait":"https://static.oschina.net/uploads/user/1360/2720166_50.jpg?t=1470892376000","authorid":2720166,"body":"又是你老婆 哪个都是你老婆","pubDate":"2017-07-31 20:31:00","imgSmall":"https://static.oschina.net/uploads/space/2017/0731/203151_OQ7m_2720166_thumb.jpg","commentCount":1},{"author":"OSC_dTcEBZ","id":14822245,"portrait":"https://static.oschina.net/uploads/user/1808/3616381_50.jpg?t=1501486868000","authorid":3616381,"body":"有没有大神远程教我建网站的，拜托了 \n<img src=\"http://www.oschina.net/js/ke/plugins/emoticons/5.gif\" alt=\"5\">","pubDate":"2017-07-31 20:29:34","commentCount":0},{"author":"罗马的王","id":14822097,"portrait":"https://static.oschina.net/uploads/user/407/815843_50.jpg?t=1415601139000","authorid":815843,"body":"一路上疾风骤雨积水成河，家这边马路却都是干的。","pubDate":"2017-07-31 20:14:57","commentCount":0},{"author":"clover灬","id":14822019,"portrait":"https://static.oschina.net/uploads/user/892/1785591_50.png?t=1409755908000","authorid":1785591,"body":"累瞎了，不想加班","pubDate":"2017-07-31 20:03:34","commentCount":2},{"author":"开源中国首席机器人","id":14821925,"portrait":"https://static.oschina.net/uploads/user/1164/2329905_50.jpg?t=1494853125000","authorid":2329905,"body":"人生如歌，悦耳动听，音律美妙，婉转悠扬，享受人生乐趣，品味喜怒哀乐，诗情画意，重温经典人生，尘缘酒歌，承载喜怒哀乐。","pubDate":"2017-07-31 19:59:16","commentCount":0},{"imgBig":"https://static.oschina.net/uploads/space/2017/0731/195706_9QH5_2894582.jpg,2017/0731/195709_c9yR_2894582.png","author":"两味真火","id":14821770,"portrait":"https://static.oschina.net/uploads/user/1447/2894582_50.jpg?t=1472402396000","authorid":2894582,"body":"汤姆猫和汉克狗","pubDate":"2017-07-31 19:56:19","imgSmall":"https://static.oschina.net/uploads/space/2017/0731/195706_9QH5_2894582_thumb.jpg,2017/0731/195709_c9yR_2894582_thumb.png","commentCount":1},{"author":"OSC_bUSRGq","id":14821520,"portrait":"https://static.oschina.net/uploads/user/1608/3216557_50.jpg?t=1484054106000","authorid":3216557,"body":"一搞算法就心情不好","pubDate":"2017-07-31 19:49:19","commentCount":0},{"author":"瞎折腾","id":14821381,"portrait":"https://static.oschina.net/uploads/user/407/815037_50.jpg?t=1354087796000","authorid":815037,"body":"四年了，第一次跳槽，好紧张 \n<img src=\"http://www.oschina.net/js/ke/plugins/emoticons/3.gif\" alt=\"3\">","pubDate":"2017-07-31 19:46:50","commentCount":1},{"author":"sellsword","id":14821322,"portrait":"https://static.oschina.net/uploads/user/1317/2634189_50.jpg?t=1494527543000","authorid":2634189,"body":"大神们，向老板提涨工资，是涨1/2合适还是1/3合适？","pubDate":"2017-07-31 19:45:38","commentCount":9},{"author":"奔跑的potato","id":14821118,"portrait":"https://static.oschina.net/uploads/user/1640/3281962_50.jpeg?t=1498398299000","authorid":3281962,"body":"假如有一批人和你一起进入某个公司，公司全是mac配置，工作环境也很不错，提供食宿，有空调，洗衣机，热水，2人一间卧室。唯一缺点就是试用期工资低，只有3500。一个星期之后，陆续有人离开，而此时的你非常在状态，而且还相信不久的将来你就会被提起来，当你看到旁边的电脑桌渐渐空荡起来，你有什么想法？[惊讶]","pubDate":"2017-07-31 19:40:39","commentCount":17},{"author":"开源中国帅哥协会会长","id":14821071,"portrait":"https://static.oschina.net/uploads/user/1166/2332983_50.JPG?t=1429262447000","authorid":2332983,"body":"幸福为什么还没来敲门？ 大概是因为你家住的偏吧！","pubDate":"2017-07-31 19:39:35","commentCount":0},{"author":"马晓倩osc","id":14821017,"portrait":"https://static.oschina.net/uploads/user/1160/2321633_50.jpg?t=1425351601000","authorid":2321633,"body":"一大群损友。。。","pubDate":"2017-07-31 19:38:32","commentCount":0},{"author":"jessiceorg","id":14820907,"portrait":"https://static.oschina.net/uploads/user/1336/2672661_50.jpeg?t=1483594202000","authorid":2672661,"body":"为什么上班？不想上班 \n<img src=\"http://www.oschina.net/js/ke/plugins/emoticons/1.gif\" alt=\"1\">","pubDate":"2017-07-31 19:36:02","commentCount":3},{"author":"猿厅长","id":14820845,"portrait":"https://static.oschina.net/uploads/user/1767/3535604_50.jpeg?t=1501498678000","authorid":3535604,"body":"VA.02017","pubDate":"2017-07-31 19:33:04","commentCount":0},{"imgBig":"https://static.oschina.net/uploads/space/2017/0731/192536_HYK8_3254435.jpg","author":"OSC_RBYWWD","id":14820791,"portrait":"https://static.oschina.net/uploads/user/1627/3254435_50.jpg?t=1486448313000","authorid":3254435,"body":"谁看过这本书","pubDate":"2017-07-31 19:24:45","imgSmall":"https://static.oschina.net/uploads/space/2017/0731/192536_HYK8_3254435_thumb.jpg","commentCount":6},{"author":"林峰","id":14820790,"portrait":"https://static.oschina.net/uploads/user/19/38679_50.jpg?t=1394346179000","authorid":38679,"body":"台风 台风 台风","pubDate":"2017-07-31 19:24:38","commentCount":1},{"author":"karol-Mary","id":14820764,"portrait":"https://static.oschina.net/uploads/user/1792/3585812_50.jpg?t=1499248947000","authorid":3585812,"body":"voacap","pubDate":"2017-07-31 19:21:55","commentCount":1},{"imgBig":"https://static.oschina.net/uploads/space/2017/0731/192118_t3FB_2702053.png","author":"penny-osc","id":14820752,"portrait":"https://static.oschina.net/uploads/user/1351/2702053_50.jpg?t=1495243836000","authorid":2702053,"body":"要下雨啦","pubDate":"2017-07-31 19:20:28","imgSmall":"https://static.oschina.net/uploads/space/2017/0731/192118_t3FB_2702053_thumb.png","commentCount":0},{"author":"开源中国首席屌炸天","id":14820724,"portrait":"https://static.oschina.net/uploads/user/1708/3417180_50.jpeg?t=1491813959000","authorid":3417180,"body":"今天是2017年7月31日，周一，深圳：多云，西南风4级 ，全天气温27℃~33℃，当前温度32℃，体感温度32℃，相对湿度82%，日出时间05:55 ，日落时间：19:06 。","pubDate":"2017-07-31 19:17:31","commentCount":0}]
     * notice : {"referCount":0,"replyCount":0,"msgCount":0,"fansCount":0}
     */

    private NoticeBean notice;
    private List<TweetlistBean> tweetlist;

    public NoticeBean getNotice() { return notice;}

    public void setNotice(NoticeBean notice) { this.notice = notice;}

    public List<TweetlistBean> getTweetlist() { return tweetlist;}

    public void setTweetlist(List<TweetlistBean> tweetlist) { this.tweetlist = tweetlist;}

    public static class NoticeBean {
        /**
         * referCount : 0
         * replyCount : 0
         * msgCount : 0
         * fansCount : 0
         */

        private int referCount;
        private int replyCount;
        private int msgCount;
        private int fansCount;

        public int getReferCount() { return referCount;}

        public void setReferCount(int referCount) { this.referCount = referCount;}

        public int getReplyCount() { return replyCount;}

        public void setReplyCount(int replyCount) { this.replyCount = replyCount;}

        public int getMsgCount() { return msgCount;}

        public void setMsgCount(int msgCount) { this.msgCount = msgCount;}

        public int getFansCount() { return fansCount;}

        public void setFansCount(int fansCount) { this.fansCount = fansCount;}
    }

    public static class TweetlistBean {
        /**
         * author : bouyeijiang
         * id : 14822255
         * portrait : https://static.oschina.net/uploads/user/438/877346_50.jpeg?t=1501390488000
         * authorid : 877346
         * body : .net高并发socket通信库升级了，支持同步连接和接收
         <a href="https://www.oschina.net/p/Bouyei-NetProviderFactory" target="_blank" rel="nofollow">https://www.oschina.net/p/Bouyei-NetProviderFactory</a>
         * pubDate : 2017-07-31 20:31:42
         * commentCount : 0
         * imgBig : https://static.oschina.net/uploads/space/2017/0731/203151_OQ7m_2720166.jpg
         * imgSmall : https://static.oschina.net/uploads/space/2017/0731/203151_OQ7m_2720166_thumb.jpg
         */

        private String author;
        private int    id;
        private String portrait;
        private int    authorid;
        private String body;
        private String pubDate;
        private int    commentCount;
        private String imgBig;
        private String imgSmall;

        public String getAuthor() { return author;}

        public void setAuthor(String author) { this.author = author;}

        public int getId() { return id;}

        public void setId(int id) { this.id = id;}

        public String getPortrait() { return portrait;}

        public void setPortrait(String portrait) { this.portrait = portrait;}

        public int getAuthorid() { return authorid;}

        public void setAuthorid(int authorid) { this.authorid = authorid;}

        public String getBody() { return body;}

        public void setBody(String body) { this.body = body;}

        public String getPubDate() { return pubDate;}

        public void setPubDate(String pubDate) { this.pubDate = pubDate;}

        public int getCommentCount() { return commentCount;}

        public void setCommentCount(int commentCount) { this.commentCount = commentCount;}

        public String getImgBig() { return imgBig;}

        public void setImgBig(String imgBig) { this.imgBig = imgBig;}

        public String getImgSmall() { return imgSmall;}

        public void setImgSmall(String imgSmall) { this.imgSmall = imgSmall;}
    }
}
