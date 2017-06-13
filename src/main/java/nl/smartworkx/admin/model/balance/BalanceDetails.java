package nl.smartworkx.admin.model.balance;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.DebitCredit;
import nl.smartworkx.admin.model.ledger.Ledger;
import nl.smartworkx.admin.model.ledger.LedgerRepository;

/**
 * Created by joris on 22-5-17.
 */
public class BalanceDetails {
    private final List<BalanceHeading> balanceHeadings;
    private Balance balance;
    private Amount debitAmount;
    private Amount creditAmount;

    public BalanceDetails(LedgerRepository ledgerRepo, Balance balance) {
        this.balance = balance;

        this.balanceHeadings = getHeadings(ledgerRepo.findAllBy());

        this.debitAmount = sumOfHeadings(DebitCredit.DEBIT);
        this.creditAmount = sumOfHeadings(DebitCredit.CREDIT);
    }

    private Amount sumOfHeadings(DebitCredit debit) {
        return this.balanceHeadings.stream().filter(balanceHeading -> balanceHeading.getDebitCredit().equals(debit)).map
                (balanceHeading -> balanceHeading.getAmount()).reduce(Amount.ZERO, Amount::add);
    }

    private List<BalanceHeading> getHeadings(Stream<Ledger> ledgerStream) {
        return ledgerStream
                .filter(Ledger::shouldShowOnBalance)
                .collect(groupingBy(Ledger::getBalanceHeading))
                .entrySet()
                .stream()
                .map(entry ->
                        new BalanceHeading
                                (entry.getKey(), entry.getValue()
                                        .stream()
                                        .map(ledger -> {
                                            final BalanceAccount account = this.balance.findBalanceAccountByLedger(ledger);
                                            return account == null ? null : new BalanceAccountDetails(account, ledger);
                                        }).filter(Objects::nonNull)
                                        .collect(toList()))).collect(toList());
    }


    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    public LocalDate getDate() {
        return balance.getDate();
    }

    public String getDescription() {
        return balance.getDescription();
    }

    public List<BalanceHeading> getBalanceHeadings() {
        return balanceHeadings;
    }

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public LocalDateTime getCreationDateTime() {
        return balance.getCreationDateTime();
    }

    public Amount getDebitAmount() {
        return debitAmount;
    }

    public Amount getCreditAmount() {
        return creditAmount;
    }
}
