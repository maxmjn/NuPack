package com.nupack;


import org.junit.*;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Unit test for simple MarkUpCalculator.
 */
public class MarkUpCalculatorTest
{
    @BeforeClass
    public static void testSetup() throws Exception {

    }

    @AfterClass
    public static void testTearDown() throws Exception{

    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     *
     */
    @Test(expected = RuntimeException.class)
    public final void whenBasePriceIsNotValid(){
        MarkUpCalculator.calculateFinalCost(null, 1, "test");
        MarkUpCalculator.calculateFinalCost(0.0, 1, "test");

    }
    @Test(expected = RuntimeException.class)
    public final void whenNumPersonsIsNotValid(){
        MarkUpCalculator.calculateFinalCost(10.0, 0, "test");

    }
    @Test(expected = RuntimeException.class)
    public final void whenCategoryIsNotValid(){
        MarkUpCalculator.calculateFinalCost(20.0, 20, null);
        MarkUpCalculator.calculateFinalCost(10.0, 10, " ");

    }
    @Test
    public final void isValidErrorWhenBasePriceIsNotValid(){
        try {
            MarkUpCalculator.calculateFinalCost(null, 1, "test");
        } catch (Exception e) {
            assertEquals("BasePrice is Invalid", e.getMessage());
        } finally {
        }
    }
    @Test
    public final void isValidErrorWhenNumPersonsIsNotValid(){
        try {
            MarkUpCalculator.calculateFinalCost(20.0, -1, "test");
        } catch (Exception e) {
            assertEquals("Num Persons is Invalid", e.getMessage());
        } finally {
        }
    }
    @Test
    public final void isValidErrorWhenCategoryIsNotValid(){
        try {
            MarkUpCalculator.calculateFinalCost(10.90, 1, "");
        } catch (Exception e) {
            assertEquals("Category is Invalid", e.getMessage());
        } finally {
        }
    }

    @Test
    public final void testForCategory_Food(){
        Double expected = 1591.58;
        Double basePrice = 1299.99;
        int numPersons = 3;
        Double finalCost = MarkUpCalculator.calculateFinalCost(basePrice, numPersons, "food" );
        assertEquals("FinalCost for Food failed:",expected, finalCost,0);
    }
    @Test
    public final void testForCategory_Pharmaceuticals(){
        Double  expected = 6199.81;
        Double basePrice = 5432.00;
        int numPersons = 1;
        Double finalCost = MarkUpCalculator.calculateFinalCost(basePrice, numPersons, "drugs" );
        assertEquals("FinalCost for Pharmaceuticals failed:",expected, finalCost,0);
    }
    @Test
    public final void testForCategory_PharmaceuticalsOrDrugs(){
        Double  expected = 6199.81;
        Double basePrice = 5432.00;
        int numPersons = 1;
        Double finalCost = MarkUpCalculator.calculateFinalCost(basePrice, numPersons, "pharmaceuticals" );
        assertEquals("FinalCost for PharmaceuticalsOrDrugs failed:",expected, finalCost,0);
    }
    @Test
    public final void testForCategory_caseInsensitivity(){
        Double  expected = 6199.81;
        Double basePrice = 5432.00;
        int numPersons = 1;
        Double finalCost = MarkUpCalculator.calculateFinalCost(basePrice, numPersons, "DruGs" );
        assertEquals("FinalCost for caseInsensitivity failed:",expected, finalCost,0);
    }
    @Test
    public final void testForCategory_Electronics(){
        Double expected = 13498.35;
        Double basePrice = 12456.95;
        int numPersons = 1;
        Double finalCost = MarkUpCalculator.calculateFinalCost(basePrice, numPersons, "tv" );
        assertEquals("FinalCost for Electronics failed:",expected, finalCost,0);
    }
    @Test
    public final void testForCategory_EverythingElse(){
        Double expected = 13707.63;
        Double basePrice = 12456.95;
        int numPersons = 4;
        Double finalCost = MarkUpCalculator.calculateFinalCost(basePrice, numPersons, "books" );
        assertEquals("FinalCost for EverythingElse failed:",expected, finalCost,0);
    }

    @Test
    public final void testGetMarkupPercent_food(){
        Double expected = 0.13;
        Double actual = MarkUpCalculator.getMarkupPercent("food");
        assertEquals("GetMarkupPercent for food failed:", expected, actual);
    }
    @Test
    public final void testGetMarkupPercent_pharmceuticals(){
        Double expected = 0.075;
        Double actual = MarkUpCalculator.getMarkupPercent("pharmaceuticals");
        assertEquals("GetMarkupPercent for pharmceuticals failed:", expected, actual);
    }
    @Test
    public final void testGetMarkupPercent_electronics(){
        Double expected = 0.02;
        Double actual = MarkUpCalculator.getMarkupPercent("electronics");
        assertEquals("GetMarkupPercent for electronics failed:", expected, actual);
    }
    @Test
    public final void testGetMarkupPercent_flat(){
        Double expected = 0.05;
        Double actual = MarkUpCalculator.getMarkupPercent("flat");
        assertEquals("GetMarkupPercent for flat failed:", expected, actual);
    }
    @Test
    public final void testGetMarkupPercent_persons(){
        Double expected = 0.012;
        Double actual = MarkUpCalculator.getMarkupPercent("persons");
        assertEquals("GetMarkupPercent for persons failed:", expected, actual);
    }

    @Test
    public final void isFileBeingLoaded(){

        InputStream inputStream = MarkUpCalculator.loadFile("");
        assertNotNull("File is not found", inputStream);
    }
    @Test
    public final void isFileSpecifiedBeingLoaded(){
        InputStream inputStream = MarkUpCalculator.loadFile("config.properties");
        assertNotNull("File is not found", inputStream);
    }

    @Test
    public final void stringParamNotEqual3(){
        try {
            MarkUpCalculator.calculateFinalCost("1");
        } catch (Exception e) {
            assertEquals("Usage: basePrice,numPersons,food|drugs|electronics", e.getMessage());
        } finally {
        }
    }
    @Test
    public final void stringParamInvalidBasePrice(){
        try {
            MarkUpCalculator.calculateFinalCost("-1,0, ");
        } catch (Exception e) {
            assertEquals("BasePrice can't be <= 0", e.getMessage());
        } finally {
        }
    }
    @Test
    public final void stringParamInvalidNumPersons(){
        try {
            MarkUpCalculator.calculateFinalCost("1,0, ");
        } catch (Exception e) {
            assertEquals("NumPersons can't be <= 0", e.getMessage());
        } finally {
        }
    }
    @Test
    public final void stringParamInvalidCategory(){
        try {
            MarkUpCalculator.calculateFinalCost("1,1, ");
        } catch (Exception e) {
            assertEquals("Category can't be empty", e.getMessage());
        } finally {
        }
    }
    @Test
    public final void stringParamValid_food(){
        try {
            Double expected = 1591.58;
            Double finalCost = MarkUpCalculator.calculateFinalCost("1299.99,3,food" );
            assertEquals("FinalCost for Food failed:",expected, finalCost,0);
        } catch (Exception e) {
            assertEquals("Category can't be empty", e.getMessage());
        } finally {
        }
    }
    @Test
    public final void stringParamValid_caseInsensitivity(){
        Double  expected = 6199.81;
        Double finalCost = MarkUpCalculator.calculateFinalCost("5432.00,1,DruGs" );
        assertEquals("FinalCost for caseInsensitivity failed:",expected, finalCost,0);
    }
    @Test
    public final void stringParamValid_Electronics(){
        Double expected = 13498.35;
        Double finalCost = MarkUpCalculator.calculateFinalCost("12456.95,1,Electronics" );
        assertEquals("FinalCost for Electronics failed:",expected, finalCost,0);
    }
    @Test
    public final void stringParamValid_EverythingElse(){
        Double expected = 13707.63;
        Double finalCost = MarkUpCalculator.calculateFinalCost("12456.95, 4, books" );
        assertEquals("FinalCost for EverythingElse failed:",expected, finalCost,0);
    }
}
