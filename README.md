# simpleDI
A really simple Dependency Injection mechanism in Java.

The project has been started initially after trying to integrate DI framework on Android. Why making the developper's life so difficult  ? Let's solve this !

## Simplest way to use it
The project allows developpers to inject dependencies directly in their class constructors. Add `@AutoInject` before your constructor to tell the framework this constructor contains class/interface to inject.
When you'll be ready, ask the framework to generate the object you want and magic happens : all dependencies will be resolved.

Exemple by the code:

```java
// First object  
class BasicObject{  
  public int get42(){  
    return 42;  
  }  
}  

// Parent object with a dependency on the BasicObject  
class ParentObject{  
  private BasicObject basicObject;  
  
  @AutoInject  
  public ParentObject(BasicObject basicObject){  
    this.basicObject = basicObject;  
  }  
  
  public int get42(){  
    return basicObject.get42();  
  }  
}  

public static int main(String[] args){  
  ParentObject parentObject = Injector.inject(ParentObject.class);  
  System.out.println(parentObject.get42());  
}  
```
## using with interfaces
Interfaces can be used (and should for mocking !) just add `DefaultInject` to the interface class and define which class implementation to use by default.

Exemple : 
```java
@DefaultInject(implementedBy=BasicObject)
class InterfaceObject{  
  public int get42();
}  

class BasicObject implements InterfaceObject{  
  public int get42(){  
    return 42;  
  }  
}  

public static int main(String[] args){  
  InterfaceObject obj = Injector.inject(InterfaceObject.class);  
  System.out.println(obj.get42());  
}  
```

## That's it ?
Yup ! The idea of the project is to use Dependency Injection easily. Any idea to make it even simpler ? Contribute !

## And Maven/Gradle ?
Yes of course !

### Maven :
```
	<repository>  
		<id>StephaneLemaireRepo</id>  
		<url>http://files.dp-lm.com/</url>  
	</repository>  

  <dependency>  
	  <groupId>com.dplm.simpleDI</groupId>  
	  <artifactId>SimpleDI</artifactId>  
	  <version>1.7.3</version>  
  </dependency>  
```

### Gradle :
```
  compile group: 'com.dplm.simpleDI', name: 'SimpleDI', version: '1.7.3'
```
