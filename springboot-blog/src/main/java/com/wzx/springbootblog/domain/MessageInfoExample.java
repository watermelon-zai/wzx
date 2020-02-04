package com.wzx.springbootblog.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MessageInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andMessageIdIsNull() {
            addCriterion("message_id is null");
            return (Criteria) this;
        }

        public Criteria andMessageIdIsNotNull() {
            addCriterion("message_id is not null");
            return (Criteria) this;
        }

        public Criteria andMessageIdEqualTo(Integer value) {
            addCriterion("message_id =", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdNotEqualTo(Integer value) {
            addCriterion("message_id <>", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdGreaterThan(Integer value) {
            addCriterion("message_id >", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("message_id >=", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdLessThan(Integer value) {
            addCriterion("message_id <", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdLessThanOrEqualTo(Integer value) {
            addCriterion("message_id <=", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdIn(List<Integer> values) {
            addCriterion("message_id in", values, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdNotIn(List<Integer> values) {
            addCriterion("message_id not in", values, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdBetween(Integer value1, Integer value2) {
            addCriterion("message_id between", value1, value2, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdNotBetween(Integer value1, Integer value2) {
            addCriterion("message_id not between", value1, value2, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageContentIsNull() {
            addCriterion("message_content is null");
            return (Criteria) this;
        }

        public Criteria andMessageContentIsNotNull() {
            addCriterion("message_content is not null");
            return (Criteria) this;
        }

        public Criteria andMessageContentEqualTo(String value) {
            addCriterion("message_content =", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentNotEqualTo(String value) {
            addCriterion("message_content <>", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentGreaterThan(String value) {
            addCriterion("message_content >", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentGreaterThanOrEqualTo(String value) {
            addCriterion("message_content >=", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentLessThan(String value) {
            addCriterion("message_content <", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentLessThanOrEqualTo(String value) {
            addCriterion("message_content <=", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentLike(String value) {
            addCriterion("message_content like", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentNotLike(String value) {
            addCriterion("message_content not like", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentIn(List<String> values) {
            addCriterion("message_content in", values, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentNotIn(List<String> values) {
            addCriterion("message_content not in", values, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentBetween(String value1, String value2) {
            addCriterion("message_content between", value1, value2, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentNotBetween(String value1, String value2) {
            addCriterion("message_content not between", value1, value2, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageTimeIsNull() {
            addCriterion("message_time is null");
            return (Criteria) this;
        }

        public Criteria andMessageTimeIsNotNull() {
            addCriterion("message_time is not null");
            return (Criteria) this;
        }

        public Criteria andMessageTimeEqualTo(Date value) {
            addCriterion("message_time =", value, "messageTime");
            return (Criteria) this;
        }

        public Criteria andMessageTimeNotEqualTo(Date value) {
            addCriterion("message_time <>", value, "messageTime");
            return (Criteria) this;
        }

        public Criteria andMessageTimeGreaterThan(Date value) {
            addCriterion("message_time >", value, "messageTime");
            return (Criteria) this;
        }

        public Criteria andMessageTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("message_time >=", value, "messageTime");
            return (Criteria) this;
        }

        public Criteria andMessageTimeLessThan(Date value) {
            addCriterion("message_time <", value, "messageTime");
            return (Criteria) this;
        }

        public Criteria andMessageTimeLessThanOrEqualTo(Date value) {
            addCriterion("message_time <=", value, "messageTime");
            return (Criteria) this;
        }

        public Criteria andMessageTimeIn(List<Date> values) {
            addCriterion("message_time in", values, "messageTime");
            return (Criteria) this;
        }

        public Criteria andMessageTimeNotIn(List<Date> values) {
            addCriterion("message_time not in", values, "messageTime");
            return (Criteria) this;
        }

        public Criteria andMessageTimeBetween(Date value1, Date value2) {
            addCriterion("message_time between", value1, value2, "messageTime");
            return (Criteria) this;
        }

        public Criteria andMessageTimeNotBetween(Date value1, Date value2) {
            addCriterion("message_time not between", value1, value2, "messageTime");
            return (Criteria) this;
        }

        public Criteria andMessageNameIsNull() {
            addCriterion("message_name is null");
            return (Criteria) this;
        }

        public Criteria andMessageNameIsNotNull() {
            addCriterion("message_name is not null");
            return (Criteria) this;
        }

        public Criteria andMessageNameEqualTo(String value) {
            addCriterion("message_name =", value, "messageName");
            return (Criteria) this;
        }

        public Criteria andMessageNameNotEqualTo(String value) {
            addCriterion("message_name <>", value, "messageName");
            return (Criteria) this;
        }

        public Criteria andMessageNameGreaterThan(String value) {
            addCriterion("message_name >", value, "messageName");
            return (Criteria) this;
        }

        public Criteria andMessageNameGreaterThanOrEqualTo(String value) {
            addCriterion("message_name >=", value, "messageName");
            return (Criteria) this;
        }

        public Criteria andMessageNameLessThan(String value) {
            addCriterion("message_name <", value, "messageName");
            return (Criteria) this;
        }

        public Criteria andMessageNameLessThanOrEqualTo(String value) {
            addCriterion("message_name <=", value, "messageName");
            return (Criteria) this;
        }

        public Criteria andMessageNameLike(String value) {
            addCriterion("message_name like", value, "messageName");
            return (Criteria) this;
        }

        public Criteria andMessageNameNotLike(String value) {
            addCriterion("message_name not like", value, "messageName");
            return (Criteria) this;
        }

        public Criteria andMessageNameIn(List<String> values) {
            addCriterion("message_name in", values, "messageName");
            return (Criteria) this;
        }

        public Criteria andMessageNameNotIn(List<String> values) {
            addCriterion("message_name not in", values, "messageName");
            return (Criteria) this;
        }

        public Criteria andMessageNameBetween(String value1, String value2) {
            addCriterion("message_name between", value1, value2, "messageName");
            return (Criteria) this;
        }

        public Criteria andMessageNameNotBetween(String value1, String value2) {
            addCriterion("message_name not between", value1, value2, "messageName");
            return (Criteria) this;
        }

        public Criteria andMessageMarkIsNull() {
            addCriterion("message_mark is null");
            return (Criteria) this;
        }

        public Criteria andMessageMarkIsNotNull() {
            addCriterion("message_mark is not null");
            return (Criteria) this;
        }

        public Criteria andMessageMarkEqualTo(String value) {
            addCriterion("message_mark =", value, "messageMark");
            return (Criteria) this;
        }

        public Criteria andMessageMarkNotEqualTo(String value) {
            addCriterion("message_mark <>", value, "messageMark");
            return (Criteria) this;
        }

        public Criteria andMessageMarkGreaterThan(String value) {
            addCriterion("message_mark >", value, "messageMark");
            return (Criteria) this;
        }

        public Criteria andMessageMarkGreaterThanOrEqualTo(String value) {
            addCriterion("message_mark >=", value, "messageMark");
            return (Criteria) this;
        }

        public Criteria andMessageMarkLessThan(String value) {
            addCriterion("message_mark <", value, "messageMark");
            return (Criteria) this;
        }

        public Criteria andMessageMarkLessThanOrEqualTo(String value) {
            addCriterion("message_mark <=", value, "messageMark");
            return (Criteria) this;
        }

        public Criteria andMessageMarkLike(String value) {
            addCriterion("message_mark like", value, "messageMark");
            return (Criteria) this;
        }

        public Criteria andMessageMarkNotLike(String value) {
            addCriterion("message_mark not like", value, "messageMark");
            return (Criteria) this;
        }

        public Criteria andMessageMarkIn(List<String> values) {
            addCriterion("message_mark in", values, "messageMark");
            return (Criteria) this;
        }

        public Criteria andMessageMarkNotIn(List<String> values) {
            addCriterion("message_mark not in", values, "messageMark");
            return (Criteria) this;
        }

        public Criteria andMessageMarkBetween(String value1, String value2) {
            addCriterion("message_mark between", value1, value2, "messageMark");
            return (Criteria) this;
        }

        public Criteria andMessageMarkNotBetween(String value1, String value2) {
            addCriterion("message_mark not between", value1, value2, "messageMark");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}