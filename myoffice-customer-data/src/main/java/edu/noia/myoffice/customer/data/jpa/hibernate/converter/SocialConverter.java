package edu.noia.myoffice.customer.data.jpa.hibernate.converter;

import edu.noia.myoffice.customer.domain.vo.Social;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.StringType;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SocialConverter implements UserType {

    @Override
    public int[] sqlTypes() {
        return new int[] {
                StringType.INSTANCE.sqlType(),
                StringType.INSTANCE.sqlType(),
                StringType.INSTANCE.sqlType(),
                StringType.INSTANCE.sqlType(),
                StringType.INSTANCE.sqlType(),
                StringType.INSTANCE.sqlType()
        };
    }

    @Override
    public Class returnedClass() {
        return Social.class;
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
        return Social.of(
                rs.getString(names[0]),
                rs.getString(names[1]),
                rs.getString(names[2]),
                rs.getString(names[3]),
                rs.getString(names[4]),
                rs.getString(names[5])
        );
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws SQLException {
        if (value != null) {
            Social social = (Social)value;
            st.setString(index++, social.getFacebookUrl());
            st.setString(index++, social.getGoogleplusUrl());
            st.setString(index++, social.getInstagramUrl());
            st.setString(index++, social.getLinkedinUrl());
            st.setString(index++, social.getSkypeUrl());
            st.setString(index, social.getTwitterUrl());
        }
        else {
            st.setNull(index++, StringType.INSTANCE.sqlType());
            st.setNull(index++, StringType.INSTANCE.sqlType());
            st.setNull(index++, StringType.INSTANCE.sqlType());
            st.setNull(index++, StringType.INSTANCE.sqlType());
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
