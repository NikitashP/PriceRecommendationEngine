package pricingStratergies;

/**
 * Created by Nikitash Pawar on 6/20/2015.
 */
public class LowSupplyLowDemand implements PricingStrategy {
    @Override
    public double generatePrice(Double chosenPrice) {
        return 1.10*chosenPrice;
    }
}
