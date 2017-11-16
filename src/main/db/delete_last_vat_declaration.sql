delete FROM vat_journal_entry WHERE vat_declaration_id = (select max(id) from vat_declaration);

delete from vat_declaration where id = (select max(id) from vat_declaration);