package app.curioustale.curioustale.ui.stories.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.curioustale.curioustale.databinding.ItemStoryBinding;
import app.curioustale.curioustale.models.Answer;
import app.curioustale.curioustale.utils.DateTimeUtils;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.MyViewHolder> {
    private final List<Answer> answers;
    private final StoryAdapter.OnStoryItemClickListener listener;

    public StoryAdapter(List<Answer> answers, StoryAdapter.OnStoryItemClickListener listener) {
        this.listener = listener;
        this.answers = answers;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemStoryBinding binding = ItemStoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Answer answer = answers.get(position);
        holder.binding.tvQuestion.setText(answer.getQuestion());
        holder.binding.tvAnswer.setText(answer.getAnswer());
        holder.binding.tvDay.setText(DateTimeUtils.getDayFromTimestamp(answer.getTimestamp()));
        holder.binding.tvMonth.setText(DateTimeUtils.getMonthFromTimestamp(answer.getTimestamp()));
    }

    @Override
    public int getItemCount() {
        return answers.size();
    }

    public interface OnStoryItemClickListener {
        void onStoryItemClicked(Answer answer);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ItemStoryBinding binding;

        public MyViewHolder(@NonNull ItemStoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.cvStoryItem.setOnClickListener(v ->
                    listener.onStoryItemClicked(answers.get(getAdapterPosition())));
        }
    }
}
