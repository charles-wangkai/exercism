# Wizards and Warriors

Welcome to Wizards and Warriors on Exercism's Java Track.
If you need help running the tests or submitting your code, check out `HELP.md`.
If you get stuck on the exercise, check out `HINTS.md`, but try and solve it without using those first :)

## Introduction

## Inheritance

Inheritance is a core concept in OOP (Object-Oriented Programming).
It represents an IS-A relationship.
It literally means in programming as it means in english, inheriting features from parent (in programming features is normally functions and variables).

Consider a class, `Animal` as shown,

```java
//Creating an Animal class with bark() as a member function.
public class Animal {

    public void bark() {
        System.out.println("This is an animal");
    }

}
```

`Animal` is a parent class, because the properties this class has can be extended to all the animals in general.

Consider an animal named `Lion`, having a class like,

```java
//Lion class is a child class of Animal.
public class Lion extends Animal {

    @Override
    public void bark() {
        System.out.println("Lion here!!");
    }

}
```

~~~~exercism/note
The `Override` annotation is used to indicate that a method in a subclass is overriding a method of its superclass.
It's not strictly necessary but it's a best practice to use it.
~~~~

Now whenever we do,

```java
Animal animal = new Lion(); //creating instance of Animal, of type Lion
animal.bark();
```

Note: Initialising the `Animal` class with `Lion`.
The output will look like

```java
Lion here!!
```

According to OOP, there are many types of inheritance, but Java supports only some of them (Multi-level and Hierarchical).
To read more about it, please read [this][java-inheritance].

[java-inheritance]: https://www.javatpoint.com/inheritance-in-java#:~:text=On%20the%20basis%20of%20class,will%20learn%20about%20interfaces%20later.

## Instructions

In this exercise you're playing a role-playing game where different types of fighters can combat each other.
The game has different rules for each type of fighter.
We are going to focus on two specific types: Wizards and Warriors.

For a Warrior, these are the rules:

- A Warrior is never vulnerable.
- A Warrior deals `6` points of damage if the fighter they are attacking is not vulnerable.
- A Warrior deals `10` points of damage if the fighter they are attacking is vulnerable.

For a Wizard, these are the rules:

- A Wizard can prepare a spell in advance.
- A Wizard is vulnerable unless they have prepared a spell in advance.
- A Wizard deals `12` points of damage if they prepared a spell in advance.
- A Wizard deals `3` points of damage if they did not prepare a spell in advance.

## 1. Create the Warrior class

Create a new class called `Warrior`.
This class should inherit from the existing `Fighter` class.

## 2. Describe a Warrior

Update the `Warrior` class so that its `toString()` method describes what kind of fighter they are.
The method should return the string `"Fighter is a Warrior"`.

```java
Warrior warrior = new Warrior();
warrior.toString();
// => "Fighter is a Warrior"
```

## 3. Make Warriors invulnerable

Update the `Warrior` class so that its `isVulnerable()` method always returns `false`.

```java
Warrior warrior = new Warrior();
warrior.isVulnerable();
// => false
```

## 4. Calculate the damage points for a Warrior

Update the `Warrior` class so that its `getDamagePoints(Fighter)` method calculates the damage dealt by a Warrior according to the rules above.

```java
Warrior warrior = new Warrior();
Wizard wizard = new Wizard();

warrior.getDamagePoints(wizard);
// => 10
```

## 5. Create the Wizard class

Create another new class called `Wizard`.
This class should also inherit from the existing `Fighter` class.

## 6. Describe a Wizard

Update the `Wizard` class so that its `toString()` method describes what kind of fighter they are.
The method should return the string `"Fighter is a Wizard"`.

```java
Wizard wizard = new Wizard();
wizard.toString();
// => "Fighter is a Wizard"
```

## 7. Allow Wizards to prepare a spell and make them vulnerable when not having prepared a spell

Update the `Wizard` class to add a method called `prepareSpell()`.
The class should remember when this method is called, and make sure that its `isVulnerable()` method returns `false` only when a spell is prepared.

```java
Wizard wizard = new Wizard();
wizard.prepareSpell();
wizard.isVulnerable();
// => false
```

## 8. Calculate the damage points for a Wizard

Update the `Wizard` class so that its `getDamagePoints(Fighter)` method calculates the damage dealt by a Wizard according to the rules above.

```java
Wizard wizard = new Wizard();
Warrior warrior = new Warrior();

wizard.prepareSpell();
wizard.getDamagePoints(warrior);
// => 12
```

## Source

### Created by

- @himanshugoyal1065

### Contributed to by

- @manumafe98
- @sanderploegsma