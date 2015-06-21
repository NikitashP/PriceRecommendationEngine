package priceRecommendationEngine;

import DataFilterStratergies.LeastMostFrequentDataFilter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class RecommendationEngineTest {

    RecommendationEngineFeed feedOne;
    RecommendationEngineFeed feedTwo;
    Map<String,Double> expectedRecommendationForFeedOne;
    Map<String,Double> expectedRecommendationForFeedTwo;
    Map<String,Double> actualRecommendationForFeedOne=new HashMap<String, Double>();
    Map<String,Double> actualRecommendationForFeedTwo=new HashMap<String, Double>();
    RecommendationEngine engine;



    @Before
    public void setUp() throws Exception {

        Map<String, ProductParameters> productData= new HashMap<String, ProductParameters>();
        productData.put("flashdrive",new ProductParameters('H','H'));
        productData.put("ssd",new ProductParameters('L','H'));

        Map<String, List<SurveyData>> surveyDataMap =new HashMap<String, List<SurveyData>>();
        List<SurveyData> surveyListFlashDrive=new ArrayList<SurveyData>();
        surveyListFlashDrive.add(new SurveyData("flashdrive","X",1.0));
        surveyListFlashDrive.add(new SurveyData("flashdrive","Y",0.9));
        surveyListFlashDrive.add(new SurveyData("flashdrive","Z",1.1));
        List<SurveyData> surveyListSsd=new ArrayList<SurveyData>();
        surveyListSsd.add(new SurveyData("ssd","X",10.0));
        surveyListSsd.add(new SurveyData("ssd","Y",12.5));
        surveyDataMap.put("flashdrive",surveyListFlashDrive);
        surveyDataMap.put("ssd",surveyListSsd);

        feedOne=new RecommendationEngineFeed(productData,surveyDataMap);
        expectedRecommendationForFeedOne=new HashMap<String, Double>();
        expectedRecommendationForFeedOne.put("flashdrive",0.9);
        expectedRecommendationForFeedOne.put("ssd",10.5);

        productData= new HashMap<String, ProductParameters>();
        productData.put("mp3player",new ProductParameters('H','H'));
        productData.put("ssd",new ProductParameters('L','L'));

        surveyDataMap =new HashMap<String, List<SurveyData>>();
        List<SurveyData> surveyListMp3Player=new ArrayList<SurveyData>();
        surveyListMp3Player.add(new SurveyData("mp3player", "X", 60.0));
        surveyListMp3Player.add(new SurveyData("mp3player", "Y", 20.0));
        surveyListMp3Player.add(new SurveyData("mp3player", "Z", 50.0));
        surveyListSsd=new ArrayList<SurveyData>();
        surveyListSsd.add(new SurveyData("ssd","W",11.0));
        surveyListSsd.add(new SurveyData("ssd","X",12.0));
        surveyListSsd.add(new SurveyData("ssd","V",10.0));
        surveyListSsd.add(new SurveyData("ssd","Y",11.0));
        surveyListSsd.add(new SurveyData("ssd","Z",12.0));
        surveyDataMap.put("mp3player",surveyListMp3Player);
        surveyDataMap.put("ssd",surveyListSsd);

        feedTwo=new RecommendationEngineFeed(productData,surveyDataMap);
        expectedRecommendationForFeedTwo=new HashMap<String, Double>();
        expectedRecommendationForFeedTwo.put("mp3player",50.0);
        expectedRecommendationForFeedTwo.put("ssd",12.1);

    }

    @Test
    public void testGenerateRecommendationsForFeedOne() throws Exception {

        engine=new RecommendationEngine(new LeastMostFrequentDataFilter());
        for (Map<String,Double> recommendation: engine.generateRecommendations(feedOne))
        {
            actualRecommendationForFeedOne.putAll(recommendation);
        }
        Assert.assertEquals(expectedRecommendationForFeedOne, actualRecommendationForFeedOne);
        System.out.println(actualRecommendationForFeedOne.toString());

    }

    @Test
    public void testGenerateRecommendationsForFeedTwo() throws Exception {
        engine=new RecommendationEngine(new LeastMostFrequentDataFilter());
        for (Map<String,Double> recommendation: engine.generateRecommendations(feedTwo))
        {
           actualRecommendationForFeedTwo.putAll(recommendation);
        }
        Assert.assertEquals(expectedRecommendationForFeedTwo,actualRecommendationForFeedTwo);
        System.out.println(actualRecommendationForFeedTwo.toString());
    }
}