package DataFilterStratergies;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class LeastMostFrequentDataFilterTest {
    DataFilterStrategy filter=new LeastMostFrequentDataFilter();

    @Test
    public void testFilterDataWhenSecondLeastNumberIsRepeated() throws Exception {
        List<Double> input=Arrays.asList(10.0,11.0,11.0,12.0,12.0);
        Assert.assertEquals(new Double(11.0),filter.filterData(input));
    }

    @Test
    public void testFilterDataWhenOneNumberIsRepeatedManyTimes() throws Exception {
        List<Double> input=Arrays.asList(11.0,11.0,11.0,12.0,12.0);
        Assert.assertEquals(new Double(11.0),filter.filterData(input));
    }
}