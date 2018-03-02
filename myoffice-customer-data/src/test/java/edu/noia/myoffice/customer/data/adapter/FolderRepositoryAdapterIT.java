package edu.noia.myoffice.customer.data.adapter;

import edu.noia.myoffice.customer.data.CustomerDataComponentConfig;
import edu.noia.myoffice.customer.data.jpa.JpaFolderState;
import edu.noia.myoffice.customer.data.jpa.JpaFolderStateRepository;
import edu.noia.myoffice.customer.data.test.util.TestAffiliate;
import edu.noia.myoffice.customer.data.test.util.TestFolder;
import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.vo.Affiliate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CustomerDataComponentConfig.class})
@DataJpaTest
public class FolderRepositoryAdapterIT {

    @Autowired
    private FolderRepositoryAdapter repositoryAdapter;
    @Autowired
    private JpaFolderStateRepository jpaRepository;
    @PersistenceContext
    private EntityManager em;

    @Test
    public void save_should_persist_and_return_the_folder() {
        // Given
        Folder anyFolder = TestFolder.random();
        // When
        Folder folder = repositoryAdapter.save(anyFolder.getId(), anyFolder.getState());
        flushClear();
        // Then
        JpaFolderState jpaFolder = jpaRepository.findById(folder.getId().getId()).orElse(null);
        assertThat(jpaFolder).isNotNull();
        assertThat(jpaFolder).isEqualToComparingOnlyGivenFields(folder.getState(),"name", "notes");
    }

    @Test
    public void save_should_return_the_folder_with_two_affiliates() {
        // Given
        Affiliate affiliate1 = TestAffiliate.random();
        Affiliate affiliate2 = TestAffiliate.random();
        Folder anyFolder = TestFolder.random(affiliate1, affiliate2);
        // When
        Folder folder = repositoryAdapter.save(anyFolder.getId(), anyFolder.getState());
        flushClear();
        // Then
        JpaFolderState jpaFolder = jpaRepository.findById(folder.getId().getId()).orElse(null);
        assertThat(jpaFolder).isNotNull();
        assertThat(jpaFolder).isEqualToComparingOnlyGivenFields(folder.getState(),"name", "notes");
        assertThat(jpaFolder.getAffiliates()).containsExactlyInAnyOrder(affiliate1, affiliate2);
    }

    private void flushClear() {
        em.flush();
        em.clear();
    }
}
