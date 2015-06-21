package DataFilterStratergies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Nikitash Pawar on 6/21/2015.
 */
public class LeastMostFrequentDataFilter implements DataFilterStrategy {
    @Override
    public Double filterData(List<Double> competitorPrices) {

        List<Double> validPrices=new ArrayList<Double>(competitorPrices);
        double sum=0;
        for (Double price: competitorPrices) {
            sum += price;
        }
        double averagePrice= sum/competitorPrices.size();
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
        double chosenValue=validPrices.get(0);
        int chosenValueOccurrenceCount=1;
        int frequency=0;
        for (int i=0;i<validPrices.size();i++){
            frequency = Collections.frequency(validPrices, validPrices.get(i));
            if(frequency > chosenValueOccurrenceCount)
            {
                chosenValue=validPrices.get(i);
                chosenValueOccurrenceCount= frequency;
            }
        }
        return chosenValue;
    }
}
