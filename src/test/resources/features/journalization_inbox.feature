# Created by joris at 11-4-17
Feature: The journalization inbox has financial facts that are not journalized yet. The system wil do proposals
  based on the financial fact type and other characteristics of the financial fact.

  Scenario: The inbox contains a non journalized financial fact
    Given a financial fact
    When the entrepreneur asks for the journalization inbox
    Then 1 financial fact is shown in the journalization inbox

  Scenario: The inbox does not contain a journalized financial fact
    Given a financial fact
    And a journal entry is created for the financial fact
    When the entrepreneur asks for the journalization inbox
    Then 0 financial facts are shown in the journalization inbox
