import java.util.*;

public class Client {
    private static final Random RAND = new Random();

    public static void main(String[] args) throws Exception {
        //List<Region> scenario = createRandomScenario(10, 10, 100, 1000, 100000);
        List<Region> scenario = createSimpleScenario();
        System.out.println(scenario);
        
        double budget = 2000;
        Allocation allocation = allocateRelief(budget, scenario);
        printResult(allocation, budget);
    }


    // Behavior: 
    //      - provides an allocation of the budget provided that assists the most amount of 
    //      - people in total for the lowest cost. If there is a tie between allocations that 
    //      - help the most people, then the allocation with the lowest cost is provided. If
    //      - there are multiple allocations that help the most amount of people for the same
    //      - then one of them will be provided.
    // Parameter:
    //      - 'budget': the amount of money that is given to allocate to different regions 
    //                  (the budget is assumed to be non-negative)
    //      - 'sites': the regions where the money can be allocated to (has a given population of
    //                 people and a base cost for the amount of money needed to provided relief to
    //                 the disaster-stricken region). This list of regions is assumed 
    //                 to be non-null.
    // Returns: 
    //      - Allocation: true if the drop was successful (commit was present in the repository
    //      - and was removed) and false if the drop was unsuccesful (commit wasn't present in
    //      - the repository so nothing was removed)
    public static Allocation allocateRelief(double budget, List<Region> sites) {
        // TODO: implement your method here
        Set<Allocation> allocations = new HashSet<>();
        Allocation best = new Allocation();
        allocationRelief(new Allocation(), budget, sites, allocations);

        for(Allocation current: allocations){
            if(current.totalPeople() > best.totalPeople() || 
                    (current.totalPeople() == best.totalPeople() && 
                            current.totalCost() < best.totalCost())){
                best = current;
            }
        }

        return best;
    }

    

    // TODO: add any of your own helper methods here


    // Behavior: 
    //      - provides an allocation of the budget provided that assists the most amount of 
    //      - people in total for the lowest cost
    // Parameter:
    //      - 'allocation': a possible allocation for the budget to different regions 
    //      - 'budget': the amount of money that is given to allocate to different regions 
    //                  (the budget is assumed to be non-negative)
    //      - 'sites': the regions where the money can be allocated to (has a given population of
    //                 people and a base cost for the amount of money needed to provided relief to
    //                 the disaster-stricken region). This list of regions is assumed 
    //                 to be non-null.
    //      - 'allocations': will hold all the possible allocations to compare which one is the
    //                       most optimal 
    private static void allocationRelief(Allocation allocation, double budget, List<Region> sites, Set<Allocation> allocations){
        if(sites.size() > 0){
            for(int i = 0; i < sites.size(); i++){
                Region currentRegion = sites.get(i);
                double newTotalCost = allocation.withRegion(currentRegion).totalCost();

                if(newTotalCost <= budget){
                    sites.remove(i);
                    allocationRelief(allocation.withRegion(currentRegion), budget, sites, allocations);
                    sites.add(i, currentRegion);
                }else{
                    allocations.add(allocation);
                }
            }

        }else{
            allocations.add(allocation);
        }

    }

    ///////////////////////////////////////////////////////////////////////////
    // PROVIDED HELPER METHODS - **DO NOT MODIFY ANYTHING BELOW THIS LINE!** //
    ///////////////////////////////////////////////////////////////////////////
    
    /**
    * Prints each allocation in the provided set. Useful for getting a quick overview
    * of all allocations currently in the system.
    * @param allocations Set of allocations to print
    */
    public static void printAllocations(Set<Allocation> allocations) {
        System.out.println("All Allocations:");
        for (Allocation a : allocations) {
            System.out.println("  " + a);
        }
    }

    /**
    * Prints details about a specific allocation result, including the total people
    * helped, total cost, and any leftover budget. Handy for checking if we're
    * within budget limits!
    * @param alloc The allocation to print
    * @param budget The budget to compare against
    */
    public static void printResult(Allocation alloc, double budget) {
        System.out.println("Result: ");
        System.out.println("  " + alloc);
        System.out.println("  People helped: " + alloc.totalPeople());
        System.out.printf("  Cost: $%.2f\n", alloc.totalCost());
        System.out.printf("  Unused budget: $%.2f\n", (budget - alloc.totalCost()));
        
    }

    /**
    * Creates a scenario with numRegions regions by randomly choosing the population 
    * and cost of each region.
    * @param numLocs Number of regions to create
    * @param minPop Minimum population per region
    * @param maxPop Maximum population per region
    * @param minCostPer Minimum cost per person
    * @param maxCostPer Maximum cost per person
    * @return A list of randomly generated regions
    */
    public static List<Region> createRandomScenario(int numLocs, int minPop, int maxPop,
                                                    double minCostPer, double maxCostPer) {
        List<Region> result = new ArrayList<>();

        for (int i = 0; i < numLocs; i++) {
            int pop = RAND.nextInt(minPop, maxPop + 1);
            double cost = RAND.nextDouble(minCostPer, maxCostPer) * pop;
            result.add(new Region("Region #" + i, pop, round2(cost)));
        }

        return result;
    }

    /**
    * Manually creates a simple list of regions to represent a known scenario.
    * @return A simple list of regions
    */
    public static List<Region> createSimpleScenario() {
        List<Region> result = new ArrayList<>();

        result.add(new Region("Region #1", 50, 500));
        result.add(new Region("Region #2", 100, 700));
        result.add(new Region("Region #3", 50, 1000));
        // result.add(new Region("Region #4", 20, 1000));
        // result.add(new Region("Region #5", 200, 900));

        return result;
    }    

    /**
    * Rounds a number to two decimal places.
    * @param num The number to round
    * @return The number rounded to two decimal places
    */
    private static double round2(double num) {
        return Math.round(num * 100) / 100.0;
    }
}
