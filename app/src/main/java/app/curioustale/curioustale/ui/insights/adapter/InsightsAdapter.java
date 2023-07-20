package app.curioustale.curioustale.ui.insights.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.curioustale.curioustale.databinding.ItemInsightBinding;
import app.curioustale.curioustale.models.Insight;

public class InsightsAdapter extends RecyclerView.Adapter<InsightsAdapter.MyViewHolder> {
    private final List<Insight> insights;
    private final OnInsightClickListener listener;

    public InsightsAdapter(List<Insight> insights, InsightsAdapter.OnInsightClickListener listener) {
        this.insights = insights;
        this.listener = listener;
    }

    @NonNull
    @Override
    public InsightsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemInsightBinding binding = ItemInsightBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull InsightsAdapter.MyViewHolder holder, int position) {
        Insight insight = insights.get(position);
        holder.binding.tvTitle.setText(insight.getTitle());
        holder.binding.tvSubtitle.setText(insight.getSubtitle());
        holder.binding.tvTypeDate.setText(insight.getFormattedDayAndType());
    }

    @Override
    public int getItemCount() {
        return insights.size();
    }

    public interface OnInsightClickListener {
        void onInsightClicked(Insight insight);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ItemInsightBinding binding;

        public MyViewHolder(@NonNull ItemInsightBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.cvInsightItem.setOnClickListener(v ->
                    listener.onInsightClicked(insights.get(getAdapterPosition())));
        }
    }
}
