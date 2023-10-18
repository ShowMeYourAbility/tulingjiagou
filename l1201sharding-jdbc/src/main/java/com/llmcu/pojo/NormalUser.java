package com.llmcu.pojo;

import javax.annotation.Generated;

public class NormalUser {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: normal_user.user_id")
    private Long userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: normal_user.username")
    private String username;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: normal_user.ustatus")
    private String ustatus;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: normal_user.uage")
    private Integer uage;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: normal_user.user_id")
    public Long getUserId() {
        return userId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: normal_user.user_id")
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: normal_user.username")
    public String getUsername() {
        return username;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: normal_user.username")
    public void setUsername(String username) {
        this.username = username;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: normal_user.ustatus")
    public String getUstatus() {
        return ustatus;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: normal_user.ustatus")
    public void setUstatus(String ustatus) {
        this.ustatus = ustatus;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: normal_user.uage")
    public Integer getUage() {
        return uage;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: normal_user.uage")
    public void setUage(Integer uage) {
        this.uage = uage;
    }

    @Override
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal_user")
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
        NormalUser other = (NormalUser) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getUstatus() == null ? other.getUstatus() == null : this.getUstatus().equals(other.getUstatus()))
            && (this.getUage() == null ? other.getUage() == null : this.getUage().equals(other.getUage()));
    }

    @Override
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal_user")
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getUstatus() == null) ? 0 : getUstatus().hashCode());
        result = prime * result + ((getUage() == null) ? 0 : getUage().hashCode());
        return result;
    }

    @Override
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal_user")
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", username=").append(username);
        sb.append(", ustatus=").append(ustatus);
        sb.append(", uage=").append(uage);
        sb.append("]");
        return sb.toString();
    }
}