package priceRecommendationEngine;

import java.util.List;
import java.util.Map;

/**
 * Created by Nikitash Pawar on 6/21/2015.
 */
public class EntryPoint {
    public static void main(String[] args) {

        RecommendationEngineFeed feed=RecommendationEngineFeedBuilder.buildFeedForRecommendationEngine();
        List<Recommendation> recommendations = RecommendationEngine.generateRecommendations(feed);
        System.out.println("Recommended Prices");
        for (Recommendation recommendation: recommendations)
        {
            Map<String,Double> recommendationData= recommendation.getRecommendations();
            System.out.print(recommendationData.keySet().toString());
            System.out.print("=>");
            System.out.println(recommendationData.values().toString());
        }
    }
}
