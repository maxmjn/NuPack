package com.nupack;


import org.junit.*;

import java.io.InputStream;
import java.util.Map;

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
    public final void stringParamNotEqual3(){
        try {
            MarkUpCalculator.calculateFinalCost("1");
        } catch (Exception e) {
            assertEquals("Usage: bp=basePrice,np=numPersons,category=food|drugs|electronics", e.getMessage());
        } finally {
        }
    }
    @Test
    public final void stringParamInvalidBasePrice(){
        try {
            MarkUpCalculator.calculateFinalCost("-1,0, ");
        } catch (Exception e) {
            assertEquals("Key=value missing. Usage: bp=basePrice,np=numPersons,category=food|drugs|electronics", e.getMessage());
        } finally {
        }
    }
    @Test
    public final void stringParamInvalidNumPersons(){
        try {
            MarkUpCalculator.calculateFinalCost("bp=1,np=0, ");
        } catch (Exception e) {
            assertEquals("NumPersons can't be <= 0", e.getMessage());
        } finally {
        }
    }
    @Test
    public final void stringParamInvalidCategory(){
        try {
            MarkUpCalculator.calculateFinalCost("1,1,category='' ");
        } catch (Exception e) {
            assertEquals("Key=value missing. Usage: bp=basePrice,np=numPersons,category=food|drugs|electronics", e.getMessage());
        } finally {
        }
    }

    @Test
    public final void isFileBeingLoaded(){

        InputStream inputStream = MarkUpCalculator.loadFile("");
        assertNotNull("File is not found", inputStream);
    }
    @Test
    public final void isFileSpecifiedBeingLoaded(){
        InputStream inputStream = MarkUpCalculator.loadFile("markUpPercent.txt");
        assertNotNull("File is not found", inputStream);
    }
    @Test
    public final void fileNotPresent(){
        try {
            MarkUpCalculator.initializeMarkUpPercent("xxx");
        } catch (Exception e) {
            assertEquals("File not found", e.getMessage());
        }
    }
    @Test
    public final void fileContainsNaN(){
        try {
            MarkUpCalculator.initializeMarkUpPercent("NaN.properties");
        } catch (Exception e) {
            assertEquals("File key values NaN", e.getMessage());
        } finally {
        }
    }
    @Test
    public final void isMarkUpPercentLookUpLoadedFromFile(){
        MarkUpCalculator.initializeMarkUpPercent("markUpPercent.txt");
        Map<String, Double> categoryMarkUpPercent = MarkUpCalculator.getCategoryMarkUpPercent();
        assertEquals("Key Not matching", true, categoryMarkUpPercent.containsKey("flat"));
        assertEquals("Value Not matching", 0.05, (Double) categoryMarkUpPercent.get("flat"), 0);
    }
    @Test
    public final void useFile_food(){
        Double expected = 1591.58;
        Double finalCost = MarkUpCalculator.calculateFinalCost("bp=1299.99,np=3,category=food" , "markUpPercent.txt");
        assertEquals("FinalCost for Food failed:",expected, finalCost,0);
    }
    @Test
    public final void useFile_drugs(){
        Double  expected = 6199.81;
        Double finalCost = MarkUpCalculator.calculateFinalCost("bp=5432.00,np=1,category=DruGs", "markUpPercent.txt" );
        assertEquals("FinalCost for caseInsensitivity failed:",expected, finalCost,0);
    }
    @Test
    public final void useFile_electronics(){
        Double expected = 13498.35;
        Double finalCost = MarkUpCalculator.calculateFinalCost("bp=12456.95,np=1,category=Electronics","markUpPercent.txt" );
        assertEquals("FinalCost for Electronics failed:",expected, finalCost,0);
    }
    @Test
    public final void useFile_EverythingElse(){
        Double expected = 13707.63;
        Double finalCost = MarkUpCalculator.calculateFinalCost("bp=12456.95,np =4,category= books","markUpPercent.txt" );
        assertEquals("FinalCost for EverythingElse failed:",expected, finalCost,0);
    }

    @Test
    public final void useFile_NamedParams(){
        Double expected = 1591.58;
        Double finalCost = MarkUpCalculator.calculateFinalCost("category=food,bp=1299.99,np=3" , "markUpPercent.txt");
        assertEquals("FinalCost for Food failed:",expected, finalCost,0);
    }
}
