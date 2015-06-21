package pricingStratergies;

/**
 * Created by Nikitash Pawar on 6/20/2015.
 */
public class HighSupplyHighDemand implements PricingStrategy {
    @Override
    public double generatePrice(Double chosenPrice) {
        return chosenPrice;
    }
}
