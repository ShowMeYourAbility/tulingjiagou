package com.llmcu.pojo;

import javax.annotation.Generated;

public class TDict {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: t_dict.dict_id")
    private Long dictId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: t_dict.ustatus")
    private String ustatus;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: t_dict.uvalue")
    private String uvalue;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: t_dict.dict_id")
    public Long getDictId() {
        return dictId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: t_dict.dict_id")
    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: t_dict.ustatus")
    public String getUstatus() {
        return ustatus;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: t_dict.ustatus")
    public void setUstatus(String ustatus) {
        this.ustatus = ustatus;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: t_dict.uvalue")
    public String getUvalue() {
        return uvalue;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: t_dict.uvalue")
    public void setUvalue(String uvalue) {
        this.uvalue = uvalue;
    }

    @Override
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_dict")
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
        TDict other = (TDict) that;
        return (this.getDictId() == null ? other.getDictId() == null : this.getDictId().equals(other.getDictId()))
            && (this.getUstatus() == null ? other.getUstatus() == null : this.getUstatus().equals(other.getUstatus()))
            && (this.getUvalue() == null ? other.getUvalue() == null : this.getUvalue().equals(other.getUvalue()));
    }

    @Override
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_dict")
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDictId() == null) ? 0 : getDictId().hashCode());
        result = prime * result + ((getUstatus() == null) ? 0 : getUstatus().hashCode());
        result = prime * result + ((getUvalue() == null) ? 0 : getUvalue().hashCode());
        return result;
    }

    @Override
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_dict")
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", dictId=").append(dictId);
        sb.append(", ustatus=").append(ustatus);
        sb.append(", uvalue=").append(uvalue);
        sb.append("]");
        return sb.toString();
    }
}