# NuPack
- Final cost calculator library applies markup percent on given base price to output final cost
- Expected input are BasePrice, Number of Persons, Category
- Category values are Food, Electronics, Pharmaceuticals

#### Support
- dev@nupack.com

#### Setup

 - Java 1.8
 - Maven 3.3.x
 - git clone https://github.com/maxmjn/NuPack.git

#### Build JAR
 - ```cd NuPack``` 
 - from terminal ```mvn clean install```<i>Note:pom.xml should be present</i>
 - pricing-1.0.jar file created in maven's home directory
 ```<your home>/.m2/repository/com/nupack/pricing/1.0/pricing-1.0.jar```

#### Usage
 - To include JAR file in your project in your pom.xml add dependency
 <pre>
 <code>
 &lt;dependency&gt;
     &lt;groupId&gt;com.nupack&lt;/groupId&gt;
     &lt;artifactId&gt;pricing&lt;/artifactId&gt;
     &lt;version&gt;1.0&lt;/version&gt;
 &lt;/dependency&gt;
 </code>
 </pre>

#### Test
 - Clone project and build it using maven
 - Make sure pricing-1.0.jar is available in maven home directory
  ```<your home>/.m2/repository/com/nupack/pricing/1.0/pricing-1.0.jar```
 - Create a new folder lets call it ```myfolder```
 - ```cd myfolder``` Create a file with below contents and save file as App.java
 <pre>
 <code>
import com.nupack.MarkUpCalculator;
public class App{
    public static void main(String[] args){
        Double expected = 1591.58;
        Double basePrice = 1299.99;
        int numPersons = 3;
        Double finalCost = MarkUpCalculator.calculateFinalCost(basePrice, numPersons, "food" );
        System.out.println("FinalCost:" + finalCost);
    }
}
</code>
</pre>

- Copy ```pricing-1.0.jar``` into the same folder as ```App.java```
```
myfolder
|--- App.java
|--- pricing-1.0.jar

```
- From terminal navigate to ```myfolder``` that holds ```App.java```
- Compile ```javac -cp "pricing-1.0.jar" App.java ``` If no errors, you should get ```App.class``` file
- Run ```java -cp "pricing-1.0.jar": App``` to output ```FinalCost:1591.58```
