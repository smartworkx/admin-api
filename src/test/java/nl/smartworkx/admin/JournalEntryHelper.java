package nl.smartworkx.admin;

import org.javamoney.moneta.Money;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public interface JournalEntryHelper {
	void createJournalEntry(Money amount, int taxRate);

}
