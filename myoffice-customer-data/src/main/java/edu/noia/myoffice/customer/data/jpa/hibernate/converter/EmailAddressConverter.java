package edu.noia.myoffice.customer.data.jpa.hibernate.converter;

import edu.noia.myoffice.customer.domain.vo.EmailAddress;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.StringType;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmailAddressConverter implements UserType {
    @Override
    public int[] sqlTypes() {
        return new int[] {
            StringType.INSTANCE.sqlType(),
            StringType.INSTANCE.sqlType()
        };
    }

    @Override
    public Class returnedClass() {
        return EmailAddress.class;
    }

    @Override
    public boolean equals(Object x, Object y) {
        return x != null && x.equals(y);
    }

    @Override
    public int hashCode(Object x){
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws SQLException {
        final String address = rs.getString(names[0]);
        final String kind = rs.getString(names[1]);
        return address != null && kind != null ? EmailAddress.of(address, EmailAddress.Kind.valueOf(kind)) : null;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws SQLException {
        if (value != null) {
            EmailAddress ea = (EmailAddress) value;
            st.setString(index++, ea.getAddress());
            st.setString(index, ea.getKind().toString());
        }
        else {
            st.setNull(index++, StringType.INSTANCE.sqlType());
            st.setNull(index, StringType.INSTANCE.sqlType());
        }
    }

    @Override
    public Object deepCopy(Object value) {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object value) {
        return (Serializable)value;
    }

    @Override
    public Object assemble(Serializable cached, Object owner) {
        return cached;
    }

    @Override
    public Object replace(Object original, Object target, Object owner) {
        return original;
    }
}
