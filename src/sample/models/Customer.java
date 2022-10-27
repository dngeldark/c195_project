package sample.models;
import java.util.Objects;

/** Class for the customer objects.*/
public final class Customer {
    private int customerId;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private String subdivision;
    private int divisionId;
    private CountryDivision division;

    public Customer(
            String name,
            String address,
            String postalCode,
            String phone,
            int divisionId,
            String subdivision) {

        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionId = divisionId;
        this.subdivision = subdivision;
    }

    public void setName(String name) {
        this.name = name;
    }

    /** Set address
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /** Set postalcode
     *
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /** set Phone number
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /** Set subdivision.
     *
     * @param subdivision
     */
    public void setSubdivisionName(String subdivision) {
        this.subdivision = subdivision;
    }

    /** Get Subdivison
     *
     * @return division
     */
    public CountryDivision getDivision(){
        return division;
    }

    /** Set division id
     *
     * @param divisionId
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /** Set subdivision
     *
     * @param subId
     */
    public void setDivision(int subId) {
        this.division = UtilityLists.findSubDivisionById(subId);
    }

    /** Set customer id
     *
     * @param customerId
     */
    public void setCustomerId(int customerId){
        this.customerId = customerId;
    }

    /** Get customer id
     *
     * @return customer id
     */
    public int getCustomerId() {
        return customerId;
    }

    /** Get customer name
     *
     * @return customer name
     */
    public String getName() {
        return name;
    }

    /** Get address
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /** Get postal code
     *
     * @return postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /** Get phone number
     *
     * @return phone number
     */
    public String getPhone() {
        return phone;
    }

    /** Get subdivision id
     *
     * @return subdivision id
     */
    public int getDivisionId() {
        return divisionId;
    }

    public String getSubdivision() {return subdivision;}


//    @Override
//    public boolean equals(Object obj) {
//        if (obj == this) return true;
//        if (obj == null || obj.getClass() != this.getClass()) return false;
//        var that = (Customer) obj;
//        return this.customerId == that.customerId &&
//                Objects.equals(this.name, that.name) &&
//                Objects.equals(this.address, that.address) &&
//                Objects.equals(this.postalCode, that.postalCode) &&
//                Objects.equals(this.phone, that.phone) &&
//                this.divisionId == that.divisionId;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(customerId, name, address, postalCode, phone, divisionId);
//    }
//
//    @Override
//    public String toString() {
//        return "Customer[" +
//                "customerId=" + customerId + ", " +
//                "name=" + name + ", " +
//                "address=" + address + ", " +
//                "postalCode=" + postalCode + ", " +
//                "phone=" + phone + ", " +
//                "divisionId=" + divisionId + ']';
//    }

}
