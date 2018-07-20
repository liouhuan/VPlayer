package com.vplayer.www.model;

import android.app.Activity;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.tnt.MaterialRefreshLayout;
import com.vplayer.www.api.API;
import com.vplayer.www.api.callback.StringDialogCallback;
import com.vplayer.www.model.bean.VideoBean;
import com.vplayer.www.utils.LogUtil;
import com.vplayer.www.utils.ToastUtil;
import org.greenrobot.eventbus.EventBus;

/**
 * Description:
 */

public class Model {
    private static Model model;
    private Gson gson = new Gson();

    private Model() {

    }

    public static Model getIstace() {
        if (model == null) {
            synchronized (Model.class) {
                if (model == null) {
                    model = new Model();
                }
            }
        }
        return model;
    }

    public void getVideo(final Activity activity, final MaterialRefreshLayout refresh) {
        OkGo.<String>post(API.VIDEO)
                .tag(this)
                .execute(new StringDialogCallback(activity) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        VideoBean videoBean = gson.fromJson(body, VideoBean.class);
                        EventBus.getDefault().post(videoBean);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        LogUtil.e("请求异常");
                        refresh.finishRefresh();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        refresh.finishRefresh();
                    }
                });
    }

    public void getMore(final Activity activity, final MaterialRefreshLayout refresh, String api) {
        if (TextUtils.isEmpty(api)) {
            ToastUtil.showShort(activity, "没有更多了...");
            refresh.finishRefreshLoadMore();
        } else {
            OkGo.<String>get(API.BASEURL + "avideo/" + api)
                    .tag(this)
                    .execute(new StringDialogCallback(activity) {
                        @Override
                        public void onSuccess(Response<String> response) {
                            String body = response.body();
                            VideoBean videoBean = gson.fromJson(body, VideoBean.class);
                            EventBus.getDefault().post(videoBean);
                        }

                        @Override
                        public void onFinish() {
                            super.onFinish();
                            refresh.finishRefreshLoadMore();
                        }
                    });
        }
    }
}
