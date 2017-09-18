package edu.noia.myoffice.customer.domain.service;

import edu.noia.myoffice.common.domain.exception.ResourceNotFoundException;
import edu.noia.myoffice.customer.domain.aggregate.*;
import edu.noia.myoffice.customer.domain.repository.AffiliationRepository;
import edu.noia.myoffice.customer.domain.repository.CustomerRepository;
import edu.noia.myoffice.customer.domain.repository.FolderRepository;
import edu.noia.myoffice.customer.domain.vo.AffiliationVO;
import edu.noia.myoffice.customer.domain.vo.CustomerVO;
import edu.noia.myoffice.customer.domain.vo.FolderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
public class CustomerDataService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private FolderRepository folderRepository;
    @Autowired
    private AffiliationRepository affiliationRepository;

    @Transactional(readOnly = true)
    public List<Pair<CustomerVO, AffiliationVO>> findAllCustomers(UUID folderId) {
        return affiliationRepository.findAllByFolder(folderId)
                .stream()
                .map(Affiliation::getState)
                .map(AffiliationState::getData)
                .map(affiliation -> Pair.of(
                        customerRepository.findOne(affiliation.getCustomer())
                                .map(Customer::getState)
                                .map(CustomerState::getData)
                                .get(),
                        affiliation))
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<Pair<FolderVO, AffiliationVO>> findAllFolders(UUID customerId) {
        return affiliationRepository.findAllByCustomer(customerId)
                .stream()
                .map(Affiliation::getState)
                .map(AffiliationState::getData)
                .map(affiliation -> Pair.of(
                        folderRepository.findOne(affiliation.getFolder())
                                .map(Folder::getState)
                                .map(FolderState::getData)
                                .get(),
                        affiliation))
                .collect(toList());
    }
}