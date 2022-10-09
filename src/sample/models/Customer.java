package sample.models;

public record Customer(
        int customerId,
        String name,
        String address,
        String postalCode,
        String phone,
        int divisionId) {
}
