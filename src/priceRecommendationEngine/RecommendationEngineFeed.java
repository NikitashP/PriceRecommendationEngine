package priceRecommendationEngine;

import java.util.*;

/**
 * Created by Nikitash Pawar on 6/21/2015.
 */
public class RecommendationEngineFeed {

    public RecommendationEngineFeed(Map<String, ProductParameters> productData, Map<String, List<SurveyData>> surveyDataMap) {
        this.productData = productData;
        this.surveyDataMap = surveyDataMap;
    }

    public Map<String, ProductParameters> getProductData() {
        return productData;
    }

    public Map<String, List<SurveyData>> getSurveyDataMap() {
        return surveyDataMap;
    }

    private Map<String,ProductParameters> productData;
    private Map<String,List<SurveyData>> surveyDataMap;
}
