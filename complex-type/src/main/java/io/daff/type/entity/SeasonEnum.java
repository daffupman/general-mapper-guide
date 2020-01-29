package io.daff.type.entity;

/**
 * @author daffupman
 * @since 2020/1/29
 */
public enum SeasonEnum {
    SPRING("spring"),
    SUMMER("summer"),
    AUTUMN("autumn"),
    WINTER("winter");

    String seasonName;

    SeasonEnum(String seasonName) {
        this.seasonName = seasonName;
    }
}
