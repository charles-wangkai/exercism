# Wizards and Warriors 2

Welcome to Wizards and Warriors 2 on Exercism's Java Track.
If you need help running the tests or submitting your code, check out `HELP.md`.
If you get stuck on the exercise, check out `HINTS.md`, but try and solve it without using those first :)

## Introduction

## Method Overloading

In Java, method overloading is a feature that allows a class to have more than one method having the same name, if their
parameter lists are different.
It is related to compile-time (or static) polymorphism.
This concept is crucial for
creating methods that perform similar tasks but with different inputs.

### Why Overload Methods?

Method overloading increases the readability of the program.
Different methods can be given the same name but with
different parameters.
Depending on the number of parameters or the type of parameters, the corresponding method is called.

### How to Overload Methods?

The key to method overloading is a method's signature.
Two methods will be considered different if they have different signatures.
There are two ways to overload a method:

1. **Different Number of Parameters**: Methods can have the same name but a different number of parameters.

   ```java
   public class Display {

       public void show(int x) {
           System.out.println("Show with int: " + x);
       }

       public void show(int x, int y) {
           System.out.println("Show with two ints: " + x + ", " + y);
       }
   }
   ```

2. **Different Types of Parameters**: Methods can have the same name and the same number of parameters but with
   different types.

   ```java
   public class Display {

       public void show(int x) {
           System.out.println("Show with int: " + x);
       }

       public void show(String s) {
           System.out.println("Show with String: " + s);
       }
   }
   ```

### Points to Remember

- Overloaded methods must change the argument list.
- Overloaded methods can also change the return type, but merely changing the return type does not constitute method
  overloading.
- Methods can be overloaded in the same class or in a subclass.

In this concept, we will explore various examples and nuances of method overloading in Java.

## Instructions

In this exercise you're playing a role-playing game named "Wizard and Warriors" with your best friends.
You are the Game Master, the person tasked with making the game world come alive for the players.
A key aspect of this is describing the game to the players: what is a character's status, what the town they're visiting looks like, etc.

You have five tasks that have you describe parts of the game to the players.

## 1. Describe a character

Each character has a class, level and number of hit points and is described as: `"You're a level <LEVEL> <CLASS> with <HIT_POINTS> hit points."`.
Implement the `GameMaster.describe` method that takes a `Character` as its sole parameter and returns its description.

```java
Character character = new Character();
character.setCharacterClass("Wizard");
character.setLevel(4);
character.setHitPoints(28);

new GameMaster().describe(character);
// => "You're a level 4 Wizard with 28 hit points."
```

## 2. Describe a destination

Each destination has a name and a number of inhabitants and is described as: `"You've arrived at <NAME>, which has <INHABITANTS> inhabitants."`.
Implement the `GameMaster.describe` method that takes a `Destination` as its sole parameter and returns its description.

```java
Destination destination = new Destination();
destination.setName("Muros");
destination.setInhabitants(732);

new GameMaster().describe(destination);
// => "You've arrived at Muros, which has 732 inhabitants."
```

## 3. Describe the travel method

Characters can travel to a destination using one of two options:

- Walking, described as: `"You're traveling to your destination by walking."`
- On horseback, described as: `"You're traveling to your destination on horseback."`

Implement the `GameMaster.describe` method that takes a `TravelMethod` as its sole parameter and returns its description.

```java
new GameMaster().describe(TravelMethod.HORSEBACK);
// => "You're traveling to your destination on horseback."
```

## 4. Describe a character traveling to a destination

When a character is traveling to a destination, this is described as a combination of the individual descriptions: `"<CHARACTER> <TRAVEL_METHOD> <DESTINATION>"`.
Implement the `GameMaster.describe` method that takes a `Character`, a `Destination` and a `TravelMethod` as its parameters and return its description.

```java
Character character = new Character();
character.setCharacterClass("Wizard");
character.setLevel(4);
character.setHitPoints(28);

Destination destination = new Destination();
destination.setName("Muros");
destination.setInhabitants(732);

new GameMaster().describe(character, destination, TravelMethod.HORSEBACK);
// => "You're a level 4 Wizard with 28 hit points. You're traveling to your destination on horseback. You've arrived at Muros, which has 732 inhabitants."
```

## 5. Describe a character traveling to a destination without specifying the travel method

In the majority of cases, characters are traveling to a destination by walking.
For convenience, players are allowed to omit mentioning their travel method, in which case walking will be assumed to be the travel method.
Implement the `GameMaster.describe` method that takes a `Character` and a `Destination` as its parameters and return its description.

```java
Character character = new Character();
character.setCharacterClass("Wizard");
character.setLevel(4);
character.setHitPoints(28);

Destination destination = new Destination();
destination.setName("Muros");
destination.setInhabitants(732);

new GameMaster().describe(character, destination);
// => "You're a level 4 Wizard with 28 hit points. You're traveling to your destination by walking. You've arrived at Muros, which has 732 inhabitants."
```

## Source

### Created by

- @sougat818

### Contributed to by

- @jagdish-15
- @sanderploegsma