Feature: Update products using Put Api

  Scenario Outline: Validate put product api status code correctly
    Given I hit the url of put product api endpoint
    When I pass the url of products in request with <ProductNo>
    Then I receive the response code as 200

    Examples: 
      | ProductNo |
      |         6 |
