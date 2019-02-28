package io.github.brightloong.mybatis.learn;

import io.github.brightloong.mybatis.learn.mapper.BindRecordMapper;
import io.github.brightloong.mybatis.learn.pojo.BindRecord;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author BrightLoong
 * @date 2019-02-14 14:20
 * @description
 */
public class MapperTest {
    private SqlSession sqlSession;

    @Before
    public void before() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
    }

    @After
    public void after() {
        sqlSession.close();
    }

    @Test
    public void testSelect() {
        BindRecordMapper mapper = sqlSession.getMapper(BindRecordMapper.class);
        List<BindRecord> bindRecord = mapper.selectAll();
        Assert.assertNotNull(bindRecord);
    }
}
