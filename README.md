# stc

## About

This is a project for practising unit testing skills. It contains the following exercises:

* Refactor a unit test to make it more readable
* Fix a flaky unit test
* Create a fake to be used in a unit test
* Implement a class using Test Driven Development

Clone this repo on your computer and follow the instructions for each exercise below.

### Refactor a unit test to make it more readable

The purpose of this exercise is to practise to design tests so that the intention of the test is
clear.

1. Take a look at the test BugCountTest class
1. Run the test. It should pass:
   ```
   ./gradlew test --tests '*BugCountTest'
   ```
1. Refactor the test so that it's easy to understand what is tested in each interaction with the class
   * HINT: How much work should `setUp` be doing? How many test cases should there be?
1. Run the tests. They should pass

### Fix a flaky unit test

The purpose of this exercise is to practise to recognize and figure out the cause of a flaky test.

1. Take a look at the test DivisorTest class
1. Run the test a few times. What happens?
   ```
   ./gradlew test --tests '*DivisorTest'
   ```
1. Change the test so that it's not flaky, without changing the Divisor class being tested
   * HINT: How reliable are comparisons between floating-point numbers using normal comparison operators?
1. Run the test as many times you like. It should now pass every time

### Create a fake to be used in a unit test

The purpose of this exercise is to practise writing fakes to provide with your API. In this scenario
you have a service (GreetingApplication), with a client (GreetingClient). However, your customer
need to test their integration with your service without having to make network calls with your
client class. Your job is to create a fake client (GreetingClientFake) that behaves like your
client, so that it may be used in a unit test.

1. Take a look at the test GreetingClientFakeTest class
1. Correct the assertions in the GreetingClientFakeTest class. Run the test with the new assertion and make sure it fails.
   * HINT: You'll need to update an `import` statement to access the new assertion.
   ```
   ./gradlew test --tests '*GreetingClientFakeTest'
   ```
1. Implement the GreetingClientFake class
   * HINT: Do the absolute most straightforward thing that could possibly make the test pass.
1. Run the test. It should pass

### Implement a trivial class using Test Driven Development (TDD)

The purpose of this exercise is to practise TDD. You'll be tasked to write a simple class from
requirements that keeps evolving. This allows you to use the quick feedback loop of writing tests
for the new feature, implement it, run tests and refactor iteratively.

[Click here to go to the TDD exercise](tdd.md)

## How to build and run

### How to build

```
./gradlew build
```

### How to run tests

```
./gradlew test
```

### How to run GreetingApplication

```
./gradlew clean run
```

May be tested as follows:

```
curl http://localhost:8080/?name=Dr.Falken
```

With the response:

```
{"id":1,"content":"Greetings, Dr.Falken!"}
```

Stop the app using Ctrl+c

### How to run GreetingClient

Requires that GreetingApplication is running.

```
./gradlew clean run -PmainClass=com.schibsted.engprod.stc.GreetingClient
```

This should yield the following somewhere in the output: `Greetings, stranger!`
