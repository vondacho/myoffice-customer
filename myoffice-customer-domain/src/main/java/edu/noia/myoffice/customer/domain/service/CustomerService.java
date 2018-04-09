package edu.noia.myoffice.customer.domain.service;

import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.aggregate.FolderState;
import edu.noia.myoffice.customer.domain.vo.Affiliate;
import edu.noia.myoffice.customer.domain.vo.Affiliation;
import edu.noia.myoffice.customer.domain.vo.CustomerId;
import edu.noia.myoffice.customer.domain.vo.FolderId;

public interface CustomerService {

    Folder create(FolderState data);

    Affiliation create(CustomerState data);

    Affiliation create(CustomerState data, FolderId folderId);

    Affiliation affiliate(CustomerId customerId);

    Affiliation affiliate(FolderId folderId, CustomerId customerId);

    Affiliation affiliate(FolderId folderId, Affiliate affiliate);

    Affiliation affiliate(Customer customer);

    Folder unaffiliate(FolderId folderId, CustomerId customerId);

    Customer modify(CustomerId customerId, CustomerState modifier);

    Customer patch(CustomerId customerId, CustomerState modifier);

    Folder modify(FolderId folderId, FolderState modifier);

    Folder patch(FolderId folderId, FolderState modifier);

    Folder modify(FolderId folderId, Affiliate modifier);

    void delete(FolderId folderId);

    void delete(CustomerId customerId);
}