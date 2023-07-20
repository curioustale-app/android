package app.curioustale.curioustale.ui.insights;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import app.curioustale.curioustale.models.Insight;
import app.curioustale.curioustale.repo.insights.FirebaseInsightRepository;
import app.curioustale.curioustale.repo.insights.InsightRepository;

public class InsightsViewModel extends ViewModel {
    private final InsightRepository insightRepository;
    private MutableLiveData<List<Insight>> insights;

    public InsightsViewModel() {
        insightRepository = new FirebaseInsightRepository();
    }

    public MutableLiveData<List<Insight>> getInsights() {
        if (insights != null) return insights;
        insights = new MutableLiveData<>();
        insightRepository.listInsights(new InsightRepository.InsightListListener() {
            @Override
            public void onInsightsListResult(List<Insight> result) {
                insights.postValue(result);
            }

            @Override
            public void onInsightsListError(Exception e) {
                insights.postValue(new ArrayList<>());
            }
        });
        return insights;
    }
}
