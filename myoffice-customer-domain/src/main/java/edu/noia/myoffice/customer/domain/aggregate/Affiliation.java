package edu.noia.myoffice.customer.domain.aggregate;

import edu.noia.myoffice.customer.domain.vo.AffiliationVO;
import edu.noia.myoffice.customer.domain.vo.CustomerVO;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@EqualsAndHashCode(of = {"state"}, doNotUseGetters = true, callSuper = false)
@RequiredArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Affiliation {
    @NonNull
    AffiliationState state;

    public AffiliationState getState() {
        return state;
    }

    public Affiliation setData(AffiliationVO data) {
        state.setData(data);
        return this;
    }
}
