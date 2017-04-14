# Created by joris at 11-4-17
Feature: Based on configuration and the financial fact the system creates proposals for journal entries, so that the user only has to approve the proposals.

  Scenario: Based on an incoming invoice the system creates a proposal
    Given a financial fact
    And the financial fact has an origin of "OUTGOING_INVOICE"
    And the financial fact has an amount of 12435.45
    And the financial fact is created
    When the system is asked to produce a proposal
    Then the proposal has a record for "DEB" "DEBIT" of 15046.8945
    And the proposal has a record for "VATS" "CREDIT" of 2611.4445
    And the proposal has a record for "TOJ" "CREDIT" of 12435.45
