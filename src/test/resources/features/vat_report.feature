Feature: VAT report

  As an entrepreneur
  I want a VAT report
  So that I can do the VAT declaration without thinking

Scenario: Happy flow VAT with outgoing invoices and costs
  Given today is "2016-04-01"
  And there is an outgoing invoice with an amount of 12500 ex VAT of 21%
  And there is an outgoing invoice on 2016-04-01 with an amount of 14500 ex VAT of 21%
  And today is "2016-06-30"
  And there was an incoming invoice with an amount of 300 ex VAT of 21%
  When today is "2016-07-26"
  And the entrepeneur asks for the VAT report for the 2nd quarter of 2016
  Then the report has an services taxed with high rate of ""
  And the report has an VAT of ""
  And te report has an voorbelasting of ""

Scenario: Report is not affected by changes afterwards

Scenario: 6% in costs
