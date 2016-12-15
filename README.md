# simpleDI
A really simple Dependency Injection mechanism in Java.

The project has been started initially when after trying to integrate DI framework on Android. Why making the developper's life so difficult just for a graph resolution problem ?

## How to use it ?
The project allows developpers to inject dependencies directly in their class constructors. Add `@AutoInject` before your constructor to tell the framework this constructor contains class/interface to inject.
When you'll be ready, ask the framework to generate the object you want and magic happens : all dependencies will be resolved.

Exemple by the code:

```javascript
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
    return basicObject;  
  }  
}  

public static int main(String[] args){  
  ParentObject parentObject = Injector.inject(ParentObject.class);  
  System.out.println(parentObject.get42());  
}  
```
