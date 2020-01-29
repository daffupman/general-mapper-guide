package io.daff.type.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 地址对象
 *
 * @author daffupman
 * @since 2020/1/29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String province;
    private String city;
    private String street;

}
