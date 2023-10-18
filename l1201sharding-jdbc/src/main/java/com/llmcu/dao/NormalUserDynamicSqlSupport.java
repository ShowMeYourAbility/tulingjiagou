package com.llmcu.dao;

import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class NormalUserDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal_user")
    public static final NormalUser normalUser = new NormalUser();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: normal_user.user_id")
    public static final SqlColumn<Long> userId = normalUser.userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: normal_user.username")
    public static final SqlColumn<String> username = normalUser.username;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: normal_user.ustatus")
    public static final SqlColumn<String> ustatus = normalUser.ustatus;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: normal_user.uage")
    public static final SqlColumn<Integer> uage = normalUser.uage;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal_user")
    public static final class NormalUser extends AliasableSqlTable<NormalUser> {
        public final SqlColumn<Long> userId = column("user_id", JDBCType.BIGINT);

        public final SqlColumn<String> username = column("username", JDBCType.VARCHAR);

        public final SqlColumn<String> ustatus = column("ustatus", JDBCType.VARCHAR);

        public final SqlColumn<Integer> uage = column("uage", JDBCType.INTEGER);

        public NormalUser() {
            super("normal_user", NormalUser::new);
        }
    }
}