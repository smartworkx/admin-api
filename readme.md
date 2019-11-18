
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
-Controleer dat het boeken van de btw goed is gedaan door te controleren dat er 8 VATP journal entries zijn
-Begin met winst en verlies rekening
-Boek winst naar eigen vermogen door op de winst en verlies rekening

-Boek prive ontrekkingen naar eigen vermogen zie sum_of_private_for_income_tax.sql

balance debit = balance credit - prive ontrekkingen + winst
112378.79 = 103266.12 - 151863.49 + 160976.16 voor 2017

select je.id, je.value_date, ff.description, r.debit_credit, l.name from record r
join journal_entry je on je.id = r.journal_entry_id
join financial_fact ff on ff.id = je.financial_fact_id
JOIN ledger l on l.id = r.ledger_id
where ff.value_date = '2015-12-31'

Opmerkingen inkomsten belasting:

- Afschrijving naar winst en verlies
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

- Creating the database docker run -it --rm --volume=$(pwd)/src/main/db:/scripts postgres psql -h 192.168.99.100 -p <PORT> -U postgres -f ./scripts/init_db.sql

https://github.com/linkyard/concourse-helm-resource
https://cloud.google.com/solutions/continuous-integration-helm-concourse
