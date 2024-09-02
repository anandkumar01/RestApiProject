Feature: Insert products using Post Api

  Scenario Outline: Validate post product api status code correctly
    Given I hit the url of post product api endpoint
    When I pass the url of products in request
    And I pass the request body of product title <ProductTitle>
    Then I receive the response code as 200

    Examples: 
      | ProductTitle |
      | Shoes        |

  Scenario Outline: Validate post product api response body works correctly
    Given I hit the url of post product api endpoint
    When I pass the url of products in request
    And I pass the request body of product title <ProductTitle>
    Then I receive the response body with id as <Id>

    Examples: 
      | ProductTitle | Id |
      | Shoes        | 21 |
