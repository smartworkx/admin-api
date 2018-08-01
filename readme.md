
http://online4mkb.nl/starters-administratiekantoor/boekhouding/prive-opnames-en-stortingen/

https://www.belastingdienst.nl/wps/wcm/connect/bldcontentnl/belastingdienst/zakelijk/winst/inkomstenbelasting/verandering_inkomstenbelasting_vorige_jaren/veranderingen_inkomstenbelasting_2015/mkb_winstvrijstelling_2015

https://www.belastingdienst.nl/wps/wcm/connect/bldcontentnl/belastingdienst/zakelijk/winst/inkomstenbelasting/verandering_inkomstenbelasting_vorige_jaren/veranderingen_inkomstenbelasting_2015/ondernemersaftrek_2015/zelfstandigenaftrek_2015

Opmerkingen btw afdracht vergeet niet te boeken aan het eind
van het kwartaal zie doc/btw 
Te vorderen btw bij het maken van kosten
Af te dragen btw bij het boeken van omzet
Te betalen btw bij het boeken van de btw aangifte en het betalen van de btw aangifte

NS word pas veel later afgeschreven dus als de factuur buiten het kwartaal ligt datum veranderen zodat het wel binnen het kwartaal valt. Bijvoorbeeld NS factuur
van 14 september 2017.


Opmerkingen bij het opstellen van de balans
-Begin met winst en verlies rekening
-Boek winst naar eigen vermogen door op de winst en verlies rekening
-Boek prive ontrekkingen naar eigen vermogen

select je.id, je.value_date, ff.description, r.debit_credit, l.name from record r
join journal_entry je on je.id = r.journal_entry_id
join financial_fact ff on ff.id = je.financial_fact_id
JOIN ledger l on l.id = r.ledger_id
where ff.value_date = '2015-12-31'

Opmerkingen inkomsten belasting:

- Pas op vul in de mutaties op reserveringen voor de for
- Zelfstandigen aftrek
- mkb winst vrij stelling
- Zeg dat je het op persoonlijk niveau wil invullen
- investeringsaftrek
- Alles wat invloed heeft op de hoogte van het ondernemings vermogen moet via de winst
en verliesrekening!!!!!!!!


## Technical

- When getting compiling issues that have to do with lombok in intellij enable annotation processing.

- Buitenlandse uitgaven geen btw boeken niet de moeite waard om terug te vragen.
