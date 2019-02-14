package io.github.brightloong.mybatis.learn.pojo;

import java.util.Date;

public class BindRecord {
    private Long id;

    /**
     * 渠道/绑定方uid
     */
    private Long bindUid;

    /**
     * 被绑定方uid
     */
    private Long bindByUid;

    /**
     * 绑定规则类型，1-长期，2-连续订阅，3-短期
     */
    private Integer bindType;

    /**
     * 绑定规则id
     */
    private Long bindRuleRef;

    /**
     * 有效起始时间
     */
    private Date startTime;

    /**
     * 有效终止时间
     */
    private Date endTime;

    /**
     * 额外信息，json字段
     */
    private String extra;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 修改时间
     */
    private Date lastUpdatedTime;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取渠道/绑定方uid
     *
     * @return bind_uid - 渠道/绑定方uid
     */
    public Long getBindUid() {
        return bindUid;
    }

    /**
     * 设置渠道/绑定方uid
     *
     * @param bindUid 渠道/绑定方uid
     */
    public void setBindUid(Long bindUid) {
        this.bindUid = bindUid;
    }

    /**
     * 获取被绑定方uid
     *
     * @return bind_by_uid - 被绑定方uid
     */
    public Long getBindByUid() {
        return bindByUid;
    }

    /**
     * 设置被绑定方uid
     *
     * @param bindByUid 被绑定方uid
     */
    public void setBindByUid(Long bindByUid) {
        this.bindByUid = bindByUid;
    }

    /**
     * 获取绑定规则类型，1-长期，2-连续订阅，3-短期
     *
     * @return bind_type - 绑定规则类型，1-长期，2-连续订阅，3-短期
     */
    public Integer getBindType() {
        return bindType;
    }

    /**
     * 设置绑定规则类型，1-长期，2-连续订阅，3-短期
     *
     * @param bindType 绑定规则类型，1-长期，2-连续订阅，3-短期
     */
    public void setBindType(Integer bindType) {
        this.bindType = bindType;
    }

    /**
     * 获取绑定规则id
     *
     * @return bind_rule_ref - 绑定规则id
     */
    public Long getBindRuleRef() {
        return bindRuleRef;
    }

    /**
     * 设置绑定规则id
     *
     * @param bindRuleRef 绑定规则id
     */
    public void setBindRuleRef(Long bindRuleRef) {
        this.bindRuleRef = bindRuleRef;
    }

    /**
     * 获取有效起始时间
     *
     * @return start_time - 有效起始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置有效起始时间
     *
     * @param startTime 有效起始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取有效终止时间
     *
     * @return end_time - 有效终止时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置有效终止时间
     *
     * @param endTime 有效终止时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取额外信息，json字段
     *
     * @return extra 额外信息，json字段
     */
    public String getExtra() {
        return extra;
    }

    /**
     * 设置额外信息，json字段
     *
     * @param extra - 额外信息，json字段
     */
    public void setExtra(String extra) {
        this.extra = extra;
    }

    /**
     * 获取创建时间
     *
     * @return created_time - 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置创建时间
     *
     * @param createdTime 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取修改时间
     *
     * @return last_updated_time - 修改时间
     */
    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    /**
     * 设置修改时间
     *
     * @param lastUpdatedTime 修改时间
     */
    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
}