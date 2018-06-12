package kr.hs.dgsw.flow.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.hs.dgsw.flow.R;
import kr.hs.dgsw.flow.model.Leave;
import kr.hs.dgsw.flow.type.LeaveType;

import java.util.ArrayList;
import java.util.List;

import kr.hs.dgsw.flow.model.Leave;

public class LeaveListAdapter extends RecyclerView.Adapter {

    final private List<Leave> mDataList;

    public LeaveListAdapter() {
        mDataList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_leave_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Leave leave = mDataList.get(position);

        ((ViewHolder) holder).bind(leave);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.leave_list_image)
        ImageView typeIcon;
        @BindView(R.id.leave_list_icon)
        ImageView confirmIcon;
        @BindView(R.id.leave_list_title)
        TextView title;
        @BindView(R.id.leave_list_start_time)
        TextView startTime;
        @BindView(R.id.leave_list_end_time)
        TextView endTime;

        ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        void bind(Leave leave) {
            if(leave.getLeaveType() == LeaveType.OUT) {
                typeIcon.setImageResource(R.drawable.ic_walk_white_24dp);
                title.setText(R.string.action_leave_out);
            } else {
                typeIcon.setImageResource(R.drawable.ic_bed_white_24dp);
                title.setText(R.string.action_leave_sleep);
            }

            if(leave.isConfirmed())
                confirmIcon.setImageResource(R.drawable.ic_check_24dp);
            else
                confirmIcon.setImageResource(R.drawable.ic_uncheck_24dp);

            startTime.setText(leave.getStartTime());
            endTime.setText(leave.getEndTime());
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void addItem(Leave leave) { mDataList.add(leave); }

    public void clearData() { mDataList.clear(); }
}
