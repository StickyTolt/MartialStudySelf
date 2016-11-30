package com.phone1000.martialstudyself.model;

import java.util.List;

/**
 * Created by chen on 2016/11/29.
 */
public class LatestGongFa {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * info_has_catalog : 0
         * info_id : 37392
         * info_img_path : /static/up/2016/1030/b8c8edf4-ec3a-4e65-a79b-bbdd4783b159.jpg,/static/up/2016/1030/d7a0477f-f851-4382-8691-a61ff5e54587.jpg,/static/up/2016/1030/60d3d5ba-4160-4b05-bb7d-12021ba0e576.jpg
         * info_read_count : 32028
         * info_reply_count : 8
         * info_title : 健身气功.六字诀动作要点口诀
         * info_type : 1
         */

        private String info_has_catalog;
        private int info_id;
        private String info_img_path;
        private int info_read_count;
        private int info_reply_count;
        private String info_title;
        private int info_type;

        public boolean isVideo() {
            return isVideo;
        }

        public void setVideo(boolean video) {
            isVideo = video;
        }

        private boolean isVideo;

        public String getInfo_has_catalog() {
            return info_has_catalog;
        }

        public void setInfo_has_catalog(String info_has_catalog) {
            this.info_has_catalog = info_has_catalog;
        }

        public int getInfo_id() {
            return info_id;
        }

        public void setInfo_id(int info_id) {
            this.info_id = info_id;
        }

        public String getInfo_img_path() {
            return info_img_path;
        }

        public void setInfo_img_path(String info_img_path) {
            this.info_img_path = info_img_path;
        }

        public int getInfo_read_count() {
            return info_read_count;
        }

        public void setInfo_read_count(int info_read_count) {
            this.info_read_count = info_read_count;
        }

        public int getInfo_reply_count() {
            return info_reply_count;
        }

        public void setInfo_reply_count(int info_reply_count) {
            this.info_reply_count = info_reply_count;
        }

        public String getInfo_title() {
            return info_title;
        }

        public void setInfo_title(String info_title) {
            this.info_title = info_title;
        }

        public int getInfo_type() {
            return info_type;
        }

        public void setInfo_type(int info_type) {
            this.info_type = info_type;
        }
    }
}
