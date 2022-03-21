package com.example.hobbiezz.service;



import com.example.hobbiezz.dto.AddressRequest;
import com.example.hobbiezz.dto.AddressResponse;
import com.example.hobbiezz.entity.Address;
import com.example.hobbiezz.repository.AddressRepository;
import com.example.hobbiezz.repository.PersonRepository;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.util.List;
@ToString
@Service
public class AddressService {

    AddressRepository addressRepository;

    PersonRepository personRepository;


    //Constructor
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository=addressRepository;
    }


    //Denne metode virker - Andrea
   public AddressResponse addAddress(AddressRequest body) throws Exception {

        Address addressNew = addressRepository.save(new Address(body));
       return new AddressResponse(addressNew);


       /*

           public CarResponse addCar(CarRequest body){
            Car carNew = carRepository.save(new Car(body));
            //hvis vi ømnsker alle informationer, skal includeAll være true, ønsker vi ikke alle informationer,
            // skal det være false
            return new CarResponse(carNew, true) ;
        }


        if (addressRepository.addressExists(body)) {
            System.out.println("Address is already in database");
            return new AddressResponse(body);
        }*/

    }

    //Denne metode virker- Andrea
    public AddressResponse getAddressById(int id) throws Exception {
        Address address = addressRepository.findById(id).orElseThrow(()->new Exception(
                "No address with this id exists"));
        return new AddressResponse(address);
    }

    /*    public CarResponse getCar(int id,boolean all) throws Exception {
            //Car car = carRepository.findById(id).orElseThrow(()->new Exception("not found"));

            Car car2 = carRepository.findById(id).orElseThrow(()->new Client4xxException("not found"));
            return new CarResponse(car2, false);

        }*/



    public AddressResponse getAddressConnectedToPerson(int personId) throws Exception {
        Address address = personRepository.findById(personId).get().getConnectedAddress();
        //orElseThrow(()->new Client4xxException("No address with this id exists"));
        return new AddressResponse(address);
    }




    //Skal man kunne slette addresser?
}
