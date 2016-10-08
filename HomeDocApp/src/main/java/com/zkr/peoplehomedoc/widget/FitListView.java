package com.zkr.peoplehomedoc.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * @Description: -------解决listview与scrollview滑动冲突
 * @author --------------LF
 * @date ----------------2016/9/1 17:40
 */
public class FitListView extends ListView {

	public FitListView(Context context) {  
        // TODO Auto-generated method stub  
        super(context);  
    }  
  
    public FitListView(Context context, AttributeSet attrs) {  
        // TODO Auto-generated method stub  
        super(context, attrs);  
    }  
  
    public FitListView(Context context, AttributeSet attrs, int defStyle) {  
        // TODO Auto-generated method stub  
        super(context, attrs, defStyle);  
    }  
  
    @Override  
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
        // TODO Auto-generated method stub  
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,  
                MeasureSpec.AT_MOST);  
        super.onMeasure(widthMeasureSpec, expandSpec);  
    }  
}
