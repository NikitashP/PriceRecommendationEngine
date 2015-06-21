package priceRecommendationEngine;

import pricingStratergies.PricingFactory;
import pricingStratergies.PricingStrategy;

import java.util.*;

/**
 * Created by Nikitash Pawar on 6/20/2015.
 */
public class RecommendationEngine {

    public static List<Recommendation> generateRecommendations(RecommendationEngineFeed feed) {

        Map<String, ProductParameters> productData=feed.getProductData();
        Map<String,List<SurveyData>> surveyData=feed.getSurveyDataMap();
        ProductParameters productParameters;
        List<SurveyData> surveyDataList;
        List<Recommendation> recommendations=new ArrayList<Recommendation>();
        for(String productName : productData.keySet() )
        {
            productParameters=productData.get(productName);
            surveyDataList=surveyData.get(productName);
            PricingStrategy pricingStrategy= PricingFactory.generatePricingStrategy(productParameters);
            Recommendation recommendation=new Recommendation(productName,pricingStrategy,surveyDataList);
            recommendations.add(recommendation);
        }
        return recommendations;
    }
}
