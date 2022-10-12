package sample.models;

import javafx.collections.ObservableList;

import java.util.Objects;

public final class Customer {
    private int customerId;

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSubdivisionName(String subdivision) {
        this.subdivision = subdivision;
    }

    public CountryDivision getDivision(){
        return division;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public void setDivision(int subId) {
        this.division = UtilityLists.findSubDivisionById(subId);
    }

    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private String subdivision;
    private int divisionId;
    private CountryDivision division;

    public Customer(
            //int customerId,
            String name,
            String address,
            String postalCode,
            String phone,
            int divisionId,
            String subdivision) {
        //this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionId = divisionId;
        this.subdivision = subdivision;
    }

    public void setCustomerId(int customerId){
        this.customerId = customerId;
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

    public String getSubdivision() {return subdivision;}


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
