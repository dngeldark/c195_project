package sample.models;

import java.util.Objects;

public final class Customer {
    private final int customerId;
    private final String name;
    private final String address;
    private final String postalCode;
    private final String phone;
    private final String subdivision;
    private final int divisionId;

    public Customer(
            int customerId,
            String name,
            String address,
            String postalCode,
            String phone,
            int divisionId) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionId = divisionId;
        this.subdivision = getSubdivision();
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public int getDivisionId() {
        return divisionId;
    }

    private String getSubdivision(){
        String sub = null;

        return sub;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Customer) obj;
        return this.customerId == that.customerId &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.address, that.address) &&
                Objects.equals(this.postalCode, that.postalCode) &&
                Objects.equals(this.phone, that.phone) &&
                this.divisionId == that.divisionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, name, address, postalCode, phone, divisionId);
    }

    @Override
    public String toString() {
        return "Customer[" +
                "customerId=" + customerId + ", " +
                "name=" + name + ", " +
                "address=" + address + ", " +
                "postalCode=" + postalCode + ", " +
                "phone=" + phone + ", " +
                "divisionId=" + divisionId + ']';
    }

}
