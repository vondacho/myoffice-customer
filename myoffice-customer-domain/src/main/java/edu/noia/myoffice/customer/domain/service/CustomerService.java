package edu.noia.myoffice.customer.domain.service;

import edu.noia.myoffice.common.domain.exception.ResourceNotFoundException;
import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.aggregate.FolderState;
import edu.noia.myoffice.customer.domain.factory.CustomerStateFactory;
import edu.noia.myoffice.customer.domain.factory.FolderStateFactory;
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

import java.util.Optional;
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

    @Transactional
    public Folder createFolder(FolderVO data) {
        return folderRepository.save(Folder.of(folderStateFactory.of(data)));
    }

    @Transactional
    public AffiliationVO create(CustomerVO data) {
        return createInFolder(data, Optional.empty());
    }

    @Transactional
    public AffiliationVO createInFolder(CustomerVO data, UUID folderId) {
        return createInFolder(data, folderRepository.findOne(folderId));
    }

    private AffiliationVO createInFolder(CustomerVO data, Optional<Folder> folder) {
        Customer customer = customerRepository.save(Customer.of(customerStateFactory.of(data)));

        return AffiliationVO.of(
                folderRepository.save(
                        folder.orElse(
                                Folder.of(folderStateFactory.of(
                                        FolderVO.builder().name(customer.getState().getFullname()).build())))
                                .add(customer)),
                customer);
    }

    @Transactional
    public AffiliationVO affiliate(UUID customerId, UUID folderId) {
        return folderRepository.findOne(folderId)
                .flatMap(folder -> customerRepository.findOne(customerId).map(customer -> Pair.of(folder, customer)))
                .map(pair -> Pair.of(folderRepository.save(pair.getFirst().add(pair.getSecond())), pair.getSecond()))
                .map(pair -> AffiliationVO.of(pair.getFirst(), pair.getSecond()))
                .orElseThrow(() -> new ResourceNotFoundException(""));
    }

    @Transactional
    public Customer modify(UUID customerId, CustomerVO data) {
        return customerRepository.findOne(customerId)
                .map(customer -> customer.setState(data))
                .orElseThrow(() -> new ResourceNotFoundException(""));
    }

    @Transactional
    public Folder modify(UUID folderId, FolderVO data) {
        return folderRepository.findOne(folderId)
                .map(folder -> folder.setState(data))
                .orElseThrow(() -> new ResourceNotFoundException(""));
    }

    @Transactional
    public void folderize() {
        customerRepository
                .findAll()
                .forEach(customer -> {
                    LOG.debug(customer.toString());
                    folderRepository.save(
                            Folder.of(folderStateFactory.of(
                                    FolderVO.builder().name(customer.getState().getFullname()).build()))
                            .add(customer));
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