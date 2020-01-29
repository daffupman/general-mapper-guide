package io.daff.type.handler;

import io.daff.type.entity.Address;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Address类型的类型处理器
 *
 * @author daffupman
 * @since 2020/1/29
 */
public class AddressTypeHandler extends BaseTypeHandler<Address> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Address address, JdbcType jdbcType) throws SQLException {
        if (address == null) {
            return;
        }

        // 使用“，”拼接地址
        String result = address.getProvince() + "，" +
                address.getCity() + "，" +
                address.getStreet();
        ps.setString(i, result);

    }

    @Override
    public Address getNullableResult(ResultSet rs, String columnName) throws SQLException {
        // 获取列名对应的值
        String value = rs.getString(columnName);
        if(StringUtils.isEmpty(value.trim()) || !value.contains("，")) {
            return null;
        }
        String[] addresses = value.split("，");
        String province = addresses[0];
        String city = addresses[1];
        String street = addresses[2];

        return new Address(province, city, street);
    }

    @Override
    public Address getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        // 获取列名对应的值
        String value = rs.getString(columnIndex);
        if(StringUtils.isEmpty(value.trim()) || !value.contains("，")) {
            return null;
        }
        String[] addresses = value.split("，");
        String province = addresses[0];
        String city = addresses[1];
        String street = addresses[2];

        return new Address(province, city, street);
    }

    @Override
    public Address getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        // 获取列名对应的值
        String value = cs.getString(columnIndex);
        if(StringUtils.isEmpty(value.trim()) || !value.contains("，")) {
            return null;
        }
        String[] addresses = value.split("，");
        String province = addresses[0];
        String city = addresses[1];
        String street = addresses[2];

        return new Address(province, city, street);
    }
}
