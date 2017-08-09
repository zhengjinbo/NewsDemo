package com.zhengjinbo.newsdemo.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhengjinbo. on 17/7/31.
 */

public class NewsDetailBean implements Serializable {


    /**
     * author : 局长
     * id : 87274
     * authorid : 2720166
     * title : 这个漏洞要命：10 亿部 iPhone、安卓手机遭殃
     * body : <style type='text/css'>pre {white-space:pre-wrap;word-wrap:break-word;}</style><p><img src="https://static.oschina.net/uploads/space/2017/0731/115708_EOrM_2720166.jpg"></p>
     <p>上周，安全研究机构Exodus Intelligence发文称，博通Wi-Fi芯片（BCM43系列）存在安全漏洞，<strong>只要你的移动设备开启WiFi，就能被黑客轻易黑掉，漏洞十分严重且可怕。</strong></p>
     <p>现在，Exodus Intelligence又送出了最新统计显示，目前受这个漏洞侵害的设备有近10亿部，而BCM43系列芯片这个漏洞可以被黑客利用，并最终实现全远程攻击，因为这可以在Android和iOS的主应用处理器上执行代码。</p>
     <p>最新的报告中还指出，博通BCM43系列芯片不受ASLR、DEP保护，<strong>这也可以让黑客明确知道恶意代码在芯片加载的具体位置，从而实现网络攻击，且用户无一点防备能力。</strong></p>
     <p>由于这类芯片漏洞具有共通性，所以攻击者不用为每个固件版本定制代码，而只需一个通用版本即可。更糟糕的是，该攻击不需要连接到攻击Wi-Fi网络也能展开。也就是说，只要用户打开了博通Wi-Fi网络，那么就可能要沦为了攻击被害者。</p>
     <p><strong>iPhone 5、5S、6、6S、Nexus 5、6、6X、6P、Galaxy S3、S4、S5、S6、S7、S8等都是受害机型，虽然iOS、安卓都给出了更新，但是具体到厂商上，如何让受害机型更新，就又是另外一回事了</strong>（<strong>苹果的封闭性导致受害者不会太崩溃、一众安卓手机基本没啥指望</strong>）。</p>
     <p>来自：<a target="_blank" href="http://news.mydrivers.com/1/542/542521.htm" rel="nofollow">驱动之家</a></p>
     * pubDate : 2017-07-31 11:56:29
     * favorite : 0
     * url : https://www.oschina.net/news/87274/ios-and-android-mobile-phone-wifi-vulnerable
     * relativies : [{"title":"iPhone 如何做到 10 年不被恶意软件攻陷","url":"https://www.oschina.net/news/86565/iphone-and-its-security"},{"title":"码云推荐 | 用于快速访问 iPhone 模拟器的开发者工具","url":"http://git.oschina.net/luckytianyiyan/TySimulator"},{"title":"iPhone 也能当公交卡？iOS11 将扩展 NFC 相关功能","url":"https://www.oschina.net/news/85932/ios11-nfc-reader-mode"},{"title":"更安全 特朗普总统放弃 Android 手机，换 iPhone","url":"https://www.oschina.net/news/81188/trump-use-iphone-instead-of-android"},{"title":"谷歌报复，推出\u201c一键迁移 iPhone 数据到安卓\u201d","url":"https://www.oschina.net/news/80009/iphone-switching-android-eeasier-than-ever"},{"title":"Windows 10 \u201c杀手级应用\u201d 将适配安卓和 iPhone","url":"https://www.oschina.net/news/77727/windows-hello-to-android-and-iphone"},{"title":"OSChina 周四乱弹\u2014\u2014iPhone7 出了开始做牛做马了","url":"http://my.oschina.net/xxiaobian/blog/743979"},{"title":"苹果 iOS 10 更新消息汇总，iPhone 4s 可能用不了","url":"https://www.oschina.net/news/73845/apple-ios-10"},{"title":"FBI 宣布成功破解 iPhone，库克估计要有几个不眠之夜","url":"https://www.oschina.net/news/71986/fbi-hacked-iphone"},{"title":"iPhone手机掉了怎么找回","url":"https://www.oschina.net/news/69839"}]
     * commentCount : 1
     * notice : {"referCount":0,"replyCount":0,"msgCount":0,"fansCount":0}
     */

    private String author;
    private int                  id;
    private int                  authorid;
    private String               title;
    private String               body;
    private String               pubDate;
    private int                  favorite;
    private String               url;
    private int                  commentCount;
    private NoticeBean           notice;
    private List<RelativiesBean> relativies;

    public String getAuthor() { return author;}

    public void setAuthor(String author) { this.author = author;}

    public int getId() { return id;}

    public void setId(int id) { this.id = id;}

    public int getAuthorid() { return authorid;}

    public void setAuthorid(int authorid) { this.authorid = authorid;}

    public String getTitle() { return title;}

    public void setTitle(String title) { this.title = title;}

    public String getBody() { return body;}

    public void setBody(String body) { this.body = body;}

    public String getPubDate() { return pubDate;}

    public void setPubDate(String pubDate) { this.pubDate = pubDate;}

    public int getFavorite() { return favorite;}

    public void setFavorite(int favorite) { this.favorite = favorite;}

    public String getUrl() { return url;}

    public void setUrl(String url) { this.url = url;}

    public int getCommentCount() { return commentCount;}

    public void setCommentCount(int commentCount) { this.commentCount = commentCount;}

    public NoticeBean getNotice() { return notice;}

    public void setNotice(NoticeBean notice) { this.notice = notice;}

    public List<RelativiesBean> getRelativies() { return relativies;}

    public void setRelativies(List<RelativiesBean> relativies) { this.relativies = relativies;}

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

    public static class RelativiesBean {
        /**
         * title : iPhone 如何做到 10 年不被恶意软件攻陷
         * url : https://www.oschina.net/news/86565/iphone-and-its-security
         */

        private String title;
        private String url;

        public String getTitle() { return title;}

        public void setTitle(String title) { this.title = title;}

        public String getUrl() { return url;}

        public void setUrl(String url) { this.url = url;}
    }
}
