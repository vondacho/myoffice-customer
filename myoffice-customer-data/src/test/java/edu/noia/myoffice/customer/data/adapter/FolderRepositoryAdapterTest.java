package edu.noia.myoffice.customer.data.adapter;

import edu.noia.myoffice.customer.data.jpa.JpaFolderState;
import edu.noia.myoffice.customer.data.jpa.JpaFolderStateRepository;
import edu.noia.myoffice.customer.data.test.util.TestAffiliate;
import edu.noia.myoffice.customer.data.test.util.TestFolder;
import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.aggregate.FolderState;
import edu.noia.myoffice.customer.domain.vo.Affiliate;
import edu.noia.myoffice.customer.domain.vo.FolderId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FolderRepositoryAdapterTest {

    @Mock
    private JpaFolderStateRepository jpaRepository;
    @InjectMocks
    private FolderRepositoryAdapter repositoryAdapter;

    @Test
    public void save_should_call_jpa_persistence_and_return_the_folder_with_the_expected_state() {
        // Given
        FolderState anyState = TestFolder.randomValid();
        Folder anyFolder = Folder.of(FolderId.of(UUID.randomUUID()), anyState);
        given(jpaRepository.save(any(JpaFolderState.class)))
                .willAnswer(invocation -> invocation.getArgument(0));
        // When
        Folder folder1 = repositoryAdapter.save(anyFolder);
        Folder folder2 = repositoryAdapter.save(anyFolder.getId(), anyState);
        // Then
        assertThat(folder1).isEqualTo(folder2);
        assertThat(folder1.getState()).isEqualTo(folder2.getState()).isEqualToComparingFieldByField(anyState);
        verify(jpaRepository, times(2)).save(any(JpaFolderState.class));
    }

    @Test
    public void save_should_call_jpa_persistence_and_return_the_folder_with_the_expected_native_state() {
        // Given
        FolderState anyState = TestFolder.randomValid();
        Folder anyFolder = Folder.of(FolderId.of(UUID.randomUUID()), anyState);
        JpaFolderState jpaState = JpaFolderState.of(anyState);
        given(jpaRepository.save(any(JpaFolderState.class))).willReturn(jpaState);
        // When
        Folder folder = repositoryAdapter.save(anyFolder);
        // Then
        assertThat(folder).isEqualTo(anyFolder);
        Object state = ReflectionTestUtils.getField(folder,"state");
        assertThat(state).isInstanceOf(JpaFolderState.class);
        assertThat(state).isEqualToIgnoringGivenFields(jpaState,"primaryId");
        verify(jpaRepository).save(any(JpaFolderState.class));
    }

    @Test
    public void save_should_return_the_folder_with_two_affiliates() {
        // Given
        Affiliate affiliate1 = TestAffiliate.random();
        Affiliate affiliate2 = TestAffiliate.random();
        Folder anyFolder = TestFolder.random(affiliate1, affiliate2);
        given(jpaRepository.save(any(JpaFolderState.class)))
                .willAnswer(invocation -> invocation.getArgument(0));
        // When
        Folder folder = repositoryAdapter.save(anyFolder);
        // Then
        assertThat(folder.getState()).isEqualTo(anyFolder.getState());
        assertThat(folder.getAffiliates()).containsExactlyInAnyOrder(affiliate1, affiliate2);
        verify(jpaRepository).save(any(JpaFolderState.class));
    }

    @Test
    public void save_should_filter_duplicate_affiliates() {
        // Given
        Affiliate affiliate = TestAffiliate.random();
        Folder anyFolder = TestFolder.random(affiliate, affiliate);
        given(jpaRepository.save(any(JpaFolderState.class)))
                .willAnswer(invocation -> invocation.getArgument(0));
        // When
        Folder folder = repositoryAdapter.save(anyFolder);
        // Then
        assertThat(folder.getAffiliates()).containsExactly(affiliate);
        verify(jpaRepository).save(any(JpaFolderState.class));
    }

}
