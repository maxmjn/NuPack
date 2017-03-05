package com.nupack;

import java.util.HashMap;
import java.util.Map;

/**
 * Calculate final cost by adding appropriate markup prices to provided based price
 *
 */
public class MarkUpCalculator
{

    private static Map<String, Double> categoryMarkUpPercent = new HashMap<String, Double>();

    /**
     * Initialize Map with Category pricing logic
     */
    static {
        categoryMarkUpPercent.put("flat", 0.05);
        categoryMarkUpPercent.put("persons", 0.012);
        categoryMarkUpPercent.put("pharmaceuticals", 0.075);
        categoryMarkUpPercent.put("food", 0.13);
        categoryMarkUpPercent.put("electronics", 0.02);
    }

    public static final Double calculateFinalCost(Double basePrice, int numPersons, String category){

        /**
         * Input validation
         */
        if(basePrice == null || basePrice <= 0){
           throw new RuntimeException("BasePrice is Invalid");
        }
        if(numPersons <= 0){
            throw new RuntimeException("Num Persons is Invalid");
        }
        if(category == null || category.trim().length() == 0){
            throw new RuntimeException("Category is Invalid");
        }

        /**
         * Get pricing for category
         */
        return 0.0;
    }
}
