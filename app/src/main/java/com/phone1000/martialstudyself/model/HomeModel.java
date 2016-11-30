package com.phone1000.martialstudyself.model;

import java.util.List;

/**
 * Created by 马金利 on 2016/11/28.
 */
public class HomeModel {


    /**
     * type : slide
     * desc : 幻灯片
     * list : [{"pic":"static/img/tietu/hdp3.jpg","desc":"体验真功夫","link":"info-catalog.html?id=5514"},{"pic":"static/img/tietu/hdp1.jpg","desc":"世界格斗术揭秘","link":"info-catalog.html?id=10726"},{"pic":"static/img/tietu/hdp2.jpg","desc":"武林探秘","link":"info-catalog.html?id=10966"},{"pic":"static/img/tietu/hdp4.jpg","desc":"李小龙改变世界","link":"info-read.html?id=31925"}]
     * link : info-list.html?ptype=3
     */

    private String type;
    private String desc;
    private String link;
    private List<ListBean> list;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * pic : static/img/tietu/hdp3.jpg
         * desc : 体验真功夫
         * link : info-catalog.html?id=5514
         */

        private String pic;
        private String desc;
        private String link;
        private String info_img_path;
        private String info_read_count;
        private String info_reply_count;
        private String info_title;
        private String topic_title;
        private String topic_reply_count;
        private String topic_add_user;
        private String info_id;
        private String topic_id;

        public String getInfo_id() {
            return info_id;
        }

        public void setInfo_id(String info_id) {
            this.info_id = info_id;
        }

        public String getTopic_id() {
            return topic_id;
        }

        public void setTopic_id(String topic_id) {
            this.topic_id = topic_id;
        }

        public String getTopic_add_user() {
            return topic_add_user;
        }

        public void setTopic_add_user(String topic_add_user) {
            this.topic_add_user = topic_add_user;
        }

        public String getTopic_title() {
            return topic_title;
        }

        public void setTopic_title(String topic_title) {
            this.topic_title = topic_title;
        }

        public String getTopic_reply_count() {
            return topic_reply_count;
        }

        public void setTopic_reply_count(String topic_reply_count) {
            this.topic_reply_count = topic_reply_count;
        }

        public String getInfo_img_path() {
            return info_img_path;
        }

        public void setInfo_img_path(String info_img_path) {
            this.info_img_path = info_img_path;
        }

        public String getInfo_read_count() {
            return info_read_count;
        }

        public void setInfo_read_count(String info_read_count) {
            this.info_read_count = info_read_count;
        }

        public String getInfo_reply_count() {
            return info_reply_count;
        }

        public void setInfo_reply_count(String info_reply_count) {
            this.info_reply_count = info_reply_count;
        }

        public String getInfo_title() {
            return info_title;
        }

        public void setInfo_title(String info_title) {
            this.info_title = info_title;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }
}
