package pricingStratergies;

/**
 * Created by Nikitash Pawar on 6/20/2015.
 */
public class HighSupplyLowDemand implements PricingStrategy {
    @Override
    public double generatePrice(Double chosenPrice) {
        return 0.95*chosenPrice;
    }
}
