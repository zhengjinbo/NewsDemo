package com.zhengjinbo.newsdemo.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhengjinbo.
 */

public class NewsListBean
        implements Serializable {


    /**
     * newslist : [{"author":"淡漠悠然","id":87243,"title":"Jackson 2.9.0 发布，高性能 JSON 处理","type":4,"authorid":2305107,"pubDate":"2017-07-30 16:50:44","commentCount":1},{"author":"陈宝仪","id":87242,"title":"redis-replicator-2.3.1 发布 增加新的命令支持","type":4,"authorid":2896188,"pubDate":"2017-07-30 13:10:17","commentCount":1},{"author":"clouddyy","id":87241,"title":"FFmpeg 3.3.3 发布，多媒体处理工具","type":4,"authorid":347223,"pubDate":"2017-07-30 12:41:28","commentCount":0},{"author":"javazj","id":87240,"title":"微信小程序商城更新最新版 v1.2.2","type":4,"authorid":180936,"pubDate":"2017-07-30 11:01:01","commentCount":0},{"author":"局长","id":87239,"title":"软件周刊 | Debian 9.1/8.9 和 LibreOffice 5.4 正式发布","type":3,"authorid":2720166,"pubDate":"2017-07-30 08:42:30","commentCount":6,"object":1492180},{"author":"局长","id":87238,"title":"码云推荐 | 模仿 dubbo 的分布式服务框架 remoter","type":0,"authorid":2720166,"pubDate":"2017-07-30 08:40:35","url":"https://git.oschina.net/desktop/remoter","object":0,"commentCount":0},{"author":"局长","id":87237,"title":"Bash-Snippets \u2014\u2014 小型 Bash 脚本的集合","type":1,"authorid":2720166,"pubDate":"2017-07-30 08:39:39","object":45712,"commentCount":0},{"author":"局长","id":87236,"title":"每日一博 | RSA 公私钥生成和 RSA 证书创建\"利器\"","type":3,"authorid":2720166,"pubDate":"2017-07-30 08:38:47","commentCount":3,"object":1490774},{"author":"局长","id":87235,"title":"绝不只是堆砌闪存！SSD 中的软件算法你知多少","type":4,"authorid":2720166,"pubDate":"2017-07-30 08:37:43","commentCount":11},{"author":"局长","id":87234,"title":"一位开发者对请愿开源 Flash 的吐槽：千万别这样做","type":4,"authorid":2720166,"pubDate":"2017-07-30 08:28:28","commentCount":15},{"author":"局长","id":87233,"title":"苹果被打脸 黑客分分钟越狱 Apple Watch","type":4,"authorid":2720166,"pubDate":"2017-07-30 08:27:30","commentCount":2},{"author":"局长","id":87232,"title":"OSChina 周日乱弹 \u2014\u2014 如何假装在生活。","type":3,"authorid":2720166,"pubDate":"2017-07-30 08:16:52","commentCount":24,"object":1492212},{"author":"局长","id":87231,"title":"苹果更新 XProtect，遮蔽最新 Leverage 恶意软件变种","type":4,"authorid":2720166,"pubDate":"2017-07-30 08:14:40","commentCount":1},{"author":"局长","id":87230,"title":"Win 10 两周岁了，但市场份额依然被 Win 7 碾压","type":4,"authorid":2720166,"pubDate":"2017-07-30 08:07:51","commentCount":15},{"author":"王练","id":87229,"title":"MaterialDrawer 5.9.5 发布，Android 侧滑显示控件","type":4,"authorid":2896879,"pubDate":"2017-07-30 07:35:12","commentCount":0},{"author":"王练","id":87228,"title":"Serverless 1.18.1 发布，无服务器架构","type":4,"authorid":2896879,"pubDate":"2017-07-30 07:31:03","commentCount":0},{"author":"王练","id":87227,"title":"NodeBB 1.5.3 发布，Node.js 论坛系统","type":4,"authorid":2896879,"pubDate":"2017-07-30 07:30:18","commentCount":4},{"author":"王练","id":87226,"title":"PDFBox 2.0.7 发布，Java 的 PDF 处理类库","type":4,"authorid":2896879,"pubDate":"2017-07-30 07:29:34","commentCount":0},{"author":"王练","id":87225,"title":"Pony 0.16.0 发布，Actor 模型高性能编程语言","type":4,"authorid":2896879,"pubDate":"2017-07-30 07:29:02","commentCount":0},{"author":"王练","id":87224,"title":"Next.js 2.4.9 发布，React  应用的后端渲染框架","type":4,"authorid":2896879,"pubDate":"2017-07-30 07:27:54","commentCount":0}]
     * notice : {"referCount":0,"replyCount":1,"msgCount":0,"fansCount":0}
     */

    private NoticeBean notice;
    private List<NewslistBean> newslist;

    public NoticeBean getNotice() { return notice;}

    public void setNotice(NoticeBean notice) { this.notice = notice;}

    public List<NewslistBean> getNewslist() { return newslist;}

    public void setNewslist(List<NewslistBean> newslist) { this.newslist = newslist;}

    public static class NoticeBean {
        /**
         * referCount : 0
         * replyCount : 1
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

    public static class NewslistBean {
        /**
         * author : 淡漠悠然
         * id : 87243
         * title : Jackson 2.9.0 发布，高性能 JSON 处理
         * type : 4
         * authorid : 2305107
         * pubDate : 2017-07-30 16:50:44
         * commentCount : 1
         * object : 1492180
         * url : https://git.oschina.net/desktop/remoter
         */

        private String author;
        private int    id;
        private String title;
        private int    type;
        private int    authorid;
        private String pubDate;
        private int    commentCount;
        private int    object;
        private String url;

        public String getAuthor() { return author;}

        public void setAuthor(String author) { this.author = author;}

        public int getId() { return id;}

        public void setId(int id) { this.id = id;}

        public String getTitle() { return title;}

        public void setTitle(String title) { this.title = title;}

        public int getType() { return type;}

        public void setType(int type) { this.type = type;}

        public int getAuthorid() { return authorid;}

        public void setAuthorid(int authorid) { this.authorid = authorid;}

        public String getPubDate() { return pubDate;}

        public void setPubDate(String pubDate) { this.pubDate = pubDate;}

        public int getCommentCount() { return commentCount;}

        public void setCommentCount(int commentCount) { this.commentCount = commentCount;}

        public int getObject() { return object;}

        public void setObject(int object) { this.object = object;}

        public String getUrl() { return url;}

        public void setUrl(String url) { this.url = url;}
    }
}
