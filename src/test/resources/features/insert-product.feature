Feature: Insert new product

  As a restaurant employee, I want to register a new product in the database so that it becomes available to customers.

  Scenario: Insert a new product to the database
    Given that I own a product with the following characteristics
      | productName | category | price | description    | imagesUrls |
      | Cake        | DESSERT  | 15.00 | Wonderful cake | cake.png   |
    When I make a POST request to the API
    Then the product should be successfully registered in the database