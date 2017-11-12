package edu.noia.myoffice.customer.data.jpa.hibernate;

import edu.noia.myoffice.customer.domain.vo.Affiliate;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
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
    public boolean equals(Object x, Object y) throws HibernateException {
        return x != null ? x.equals(y) : false;
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
        final String uuid = rs.getString(names[0]);
        final Boolean primaryDebtor = rs.getBoolean(names[1]);
        return uuid != null && primaryDebtor != null ? Affiliate.of(UUID.fromString(uuid), primaryDebtor) : null;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
        Affiliate affiliate = (Affiliate)value;
        st.setString(index++, affiliate.getCustomerId().toString());
        st.setBoolean(index, affiliate.getPrimaryDebtor());
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable)value;
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return cached;
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }
}
