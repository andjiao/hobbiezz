package com.example.hobbiezz.repository;


import com.example.hobbiezz.dto.AddressRequest;
import com.example.hobbiezz.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<Address, Integer> {

   /* @Query("select (count(m) > 0) from Address m where m.street = :street and m.additionalInfo = :additionalInfo " +
            "and m.city = :city and m.zipCode = :zipCode")
    boolean addressExists(AddressRequest body);*/
}
