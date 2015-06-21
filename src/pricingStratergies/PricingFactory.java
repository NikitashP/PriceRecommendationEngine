package pricingStratergies;

import priceRecommendationEngine.ProductParameters;

/**
 * Created by Nikitash Pawar on 6/20/2015.
 */
public class PricingFactory {

    private static final char HIGH = 'H';
    private static final char LOW = 'L';

    public static PricingStrategy generatePricingStrategy(ProductParameters productParameters)
    {
        Character supply=productParameters.getSupply();
        Character demand=productParameters.getDemand();
        if(supply.equals(HIGH) && demand.equals(HIGH))
            return new HighSupplyHighDemand();
        else if (supply.equals(HIGH) && demand.equals(LOW))
            return new HighSupplyLowDemand();
        else if (supply.equals(LOW) && demand.equals(HIGH))
            return new LowSupplyHighDemand();
        else if (supply.equals(LOW) && demand.equals(LOW))
            return new LowSupplyLowDemand();
        else
            throw new RuntimeException("No Such Pricing Strategy Found");
    }
}
