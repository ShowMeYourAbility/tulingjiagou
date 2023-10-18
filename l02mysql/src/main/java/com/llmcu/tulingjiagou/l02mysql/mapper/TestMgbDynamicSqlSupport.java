package com.llmcu.tulingjiagou.l02mysql.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class TestMgbDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_mgb")
    public static final TestMgb testMgb = new TestMgb();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: test_mgb.id")
    public static final SqlColumn<Long> id = testMgb.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: test_mgb.name")
    public static final SqlColumn<String> name = testMgb.name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: test_mgb.is_chinese")
    public static final SqlColumn<Byte> chineseOrNot = testMgb.chineseOrNot;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: test_mgb.addr")
    public static final SqlColumn<String> addr = testMgb.addr;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_mgb")
    public static final class TestMgb extends AliasableSqlTable<TestMgb> {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Byte> chineseOrNot = column("is_chinese", JDBCType.TINYINT);

        public final SqlColumn<String> addr = column("addr", JDBCType.VARCHAR);

        public TestMgb() {
            super("test_mgb", TestMgb::new);
        }
    }
}