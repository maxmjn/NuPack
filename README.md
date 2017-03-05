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
 - from terminal ```mvn clean install```
 - pricing-1.0.jar file created in maven's home directory
 ```<your home>/.m2/repository/com/nupack/pricing/1.0/pricing-1.0.jar```

#### Usage
 - To include JAR file in your project in your pom.xml add dependency
  <pre>
  <dependency>
      <groupId>com.nupack</groupId>
      <artifactId>pricing</artifactId>
      <version>1.0</version>
  </dependency>
  </pre>

#### Test
 - Make sure pricing-1.0.jar is available in maven home directory
  ```<your home>/.m2/repository/com/nupack/pricing/1.0/pricing-1.0.jar```
 - First create a sample project (maven or gradle)
  ```
  mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
  ```
 - src/main/java directory contains the project source code, the src/test/java directory contains the test source, and the pom.xml file is the project's Project Object Model, or POM 
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
 - In your src/main/java/com/mycompany/app/App.java
 ```Java
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
```
- In src/test/java/com/mycompany/app/AppTest.java
```Java
public class AppTest{
    ...
    public void testMarkUpCalculator(){
        App.main(new String[] {});
    }
}
```
- From terminal run ```mvn test``` to see output 
```
Running com.mycompany.app.AppTest
FinalCost:1591.58
```
