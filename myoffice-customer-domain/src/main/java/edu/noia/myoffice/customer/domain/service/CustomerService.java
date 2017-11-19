package edu.noia.myoffice.customer.domain.service;

import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.aggregate.FolderState;
import edu.noia.myoffice.customer.domain.repository.CustomerRepository;
import edu.noia.myoffice.customer.domain.repository.FolderRepository;
import edu.noia.myoffice.customer.domain.util.EntityFinder;
import edu.noia.myoffice.customer.domain.vo.Affiliate;
import edu.noia.myoffice.customer.domain.vo.Affiliation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private FolderRepository folderRepository;

    @Transactional
    public Affiliation create(CustomerState data) {
        return affiliate(Customer.of(data).save(customerRepository));
    }

    @Transactional
    public Affiliation create(CustomerState data, UUID folderId) {
        return affiliate(findFolder(folderId), Customer.of(data).save(customerRepository));
    }

    @Transactional
    public Affiliation affiliate(UUID customerId) {
        return affiliate(findCustomer(customerId));
    }

    @Transactional
    public Affiliation affiliate(UUID folderId, UUID customerId) {
        return affiliate(findFolder(folderId), findCustomer(customerId));
    }

    @Transactional
    public Affiliation affiliate(UUID folderId, Affiliate affiliate) {
        return affiliate(findFolder(folderId), affiliate);
    }

    private Affiliation affiliate(Folder folder, Affiliate affiliate) {
        Customer customer = findCustomer(affiliate.getCustomerId());
        return Affiliation.of(folder.affiliate(affiliate).save(folderRepository), customer);
    }

    private Affiliation affiliate(Folder folder, Customer customer) {
        return Affiliation.of(folder.affiliate(customer.getId()).save(folderRepository), customer);
    }

    @Transactional
    public Affiliation affiliate(Customer customer) {
        return affiliate(customer.folderize(), customer);
    }

    @Transactional
    public Folder unaffiliate(UUID folderId, UUID customerId) {
        return findFolder(folderId).unaffiliate(customerId).save(folderRepository);
    }

    @Transactional
    public Customer modify(UUID customerId, CustomerState modifier) {
        return findCustomer(customerId).modify(modifier).save(customerRepository);
    }

    @Transactional
    public Customer patch(UUID customerId, CustomerState modifier) {
        return findCustomer(customerId).patch(modifier).save(customerRepository);
    }

    @Transactional
    public Folder modify(UUID folderId, FolderState modifier) {
        return findFolder(folderId).modify(modifier).save(folderRepository);
    }

    @Transactional
    public Folder patch(UUID folderId, FolderState modifier) {
        return findFolder(folderId).patch(modifier).save(folderRepository);
    }

    @Transactional
    public Folder modify(UUID folderId, Affiliate modifier) {
        return findFolder(folderId).modify(modifier).save(folderRepository);
    }

    Folder findFolder(UUID id) {
        return EntityFinder.find(Folder.class, id, folderRepository::findOne);
    }

    Customer findCustomer(UUID id) {
        return EntityFinder.find(Customer.class, id, customerRepository::findOne);
    }
}