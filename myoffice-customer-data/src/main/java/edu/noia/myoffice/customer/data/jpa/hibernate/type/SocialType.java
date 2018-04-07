package edu.noia.myoffice.customer.data.jpa.hibernate.type;

import edu.noia.myoffice.common.data.jpa.hibernate.type.AbstractUserType;
import edu.noia.myoffice.customer.domain.vo.Social;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.StringType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SocialType extends AbstractUserType {

    @Override
    public int[] sqlTypes() {
        return new int[]{
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
            Social social = (Social) value;
            st.setString(index++, social.getFacebookUrl());
            st.setString(index++, social.getGoogleplusUrl());
            st.setString(index++, social.getInstagramUrl());
            st.setString(index++, social.getLinkedinUrl());
            st.setString(index++, social.getSkypeUrl());
            st.setString(index, social.getTwitterUrl());
        } else {
            st.setNull(index++, StringType.INSTANCE.sqlType());
            st.setNull(index++, StringType.INSTANCE.sqlType());
            st.setNull(index++, StringType.INSTANCE.sqlType());
            st.setNull(index++, StringType.INSTANCE.sqlType());
            st.setNull(index++, StringType.INSTANCE.sqlType());
            st.setNull(index, StringType.INSTANCE.sqlType());
        }
    }
}
