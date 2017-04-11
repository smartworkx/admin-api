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

  Scenario: The inbox shows proposals
    Given a bank import from ING
    And the bank import has a bank operation with "simyo" in the description"
    And the bank operation has an amount is 18.97
    When the entrepreneur asks for the journalization inbox
    Then the financial fact has a record proposal for "telephone cost" "CREDIT" of 13.79
    And the financial fact has a record proposal for "deducted vat" "CREDIT" of 2.34

  Scenario: When a proposal is approved the journal entry is created
    Given an inbox proposal
    And the proposal has a financial fact with "ING bank operation"
    And the proposal has a record proposal
    And the proposal is approved
    Then a journal entry is created
    And the journal entry has a record for the financial fact
    And the journal entry has a record for the record proposal
    And the journal entry has book date of today
    And the journal entry has a reference to the financial fact
