package com.llmcu.dao;

import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class TUserDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_user")
    public static final TUser TUser = new TUser();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: t_user.user_id")
    public static final SqlColumn<Long> userId = TUser.userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: t_user.username")
    public static final SqlColumn<String> username = TUser.username;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: t_user.ustatus")
    public static final SqlColumn<String> ustatus = TUser.ustatus;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: t_user.uage")
    public static final SqlColumn<Integer> uage = TUser.uage;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_user")
    public static final class TUser extends AliasableSqlTable<TUser> {
        public final SqlColumn<Long> userId = column("user_id", JDBCType.BIGINT);

        public final SqlColumn<String> username = column("username", JDBCType.VARCHAR);

        public final SqlColumn<String> ustatus = column("ustatus", JDBCType.VARCHAR);

        public final SqlColumn<Integer> uage = column("uage", JDBCType.INTEGER);

        public TUser() {
            super("t_user", TUser::new);
        }
    }
}