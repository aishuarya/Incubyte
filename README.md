# String Calculator

A simple string calculator that allows adding numbers from a string input, with support for custom delimiters, multiple numbers, and error handling for invalid or negative inputs.

## **Features**

Handles empty strings: Returns 0 for empty input.

Single number input: Returns the number itself.

Comma-separated numbers: Supports adding numbers separated by commas.

Supports custom delimiters: You can define custom delimiters for splitting the numbers in the string.

## **Error handling:**

Throws an exception for invalid non-numeric values.

Throws an exception for negative numbers, listing them in the error message.

## **API**

`public int add(String number)`

Examples:

`add("//;\n1;2;3"); // returns 6`

`add("1,2,abc"); // throws IllegalArgumentException: Non numerical value`

`add("1,-2,3"); // throws IllegalArgumentException: negative numbers not allowed: -2`

`add("1,-2,-3,4"); // throws IllegalArgumentException: negative numbers not allowed: -2, -3`


### **How to Run**

1. Clone this repository to your local machine:
2. Navigate to the project directory: `cd Incubyte`
3. Build and run the tests:
 `mvn clean install
   mvn test
`

## **Future Development**

This StringCalculator can be extended in the future to expose the functionality as a REST API. The calculator logic would be made available through a POST request to a specified endpoint, allowing users to send the input string and receive the sum as the output.

For example, the future API could look like this:

````POST /api/calculate
POST /api/calculate

Request Body: {"numbers": "1,2,3"}

Response: {"result": 6}
````

Note: This functionality is planned for future development, and currently, the project is implemented as a simple Java application.