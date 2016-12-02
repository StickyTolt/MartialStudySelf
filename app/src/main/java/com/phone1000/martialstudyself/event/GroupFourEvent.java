package com.phone1000.martialstudyself.event;

/**
 * Created by 马金利 on 2016/12/2.
 */
public class GroupFourEvent {

    private String msg;

    public final int what;

    public GroupFourEvent(int what) {
        this.what = what;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
