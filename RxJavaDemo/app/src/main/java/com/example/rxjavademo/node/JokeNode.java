package com.example.rxjavademo.node;

import java.util.List;

/**
 * Title:       JokeNode
 * <p>
 * Package:     com.example.rxjavademo.node
 * <p>
 * Author:      fxp
 * <p>
 * Create at:   2018/6/26 下午3:58
 * <p>
 * Description: 热门段子node（本示例中只取部分字段）
 * <p>
 * <p>
 * Modification History:
 * <p>
 * Date       Author       Version      Description
 * -----------------------------------------------------------------
 * 2018/6/26    fxp       1.0         First Created
 * <p>
 * Github:  https://github.com/fangxiaopeng
 */
public class JokeNode {

/*        {
            "code": 200,
            "msg": "成功!",
            "data": [
            {
                "type": "41",
                    "text": "我到的时候就剩下这个视频了。。。。。。",
                    "user_id": "22152999",
                    "name": "淼菟兎i",
                    "screen_name": "淼菟兎i",
                    "profile_image": "http://wimg.spriteapp.cn/profile/20180415155022838575.jpg",
                    "created_at": "2018-06-26 06:11:02",
                    "create_time": null,
                    "passtime": "2018-06-26 06:11:02",
                    "love": "87",
                    "hate": "3",
                    "comment": "16",
                    "repost": "2",
                    "bookmark": "1",
                    "bimageuri": "http://wimg.spriteapp.cn/picture/2018/0625/5b30bbb5c9bb6__b.jpg",
                    "voiceuri": null,
                    "voicetime": null,
                    "voicelength": null,
                    "status": "4",
                    "theme_id": "58191",
                    "theme_name": "搞笑视频",
                    "theme_type": "1",
                    "videouri": "http://wvideo.spriteapp.cn/video/2018/0625/5b30bbb5db99f_wpd.mp4",
                    "videotime": 19,
                    "original_pid": "0",
                    "cache_version": 2,
                    "playcount": "2049",
                    "playfcount": "330",
                    "cai": "3",
                    "weixin_url": null,
                    "image1": "http://wimg.spriteapp.cn/picture/2018/0625/5b30bbb5c9bb6__b.jpg",
                    "image2": "http://wimg.spriteapp.cn/picture/2018/0625/5b30bbb5c9bb6__b.jpg",
                    "is_gif": false,
                    "image0": "http://wimg.spriteapp.cn/picture/2018/0625/5b30bbb5c9bb6__b.jpg",
                    "image_small": "http://wimg.spriteapp.cn/picture/2018/0625/5b30bbb5c9bb6__b.jpg",
                    "cdn_img": "http://wimg.spriteapp.cn/picture/2018/0625/5b30bbb5c9bb6__b.jpg",
                    "width": "600",
                    "height": "1066",
                    "tag": "",
                    "t": 1529964662,
                    "ding": "87",
                    "favourite": "1",
                    "top_cmt": null,
                    "themes": null
            }
            ]
        }*/


    private int code;

    private String msg;

    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

/*        {
            "type": "41",
                "text": "我到的时候就剩下这个视频了。。。。。。",
                "user_id": "22152999",
                "name": "淼菟兎i",
                "screen_name": "淼菟兎i",
                "profile_image": "http://wimg.spriteapp.cn/profile/20180415155022838575.jpg",
                "created_at": "2018-06-26 06:11:02",
                "create_time": null,
                "passtime": "2018-06-26 06:11:02",
                "love": "87",
                "hate": "3",
                "comment": "16",
                "repost": "2",
                "bookmark": "1",
                "bimageuri": "http://wimg.spriteapp.cn/picture/2018/0625/5b30bbb5c9bb6__b.jpg",
                "voiceuri": null,
                "voicetime": null,
                "voicelength": null,
                "status": "4",
                "theme_id": "58191",
                "theme_name": "搞笑视频",
                "theme_type": "1",
                "videouri": "http://wvideo.spriteapp.cn/video/2018/0625/5b30bbb5db99f_wpd.mp4",
                "videotime": 19,
                "original_pid": "0",
                "cache_version": 2,
                "playcount": "2049",
                "playfcount": "330",
                "cai": "3",
                "weixin_url": null,
                "image1": "http://wimg.spriteapp.cn/picture/2018/0625/5b30bbb5c9bb6__b.jpg",
                "image2": "http://wimg.spriteapp.cn/picture/2018/0625/5b30bbb5c9bb6__b.jpg",
                "is_gif": false,
                "image0": "http://wimg.spriteapp.cn/picture/2018/0625/5b30bbb5c9bb6__b.jpg",
                "image_small": "http://wimg.spriteapp.cn/picture/2018/0625/5b30bbb5c9bb6__b.jpg",
                "cdn_img": "http://wimg.spriteapp.cn/picture/2018/0625/5b30bbb5c9bb6__b.jpg",
                "width": "600",
                "height": "1066",
                "tag": "",
                "t": 1529964662,
                "ding": "87",
                "favourite": "1",
                "top_cmt": null,
                "themes": null
        }*/


        private String type;

        private String text;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
