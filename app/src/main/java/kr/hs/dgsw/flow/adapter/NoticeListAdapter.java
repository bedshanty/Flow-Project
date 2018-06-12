package kr.hs.dgsw.flow.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import kr.hs.dgsw.flow.R;
import kr.hs.dgsw.flow.network.networkModel.Notice.Notice;

public class NoticeListAdapter extends RecyclerView.Adapter {

    private ArrayList<Notice> mDataList;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder {
        
    }
}
