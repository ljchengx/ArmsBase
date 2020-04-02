package me.jessyan.armscomponent.commonres.loadmore;


import me.jessyan.armscomponent.commonres.R;

/**
 * 描述：
 *
 * @author 林
 * @date 2018/5/12
 */
public final class SimpleLoadMoreView extends LoadMoreView {

    @Override
    public int getLayoutId() {
        return R.layout.brvah_quick_view_load_more;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }

}
