package com.wzx.springbootblog.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ArticleInfoExample() {
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

        public Criteria andArticleIdIsNull() {
            addCriterion("article_id is null");
            return (Criteria) this;
        }

        public Criteria andArticleIdIsNotNull() {
            addCriterion("article_id is not null");
            return (Criteria) this;
        }

        public Criteria andArticleIdEqualTo(Integer value) {
            addCriterion("article_id =", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotEqualTo(Integer value) {
            addCriterion("article_id <>", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdGreaterThan(Integer value) {
            addCriterion("article_id >", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("article_id >=", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdLessThan(Integer value) {
            addCriterion("article_id <", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdLessThanOrEqualTo(Integer value) {
            addCriterion("article_id <=", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdIn(List<Integer> values) {
            addCriterion("article_id in", values, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotIn(List<Integer> values) {
            addCriterion("article_id not in", values, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdBetween(Integer value1, Integer value2) {
            addCriterion("article_id between", value1, value2, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("article_id not between", value1, value2, "articleId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNull() {
            addCriterion("category_id is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("category_id is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(Integer value) {
            addCriterion("category_id =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(Integer value) {
            addCriterion("category_id <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(Integer value) {
            addCriterion("category_id >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("category_id >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(Integer value) {
            addCriterion("category_id <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("category_id <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<Integer> values) {
            addCriterion("category_id in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<Integer> values) {
            addCriterion("category_id not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(Integer value1, Integer value2) {
            addCriterion("category_id between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("category_id not between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andArticleTitleIsNull() {
            addCriterion("article_title is null");
            return (Criteria) this;
        }

        public Criteria andArticleTitleIsNotNull() {
            addCriterion("article_title is not null");
            return (Criteria) this;
        }

        public Criteria andArticleTitleEqualTo(String value) {
            addCriterion("article_title =", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleNotEqualTo(String value) {
            addCriterion("article_title <>", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleGreaterThan(String value) {
            addCriterion("article_title >", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleGreaterThanOrEqualTo(String value) {
            addCriterion("article_title >=", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleLessThan(String value) {
            addCriterion("article_title <", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleLessThanOrEqualTo(String value) {
            addCriterion("article_title <=", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleLike(String value) {
            addCriterion("article_title like", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleNotLike(String value) {
            addCriterion("article_title not like", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleIn(List<String> values) {
            addCriterion("article_title in", values, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleNotIn(List<String> values) {
            addCriterion("article_title not in", values, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleBetween(String value1, String value2) {
            addCriterion("article_title between", value1, value2, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleNotBetween(String value1, String value2) {
            addCriterion("article_title not between", value1, value2, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleContentIsNull() {
            addCriterion("article_content is null");
            return (Criteria) this;
        }

        public Criteria andArticleContentIsNotNull() {
            addCriterion("article_content is not null");
            return (Criteria) this;
        }

        public Criteria andArticleContentEqualTo(String value) {
            addCriterion("article_content =", value, "articleContent");
            return (Criteria) this;
        }

        public Criteria andArticleContentNotEqualTo(String value) {
            addCriterion("article_content <>", value, "articleContent");
            return (Criteria) this;
        }

        public Criteria andArticleContentGreaterThan(String value) {
            addCriterion("article_content >", value, "articleContent");
            return (Criteria) this;
        }

        public Criteria andArticleContentGreaterThanOrEqualTo(String value) {
            addCriterion("article_content >=", value, "articleContent");
            return (Criteria) this;
        }

        public Criteria andArticleContentLessThan(String value) {
            addCriterion("article_content <", value, "articleContent");
            return (Criteria) this;
        }

        public Criteria andArticleContentLessThanOrEqualTo(String value) {
            addCriterion("article_content <=", value, "articleContent");
            return (Criteria) this;
        }

        public Criteria andArticleContentLike(String value) {
            addCriterion("article_content like", value, "articleContent");
            return (Criteria) this;
        }

        public Criteria andArticleContentNotLike(String value) {
            addCriterion("article_content not like", value, "articleContent");
            return (Criteria) this;
        }

        public Criteria andArticleContentIn(List<String> values) {
            addCriterion("article_content in", values, "articleContent");
            return (Criteria) this;
        }

        public Criteria andArticleContentNotIn(List<String> values) {
            addCriterion("article_content not in", values, "articleContent");
            return (Criteria) this;
        }

        public Criteria andArticleContentBetween(String value1, String value2) {
            addCriterion("article_content between", value1, value2, "articleContent");
            return (Criteria) this;
        }

        public Criteria andArticleContentNotBetween(String value1, String value2) {
            addCriterion("article_content not between", value1, value2, "articleContent");
            return (Criteria) this;
        }

        public Criteria andArticleImgIsNull() {
            addCriterion("article_img is null");
            return (Criteria) this;
        }

        public Criteria andArticleImgIsNotNull() {
            addCriterion("article_img is not null");
            return (Criteria) this;
        }

        public Criteria andArticleImgEqualTo(String value) {
            addCriterion("article_img =", value, "articleImg");
            return (Criteria) this;
        }

        public Criteria andArticleImgNotEqualTo(String value) {
            addCriterion("article_img <>", value, "articleImg");
            return (Criteria) this;
        }

        public Criteria andArticleImgGreaterThan(String value) {
            addCriterion("article_img >", value, "articleImg");
            return (Criteria) this;
        }

        public Criteria andArticleImgGreaterThanOrEqualTo(String value) {
            addCriterion("article_img >=", value, "articleImg");
            return (Criteria) this;
        }

        public Criteria andArticleImgLessThan(String value) {
            addCriterion("article_img <", value, "articleImg");
            return (Criteria) this;
        }

        public Criteria andArticleImgLessThanOrEqualTo(String value) {
            addCriterion("article_img <=", value, "articleImg");
            return (Criteria) this;
        }

        public Criteria andArticleImgLike(String value) {
            addCriterion("article_img like", value, "articleImg");
            return (Criteria) this;
        }

        public Criteria andArticleImgNotLike(String value) {
            addCriterion("article_img not like", value, "articleImg");
            return (Criteria) this;
        }

        public Criteria andArticleImgIn(List<String> values) {
            addCriterion("article_img in", values, "articleImg");
            return (Criteria) this;
        }

        public Criteria andArticleImgNotIn(List<String> values) {
            addCriterion("article_img not in", values, "articleImg");
            return (Criteria) this;
        }

        public Criteria andArticleImgBetween(String value1, String value2) {
            addCriterion("article_img between", value1, value2, "articleImg");
            return (Criteria) this;
        }

        public Criteria andArticleImgNotBetween(String value1, String value2) {
            addCriterion("article_img not between", value1, value2, "articleImg");
            return (Criteria) this;
        }

        public Criteria andArticleRecomIsNull() {
            addCriterion("article_recom is null");
            return (Criteria) this;
        }

        public Criteria andArticleRecomIsNotNull() {
            addCriterion("article_recom is not null");
            return (Criteria) this;
        }

        public Criteria andArticleRecomEqualTo(String value) {
            addCriterion("article_recom =", value, "articleRecom");
            return (Criteria) this;
        }

        public Criteria andArticleRecomNotEqualTo(String value) {
            addCriterion("article_recom <>", value, "articleRecom");
            return (Criteria) this;
        }

        public Criteria andArticleRecomGreaterThan(String value) {
            addCriterion("article_recom >", value, "articleRecom");
            return (Criteria) this;
        }

        public Criteria andArticleRecomGreaterThanOrEqualTo(String value) {
            addCriterion("article_recom >=", value, "articleRecom");
            return (Criteria) this;
        }

        public Criteria andArticleRecomLessThan(String value) {
            addCriterion("article_recom <", value, "articleRecom");
            return (Criteria) this;
        }

        public Criteria andArticleRecomLessThanOrEqualTo(String value) {
            addCriterion("article_recom <=", value, "articleRecom");
            return (Criteria) this;
        }

        public Criteria andArticleRecomLike(String value) {
            addCriterion("article_recom like", value, "articleRecom");
            return (Criteria) this;
        }

        public Criteria andArticleRecomNotLike(String value) {
            addCriterion("article_recom not like", value, "articleRecom");
            return (Criteria) this;
        }

        public Criteria andArticleRecomIn(List<String> values) {
            addCriterion("article_recom in", values, "articleRecom");
            return (Criteria) this;
        }

        public Criteria andArticleRecomNotIn(List<String> values) {
            addCriterion("article_recom not in", values, "articleRecom");
            return (Criteria) this;
        }

        public Criteria andArticleRecomBetween(String value1, String value2) {
            addCriterion("article_recom between", value1, value2, "articleRecom");
            return (Criteria) this;
        }

        public Criteria andArticleRecomNotBetween(String value1, String value2) {
            addCriterion("article_recom not between", value1, value2, "articleRecom");
            return (Criteria) this;
        }

        public Criteria andArticleTimeIsNull() {
            addCriterion("article_time is null");
            return (Criteria) this;
        }

        public Criteria andArticleTimeIsNotNull() {
            addCriterion("article_time is not null");
            return (Criteria) this;
        }

        public Criteria andArticleTimeEqualTo(Date value) {
            addCriterion("article_time =", value, "articleTime");
            return (Criteria) this;
        }

        public Criteria andArticleTimeNotEqualTo(Date value) {
            addCriterion("article_time <>", value, "articleTime");
            return (Criteria) this;
        }

        public Criteria andArticleTimeGreaterThan(Date value) {
            addCriterion("article_time >", value, "articleTime");
            return (Criteria) this;
        }

        public Criteria andArticleTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("article_time >=", value, "articleTime");
            return (Criteria) this;
        }

        public Criteria andArticleTimeLessThan(Date value) {
            addCriterion("article_time <", value, "articleTime");
            return (Criteria) this;
        }

        public Criteria andArticleTimeLessThanOrEqualTo(Date value) {
            addCriterion("article_time <=", value, "articleTime");
            return (Criteria) this;
        }

        public Criteria andArticleTimeIn(List<Date> values) {
            addCriterion("article_time in", values, "articleTime");
            return (Criteria) this;
        }

        public Criteria andArticleTimeNotIn(List<Date> values) {
            addCriterion("article_time not in", values, "articleTime");
            return (Criteria) this;
        }

        public Criteria andArticleTimeBetween(Date value1, Date value2) {
            addCriterion("article_time between", value1, value2, "articleTime");
            return (Criteria) this;
        }

        public Criteria andArticleTimeNotBetween(Date value1, Date value2) {
            addCriterion("article_time not between", value1, value2, "articleTime");
            return (Criteria) this;
        }

        public Criteria andArticleMarkIsNull() {
            addCriterion("article_mark is null");
            return (Criteria) this;
        }

        public Criteria andArticleMarkIsNotNull() {
            addCriterion("article_mark is not null");
            return (Criteria) this;
        }

        public Criteria andArticleMarkEqualTo(String value) {
            addCriterion("article_mark =", value, "articleMark");
            return (Criteria) this;
        }

        public Criteria andArticleMarkNotEqualTo(String value) {
            addCriterion("article_mark <>", value, "articleMark");
            return (Criteria) this;
        }

        public Criteria andArticleMarkGreaterThan(String value) {
            addCriterion("article_mark >", value, "articleMark");
            return (Criteria) this;
        }

        public Criteria andArticleMarkGreaterThanOrEqualTo(String value) {
            addCriterion("article_mark >=", value, "articleMark");
            return (Criteria) this;
        }

        public Criteria andArticleMarkLessThan(String value) {
            addCriterion("article_mark <", value, "articleMark");
            return (Criteria) this;
        }

        public Criteria andArticleMarkLessThanOrEqualTo(String value) {
            addCriterion("article_mark <=", value, "articleMark");
            return (Criteria) this;
        }

        public Criteria andArticleMarkLike(String value) {
            addCriterion("article_mark like", value, "articleMark");
            return (Criteria) this;
        }

        public Criteria andArticleMarkNotLike(String value) {
            addCriterion("article_mark not like", value, "articleMark");
            return (Criteria) this;
        }

        public Criteria andArticleMarkIn(List<String> values) {
            addCriterion("article_mark in", values, "articleMark");
            return (Criteria) this;
        }

        public Criteria andArticleMarkNotIn(List<String> values) {
            addCriterion("article_mark not in", values, "articleMark");
            return (Criteria) this;
        }

        public Criteria andArticleMarkBetween(String value1, String value2) {
            addCriterion("article_mark between", value1, value2, "articleMark");
            return (Criteria) this;
        }

        public Criteria andArticleMarkNotBetween(String value1, String value2) {
            addCriterion("article_mark not between", value1, value2, "articleMark");
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