import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

//Represents different scenarios for testing out how funds are allocated between regions with
//varying populations and base costs
public class Testing {


    // Behavior: 
    //      - tests to see how funds are allocated to two regions with a budget of $500.
    //      - Region 1 has a population of 100 and a base cost of $400
    //      - while region 2 has a population of 150 and a base cost of $600
    @Test
    @DisplayName("STUDENT TEST - Case #1 (2 regions with one of them exceeding budget)")
    public void firstCaseTest() {
        Allocation allocation = Client.allocateRelief(500, createScenarioOne());
        String result = storeResult(allocation);
        
        assertTrue(scenarioOneExpectedResult().equals(result));
    }

    

    // Behavior: 
    //      - tests to see how funds are allocated to two regions with a budget of $500.
    //      - Region 1 has a population of 150 and a base cost of $400
    //      - while region 2 has a population of 100 and a base cost of $450
    @Test
    @DisplayName("STUDENT TEST - Case #2 (2 regions for comparing most amout of people helped)")
    public void secondCaseTest() {
        Allocation allocation = Client.allocateRelief(500, createScenarioTwo());
        String result = storeResult(allocation);
        
        assertTrue(scenarioTwoExpectedResult().equals(result));
    }
    

    // Behavior: 
    //      - tests to see how funds are allocated to two regions with a budget of $500.
    //      - Region 1 has a population of 150 and a base cost of $450
    //      - while region 2 has a population of 150 and a base cost of $400
    @Test
    @DisplayName("STUDENT TEST - Case #3 (2 regions comparing lowest cost)")
    public void thirdCaseTest() {
        Allocation allocation = Client.allocateRelief(500, createScenarioThree());
        String result = storeResult(allocation);
        
        assertTrue(scenarioThreeExpectedResult().equals(result));
    }


    // Behavior: 
    //      - tests to see how funds are allocated to three regions with a budget of $1000.
    //      - Region 1 has a population of 100 and a base cost of $400
    //      - while region 2 has a population of 150 and a base cost of $600
    //      - and region 3 has a population of 50 and a base cost of $300
    @Test
    @DisplayName("STUDENT TEST - DIY (3 Regions with checking for exceeding budget)")
    public void diyTest() {
        Allocation allocation = Client.allocateRelief(1000, createScenarioFour());
        String result = storeResult(allocation);
        
        assertTrue(scenarioFourExpectedResult().equals(result));
    }



    // Behavior: 
    //      - manually creates two regions for which will be tested for in the first case
    // Returns: 
    //      - List<Region>: all the regions that we will be testing how funds are allocated
    //                      to in case 1
    private static List<Region> createScenarioOne() {
        List<Region> result = new ArrayList<>();

        result.add(new Region("Region #1", 100, 400));
        result.add(new Region("Region #2", 150, 600));
        
        return result;
    }

    // Behavior: 
    //      - manually creates the expected allocation result for funds for the first case
    // Returns: 
    //      - String: representation of which regions funds are expected to be allocated 
    //                to for case 1
    private static String scenarioOneExpectedResult() {
        String result = "";
        result += "Result: \n";
        result += "[Region #1: pop. 100, base cost: $400.0]" + "\n";
        return result;
    }

   // Behavior: 
    //      - manually creates two regions for which will be tested for in the second case
    // Returns: 
    //      - List<Region>: all the regions that we will be testing how funds are allocated
    //                      to in case 2
    private static List<Region> createScenarioTwo() {
        List<Region> result = new ArrayList<>();

        result.add(new Region("Region #1", 150, 400));
        result.add(new Region("Region #2", 100, 450));
        
        return result;
    }

    // Behavior: 
    //      - manually creates the expected allocation result for funds for the second case
    // Returns: 
    //      - String: representation of which regions funds are expected to be allocated 
    //                to for case 2
    private static String scenarioTwoExpectedResult() {
        String result = "";
        result += "Result: \n";
        result += "[Region #1: pop. 150, base cost: $400.0]" + "\n";
        return result;
    }

    // Behavior: 
    //      - manually creates two regions for which will be tested for in the third case
    // Returns: 
    //      - List<Region>: all the regions that we will be testing how funds are allocated
    //                      to in case 3
    private static List<Region> createScenarioThree() {
        List<Region> result = new ArrayList<>();

        result.add(new Region("Region #1", 150, 450));
        result.add(new Region("Region #2", 150, 400));
        
        return result;
    }

    // Behavior: 
    //      - manually creates the expected allocation result for funds for the third case
    // Returns: 
    //      - String: representation of which regions funds are expected to be allocated 
    //                to for case 3
    private static String scenarioThreeExpectedResult() {
        String result = "";
        result += "Result: \n";
        result += "[Region #2: pop. 150, base cost: $400.0]" + "\n";
        return result;
    }

    // Behavior: 
    //      - manually creates three regions for which will be tested for in the fourth case
    // Returns: 
    //      - List<Region>: all the regions that we will be testing how funds are allocated
    //                      to in case 4
    private static List<Region> createScenarioFour() {
        List<Region> result = new ArrayList<>();

        result.add(new Region("Region #1", 100, 400));
        result.add(new Region("Region #2", 150, 600));
        result.add(new Region("Region #3", 50, 300));
        
        return result;
    }

    // Behavior: 
    //      - manually creates the expected allocation result for funds for the fourth case
    // Returns: 
    //      - String: representation of which regions funds are expected to be allocated 
    //                to for case 4
    private static String scenarioFourExpectedResult() {
        String result = "";
        result += "Result: \n";
        result += "[Region #2: pop. 150, base cost: $600.0, Region #3: pop. 50, ";
        result += "base cost: $300.0]" + "\n";
        return result;
    }

    // Behavior: 
    //      - Accepts the allocation that has been provided and provides a representation of 
    //      - the regions within that allocation
    // Returns: 
    //      - String: representation of which regions funds will be allocated to for the provided
    //                allocation
    private static String storeResult(Allocation alloc) {
        String result = "";
        result += "Result: \n";
        result += alloc + "\n";
        return result;
    }
}
