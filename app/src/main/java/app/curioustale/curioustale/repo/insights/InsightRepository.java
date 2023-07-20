package app.curioustale.curioustale.repo.insights;

import java.util.List;

import app.curioustale.curioustale.models.Insight;

public interface InsightRepository {
    void listInsights(InsightListListener listener);

    interface InsightListListener {
        void onInsightsListResult(List<Insight> insights);
        void onInsightsListError(Exception e);
    }
}
