# NuPack
- Final cost calculator library applies markup percent on given base price to output final cost
- Expected input are BasePrice, Number of Persons, Category
- Category can be Food,

#### Support
- dev@nupack.com

#### Setup

 - Java 1.8
 - Maven 3.3.x
 - git clone https://github.com/maxmjn/NuPack.git

#### Build JAR
 - from terminal mvn clean install
 - JAR file created
 
#### Usage
 ```Java
import com.nupack.MarkUpCalculator;
public class App{
    public static void main(String[] args){
        Double finalCost = MarkUpCalculator.calculateFinalCost(basePrice, numPersons, category);
    }
}
```
