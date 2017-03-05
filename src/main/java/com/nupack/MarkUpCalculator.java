package com.nupack;

/**
 * Calculate final cost by adding appropriate markup prices to provided based price
 *
 */
public class MarkUpCalculator
{

    public static final Double calculateFinalCost(Double basePrice, int numPersons, String category){

        if(basePrice == null || basePrice <= 0){
           throw new RuntimeException("BasePrice is Invalid");
        }
        if(numPersons <= 0){
            throw new RuntimeException("Num Persons is Invalid");
        }
        if(category == null || category.trim().length() == 0){
            throw new RuntimeException("Category is Invalid");
        }
        return 0.0;
    }
}
