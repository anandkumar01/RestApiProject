Feature: Delete product using Delete Api

  Scenario Outline: Validate delete product api status code correctly
    Given I hit the url of delete product api endpoint
    When I pass the url of delete product in request with <ProductNo>
    Then I receive the response code as 200

    Examples: 
      | ProductNo |
      |         5 |
