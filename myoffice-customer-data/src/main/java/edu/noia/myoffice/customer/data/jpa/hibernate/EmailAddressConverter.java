package edu.noia.myoffice.customer.data.jpa.hibernate;

import edu.noia.myoffice.customer.domain.vo.EmailAddress;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
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
    public boolean equals(Object x, Object y) throws HibernateException {
        return x != null ? x.equals(y) : false;
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
        final String address = rs.getString(names[0]);
        final String kind = rs.getString(names[1]);
        return address != null && kind != null ? EmailAddress.of(address, EmailAddress.Kind.valueOf(kind)) : null;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
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
