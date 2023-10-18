package com.llmcu.tulingjiagou.l02mysql.pojo;

import javax.annotation.Generated;

public class TestMgb {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: test_mgb.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: test_mgb.name")
    private String name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: test_mgb.is_chinese")
    private byte chineseOrNot;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: test_mgb.addr")
    private String addr;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: test_mgb.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: test_mgb.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: test_mgb.name")
    public String getName() {
        return name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: test_mgb.name")
    public void setName(String name) {
        this.name = name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: test_mgb.is_chinese")
    public byte getChineseOrNot() {
        return chineseOrNot;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: test_mgb.is_chinese")
    public void setChineseOrNot(byte chineseOrNot) {
        this.chineseOrNot = chineseOrNot;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: test_mgb.addr")
    public String getAddr() {
        return addr;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: test_mgb.addr")
    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_mgb")
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
        TestMgb other = (TestMgb) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getChineseOrNot() == other.getChineseOrNot())
            && (this.getAddr() == null ? other.getAddr() == null : this.getAddr().equals(other.getAddr()));
    }

    @Override
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_mgb")
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + getChineseOrNot();
        result = prime * result + ((getAddr() == null) ? 0 : getAddr().hashCode());
        return result;
    }

    @Override
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_mgb")
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", chineseOrNot=").append(chineseOrNot);
        sb.append(", addr=").append(addr);
        sb.append("]");
        return sb.toString();
    }
}