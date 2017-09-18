package edu.noia.myoffice.customer.domain.service;

import edu.noia.myoffice.common.domain.exception.ResourceNotFoundException;
import edu.noia.myoffice.customer.domain.aggregate.Affiliation;
import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.factory.AffiliationStateFactory;
import edu.noia.myoffice.customer.domain.factory.CustomerStateFactory;
import edu.noia.myoffice.customer.domain.factory.FolderStateFactory;
import edu.noia.myoffice.customer.domain.repository.AffiliationRepository;
import edu.noia.myoffice.customer.domain.repository.CustomerRepository;
import edu.noia.myoffice.customer.domain.repository.FolderRepository;
import edu.noia.myoffice.customer.domain.vo.AffiliationVO;
import edu.noia.myoffice.customer.domain.vo.CustomerVO;
import edu.noia.myoffice.customer.domain.vo.FolderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerStateFactory customerStateFactory;
    @Autowired
    private FolderRepository folderRepository;
    @Autowired
    private FolderStateFactory folderStateFactory;
    @Autowired
    private AffiliationRepository affiliationRepository;
    @Autowired
    private AffiliationStateFactory affiliationStateFactory;
    @Autowired
    private CustomerDataService dataService;

    @Transactional
    public Folder create(FolderVO data) {
        return folderRepository.save(Folder.of(folderStateFactory.of(data)));
    }

    @Transactional
    public Affiliation create(CustomerVO data) {
        return createAndAffiliate(data);
    }

    @Transactional
    public Affiliation createAndAffiliate(CustomerVO data, UUID folderId) {
        return folderRepository.findOne(folderId)
                .map(folder -> createAndAffiliate(data, folder))
                .orElse(createAndAffiliate(data));
    }

    private Affiliation createAndAffiliate(CustomerVO data, Folder folder) {
        Customer customer = customerRepository.save(Customer.of(customerStateFactory.of(data)));
        return affiliate(customer, folder);
    }

    private Affiliation createAndAffiliate(CustomerVO data) {
        Customer customer = customerRepository.save(Customer.of(customerStateFactory.of(data)));
        Folder folder = folderRepository.save(
                Folder.of(folderStateFactory.of(
                        FolderVO.builder().name(customer.getState().getData().getFullname()).build())));
        return affiliate(customer, folder);
    }

    @Transactional
    public Affiliation affiliate(UUID customerId, UUID folderId) {
        return folderRepository.findOne(folderId)
                .flatMap(folder -> customerRepository.findOne(customerId).map(customer -> Pair.of(customer, folder)))
                .map(pair -> affiliate(pair.getFirst(), pair.getSecond()))
                .orElseThrow(() -> new ResourceNotFoundException(""));
    }

    private Affiliation affiliate(Customer customer, Folder folder) {
        return affiliationRepository.save(
                Affiliation.of(
                    affiliationStateFactory.of(
                        AffiliationVO.builder()
                                .folder(folder.getId())
                                .customer(customer.getId()).build())));
    }

    @Transactional
    public Customer modify(UUID customerId, CustomerVO data) {
        return customerRepository.findOne(customerId)
                .map(customer -> customer.setData(data))
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("No %s identified by %s has been found", Customer.class, customerId)));
    }

    @Transactional
    public Folder modify(UUID folderId, FolderVO data) {
        return folderRepository.findOne(folderId)
                .map(folder -> folder.setData(data))
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("No %s identified by %s has been found", Folder.class, folderId)));
    }

    @Transactional
    public Affiliation modify(UUID customerId, UUID folderId, AffiliationVO data) {
        return affiliationRepository.findOne(customerId, folderId)
                .map(affiliation -> affiliation.setData(data))
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("No %s identified by %s and by %s has been found", Affiliation.class, customerId, folderId)));
    }

    @Transactional
    public void folderize() {
        customerRepository
                .findAll()
                .forEach(customer -> {
                    LOG.debug(customer.toString());
                    affiliate(customer,
                        folderRepository.save(
                            Folder.of(folderStateFactory.of(
                                FolderVO.builder().name(customer.getState().getData().getFullname()).build()))));
                });
    }

    @Transactional
    public void sanitize() {
        customerRepository
                .findAll()
                .forEach(customer -> {
                    LOG.debug(customer.toString());
                    customerRepository.save(customer.sanitize());
                });
    }
}