package edu.noia.myoffice.customer.data.jpa.hibernate.type;

import edu.noia.myoffice.common.data.jpa.hibernate.type.AbstractUserType;
import edu.noia.myoffice.customer.domain.vo.EmailAddress;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.StringType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class EmailAddressType extends AbstractUserType {

    @Override
    public int[] sqlTypes() {
        return new int[]{
                StringType.INSTANCE.sqlType(),
                StringType.INSTANCE.sqlType()
        };
    }

    @Override
    public Class returnedClass() {
        return EmailAddress.class;
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws SQLException {
        final Optional<String> address = Optional.ofNullable(rs.getString(names[0]));
        final Optional<String> kind = Optional.ofNullable(rs.getString(names[1]));
        return address.flatMap(a -> kind.map(k -> EmailAddress.of(a, EmailAddress.Kind.valueOf(k)))).orElse(null);
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws SQLException {
        if (value != null) {
            EmailAddress ea = (EmailAddress) value;
            st.setString(index++, ea.getAddress());
            st.setString(index, ea.getKind().toString());
        } else {
            st.setNull(index++, StringType.INSTANCE.sqlType());
            st.setNull(index, StringType.INSTANCE.sqlType());
        }
    }
}
