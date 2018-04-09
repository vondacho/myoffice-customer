package edu.noia.myoffice.customer.domain.service;

import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.common.domain.util.EntityFinder;
import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.aggregate.FolderState;
import edu.noia.myoffice.customer.domain.event.CustomerDeletedEventPayload;
import edu.noia.myoffice.customer.domain.event.FolderDeletedEventPayload;
import edu.noia.myoffice.customer.domain.repository.CustomerRepository;
import edu.noia.myoffice.customer.domain.repository.FolderRepository;
import edu.noia.myoffice.customer.domain.vo.Affiliate;
import edu.noia.myoffice.customer.domain.vo.Affiliation;
import edu.noia.myoffice.customer.domain.vo.CustomerId;
import edu.noia.myoffice.customer.domain.vo.FolderId;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultCustomerService implements CustomerService {

    @NonNull
    private CustomerRepository customerRepository;
    @NonNull
    private FolderRepository folderRepository;
    @NonNull
    private EventPublisher eventPublisher;

    public Folder create(FolderState data) {
        return Folder.of(data).save(folderRepository, eventPublisher);
    }

    public Affiliation create(CustomerState data) {
        return affiliate(Customer.of(data).save(customerRepository, eventPublisher));
    }

    public Affiliation create(CustomerState data, FolderId folderId) {
        return affiliate(findFolder(folderId), Customer.of(data).save(customerRepository, eventPublisher));
    }

    public Affiliation affiliate(CustomerId customerId) {
        return affiliate(findCustomer(customerId));
    }

    public Affiliation affiliate(FolderId folderId, CustomerId customerId) {
        return affiliate(findFolder(folderId), findCustomer(customerId));
    }

    public Affiliation affiliate(FolderId folderId, Affiliate affiliate) {
        return affiliate(findFolder(folderId), affiliate);
    }

    private Affiliation affiliate(Folder folder, Affiliate affiliate) {
        Customer customer = findCustomer(affiliate.getCustomerId());
        return Affiliation.of(folder.affiliate(affiliate).save(folderRepository, eventPublisher), customer);
    }

    private Affiliation affiliate(Folder folder, Customer customer) {
        return Affiliation.of(folder.affiliate(customer.getId()).save(folderRepository, eventPublisher), customer);
    }

    public Affiliation affiliate(Customer customer) {
        return affiliate(Folder.of(customer.folderize()), customer);
    }

    public Folder unaffiliate(FolderId folderId, CustomerId customerId) {
        return findFolder(folderId).unaffiliate(customerId).save(folderRepository, eventPublisher);
    }

    public Customer modify(CustomerId customerId, CustomerState modifier) {
        return findCustomer(customerId).modify(modifier).save(customerRepository, eventPublisher);
    }

    public Customer patch(CustomerId customerId, CustomerState modifier) {
        return findCustomer(customerId).patch(modifier).save(customerRepository, eventPublisher);
    }

    public Folder modify(FolderId folderId, FolderState modifier) {
        return findFolder(folderId).modify(modifier).save(folderRepository, eventPublisher);
    }

    public Folder patch(FolderId folderId, FolderState modifier) {
        return findFolder(folderId).patch(modifier).save(folderRepository, eventPublisher);
    }

    public Folder modify(FolderId folderId, Affiliate modifier) {
        return findFolder(folderId).modify(modifier).save(folderRepository, eventPublisher);
    }

    public void delete(FolderId folderId) {
        findFolder(folderId);
        folderRepository.delete(folderId);
        eventPublisher.publish(FolderDeletedEventPayload.of(folderId));
    }

    public void delete(CustomerId customerId) {
        findCustomer(customerId);
        folderRepository.findAllByAffiliate(customerId).forEach(folder ->
                folder.unaffiliate(customerId).save(folderRepository, eventPublisher));
        customerRepository.delete(customerId);
        eventPublisher.publish(CustomerDeletedEventPayload.of(customerId));
    }

    private Folder findFolder(FolderId id) {
        return EntityFinder.find(Folder.class, id, folderRepository::findOne);
    }

    private Customer findCustomer(CustomerId id) {
        return EntityFinder.find(Customer.class, id, customerRepository::findOne);
    }
}