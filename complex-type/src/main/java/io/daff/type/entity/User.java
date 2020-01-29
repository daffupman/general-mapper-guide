package io.daff.type.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "`t_user`")
public class User {
    @Id
    private Integer id;
    private String name;
    private Double age;

//    @ColumnType(typeHandler = AddressTypeHandler.class)
    @Column(name = "address")
    private Address address;
    @Column
    private SeasonEnum seasonEnum;
}