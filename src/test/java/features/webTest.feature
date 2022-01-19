Feature: User validates the status and links on a web page

  Background: Open the browser to start the test
    Given the user opens the browser

  Scenario Outline: Validation of browser console errors on page load
    When the user navigates to the web page "<url>"
    Then there are no console errors on the page
    Examples:
      | url                                                  |
      | https://www.w3.org/standards/webofdevices/multimodal |
      | https://www.w3.org/standards/webdesign/htmlcss       |
      | https://www.w3.org/standards/badpage                 |

  Scenario Outline: Validation of status code on page load
    When the user navigates to the web page "<url>"
    Then the status code 200 is returned
    Examples:
      | url                                                  |
      | https://www.w3.org/standards/webofdevices/multimodal |
      | https://www.w3.org/standards/webdesign/htmlcss       |
      | https://www.w3.org/standards/badpage                 |

  Scenario Outline: Validation of all the links on page load
    When the user navigates to the web page "<url>"
    Then all the links on the page go to another live page

    Examples:
      | url                                                  |
      | https://www.w3.org/standards/webofdevices/multimodal |
      | https://www.w3.org/standards/webdesign/htmlcss       |
      | https://www.w3.org/standards/badpage                 |

