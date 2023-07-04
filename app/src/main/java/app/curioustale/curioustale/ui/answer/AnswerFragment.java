package app.curioustale.curioustale.ui.answer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import app.curioustale.curioustale.databinding.FragmentAnswerBinding;

public class AnswerFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentAnswerBinding binding;
        binding = FragmentAnswerBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

}