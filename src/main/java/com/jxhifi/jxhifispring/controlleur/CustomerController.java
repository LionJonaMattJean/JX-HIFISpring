package com.jxhifi.jxhifispring.controlleur;

import com.jxhifi.jxhifispring.DTO.ConvertAddressDTO_To_Address;
import com.jxhifi.jxhifispring.DTO.customer.CustomerDTO;
import com.jxhifi.jxhifispring.DTO.customer.NewAccountCustomerDTO;
import com.jxhifi.jxhifispring.entities.Customer;
import com.jxhifi.jxhifispring.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController extends ConvertAddressDTO_To_Address {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customersDTO = customerService.getAllCustomers();
        return ResponseEntity.ok(customersDTO);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable String id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    /**
     * Cette methode est appeler si c'est le formulaire du createAccount btn qui est remplis et soumis
     * @param newCustomerDTO l'objet recu du front
     * @return une instance de Customer pret a etre inserer dans la DB
     */
    @PostMapping("/customer/newaccount")
    public ResponseEntity<Customer> createCustomerAccount(@RequestBody NewAccountCustomerDTO newCustomerDTO) {

        Customer customer = new Customer();

        customer.setId(customerService.generateNewId());
        customer.setFirstName(newCustomerDTO.getFirstName());
        customer.setEmail(newCustomerDTO.getEmail());
        customer.setPassword(newCustomerDTO.getPassword());
        customer.setRole("customer");
        customer.setDeleted(false);

        customerService.createNewCustomer(customer);

        return ResponseEntity.ok(customer);
    }

    /**
     * Cette methode est appeler si c'est le formulaire du dasboard qui est remplis et soumis
     * @param newCustomerDTO l'objet recu du front
     * @return une instance de Customer pret a etre inserer dans la DB
     */
    @PostMapping("/customer/createnew")
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerDTO newCustomerDTO) {

        Customer customer = new Customer();

        customer.setId(customerService.generateNewId());
        customer.setEmail(newCustomerDTO.getEmail());
        customer.setPassword(newCustomerDTO.getPassword());
        customer.setFirstName(newCustomerDTO.getFirstName());
        customer.setLastName(newCustomerDTO.getLastName());
        customer.setRole("customer");
        customer.setDeleted(false);
        customer.setAddress(addressDTOToAddress(newCustomerDTO.getAddress()));

        customerService.createNewCustomer(customer);

        return ResponseEntity.ok(customer);
    }
}
