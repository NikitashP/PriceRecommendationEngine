package priceRecommendationEngine;

import java.util.*;

/**
 * Created by Nikitash Pawar on 6/21/2015.
 */
public class RecommendationEngineFeedBuilder {


    private static Scanner console= new Scanner(System.in);
    private static List<String> productNames=new ArrayList<String>();

    public static RecommendationEngineFeed buildFeedForRecommendationEngine()
    {
        HashMap<String,ProductParameters> productData=new HashMap<String, ProductParameters>();
        Map<String,List<SurveyData>> surveyDataMap=new HashMap<String, List<SurveyData>>();
        String productName;
        String competitorName;
        Double competitorPrice;
        Character supply;
        Character demand;
        int numberOfProducts = acceptNumberOfProducts();
        for (int i = 0; i < numberOfProducts ; i++) {
            productName=acceptProductName();
            supply=acceptProductSupplyValue();
            demand=acceptProductDemandValue();
            productData.put(productName,new ProductParameters(supply,demand));

        }

        int numberOfSampleProducts= acceptNumberOfProducts();
        for (int i = 0; i < numberOfSampleProducts ; i++) {
            productName=acceptSampleProductName();
            competitorName=acceptProductManufacturerName();
            competitorPrice=acceptProductPrice();
            SurveyData survey=new SurveyData(productName,competitorName,competitorPrice);
            if(surveyDataMap.containsKey(productName))
            {
                surveyDataMap.get(productName).add(survey);
            }
            else
            {
                List<SurveyData> surveyData=new ArrayList<SurveyData>();
                surveyData.add(survey);
                surveyDataMap.put(productName,surveyData);
            }

        }

        return new RecommendationEngineFeed(productData,surveyDataMap);
    }

    private static Double acceptProductPrice() {
        System.out.println("Enter valid Price of the Product");
        Double priceOfProduct=console.nextDouble();
        if(priceOfProduct == 0D)
        {
            System.out.println("Price of Product cannot be Zero.");
            priceOfProduct=acceptProductPrice();
        }
        return priceOfProduct;
    }

    private static String acceptProductManufacturerName() {
        System.out.println("Enter Product Manufacturer Name");
        String manufacturerName= console.next();
        if(manufacturerName.isEmpty())
        {
            System.out.println("Product Manufacturer Name cannot be Empty.");
            manufacturerName=acceptProductName();
        }
        return manufacturerName;
    }

    private static int acceptNumberOfProducts() {
        System.out.println("Enter valid Number of Products");
        int numberOfProducts=console.nextInt();
        if(numberOfProducts==0)
        {
            System.out.println("Invalid Number of Products.");
            numberOfProducts= acceptNumberOfProducts();
        }
        return numberOfProducts;
    }

    private static String acceptProductName() {
        System.out.println("Enter Product Name");
        String productName= console.next();
        if(productName.isEmpty())
        {
            System.out.println("Product Name cannot be Empty.");
            productName=acceptProductName();
        }
        else if(productNames.contains(productName))
        {
            System.out.println("Product Information already Present. Add new information");
            productName=acceptProductName();
        }
        productNames.add(productName);
        return productName;
    }
    private static String acceptSampleProductName() {
        System.out.println("Enter Valid Sample Product Name");
        String sampleProductName= console.next();
        if(sampleProductName.isEmpty())
        {
            System.out.println("Product Name cannot be Empty.");
            sampleProductName=acceptSampleProductName();
        }
        else if(!productNames.contains(sampleProductName))
        {
            System.out.println("This Product is not one of the Products being considered for Price recommendation.");
            sampleProductName=acceptSampleProductName();
        }
        return sampleProductName;
    }
    private static Character acceptProductSupplyValue()
    {
        System.out.println("Enter Supply Value Low(L)/High(H)");
        Character supply=console.next().charAt(0);
        if(supply.equals('H') || supply.equals('L'))
        {
            return supply;
        }
        else
        {
            System.out.println("Invalid Value.");
            supply=acceptProductSupplyValue();
        }
        return supply;

    }
    private static Character acceptProductDemandValue()
    {
        System.out.println("Enter Demand Value Low(L)/High(H)");
        Character demand=console.next().charAt(0);
        if(demand.equals('H') || demand.equals('L'))
        {
            return demand;
        }
        else
        {
            System.out.println("Invalid Value.");
            demand=acceptProductDemandValue();
        }
        return demand;
    }

}
