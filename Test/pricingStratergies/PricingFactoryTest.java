package pricingStratergies;

import org.junit.Assert;
import org.junit.Test;
import priceRecommendationEngine.ProductParameters;

import static org.junit.Assert.*;

public class PricingFactoryTest {

    PricingStrategy pricingStrategy;

    @Test
    public void testGeneratePricingStrategyWhenHighSupplyHighDemand() throws Exception {
        pricingStrategy=PricingFactory.generatePricingStrategy(new ProductParameters('H','H'));
        Assert.assertEquals(new Double(10.0),new Double(pricingStrategy.generatePrice(10.0)));
    }


    @Test
    public void testGeneratePricingStrategyWhenHighSupplyLowDemand() throws Exception {
        pricingStrategy=PricingFactory.generatePricingStrategy(new ProductParameters('H','L'));
        Assert.assertEquals(new Double(9.5),new Double(pricingStrategy.generatePrice(10.0)));
    }

    @Test
    public void testGeneratePricingStrategyWhenLowSupplyHighDemand() throws Exception {
        pricingStrategy=PricingFactory.generatePricingStrategy(new ProductParameters('L','H'));
        Assert.assertEquals(new Double(10.5),new Double(pricingStrategy.generatePrice(10.0)));
    }

    @Test
    public void testGeneratePricingStrategyWhenLowSupplyLowDemand() throws Exception {
        pricingStrategy=PricingFactory.generatePricingStrategy(new ProductParameters('L','L'));
        Assert.assertEquals(new Double(11),new Double(pricingStrategy.generatePrice(10.0)));
    }

    @Test
    public void testGeneratePricingStrategyFailCase() throws Exception {

        try
        {
            pricingStrategy=PricingFactory.generatePricingStrategy(new ProductParameters('M','M'));
        }
        catch (RuntimeException e)
        {
            Assert.assertEquals("No Such Pricing Strategy Found",e.getMessage());
        }
    }
}