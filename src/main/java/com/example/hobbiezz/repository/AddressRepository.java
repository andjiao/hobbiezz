package com.example.hobbiezz.repository;



public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query("select (count(m) > 0) from Address m where m.street = :street and m.additionalInfo = :additionalInfo " +
            "and m.city = :city and m.zipCode = :zipCode")
    boolean addressExists(AddressRequest body);
}
