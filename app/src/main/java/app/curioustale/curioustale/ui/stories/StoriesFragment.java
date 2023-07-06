package app.curioustale.curioustale.ui.stories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import app.curioustale.curioustale.databinding.FragmentStoriesBinding;
import app.curioustale.curioustale.models.Answer;
import app.curioustale.curioustale.ui.stories.adapter.StoryAdapter;
import app.curioustale.curioustale.utils.Either;

public class StoriesFragment extends Fragment implements StoryAdapter.OnStoryItemClickListener {

    private FragmentStoriesBinding binding;
    private StoriesViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(StoriesViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentStoriesBinding.inflate(inflater, container, false);
        viewModel.myStories().observe(getViewLifecycleOwner(), this::handleMyStoriesResult);
        return binding.getRoot();
    }

    private void handleMyStoriesResult(Either<Exception, List<Answer>> result) {
        binding.progress.hide();
        if (result.isLeft()) return;
        result.getRight().ifPresent(answerList
                -> binding.rvMyStories.setAdapter(new StoryAdapter(answerList, this)));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onStoryItemClicked(Answer answer) {
        // Unused
    }
}