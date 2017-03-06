package com.nupack;

import java.io.*;
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

    /**
     * synchronized to make thread-safe
     */
    protected static synchronized InputStream loadFile(String fileName) {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = null;
        try {
//            System.out.println("File path:" + System.getProperty("user.dir"));
            input = classLoader.getResourceAsStream(fileName);
        } catch (Exception e) {

        } finally {

        }

        return input;
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

        double formattedFinalCost = 0;
        try {
            Double newBasePrice = basePrice + basePrice * getMarkupPercent("flat");
            Double personsCost = newBasePrice * getMarkupPercent("persons") * numPersons;
            Double categoryCost = newBasePrice * getMarkupPercent(category);

            Double finalCost = newBasePrice + personsCost + categoryCost;

            formattedFinalCost = Math.round(finalCost.doubleValue() * 100);
            formattedFinalCost = formattedFinalCost/100;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return formattedFinalCost;
    }

    /**
     * Accepts category type and returns markup percent
     * @param category
     * @return
     */
    protected static Double getMarkupPercent(final String category) {

        if(category.equalsIgnoreCase("food")){

            return categoryMarkUpPercent.get("food");
        } else if(category.equalsIgnoreCase("drugs")){

            return categoryMarkUpPercent.get("pharmaceuticals");
        } else if(category.equalsIgnoreCase("tv") || category.equalsIgnoreCase("phone")){

            return categoryMarkUpPercent.get("electronics");
        } else if(categoryMarkUpPercent.get(category) == null){ //No match found
            return 0.0;
        } else {
            return categoryMarkUpPercent.get(category);
        }
    }
}
