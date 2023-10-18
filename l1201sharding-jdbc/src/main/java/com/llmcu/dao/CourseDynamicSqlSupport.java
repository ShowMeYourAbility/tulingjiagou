package com.llmcu.dao;

import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class CourseDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: course")
    public static final Course course = new Course();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: course.cid")
    public static final SqlColumn<Long> cid = course.cid;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: course.cname")
    public static final SqlColumn<String> cname = course.cname;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: course.user_id")
    public static final SqlColumn<Long> userId = course.userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: course.cstatus")
    public static final SqlColumn<String> cstatus = course.cstatus;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: course")
    public static final class Course extends AliasableSqlTable<Course> {
        public final SqlColumn<Long> cid = column("cid", JDBCType.BIGINT);

        public final SqlColumn<String> cname = column("cname", JDBCType.VARCHAR);

        public final SqlColumn<Long> userId = column("user_id", JDBCType.BIGINT);

        public final SqlColumn<String> cstatus = column("cstatus", JDBCType.VARCHAR);

        public Course() {
            super("course", Course::new);
        }
    }
}