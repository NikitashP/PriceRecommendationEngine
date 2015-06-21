package priceRecommendationEngine;

import DataFilterStratergies.DataFilterStrategy;
import pricingStratergies.PricingFactory;
import pricingStratergies.PricingStrategy;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Nikitash Pawar on 6/20/2015.
 */
public class RecommendationEngine {

    private PricingStrategy pricingStrategy;
    private DataFilterStrategy dataFilterStrategy;

     public RecommendationEngine(DataFilterStrategy dataFilterStrategy) {
        this.dataFilterStrategy = dataFilterStrategy;
     }

     public List<Map<String,Double>> generateRecommendations(RecommendationEngineFeed feed) {

        Map<String, ProductParameters> productData=feed.getProductData();
        Map<String,List<SurveyData>> surveyData=feed.getSurveyDataMap();
        List<Map<String,Double>> recommendations=new ArrayList<Map<String, Double>>();
        ProductParameters productParameters;
        List<SurveyData> surveyDataList;
        double recommendedPrice=0;
        double chosenPrice=0;
        Map<String,Double> recommendation;
        for(String productName : productData.keySet() )
        {
            productParameters=productData.get(productName);
            surveyDataList=surveyData.get(productName);
            chosenPrice=dataFilterStrategy.filterData(feed.getCompetitorPrices(productName));
            pricingStrategy=PricingFactory.generatePricingStrategy(productData.get(productName));
            recommendedPrice=pricingStrategy.generatePrice(chosenPrice);
            recommendedPrice=new BigDecimal(recommendedPrice).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
            recommendation= new HashMap<String,Double>();
            recommendation.put(productName,recommendedPrice);
            recommendations.add(recommendation);
        }
        return recommendations;
    }
}
