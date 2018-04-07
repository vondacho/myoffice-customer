package edu.noia.myoffice.customer.data.jpa.hibernate.type;

import edu.noia.myoffice.common.data.jpa.hibernate.type.AbstractUserType;
import edu.noia.myoffice.customer.domain.vo.PhoneNumber;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.StringType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PhoneNumberType extends AbstractUserType {
    @Override
    public int[] sqlTypes() {
        return new int[]{
                StringType.INSTANCE.sqlType(),
                StringType.INSTANCE.sqlType()
        };
    }

    @Override
    public Class returnedClass() {
        return PhoneNumber.class;
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws SQLException {
        final Optional<String> phoneNumber = Optional.ofNullable(rs.getString(names[0]));
        final Optional<String> kind = Optional.ofNullable(rs.getString(names[1]));
        return phoneNumber.flatMap(p -> kind.map(k -> PhoneNumber.of(p, PhoneNumber.Kind.valueOf(k)))).orElse(null);
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws SQLException {
        if (value != null) {
            PhoneNumber ea = (PhoneNumber) value;
            st.setString(index++, ea.getNumber());
            st.setString(index, ea.getKind().toString());
        } else {
            st.setNull(index++, StringType.INSTANCE.sqlType());
            st.setNull(index, StringType.INSTANCE.sqlType());
        }
    }
}
