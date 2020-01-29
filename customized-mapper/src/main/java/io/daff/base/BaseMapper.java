package io.daff.base;

import tk.mybatis.mapper.common.base.select.SelectAllMapper;
import tk.mybatis.mapper.common.base.select.SelectByPrimaryKeyMapper;

/**
 * 自定义的通用Mapper接口
 * 可以自由组合我们想要的CRUD功能
 *
 * @author daffupman
 * @since 2020/1/29
 */
public interface BaseMapper<T>
        extends SelectAllMapper<T>, SelectByPrimaryKeyMapper<T>, BatchUpdateSelectiveMapper<T> {
}
