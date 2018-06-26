package com.example.rxjavademo.rxretrofit;

import com.example.rxjavademo.node.JokeNode;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * Title:       RequestManager
 * <p>
 * Package:     com.example.rxjavademo.rxretrofit
 * <p>
 * Author:      fxp
 * <p>
 * Create at:   2018/6/26 上午10:31
 * <p>
 * Description:
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
public class RequestManager {

    /**
     * @Description: 获取随机推荐的热门段子（包含文字、图片、GIF、视频）
     *
     * @Author:  fxp
     * @Date:    2018/6/26   下午3:40
     * @param    type
     * @param    page
     * @return   io.reactivex.Observable<java.lang.String>
     * @exception/throws
     */
    public static Observable<JokeNode> getHotJokeList(int type, int  page){
        HashMap<String , Object> map = new HashMap<>();
        map.put("type", type);
        map.put("page", page);
        return RetrofitUtil.GetObservable(Constants.HOT_JOKE,map,JokeNode.class);
    }

    /**
     * @Description: 获取热门小说推荐列表
     *
     * @Author:  fxp
     * @Date:    2018/6/26   下午3:53
     * @param
     * @return   io.reactivex.Observable<java.lang.String>
     * @exception/throws
     */
    public static Observable<String> getHotNovelList(){
        HashMap<String , Object> map = new HashMap<>();
        return RetrofitUtil.GetObservable(Constants.HOT_NOVEL,map,String.class);
    }

}
