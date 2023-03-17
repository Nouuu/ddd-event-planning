package org.esgi.ddd_event_planning.shared_kernel.domain.model;

public record Montant(double montant, String devise) {
    public Montant {
        if (montant < 0) {
            throw new IllegalArgumentException("Montant cannot be negative");
        }
        montant = Math.floor(montant * 100.0) / 100.0;
    }

    public Montant add(Montant other) {
        if (!this.devise.equals(other.devise())) {
            throw new IllegalArgumentException("Cannot operate with two Montant with different devise");
        }
        return new Montant(this.montant + other.montant(), devise);
    }

    public Montant subtract(Montant other) {
        if (!this.devise.equals(other.devise())) {
            throw new IllegalArgumentException("Cannot operate with two Montant with different devise");
        }
        return new Montant(this.montant - other.montant(), devise);
    }


    public Montant multiply(double coeff) {
        return new Montant(montant * coeff, devise);
    }

    public Montant divide(double coeff) {
        if (coeff == 0) {
            throw new IllegalArgumentException("Cannot divide by 0");
        }
        return new Montant(this.montant / coeff, devise);
    }

    public Montant divide(Montant other) {
        if (!this.devise.equals(other.devise())) {
            throw new IllegalArgumentException("Cannot operate with two Montant with different devise");
        }
        if (other.montant() == 0) {
            throw new IllegalArgumentException("Cannot divide by 0");
        }
        return new Montant(this.montant / other.montant(), devise);
    }
}
