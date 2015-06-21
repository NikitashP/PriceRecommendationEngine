package priceRecommendationEngine;

/**
 * Created by Nikitash Pawar on 6/21/2015.
 */
public class ProductParameters {
    public Character getSupply() {
        return supply;
    }

    public Character getDemand() {
        return demand;
    }

    private Character supply;
    private Character demand;

    public ProductParameters(Character supply, Character demand) {
        this.supply = supply;
        this.demand = demand;
    }
}
