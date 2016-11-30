package com.phone1000.martialstudyself.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by my on 2016/11/29.
 */
public class WushubaikeModel {

    public List<String> 全部 = new ArrayList<>();
    public List<String> 武术门派 = new ArrayList<>();
    public List<String> 经络脉穴 = new ArrayList<>();
    public List<String> 武术名家 = new ArrayList<>();
    public List<String> 兵器库 = new ArrayList<>();
    public List<String> 说文解字 = new ArrayList<>();
    public List<String> 武林玄学 = new ArrayList<>();
    private List<String> data = new ArrayList<>();
    private static WushubaikeModel model = null;

    private WushubaikeModel() {
        经络脉穴.add("全部");
        经络脉穴.add("奇经八脉");
        经络脉穴.add("十二正经");
        经络脉穴.add("经脉知识");

        武术名家.add("全部");
        武术名家.add("少林名家");
        武术名家.add("武当名家");
        武术名家.add("峨眉名家");
        武术名家.add("太极拳名家");
        武术名家.add("八卦掌名家");
        武术名家.add("形意拳名家");
        武术名家.add("心意拳名家");
        武术名家.add("大成拳名家");
        武术名家.add("八极拳名家");
        武术名家.add("长拳名家");
        武术名家.add("南全明家");
        武术名家.add("咏春拳名家");
        武术名家.add("通背拳名家");
        武术名家.add("劈挂拳名家");
        武术名家.add("三皇炮锤名家");
        武术名家.add("戳脚名家");
        武术名家.add("翻子拳名家");
        武术名家.add("査拳名家");
        武术名家.add("地躺拳名家");
        武术名家.add("象形拳名家");
        武术名家.add("气功名家");
        武术名家.add("格斗名家");
        武术名家.add("摔跤名家");
        武术名家.add("刀术名家");
        武术名家.add("枪术名家");
        武术名家.add("剑术名家");
        武术名家.add("棍术名家");
        武术名家.add("斧法名家");
        武术名家.add("鞭法名家");
        武术名家.add("棒法名家");
        武术名家.add("日本剑道名家");
        武术名家.add("日本空手道名家");
        武术名家.add("泰拳名家");
        武术名家.add("韩国跆拳道名家");
        武术名家.add("日本柔道名家");
        武术名家.add("拳击名家");
        武术名家.add("截拳道名家");
        武术名家.add("巴西柔术名家");
        武术名家.add("日本合气道名家");
        武术名家.add("其他武术名家");

        兵器库.add("全部");
        兵器库.add("十八般兵器");
        兵器库.add("长兵器");
        兵器库.add("短兵器");
        兵器库.add("双兵器");
        兵器库.add("软兵器");
        兵器库.add("暗兵器");
        兵器库.add("御射兵器");
        兵器库.add("其他兵器");

        武林玄学.add("全部");
        武林玄学.add("轻功玄学");
        武林玄学.add("硬功玄学");
        武林玄学.add("气功玄学");
        武林玄学.add("特技玄学");
        武林玄学.add("其他玄学");
    }

    public static WushubaikeModel getWushubaike() {
        if (model == null) {
            model = new WushubaikeModel();
        }
        return model;
    }

}
