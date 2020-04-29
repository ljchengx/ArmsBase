package com.ljchengx.mine.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.ljchengx.mine.R;
import com.ljchengx.mine.R2;
import com.ljchengx.mine.di.component.DaggerMineComponent;
import com.ljchengx.mine.mvp.contract.MineContract;
import com.ljchengx.mine.mvp.presenter.MinePresenter;
import com.xuexiang.xui.utils.DensityUtils;
import com.xuexiang.xui.widget.grouplist.XUICommonListItemView;
import com.xuexiang.xui.widget.grouplist.XUIGroupListView;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by ljchengx on 04/29/2020 17:22
 * ================================================
 */
public class MineFragment extends BaseFragment<MinePresenter> implements MineContract.View {

    @BindView(R2.id.groupListView)
    XUIGroupListView mGroupListView;

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerMineComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initGroupListView();
    }

    private void initGroupListView() {
        XUICommonListItemView item1 = mGroupListView.createItemView("我的积分");
        item1.setAccessoryType(XUICommonListItemView.ACCESSORY_TYPE_CHEVRON);


        XUICommonListItemView item2 = mGroupListView.createItemView("我的分享");
        item2.setAccessoryType(XUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        XUICommonListItemView item3 = mGroupListView.createItemView("我的收藏");
        item3.setAccessoryType(XUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        XUICommonListItemView item4 = mGroupListView.createItemView("关于作者");
        item4.setAccessoryType(XUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        XUICommonListItemView item5 = mGroupListView.createItemView("系统设置");
        item5.setAccessoryType(XUICommonListItemView.ACCESSORY_TYPE_CHEVRON);


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof XUICommonListItemView) {
                    CharSequence text = ((XUICommonListItemView) v).getText();
                   showMessage(text + " is Clicked");
                }
            }
        };

        int size = DensityUtils.dp2px(getContext(), 20);
        XUIGroupListView.newSection(getContext())
                .setTitle("Section 1: 默认提供的样式")
                .setDescription("Section 1 的描述")
                .setLeftIconSize(size, ViewGroup.LayoutParams.WRAP_CONTENT)
                .addItemView(item1, onClickListener)
                .addItemView(item2, onClickListener)
                .addItemView(item3, onClickListener)
                .addItemView(item4, onClickListener)
                .addItemView(item5, onClickListener)
                .addTo(mGroupListView);

    }

    /**
     * 通过此方法可以使 Fragment 能够与外界做一些交互和通信, 比如说外部的 Activity 想让自己持有的某个 Fragment 对象执行一些方法,
     * 建议在有多个需要与外界交互的方法时, 统一传 {@link Message}, 通过 what 字段来区分不同的方法, 在 {@link #setData(Object)}
     * 方法中就可以 {@code switch} 做不同的操作, 这样就可以用统一的入口方法做多个不同的操作, 可以起到分发的作用
     * <p>
     * 调用此方法时请注意调用时 Fragment 的生命周期, 如果调用 {@link #setData(Object)} 方法时 {@link Fragment#onCreate(Bundle)} 还没执行
     * 但在 {@link #setData(Object)} 里却调用了 Presenter 的方法, 是会报空的, 因为 Dagger 注入是在 {@link Fragment#onCreate(Bundle)} 方法中执行的
     * 然后才创建的 Presenter, 如果要做一些初始化操作,可以不必让外部调用 {@link #setData(Object)}, 在 {@link #initData(Bundle)} 中初始化就可以了
     * <p>
     * Example usage:
     * <pre>
     * public void setData(@Nullable Object data) {
     *     if (data != null && data instanceof Message) {
     *         switch (((Message) data).what) {
     *             case 0:
     *                 loadData(((Message) data).arg1);
     *                 break;
     *             case 1:
     *                 refreshUI();
     *                 break;
     *             default:
     *                 //do something
     *                 break;
     *         }
     *     }
     * }
     *
     * // call setData(Object):
     * Message data = new Message();
     * data.what = 0;
     * data.arg1 = 1;
     * fragment.setData(data);
     * </pre>
     *
     * @param data 当不需要参数时 {@code data} 可以为 {@code null}
     */
    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {

    }
}
