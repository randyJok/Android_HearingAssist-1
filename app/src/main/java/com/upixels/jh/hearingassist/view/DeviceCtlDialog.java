package com.upixels.jh.hearingassist.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.upixels.jh.hearingassist.R;

import me.forrest.commonlib.util.DensityUtil;

public class DeviceCtlDialog extends Dialog {

    private int resId;
    private ImageView imageIv;
    private TextView tv_title;
    private TextView tv_sub_title;
    private TextView tv_message;
    private Button btn_confirm;
    private Button btn_cancel;

    private String message;
    private String title;
    private String btnText;
    private boolean canceledOnTouchOutside = true;

    public DeviceCtlDialog(Context context) {
//        super(context, R.style.BaseDialogFragmentStyle);
        super(context);  // 不设置style 宽不能全屏, 但是可以通过 设置 WindowManager.LayoutParams 参数来处理
    }

    public DeviceCtlDialog(Context context, int resId, boolean canceledOnTouchOutside) {
//        super(context, R.style.BaseDialogFragmentStyle);
        super(context);
        this.resId = resId;
        this.canceledOnTouchOutside = canceledOnTouchOutside;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int id = this.resId == 0 ? R.layout.dialog_device_ctl : resId;
        View view = LayoutInflater.from(getContext()).inflate(id, null);
        setContentView(view);
        // 如何想要 setCanceledOnTouchOutside(true) 有效 必须重新设置 Window的大小，
        // 否则整个window通过Activity获取的还是全屏，没有outside可以点击
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = DensityUtil.dip2px(getContext(), 330);
        params.height = DensityUtil.dip2px(getContext(), 250);
        getWindow().setAttributes(params);
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setWindowAnimations(me.forrest.commonlib.R.style.dialog_anim_bottom_in_out);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(this.canceledOnTouchOutside);
        initView();
        //初始化界面数据
        refreshView();
        //初始化界面控件的事件
        initEvent();
    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {

    }

    /**
     * 初始化界面控件的显示数据
     */
    public void refreshView() {

    }

    @Override
    public void show() {
        super.show();
        refreshView();
    }

    /**
     * 初始化界面控件
     */
    private void initView() {

    }

    /**
     * 设置确定取消按钮的回调
     */
    private OnClickListener onClickListener;

    public DeviceCtlDialog setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }

    public interface OnClickListener {
        /**
         * 点击确定按钮事件
         */
        void onConfirmClick(DeviceCtlDialog dialog);

        void onCancelClick(DeviceCtlDialog dialog);
    }

    public void hideBtnConfirm() {
        this.btn_confirm.setVisibility(View.INVISIBLE);
    }

    public void showBtnConfirm() {
        this.btn_confirm.setVisibility(View.VISIBLE);
    }

    public String getMessage() {
        return message;
    }

    public DeviceCtlDialog setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public DeviceCtlDialog setTitle(String title) {
        this.title = title;
        return this;
    }

//    public AppUpdateInfoDialog setBtnText(String text) {
//        this.btnText = text;
//        return this;
//    }

}

