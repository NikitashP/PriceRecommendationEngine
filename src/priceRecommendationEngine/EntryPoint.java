package priceRecommendationEngine;

import DataFilterStratergies.LeastMostFrequentDataFilter;
import pricingStratergies.PricingFactory;
import pricingStratergies.PricingStrategy;

import java.util.List;
import java.util.Map;

/**
 * Created by Nikitash Pawar on 6/21/2015.
 */
public class EntryPoint {
    public static void main(String[] args) {

        RecommendationEngine engine=new RecommendationEngine(new LeastMostFrequentDataFilter());
        RecommendationEngineFeed feed=RecommendationEngineFeedBuilder.buildFeedForRecommendationEngine();
        List<Map<String,Double>> recommendations = engine.generateRecommendations(feed);
        System.out.println("Recommended Prices");
        for (Map<String,Double> recommendation: recommendations)
        {
            System.out.print(recommendation.keySet().toString());
            System.out.print("=>");
            System.out.println(recommendation.values().toString());
        }
    }
}
