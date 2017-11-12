package edu.noia.myoffice.customer.domain.service;

import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.repository.CustomerRepository;
import edu.noia.myoffice.customer.domain.vo.Affiliation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class CustomerSanitizer {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public Affiliation folderize(Customer customer) {
        return customerService.affiliate(customer);
    }

    @Transactional
    public void folderize() {
        customerRepository.findAll().forEach(this::folderize);
    }

    @Transactional
    public Customer sanitize(Customer customer) {
        return customerRepository.save(customer.sanitize());
    }

    @Transactional
    public void sanitize() {
        customerRepository.findAll().forEach(this::sanitize);
    }
}