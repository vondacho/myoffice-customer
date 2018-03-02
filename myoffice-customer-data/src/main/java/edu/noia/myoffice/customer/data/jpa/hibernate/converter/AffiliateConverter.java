package edu.noia.myoffice.customer.data.jpa.hibernate.converter;

import edu.noia.myoffice.customer.domain.vo.Affiliate;
import edu.noia.myoffice.customer.domain.vo.CustomerId;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.BooleanType;
import org.hibernate.type.StringType;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class AffiliateConverter implements UserType {

    @Override
    public int[] sqlTypes() {
        return new int[] {
                StringType.INSTANCE.sqlType(),
                BooleanType.INSTANCE.sqlType(),
        };
    }

    @Override
    public Class returnedClass() {
        return Affiliate.class;
    }

    @Override
    public boolean equals(Object x, Object y) {
        return x != null && x.equals(y);
    }

    @Override
    public int hashCode(Object x) {
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws SQLException {
        final String uuid = rs.getString(names[0]);
        final Boolean primaryDebtor = rs.getBoolean(names[1]);
        return uuid != null && primaryDebtor != null ? Affiliate.of(CustomerId.of(UUID.fromString(uuid)), primaryDebtor) : null;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws SQLException {
        Affiliate affiliate = (Affiliate)value;
        st.setString(index++, affiliate.getCustomerId().getId().toString());
        st.setBoolean(index, affiliate.getPrimaryDebtor());
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
