package edu.noia.myoffice.customer.data.jpa.hibernate.type;

import edu.noia.myoffice.common.data.jpa.hibernate.type.AbstractUserType;
import edu.noia.myoffice.customer.domain.vo.Profile;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.BooleanType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileType extends AbstractUserType {

    @Override
    public int[] sqlTypes() {
        return new int[]{
                BooleanType.INSTANCE.sqlType(),
                BooleanType.INSTANCE.sqlType(),
                BooleanType.INSTANCE.sqlType()
        };
    }

    @Override
    public Class returnedClass() {
        return Profile.class;
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws SQLException {
        return Profile.of(
                rs.getBoolean(names[0]),
                rs.getBoolean(names[1]),
                rs.getBoolean(names[2])
        );
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws SQLException {
        if (value != null) {
            Profile profile = (Profile) value;
            st.setBoolean(index++, profile.getHasMoved());
            st.setBoolean(index++, profile.getIsSubcontractor());
            st.setBoolean(index, profile.getSendInvitation());
        } else {
            st.setNull(index++, BooleanType.INSTANCE.sqlType());
            st.setNull(index++, BooleanType.INSTANCE.sqlType());
            st.setNull(index, BooleanType.INSTANCE.sqlType());
        }
    }
}
