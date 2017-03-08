package com.nupack;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Calculate final cost by adding appropriate markup prices to provided based price
 *
 */
public class MarkUpCalculator
{

    private static Map<String, Double> categoryMarkUpPercent = new HashMap<String, Double>();

    public static Map<String, Double> getCategoryMarkUpPercent() {
        return categoryMarkUpPercent;
    }

    public static void setCategoryMarkUpPercent(Map<String, Double> categoryMarkUpPercent) {
        MarkUpCalculator.categoryMarkUpPercent = categoryMarkUpPercent;
    }

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
     *
     */
    protected static void initializeMarkUpPercent(String fileName) {

        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = loadFile(fileName);

            // load a properties file
            prop.load(input);

            Enumeration<String> keys = (Enumeration<String>) prop.propertyNames();
            // get the property value to initialze
            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                Double value = null;
                try {
                    value = Double.valueOf(prop.getProperty(key));
                    categoryMarkUpPercent.put(key, value);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("File key values NaN");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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

    /**
     *
     * @param params
     * @param fileName
     * @return
     */
    public static final Double calculateFinalCost(String params, String fileName){

        //read file
        //built map - ensure keys match - if not discard file and use default
        //then call overloaded method

        initializeMarkUpPercent(fileName);
        return calculateFinalCost(params);
    }

    /**
     *
     * @param params
     * @return
     */
    public static final Double calculateFinalCost(final String params){

        String[] inputArray = params.split(",");
        Double basePrice = 0.0;
        Integer numPersons = 0;
        String category = "";

        if (inputArray == null || inputArray.length != 3) {
            throw new RuntimeException("Usage: basePrice,numPersons,food|drugs|electronics");
        } else {

//            parseInputParams(inputArray);
//            basePrice = Double.valueOf(inputArray[0].trim());
//            numPersons = Integer.valueOf(inputArray[1].trim());
//            category = inputArray[2].trim();

            //for array location check data type numPersons should be +ve integer, category should be string
            for (int i = 0; i < inputArray.length; i++) {
                if(inputArray[i] != null){
                    switch (i){
                        case 0:
                            try {
                                basePrice = Double.valueOf(inputArray[i].trim());
                                if(basePrice <=0){
                                    throw new RuntimeException("BasePrice can't be <= 0");
                                }
                            } catch (NumberFormatException e) {
                                throw new RuntimeException("BasePrice is NaN");
                            }
                            break;
                        case 1:
                            try {
                                numPersons = Integer.valueOf(inputArray[i].trim());
                                if(numPersons <=0){
                                    throw new RuntimeException("NumPersons can't be <= 0");
                                }
                            } catch (NumberFormatException e) {
                                throw new RuntimeException("NumPersons is NaN");
                            }
                            break;
                        case 2:
                            category = inputArray[i].trim();
                            if(category == null || category.length() == 0){
                                throw new RuntimeException("Category can't be empty");
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
            return calculateFinalCost(basePrice, numPersons, category);
        }
    }

    /**
     *
     * @param inputArray
     */
//    protected static void parseInputParams(String[] inputArray) {
//        Double basePrice = 0.0;
//        Integer numPersons = 0;
//        String category = "";
//
//        //for array location check data type numPersons should be +ve integer, category should be string
//        for (int i = 0; i < inputArray.length; i++) {
//            if(inputArray[i] != null){
//                switch (i){
//                    case 0:
//                        try {
//                            basePrice = Double.valueOf(inputArray[i].trim());
//                            if(basePrice <=0){
//                                throw new RuntimeException("BasePrice can't be <= 0");
//                            }
//                        } catch (NumberFormatException e) {
//                            throw new RuntimeException("BasePrice is NaN");
//                        }
//                        break;
//                    case 1:
//                        try {
//                            numPersons = Integer.valueOf(inputArray[i].trim());
//                            if(numPersons <=0){
//                                throw new RuntimeException("NumPersons can't be <= 0");
//                            }
//                        } catch (NumberFormatException e) {
//                            throw new RuntimeException("NumPersons is NaN");
//                        }
//                        break;
//                    case 2:
//                        category = inputArray[i].trim();
//                        if(category == null || category.length() == 0){
//                            throw new RuntimeException("Category can't be empty");
//                        }
//                        break;
//                    default:
//                        break;
//                }
//            }
//        }
//    }

    /**
     *
     * @param basePrice
     * @param numPersons
     * @param category
     * @return
     */
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
        } else if(category.equalsIgnoreCase("drugs") || category.equalsIgnoreCase("pharmaceuticals")){

            return categoryMarkUpPercent.get("pharmaceuticals");
        } else if(category.equalsIgnoreCase("electronics")
                    || category.equalsIgnoreCase("tv") || category.equalsIgnoreCase("phone")){

            return categoryMarkUpPercent.get("electronics");
        } else if(categoryMarkUpPercent.get(category) == null){ //No match found
            return 0.0;
        } else {
            return categoryMarkUpPercent.get(category);
        }
    }
}
