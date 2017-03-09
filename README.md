# NuPack
- Final cost calculator library applies markup percent on given base price to output final cost

#### Support
- dev@nupack.com

#### Setup

 - Java 1.8
 - Maven 3.3.x
 - git clone https://github.com/maxmjn/NuPack.git

#### Usage
 - Expected input params are BasePrice, Number of Persons, Category
 - Input params can be passed in <strong>any order</strong>
 - MarkUp percent can be provided through file. If no file is provided default markup percent values will be used
  <pre>
  <code>
  MarkUpCalculator.calculateFinalCost("bp=basePrice,np=numPersons,category=food|drugs|electronics"[, filename])
  MarkUpCalculator.calculateFinalCost(1299.99,3,"food")
  MarkUpCalculator.calculateFinalCost("bp=1299.99,np=3,category=food"[, filename])
  </pre>
  </code>

 - Default markup percent 
  ```
  flat=0.05
  persons=0.012
  pharmaceuticals=0.075
  food=0.13
  electronics=0.02
  ```
 - To include JAR file in your Java project in your pom.xml add dependency
 <pre>
 <code>
 &lt;dependency&gt;
     &lt;groupId&gt;com.nupack&lt;/groupId&gt;
     &lt;artifactId&gt;pricing&lt;/artifactId&gt;
     &lt;version&gt;1.0&lt;/version&gt;
 &lt;/dependency&gt;
 </code>
 </pre>
 
#### Build
 - ```cd NuPack``` 
 - from terminal ```mvn clean install```<i>Note:run from folder where pom.xml is present</i>
 - pricing-1.0.jar file created in maven's home directory
 ```<your home>/.m2/repository/com/nupack/pricing/1.0/pricing-1.0.jar```

#### Test
 - Clone project
 - Build using maven from terminal ```mvn clean install```<i>Note:run from folder where pom.xml is present</i>
 - Make sure pricing-1.0.jar is available in maven home directory
  ```<your home>/.m2/repository/com/nupack/pricing/1.0/pricing-1.0.jar```
 - Create a new folder lets call it ```myfolder```
 - ```cd myfolder``` Create a file with below contents and save file as App.java
 <pre>
 <code>
import com.nupack.MarkUpCalculator;
public class App{
    public static void main(String[] args){
        Double finalCost = MarkUpCalculator.calculateFinalCost("bp=1299.99,np=3,category=food" );
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
- <strong>Optionally</strong> you can provide your own MarkUp percent file. File name and extension is your choice.
- File contents should be key-value pair. Key is String, Value is numeric
- Example file "markup.txt"
```
flat=0.05
persons=0.012
pharmaceuticals=0.075
food=0.13
electronics=0.02
```
- Place file in same folder as JAR
```
myfolder
|--- App.java
|--- pricing-1.0.jar
|--- markup.txt

```
- Finally compile and run ```java -cp "pricing-1.0.jar": App``` 