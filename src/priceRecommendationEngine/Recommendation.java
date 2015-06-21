package priceRecommendationEngine;

import pricingStratergies.PricingStrategy;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Nikitash Pawar on 6/20/2015.
 */
public class Recommendation {

    private String nameOfProduct;
    private PricingStrategy pricingStrategy;
    private List<Double> competitorPrices;
    private double recommendedPrice;

    public Recommendation(String nameOfProduct, PricingStrategy pricingStrategy,List<SurveyData> surveyData) {

        if(nameOfProduct.isEmpty() || null==pricingStrategy || surveyData.isEmpty())
        {
            throw new RuntimeException("Data Not Fed Properly. Check Data.");
        }

        this.nameOfProduct = nameOfProduct;
        this.pricingStrategy = pricingStrategy;
        aggregateCompetitorPrices(surveyData);
        generateProductRecommendedPrice();
    }

    private void aggregateCompetitorPrices(List<SurveyData> surveyData) {

        competitorPrices=new ArrayList<Double>();
        for (SurveyData data: surveyData)
        {
            competitorPrices.add(data.getCompetitorPrice());
        }
    }

    private void generateProductRecommendedPrice()
    {
        List<Double> validPrices=new ArrayList<Double>(competitorPrices);
        double averagePrice= getAverageCompetitorPrice();
        double price=0;
        for (int i=0;i<validPrices.size();i++)
        {
            price=validPrices.get(i);
            if(price > averagePrice*1.50 || price < averagePrice*0.5)
            {
            validPrices.remove(price);
            }
        }

        if (validPrices.isEmpty()) throw new RuntimeException("None Of the Prices Considered for price priceRecommendationEngine.Recommendation");
        Collections.sort(validPrices);
        double chosenPrice=choosePriceFrom(validPrices);
        recommendedPrice=new BigDecimal(pricingStrategy.generatePrice(chosenPrice)).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    private double choosePriceFrom(List<Double> validPrices) {

        double chosenValue=validPrices.get(0);
        int chosenValueOccurrenceCount=1;
        for (int i=0;i<validPrices.size();i++){
            if(Collections.frequency(validPrices,validPrices.get(i))>chosenValueOccurrenceCount)
            {
            chosenValue=validPrices.get(i);
            chosenValueOccurrenceCount=Collections.frequency(validPrices,validPrices.get(i));
            }
        }
        return chosenValue;
    }

    private Double getAverageCompetitorPrice()
    {
        List<Double> priceList=new ArrayList<Double>(competitorPrices);
        double sum=0;
        for (Double price: priceList) {
            sum += price;
        }
        return sum/priceList.size();
    }

    public Map<String,Double> getRecommendations() {
        Map<String,Double> recommendation= new HashMap<String,Double>();
        recommendation.put(nameOfProduct,recommendedPrice);
        return recommendation;
    }
}
