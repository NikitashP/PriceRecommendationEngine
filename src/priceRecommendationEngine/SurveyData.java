package priceRecommendationEngine;

/**
 * Created by Nikitash Pawar on 6/21/2015.
 */
public class SurveyData {

    private String productCode;
    private String competitorName;
    private Double competitorPrice;

    public SurveyData(String productCode, String competitorName, Double competitorPrice) {
        this.productCode = productCode;
        this.competitorName = competitorName;
        this.competitorPrice = competitorPrice;
    }

    public Double getCompetitorPrice() {
        return competitorPrice;
    }
}
