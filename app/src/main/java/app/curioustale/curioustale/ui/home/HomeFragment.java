package app.curioustale.curioustale.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import app.curioustale.curioustale.R;
import app.curioustale.curioustale.databinding.FragmentHomeBinding;
import app.curioustale.curioustale.models.User;
import app.curioustale.curioustale.repo.auth.FirebaseAuthRepository;
import app.curioustale.curioustale.ui.MainViewModel;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private MainViewModel mainViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.cardQuestionOfDay.setOnClickListener(v -> navigateToQuestionPage());
        binding.cardDayStreak.setOnClickListener(v -> navigateToMyStoriesPage());
        binding.btnMyStories.setOnClickListener(v -> navigateToMyStoriesPage());
        String userId = new FirebaseAuthRepository().getCurrentUser().getUid();
        mainViewModel.getCurrentUserDetails(userId).observe(getViewLifecycleOwner(), this::handleCurrentUser);
        return binding.getRoot();
    }

    private void handleCurrentUser(User user) {
        binding.progress.hide();
        if (user != null && user.getStreak() > 0) {
            binding.cardDayStreak.setVisibility(View.VISIBLE);
            binding.tvDay.setText(String.valueOf(user.getStreak()));
        }
    }

    private void navigateToQuestionPage() {
        NavController controller = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        controller.navigate(R.id.question_fragment);
    }

    private void navigateToMyStoriesPage() {
        NavController controller = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        controller.navigate(R.id.navigation_my_stories);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
