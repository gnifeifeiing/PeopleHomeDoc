package com.zkr.peoplehomedoc.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.zkr.peoplehomedoc.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by lenovo on 2016/10/10.
 */
public class SlideShowView extends FrameLayout {
    //    private DisplayImageOptions options;
//    private ImageLoader imageLoader1;
    private String defaultImageUrl1, defaultImageUrl2, defaultImageUrl3, defaultImageUrl4;
    // 使用universal-image-loader插件读取网络图片，需要工程导入universal-image-loader-1.8.6-with-sources.jar
  //  private ImageLoader imageLoader = ImageLoader.getInstance();

    //轮播图图片数量
    private final static int IMAGE_COUNT = 3;
    //自动轮播的时间间隔
    private final static int TIME_INTERVAL = 5;
    //自动轮播启用开关
    private final static boolean isAutoPlay = true;

    //自定义轮播图的资源
    private String[] imageUrls;
    //放轮播图片的ImageView 的list
    private List<ImageView> imageViewsList;
    //放圆点的View的list
    private List<View> dotViewsList;

    private ViewPager viewPager;
    //当前轮播页
    private int currentItem = 0;
    //定时任务
    private ScheduledExecutorService scheduledExecutorService;

    private Context context;

    //Handler
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            viewPager.setCurrentItem(currentItem);
        }

    };

    public SlideShowView(Context context) {
        this(context, null);
        // TODO Auto-generated constructor stub
    }

    public SlideShowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        // TODO Auto-generated constructor stub
    }

    public SlideShowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;

       // initImageLoader(context);

        initData();
        if (isAutoPlay) {
            startPlay();
        }

    }

    /**
     * 开始轮播图切换
     */
    private void startPlay() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 1, 4, TimeUnit.SECONDS);
    }

    /**
     * 停止轮播图切换
     */
    private void stopPlay() {
        scheduledExecutorService.shutdown();
    }

    /**
     * 初始化相关Data
     */


    private void initData() {
        imageViewsList = new ArrayList<ImageView>();
        dotViewsList = new ArrayList<View>();
        initUI(context);
//        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
//        IStringRequest stringRequest = new IStringRequest(Request.Method.GET,
//                "http://app.u17.com/v3/app/android/phone/recommend/itemlist?version=1099&key=null", new
//                Response.Listener<String>() {
//
//                    @Override
//                    public void onResponse(String response) {
//                        parseJsonpic(response);
//                       // Log.i("aaa", response.toString());
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                //LogUtil.i("aaa", error.toString());
//
//            }
//        }) {
//
//
//        };
//        requestQueue.add(stringRequest);


        // 一步任务获取图片
        //new GetListTask().execute("");


    }

//    private void parseJsonpic(String response) {
//
//        Map<String, Object> map = null;
//        Map<String, Object> data = null;
//        Map<String, Object> returnData = null;
//        List<Map<String, Object>> dataList = null;
//        List<Map<String, Object>> galleryItems = null;
//        defaultImageUrl1 = "";
//        defaultImageUrl2 = "";
//        defaultImageUrl3 = "";
//        defaultImageUrl4 = "";
//        try {
////            map = JsonUtils.getMapObj(response);
////            data = JsonUtils.getMapObj(map.get("data").toString());
////            returnData = JsonUtils.getMapObj(data.get("returnData").toString());
////            dataList = JsonUtils.getListMap(returnData.get("dataList").toString());
////            galleryItems = JsonUtils.getListMap(dataList.get(0).get("galleryItems").toString());
//            defaultImageUrl1=galleryItems.get(0).get("defaultImageUrl").toString();
//            defaultImageUrl2=galleryItems.get(1).get("defaultImageUrl").toString();
//            defaultImageUrl3=galleryItems.get(2).get("defaultImageUrl").toString();
//            defaultImageUrl4=galleryItems.get(3).get("defaultImageUrl").toString();
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    /**
     * 初始化Views等UI
     */
    private void initUI(Context context) {
//        if (imageUrls == null || imageUrls.length == 0)
//            return;

        LayoutInflater.from(context).inflate(R.layout.layout_slideshow, this, true);

        LinearLayout dotLayout = (LinearLayout) findViewById(R.id.dotLayout);
        dotLayout.removeAllViews();

        // 热点个数与图片特殊相等
        for (int i = 0; i < 3; i++) {
            ImageView view = new ImageView(context);
            view.setTag(i);
            if (i==0)//给一个默认图
            {
                view.setBackgroundResource(R.mipmap.home_welcome01);
            }
            if (i==1){
                view.setBackgroundResource(R.drawable.index2);
            }
            if (i==2){
                view.setBackgroundResource(R.drawable.index3);
            }

            view.setScaleType(ImageView.ScaleType.FIT_XY);
            imageViewsList.add(view);
            ImageView dotView = new ImageView(context);
            dotView.setMaxWidth(3);
            dotView.setMaxHeight(3);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.leftMargin = 4;
            params.rightMargin = 4;
            dotLayout.addView(dotView, params);
            dotViewsList.add(dotView);
        }

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setFocusable(true);

        viewPager.setAdapter(new MyPagerAdapter());
        viewPager.setOnPageChangeListener(new MyPageChangeListener());
    }

    /**
     * 填充ViewPager的页面适配器
     */
    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public void destroyItem(View container, int position, Object object) {
            // TODO Auto-generated method stub
            //((ViewPag.er)container).removeView((View)object);
            ((ViewPager) container).removeView(imageViewsList.get(position));
        }

        @Override
        public Object instantiateItem(View container, int position) {
            ImageView imageView = imageViewsList.get(position);

           // imageLoader.displayImage(imageView.getTag() + "", imageView);
            //Picasso.with(context).load(R.drawable.index2).into(imageView);
            //Picasso.with(context).load(String.valueOf(imageView));
            ((ViewPager) container).addView(imageViewsList.get(position));
            return imageViewsList.get(position);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return imageViewsList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
            // TODO Auto-generated method stub

        }

        @Override
        public Parcelable saveState() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void finishUpdate(View arg0) {
            // TODO Auto-generated method stub

        }

    }

    /**
     * ViewPager的监听器
     * 当ViewPager中页面的状态发生改变时调用
     */
    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        boolean isAutoPlay = false;

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub
            switch (arg0) {
                case 1:// 手势滑动，空闲中
                    isAutoPlay = false;
                    break;
                case 2:// 界面切换中
                    isAutoPlay = true;
                    break;
                case 0:// 滑动结束，即切换完毕或者加载完毕
                    // 当前为最后一张，此时从右向左滑，则切换到第一张
                    if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1 && !isAutoPlay) {
                        viewPager.setCurrentItem(0);
                    }
                    // 当前为第一张，此时从左向右滑，则切换到最后一张
                    else if (viewPager.getCurrentItem() == 0 && !isAutoPlay) {
                        viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 1);
                    }
                    break;
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageSelected(int pos) {
            // TODO Auto-generated method stub

            currentItem = pos;
            for (int i = 0; i < dotViewsList.size(); i++) {
                if (i == pos) {
                    ((View) dotViewsList.get(pos)).setBackgroundResource(R.drawable.dot_focus);
                } else {
                    ((View) dotViewsList.get(i)).setBackgroundResource(R.drawable.dot_blur);
                }
            }
        }

    }

    /**
     * 执行轮播图切换任务
     */
    private class SlideShowTask implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            synchronized (viewPager) {
                currentItem = (currentItem + 1) % imageViewsList.size();
                handler.obtainMessage().sendToTarget();
            }
        }

    }

    /**
     * 销毁ImageView资源，回收内存
     */
    private void destoryBitmaps() {

        for (int i = 0; i < IMAGE_COUNT; i++) {
            ImageView imageView = imageViewsList.get(i);
            Drawable drawable = imageView.getDrawable();
            if (drawable != null) {
                //解除drawable对view的引用
                drawable.setCallback(null);
            }
        }
    }


//    /**
//     * 异步任务,获取数据
//     */
//    class GetListTask extends AsyncTask<String, Integer, Boolean> {
//
//
//        @Override
//        protected Boolean doInBackground(String... params) {
//            try {
//                // 这里一般调用服务端接口获取一组轮播图片，下面是从百度找的几个图片
//                imageUrls = new String[4];
//
//                imageUrls = new String[]{
//                        "http://image.mylife.u17t.com/2016/02/26/1456479632_Gs0j0ZzRNjDS.jpg",
//                        "http://image.mylife.u17t.com/2016/02/19/1455882528_A5UK5gwPpuaa.jpg",
//                        "http://image.mylife.u17t.com/2016/02/26/1456479736_6N5bGAjjXWnL.jpg",
//                        "http://image.mylife.u17t.com/2016/02/25/1456382482_4HHAahO9KeM0.jpg",
//                        "http://image.mylife.u17t.com/2016/02/26/1456479533_378GT77prIO7.jpg"
//
////
//                };
//
//             //   Log.i("imageUrls", imageUrls.length + "");
//
//
//                return true;
//            } catch (Exception e) {
//                e.printStackTrace();
//                return false;
//            }
//        }
//
//        @Override
//        protected void onPostExecute(Boolean result) {
//            super.onPostExecute(result);
//            if (result) {
//                initUI(context);
//            }
//        }
//    }

    /**
     * ImageLoader 图片组件初始化
     *
     * @param context
     */
//    public static void initImageLoader(Context context) {
//        // This configuration tuning is custom. You can tune every option, you
//        // may tune some of them,
//        // or you can create default configuration by
//        // ImageLoaderConfiguration.createDefault(this);
//        // method.
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory().discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).writeDebugLogs() // Remove
//                // for
//                // release
//                // app
//                .build();
//        // Initialize ImageLoader with configuration.
//        ImageLoader.getInstance().init(config);
//    }
}