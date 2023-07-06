package app.curioustale.curioustale.ui.stories.sheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import app.curioustale.curioustale.config.Constants;
import app.curioustale.curioustale.databinding.SheetStoryDetailBinding;

public class StoryDetailSheet extends BottomSheetDialogFragment {
    public static final String TAG = "story-detail-sheet";

    private SheetStoryDetailBinding binding;
    private String question;
    private String answer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            question = getArguments().getString(Constants.KEY_QUESTION_TITLE, "");
            answer = getArguments().getString(Constants.KEY_ANSWER, "");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = SheetStoryDetailBinding.inflate(inflater, container, false);
        binding.tvAnswer.setText(answer);
        binding.tvQuestion.setText(question);
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
