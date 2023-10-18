package com.llmcu.tulingjiagou.l0905clusterrediscache.pojo;

import javax.annotation.Generated;

public class Normal {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: normal.cid")
    private Long cid;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: normal.cname")
    private String cname;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: normal.user_id")
    private Long userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: normal.cstatus")
    private String cstatus;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: normal.cid")
    public Long getCid() {
        return cid;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: normal.cid")
    public void setCid(Long cid) {
        this.cid = cid;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: normal.cname")
    public String getCname() {
        return cname;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: normal.cname")
    public void setCname(String cname) {
        this.cname = cname;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: normal.user_id")
    public Long getUserId() {
        return userId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: normal.user_id")
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: normal.cstatus")
    public String getCstatus() {
        return cstatus;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: normal.cstatus")
    public void setCstatus(String cstatus) {
        this.cstatus = cstatus;
    }

    @Override
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal")
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Normal other = (Normal) that;
        return (this.getCid() == null ? other.getCid() == null : this.getCid().equals(other.getCid()))
            && (this.getCname() == null ? other.getCname() == null : this.getCname().equals(other.getCname()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCstatus() == null ? other.getCstatus() == null : this.getCstatus().equals(other.getCstatus()));
    }

    @Override
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal")
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCid() == null) ? 0 : getCid().hashCode());
        result = prime * result + ((getCname() == null) ? 0 : getCname().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCstatus() == null) ? 0 : getCstatus().hashCode());
        return result;
    }

    @Override
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal")
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cid=").append(cid);
        sb.append(", cname=").append(cname);
        sb.append(", userId=").append(userId);
        sb.append(", cstatus=").append(cstatus);
        sb.append("]");
        return sb.toString();
    }
}