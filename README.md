# json2java

## Pre-requisites

- Valid Maven installation (https://www.baeldung.com/install-maven-on-windows-linux-mac)
  - for OS X, it's easiest to run `brew install maven`
- Java 1.8+

## Build Instructions

`mvn clean install`

## Run Instructions

`mvn exec:java`

## Notes

*Still a work in progress, only core logic has been implemented.*

The currently supported "Token"s is considered to be any JSON Object, List / Array, int, long, float, double, or string of a JSON object, and it's respective value(s).  


### Packages

#### config

Holds main class and Constant file.  The main class currently loads a hard-coded JSON file, initalizes the DefaultProcessor, and calls it to tokenize the JSON and produce java code from the tokens.

#### domain

Holds Token classes representing the different JSON tokens that are represented within json2java.  

All other *Token classes extend Token.java, which represents the key and body (still in JSON) of the token.

"ParentToken"s additionally capture information about Tokens that contain Tokens within them, namely objects (by key) and lists (by index), extends Token.java.

PrimitiveToken and StringToken are concrete implementations of Token that resolve values.

ObjectToken and ListToken are concrete implemtentations of ParentToken, and they resolve their own token and their child tokens when initialized.

#### enums

Currently only contains the token types.  There are sub-types of PRIMITIVE, for which there are different types but not matching classes.  The PrimitiveToken class handles the different subtypes.

#### exception

Custom exceptions that can be thrown during processing.

#### processor

Currently only contains a regular implementation that implements Callable.  May be able to be simplified without processor package.

#### utils

Contains utilities for reading/parsing JSON, generating Java code, working with tokenized JSON, and managing the workspace.

## Usage example

Input JSON

```
{
  "Customer": {
    "id": 1,
    "ContactInformation": {
      "firstName": "John",
      "lastName": "Doe",
      "phoneNumber": "123-456-7890"
    },
    "Orders": [
      {
        "description": "Test String",
        "quantity": 3,
        "balance": 23.10
      },
      {
        "description": "Another String Here",
        "quantity": 1,
        "balance": 5.99
      }
    ]
  }
}
```

Tokenized input

```
[OBJECT] ROOT
    [OBJECT] Customer
        [PRIMITIVE_INT] id: 1
        [OBJECT] ContactInformation
            [STRING] firstName: John
            [STRING] lastName: Doe
            [STRING] phoneNumber: 123-456-7890
    [LIST] Orders
        - [OBJECT] list-element
            [STRING] description: Test String
            [PRIMITIVE_INT] quantity: 3
            [PRIMITIVE_DOUBLE] balance: 23.1
        - [OBJECT] list-element
            [STRING] description: Another String Here
            [PRIMITIVE_INT] quantity: 1
            [PRIMITIVE_DOUBLE] balance: 5.99
```

Generated code

```
Root root0 = new Root();
Customer customer0 = new Customer();
int id0 = 1;
customer0.setId(id0);
ContactInformation contactInformation0 = new ContactInformation();
String firstName0 = "John";
contactInformation0.setFirstName(firstName0);
String lastName0 = "Doe";
contactInformation0.setLastName(lastName0);
String phoneNumber0 = "123-456-7890";
contactInformation0.setPhoneNumber(phoneNumber0);
customer0.setContactInformation(contactInformation0);
root0.setCustomer(customer0);
List<Orders> ordersList0 = new ArrayList<Orders>();
Orders orders0 = new Orders();
String description0 = "Test String";
orders0.setDescription(description0);
int quantity0 = 3;
orders0.setQuantity(quantity0);
double balance0 = 23.1;
orders0.setBalance(balance0);
ordersList0.add(orders0);
Orders orders1 = new Orders();
String description1 = "Another String Here";
orders1.setDescription(description1);
int quantity1 = 1;
orders1.setQuantity(quantity1);
double balance1 = 5.99;
orders1.setBalance(balance1);
ordersList0.add(orders1);
root0.setOrders(ordersList0);
```


## Roadmap

- Add logging
- Output java code to configurable file
- Make input JSON file configurable
- Allow users to input the class names for unique objects found

