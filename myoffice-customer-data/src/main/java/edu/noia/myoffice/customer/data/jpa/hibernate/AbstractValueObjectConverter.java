package edu.noia.myoffice.customer.data.jpa.hibernate;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;
import org.springframework.data.util.Pair;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class AbstractValueObjectConverter<T> implements UserType {

    @NonNull
    Class clazz;

    LinkedHashMap<String, Integer> attributes = new LinkedHashMap<>();

    @Override
    public int[] sqlTypes() {
        return attributes.values().stream().mapToInt(x -> x).toArray();
    }

    @Override
    public Class returnedClass() {
        return clazz.getClass();
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
        return mapGet(Arrays.stream(names)
            .map(name -> {
                try {
                    return Pair.of(name, rs.getObject(name));
                } finally {
                    return null;
                }
            })
            .filter(p -> p == null)
            .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond)));
    }

    public abstract T mapGet(Map<String, Object> values);

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
        if (value != null) {
            for (Map.Entry<String, Object> e : mapSet((T)value).entrySet()) {
                st.setObject(index++, e.getValue(), attributes.get(e.getKey()));
            }
        }
        else {
            for (Map.Entry<String, Integer> e : attributes.entrySet()) {
                st.setNull(index++, e.getValue());
            }
        }
    }

    public abstract Map<String, Object> mapSet(T value);

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
