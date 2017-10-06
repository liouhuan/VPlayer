package com.vplayer.www.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 * Copyright  : Copyright (c) 2017
 * Author     : Young
 * Date       : 2017/10/2 17:04 *
 */

public class VideoBean  implements Serializable{


    /**
     * code : 200
     * msg : 请求成功
     * more : video1.json
     * data : {"adj":[{"img":"http://192.168.1.103:8080/avideo/img/f1.jpg","min":"106","date":"2017.09.30","eg":"最新","isnew":true,"title":"01.在相当长的时间内，新型外太空探测器依然无法追上旅行者二号的步伐"},{"img":"http://192.168.1.103:8080/avideo/img/f2.jpg","min":"110","date":"2017.09.26","eg":"最新","isnew":true,"title":"02.旅行者二号太空探测器虽然正在极速飞行但是在幽冷与无垠的宇宙"},{"img":"http://192.168.1.103:8080/avideo/img/f3.jpg","min":"120","date":"2017.09.25","eg":"最新","isnew":true,"title":"03.二零一零年五月二十二日，美国宇航局接收到旅行者二号传送回的一组神秘的数据"},{"img":"http://192.168.1.103:8080/avideo/img/f4.jpg","min":"112","date":"2017.09.25","eg":"最新","isnew":true,"title":"04.这世间该不会真的有佛陀吧，古庙虽然荒败了，但是依然让人感觉到了那种平淡与祥宁的禅境"},{"img":"http://192.168.1.103:8080/avideo/img/f5.jpg","min":"90","date":"2017.09.24","eg":"最新","isnew":true,"title":"05.天宫过去何其雄伟，浩大与壮阔，但终究毁灭了，只留下一地的瓦砾。而这古庙看似破败"},{"img":"http://192.168.1.103:8080/avideo/img/f6.jpg","min":"96","date":"2017.09.23","eg":"最新","isnew":true,"title":"06.平平淡淡，清清静静，经得起时间的磨砺与考验，留下的才是\u201c真\u201d，那浮奢的不过是过眼云尘"}]}
     */

    private int code;
    private String msg;
    private String more;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<AdjBean> adj;

        public List<AdjBean> getAdj() {
            return adj;
        }

        public void setAdj(List<AdjBean> adj) {
            this.adj = adj;
        }

        public static class AdjBean {
            /**
             * img : http://192.168.1.103:8080/avideo/img/f1.jpg
             * min : 106
             * date : 2017.09.30
             * eg : 最新
             * isnew : true
             * title : 01.在相当长的时间内，新型外太空探测器依然无法追上旅行者二号的步伐
             */

            private String img;
            private String min;
            private String date;
            private String eg;
            private boolean isnew;
            private String title;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getMin() {
                return min;
            }

            public void setMin(String min) {
                this.min = min;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getEg() {
                return eg;
            }

            public void setEg(String eg) {
                this.eg = eg;
            }

            public boolean isIsnew() {
                return isnew;
            }

            public void setIsnew(boolean isnew) {
                this.isnew = isnew;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
