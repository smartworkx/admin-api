package nl.smartworkx.admin.model.financialfact;

/**
 *
 */
public enum TaxRate {
    ZERO(0), LOW(6), HIGH(21);

    private double percentage;

    TaxRate(double percentage) {
        this.percentage = percentage;
    }

    public double getPercentage() {
        return percentage;
    }
}
