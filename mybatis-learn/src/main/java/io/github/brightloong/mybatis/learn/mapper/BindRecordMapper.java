package io.github.brightloong.mybatis.learn.mapper;

import io.github.brightloong.mybatis.learn.pojo.BindRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author BrightLoong
 * @date 2019-02-14 14:12
 * @description
 */
public interface BindRecordMapper {
    BindRecord selectById(@Param("id") Long id);

    List<BindRecord> selectAll();
}
