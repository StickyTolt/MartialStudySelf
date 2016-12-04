package com.phone1000.martialstudyself.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 马金利 on 2016/12/1.
 */
public class GridViewModel {

    public List<String> context = new ArrayList<>();

    public List<String> picture = new ArrayList<>();

    public List<String> book = new ArrayList<>();

    public List<String> url = new ArrayList<>();

    public List<String> search = new ArrayList<>();



    public GridViewModel(){
        context.add("全部");
        context.add("视频");
        context.add("图片");
        context.add("资讯");
        picture.add("全部");
        picture.add("比赛图片");
        picture.add("选手写真");
        picture.add("功夫美女");
        picture.add("其他图片");
        book.add("全部");
        book.add("书籍图文");
        book.add("视频教程");
        book.add("电子书下载");

        url.add("source=book");
        url.add("source=video");
        url.add("source=pdf");

        search.add("防身术");
        search.add("八卦掌");
        search.add("形意拳");
        search.add("八极拳");
        search.add("咏春拳");
        search.add("长拳");

    }

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }

    public List<String> getBook() {
        return book;
    }

    public void setBook(List<String> book) {
        this.book = book;
    }

    public List<String> getPicture() {
        return picture;
    }

    public void setPicture(List<String> picture) {
        this.picture = picture;
    }

    public List<String> getContext() {
        return context;
    }

    public void setContext(List<String> context) {
        this.context = context;
    }
}
