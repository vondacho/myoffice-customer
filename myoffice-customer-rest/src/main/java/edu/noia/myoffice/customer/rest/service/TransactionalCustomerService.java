package edu.noia.myoffice.customer.rest.service;

import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.aggregate.FolderState;
import edu.noia.myoffice.customer.domain.service.CustomerService;
import edu.noia.myoffice.customer.domain.vo.Affiliate;
import edu.noia.myoffice.customer.domain.vo.Affiliation;
import edu.noia.myoffice.customer.domain.vo.CustomerId;
import edu.noia.myoffice.customer.domain.vo.FolderId;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

/**
 * This class is a transactional implementation of the {@link CustomerService} domain class
 * Transactional aspect provided by Spring
 * Proxy pattern applied
 */
@RequiredArgsConstructor
public class TransactionalCustomerService implements CustomerService {

    @NonNull
    CustomerService customerService;

    @Transactional
    @Override
    public Folder create(FolderState data) {
        return customerService.create(data);
    }

    @Transactional
    @Override
    public Affiliation create(CustomerState data) {
        return customerService.create(data);
    }

    @Transactional
    @Override
    public Affiliation create(CustomerState data, FolderId folderId) {
        return customerService.create(data, folderId);
    }

    @Transactional
    @Override
    public Affiliation affiliate(CustomerId customerId) {
        return customerService.affiliate(customerId);
    }

    @Transactional
    @Override
    public Affiliation affiliate(FolderId folderId, CustomerId customerId) {
        return customerService.affiliate(folderId, customerId);
    }

    @Transactional
    @Override
    public Affiliation affiliate(FolderId folderId, Affiliate affiliate) {
        return customerService.affiliate(folderId, affiliate);
    }

    @Transactional
    @Override
    public Affiliation affiliate(Customer customer) {
        return customerService.affiliate(customer);
    }

    @Transactional
    @Override
    public Folder unaffiliate(FolderId folderId, CustomerId customerId) {
        return customerService.unaffiliate(folderId, customerId);
    }

    @Transactional
    @Override
    public Customer modify(CustomerId customerId, CustomerState modifier) {
        return customerService.modify(customerId, modifier);
    }

    @Transactional
    @Override
    public Customer patch(CustomerId customerId, CustomerState modifier) {
        return customerService.patch(customerId, modifier);
    }

    @Transactional
    @Override
    public Folder modify(FolderId folderId, FolderState modifier) {
        return customerService.modify(folderId, modifier);
    }

    @Transactional
    @Override
    public Folder patch(FolderId folderId, FolderState modifier) {
        return customerService.patch(folderId, modifier);
    }

    @Transactional
    @Override
    public Folder modify(FolderId folderId, Affiliate modifier) {
        return customerService.modify(folderId, modifier);
    }

    @Transactional
    @Override
    public void delete(FolderId folderId) {
        customerService.delete(folderId);
    }

    @Transactional
    @Override
    public void delete(CustomerId customerId) {
        customerService.delete(customerId);
    }
}
