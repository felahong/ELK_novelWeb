package com.sif.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NovelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NovelExample() {
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

        public Criteria andNidIsNull() {
            addCriterion("nid is null");
            return (Criteria) this;
        }

        public Criteria andNidIsNotNull() {
            addCriterion("nid is not null");
            return (Criteria) this;
        }

        public Criteria andNidEqualTo(String value) {
            addCriterion("nid =", value, "nid");
            return (Criteria) this;
        }

        public Criteria andNidNotEqualTo(String value) {
            addCriterion("nid <>", value, "nid");
            return (Criteria) this;
        }

        public Criteria andNidGreaterThan(String value) {
            addCriterion("nid >", value, "nid");
            return (Criteria) this;
        }

        public Criteria andNidGreaterThanOrEqualTo(String value) {
            addCriterion("nid >=", value, "nid");
            return (Criteria) this;
        }

        public Criteria andNidLessThan(String value) {
            addCriterion("nid <", value, "nid");
            return (Criteria) this;
        }

        public Criteria andNidLessThanOrEqualTo(String value) {
            addCriterion("nid <=", value, "nid");
            return (Criteria) this;
        }

        public Criteria andNidLike(String value) {
            addCriterion("nid like", value, "nid");
            return (Criteria) this;
        }

        public Criteria andNidNotLike(String value) {
            addCriterion("nid not like", value, "nid");
            return (Criteria) this;
        }

        public Criteria andNidIn(List<String> values) {
            addCriterion("nid in", values, "nid");
            return (Criteria) this;
        }

        public Criteria andNidNotIn(List<String> values) {
            addCriterion("nid not in", values, "nid");
            return (Criteria) this;
        }

        public Criteria andNidBetween(String value1, String value2) {
            addCriterion("nid between", value1, value2, "nid");
            return (Criteria) this;
        }

        public Criteria andNidNotBetween(String value1, String value2) {
            addCriterion("nid not between", value1, value2, "nid");
            return (Criteria) this;
        }

        public Criteria andNnameIsNull() {
            addCriterion("nname is null");
            return (Criteria) this;
        }

        public Criteria andNnameIsNotNull() {
            addCriterion("nname is not null");
            return (Criteria) this;
        }

        public Criteria andNnameEqualTo(String value) {
            addCriterion("nname =", value, "nname");
            return (Criteria) this;
        }

        public Criteria andNnameNotEqualTo(String value) {
            addCriterion("nname <>", value, "nname");
            return (Criteria) this;
        }

        public Criteria andNnameGreaterThan(String value) {
            addCriterion("nname >", value, "nname");
            return (Criteria) this;
        }

        public Criteria andNnameGreaterThanOrEqualTo(String value) {
            addCriterion("nname >=", value, "nname");
            return (Criteria) this;
        }

        public Criteria andNnameLessThan(String value) {
            addCriterion("nname <", value, "nname");
            return (Criteria) this;
        }

        public Criteria andNnameLessThanOrEqualTo(String value) {
            addCriterion("nname <=", value, "nname");
            return (Criteria) this;
        }

        public Criteria andNnameLike(String value) {
            addCriterion("nname like", value, "nname");
            return (Criteria) this;
        }

        public Criteria andNnameNotLike(String value) {
            addCriterion("nname not like", value, "nname");
            return (Criteria) this;
        }

        public Criteria andNnameIn(List<String> values) {
            addCriterion("nname in", values, "nname");
            return (Criteria) this;
        }

        public Criteria andNnameNotIn(List<String> values) {
            addCriterion("nname not in", values, "nname");
            return (Criteria) this;
        }

        public Criteria andNnameBetween(String value1, String value2) {
            addCriterion("nname between", value1, value2, "nname");
            return (Criteria) this;
        }

        public Criteria andNnameNotBetween(String value1, String value2) {
            addCriterion("nname not between", value1, value2, "nname");
            return (Criteria) this;
        }

        public Criteria andNauthorIsNull() {
            addCriterion("nauthor is null");
            return (Criteria) this;
        }

        public Criteria andNauthorIsNotNull() {
            addCriterion("nauthor is not null");
            return (Criteria) this;
        }

        public Criteria andNauthorEqualTo(String value) {
            addCriterion("nauthor =", value, "nauthor");
            return (Criteria) this;
        }

        public Criteria andNauthorNotEqualTo(String value) {
            addCriterion("nauthor <>", value, "nauthor");
            return (Criteria) this;
        }

        public Criteria andNauthorGreaterThan(String value) {
            addCriterion("nauthor >", value, "nauthor");
            return (Criteria) this;
        }

        public Criteria andNauthorGreaterThanOrEqualTo(String value) {
            addCriterion("nauthor >=", value, "nauthor");
            return (Criteria) this;
        }

        public Criteria andNauthorLessThan(String value) {
            addCriterion("nauthor <", value, "nauthor");
            return (Criteria) this;
        }

        public Criteria andNauthorLessThanOrEqualTo(String value) {
            addCriterion("nauthor <=", value, "nauthor");
            return (Criteria) this;
        }

        public Criteria andNauthorLike(String value) {
            addCriterion("nauthor like", value, "nauthor");
            return (Criteria) this;
        }

        public Criteria andNauthorNotLike(String value) {
            addCriterion("nauthor not like", value, "nauthor");
            return (Criteria) this;
        }

        public Criteria andNauthorIn(List<String> values) {
            addCriterion("nauthor in", values, "nauthor");
            return (Criteria) this;
        }

        public Criteria andNauthorNotIn(List<String> values) {
            addCriterion("nauthor not in", values, "nauthor");
            return (Criteria) this;
        }

        public Criteria andNauthorBetween(String value1, String value2) {
            addCriterion("nauthor between", value1, value2, "nauthor");
            return (Criteria) this;
        }

        public Criteria andNauthorNotBetween(String value1, String value2) {
            addCriterion("nauthor not between", value1, value2, "nauthor");
            return (Criteria) this;
        }

        public Criteria andNstaticIsNull() {
            addCriterion("nstatic is null");
            return (Criteria) this;
        }

        public Criteria andNstaticIsNotNull() {
            addCriterion("nstatic is not null");
            return (Criteria) this;
        }

        public Criteria andNstaticEqualTo(Integer value) {
            addCriterion("nstatic =", value, "nstatic");
            return (Criteria) this;
        }

        public Criteria andNstaticNotEqualTo(Integer value) {
            addCriterion("nstatic <>", value, "nstatic");
            return (Criteria) this;
        }

        public Criteria andNstaticGreaterThan(Integer value) {
            addCriterion("nstatic >", value, "nstatic");
            return (Criteria) this;
        }

        public Criteria andNstaticGreaterThanOrEqualTo(Integer value) {
            addCriterion("nstatic >=", value, "nstatic");
            return (Criteria) this;
        }

        public Criteria andNstaticLessThan(Integer value) {
            addCriterion("nstatic <", value, "nstatic");
            return (Criteria) this;
        }

        public Criteria andNstaticLessThanOrEqualTo(Integer value) {
            addCriterion("nstatic <=", value, "nstatic");
            return (Criteria) this;
        }

        public Criteria andNstaticIn(List<Integer> values) {
            addCriterion("nstatic in", values, "nstatic");
            return (Criteria) this;
        }

        public Criteria andNstaticNotIn(List<Integer> values) {
            addCriterion("nstatic not in", values, "nstatic");
            return (Criteria) this;
        }

        public Criteria andNstaticBetween(Integer value1, Integer value2) {
            addCriterion("nstatic between", value1, value2, "nstatic");
            return (Criteria) this;
        }

        public Criteria andNstaticNotBetween(Integer value1, Integer value2) {
            addCriterion("nstatic not between", value1, value2, "nstatic");
            return (Criteria) this;
        }

        public Criteria andNclassIsNull() {
            addCriterion("nclass is null");
            return (Criteria) this;
        }

        public Criteria andNclassIsNotNull() {
            addCriterion("nclass is not null");
            return (Criteria) this;
        }

        public Criteria andNclassEqualTo(Integer value) {
            addCriterion("nclass =", value, "nclass");
            return (Criteria) this;
        }

        public Criteria andNclassNotEqualTo(Integer value) {
            addCriterion("nclass <>", value, "nclass");
            return (Criteria) this;
        }

        public Criteria andNclassGreaterThan(Integer value) {
            addCriterion("nclass >", value, "nclass");
            return (Criteria) this;
        }

        public Criteria andNclassGreaterThanOrEqualTo(Integer value) {
            addCriterion("nclass >=", value, "nclass");
            return (Criteria) this;
        }

        public Criteria andNclassLessThan(Integer value) {
            addCriterion("nclass <", value, "nclass");
            return (Criteria) this;
        }

        public Criteria andNclassLessThanOrEqualTo(Integer value) {
            addCriterion("nclass <=", value, "nclass");
            return (Criteria) this;
        }

        public Criteria andNclassIn(List<Integer> values) {
            addCriterion("nclass in", values, "nclass");
            return (Criteria) this;
        }

        public Criteria andNclassNotIn(List<Integer> values) {
            addCriterion("nclass not in", values, "nclass");
            return (Criteria) this;
        }

        public Criteria andNclassBetween(Integer value1, Integer value2) {
            addCriterion("nclass between", value1, value2, "nclass");
            return (Criteria) this;
        }

        public Criteria andNclassNotBetween(Integer value1, Integer value2) {
            addCriterion("nclass not between", value1, value2, "nclass");
            return (Criteria) this;
        }

        public Criteria andNlabelIsNull() {
            addCriterion("nlabel is null");
            return (Criteria) this;
        }

        public Criteria andNlabelIsNotNull() {
            addCriterion("nlabel is not null");
            return (Criteria) this;
        }

        public Criteria andNlabelEqualTo(String value) {
            addCriterion("nlabel =", value, "nlabel");
            return (Criteria) this;
        }

        public Criteria andNlabelNotEqualTo(String value) {
            addCriterion("nlabel <>", value, "nlabel");
            return (Criteria) this;
        }

        public Criteria andNlabelGreaterThan(String value) {
            addCriterion("nlabel >", value, "nlabel");
            return (Criteria) this;
        }

        public Criteria andNlabelGreaterThanOrEqualTo(String value) {
            addCriterion("nlabel >=", value, "nlabel");
            return (Criteria) this;
        }

        public Criteria andNlabelLessThan(String value) {
            addCriterion("nlabel <", value, "nlabel");
            return (Criteria) this;
        }

        public Criteria andNlabelLessThanOrEqualTo(String value) {
            addCriterion("nlabel <=", value, "nlabel");
            return (Criteria) this;
        }

        public Criteria andNlabelLike(String value) {
            addCriterion("nlabel like", value, "nlabel");
            return (Criteria) this;
        }

        public Criteria andNlabelNotLike(String value) {
            addCriterion("nlabel not like", value, "nlabel");
            return (Criteria) this;
        }

        public Criteria andNlabelIn(List<String> values) {
            addCriterion("nlabel in", values, "nlabel");
            return (Criteria) this;
        }

        public Criteria andNlabelNotIn(List<String> values) {
            addCriterion("nlabel not in", values, "nlabel");
            return (Criteria) this;
        }

        public Criteria andNlabelBetween(String value1, String value2) {
            addCriterion("nlabel between", value1, value2, "nlabel");
            return (Criteria) this;
        }

        public Criteria andNlabelNotBetween(String value1, String value2) {
            addCriterion("nlabel not between", value1, value2, "nlabel");
            return (Criteria) this;
        }

        public Criteria andNlogoIsNull() {
            addCriterion("nlogo is null");
            return (Criteria) this;
        }

        public Criteria andNlogoIsNotNull() {
            addCriterion("nlogo is not null");
            return (Criteria) this;
        }

        public Criteria andNlogoEqualTo(String value) {
            addCriterion("nlogo =", value, "nlogo");
            return (Criteria) this;
        }

        public Criteria andNlogoNotEqualTo(String value) {
            addCriterion("nlogo <>", value, "nlogo");
            return (Criteria) this;
        }

        public Criteria andNlogoGreaterThan(String value) {
            addCriterion("nlogo >", value, "nlogo");
            return (Criteria) this;
        }

        public Criteria andNlogoGreaterThanOrEqualTo(String value) {
            addCriterion("nlogo >=", value, "nlogo");
            return (Criteria) this;
        }

        public Criteria andNlogoLessThan(String value) {
            addCriterion("nlogo <", value, "nlogo");
            return (Criteria) this;
        }

        public Criteria andNlogoLessThanOrEqualTo(String value) {
            addCriterion("nlogo <=", value, "nlogo");
            return (Criteria) this;
        }

        public Criteria andNlogoLike(String value) {
            addCriterion("nlogo like", value, "nlogo");
            return (Criteria) this;
        }

        public Criteria andNlogoNotLike(String value) {
            addCriterion("nlogo not like", value, "nlogo");
            return (Criteria) this;
        }

        public Criteria andNlogoIn(List<String> values) {
            addCriterion("nlogo in", values, "nlogo");
            return (Criteria) this;
        }

        public Criteria andNlogoNotIn(List<String> values) {
            addCriterion("nlogo not in", values, "nlogo");
            return (Criteria) this;
        }

        public Criteria andNlogoBetween(String value1, String value2) {
            addCriterion("nlogo between", value1, value2, "nlogo");
            return (Criteria) this;
        }

        public Criteria andNlogoNotBetween(String value1, String value2) {
            addCriterion("nlogo not between", value1, value2, "nlogo");
            return (Criteria) this;
        }

        public Criteria andNhotIsNull() {
            addCriterion("nhot is null");
            return (Criteria) this;
        }

        public Criteria andNhotIsNotNull() {
            addCriterion("nhot is not null");
            return (Criteria) this;
        }

        public Criteria andNhotEqualTo(Integer value) {
            addCriterion("nhot =", value, "nhot");
            return (Criteria) this;
        }

        public Criteria andNhotNotEqualTo(Integer value) {
            addCriterion("nhot <>", value, "nhot");
            return (Criteria) this;
        }

        public Criteria andNhotGreaterThan(Integer value) {
            addCriterion("nhot >", value, "nhot");
            return (Criteria) this;
        }

        public Criteria andNhotGreaterThanOrEqualTo(Integer value) {
            addCriterion("nhot >=", value, "nhot");
            return (Criteria) this;
        }

        public Criteria andNhotLessThan(Integer value) {
            addCriterion("nhot <", value, "nhot");
            return (Criteria) this;
        }

        public Criteria andNhotLessThanOrEqualTo(Integer value) {
            addCriterion("nhot <=", value, "nhot");
            return (Criteria) this;
        }

        public Criteria andNhotIn(List<Integer> values) {
            addCriterion("nhot in", values, "nhot");
            return (Criteria) this;
        }

        public Criteria andNhotNotIn(List<Integer> values) {
            addCriterion("nhot not in", values, "nhot");
            return (Criteria) this;
        }

        public Criteria andNhotBetween(Integer value1, Integer value2) {
            addCriterion("nhot between", value1, value2, "nhot");
            return (Criteria) this;
        }

        public Criteria andNhotNotBetween(Integer value1, Integer value2) {
            addCriterion("nhot not between", value1, value2, "nhot");
            return (Criteria) this;
        }

        public Criteria andNintroIsNull() {
            addCriterion("nintro is null");
            return (Criteria) this;
        }

        public Criteria andNintroIsNotNull() {
            addCriterion("nintro is not null");
            return (Criteria) this;
        }

        public Criteria andNintroEqualTo(String value) {
            addCriterion("nintro =", value, "nintro");
            return (Criteria) this;
        }

        public Criteria andNintroNotEqualTo(String value) {
            addCriterion("nintro <>", value, "nintro");
            return (Criteria) this;
        }

        public Criteria andNintroGreaterThan(String value) {
            addCriterion("nintro >", value, "nintro");
            return (Criteria) this;
        }

        public Criteria andNintroGreaterThanOrEqualTo(String value) {
            addCriterion("nintro >=", value, "nintro");
            return (Criteria) this;
        }

        public Criteria andNintroLessThan(String value) {
            addCriterion("nintro <", value, "nintro");
            return (Criteria) this;
        }

        public Criteria andNintroLessThanOrEqualTo(String value) {
            addCriterion("nintro <=", value, "nintro");
            return (Criteria) this;
        }

        public Criteria andNintroLike(String value) {
            addCriterion("nintro like", value, "nintro");
            return (Criteria) this;
        }

        public Criteria andNintroNotLike(String value) {
            addCriterion("nintro not like", value, "nintro");
            return (Criteria) this;
        }

        public Criteria andNintroIn(List<String> values) {
            addCriterion("nintro in", values, "nintro");
            return (Criteria) this;
        }

        public Criteria andNintroNotIn(List<String> values) {
            addCriterion("nintro not in", values, "nintro");
            return (Criteria) this;
        }

        public Criteria andNintroBetween(String value1, String value2) {
            addCriterion("nintro between", value1, value2, "nintro");
            return (Criteria) this;
        }

        public Criteria andNintroNotBetween(String value1, String value2) {
            addCriterion("nintro not between", value1, value2, "nintro");
            return (Criteria) this;
        }

        public Criteria andNcreatetimeIsNull() {
            addCriterion("ncreatetime is null");
            return (Criteria) this;
        }

        public Criteria andNcreatetimeIsNotNull() {
            addCriterion("ncreatetime is not null");
            return (Criteria) this;
        }

        public Criteria andNcreatetimeEqualTo(Date value) {
            addCriterion("ncreatetime =", value, "ncreatetime");
            return (Criteria) this;
        }

        public Criteria andNcreatetimeNotEqualTo(Date value) {
            addCriterion("ncreatetime <>", value, "ncreatetime");
            return (Criteria) this;
        }

        public Criteria andNcreatetimeGreaterThan(Date value) {
            addCriterion("ncreatetime >", value, "ncreatetime");
            return (Criteria) this;
        }

        public Criteria andNcreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ncreatetime >=", value, "ncreatetime");
            return (Criteria) this;
        }

        public Criteria andNcreatetimeLessThan(Date value) {
            addCriterion("ncreatetime <", value, "ncreatetime");
            return (Criteria) this;
        }

        public Criteria andNcreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("ncreatetime <=", value, "ncreatetime");
            return (Criteria) this;
        }

        public Criteria andNcreatetimeIn(List<Date> values) {
            addCriterion("ncreatetime in", values, "ncreatetime");
            return (Criteria) this;
        }

        public Criteria andNcreatetimeNotIn(List<Date> values) {
            addCriterion("ncreatetime not in", values, "ncreatetime");
            return (Criteria) this;
        }

        public Criteria andNcreatetimeBetween(Date value1, Date value2) {
            addCriterion("ncreatetime between", value1, value2, "ncreatetime");
            return (Criteria) this;
        }

        public Criteria andNcreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("ncreatetime not between", value1, value2, "ncreatetime");
            return (Criteria) this;
        }

        public Criteria andLastchapterIsNull() {
            addCriterion("lastchapter is null");
            return (Criteria) this;
        }

        public Criteria andLastchapterIsNotNull() {
            addCriterion("lastchapter is not null");
            return (Criteria) this;
        }

        public Criteria andLastchapterEqualTo(Integer value) {
            addCriterion("lastchapter =", value, "lastchapter");
            return (Criteria) this;
        }

        public Criteria andLastchapterNotEqualTo(Integer value) {
            addCriterion("lastchapter <>", value, "lastchapter");
            return (Criteria) this;
        }

        public Criteria andLastchapterGreaterThan(Integer value) {
            addCriterion("lastchapter >", value, "lastchapter");
            return (Criteria) this;
        }

        public Criteria andLastchapterGreaterThanOrEqualTo(Integer value) {
            addCriterion("lastchapter >=", value, "lastchapter");
            return (Criteria) this;
        }

        public Criteria andLastchapterLessThan(Integer value) {
            addCriterion("lastchapter <", value, "lastchapter");
            return (Criteria) this;
        }

        public Criteria andLastchapterLessThanOrEqualTo(Integer value) {
            addCriterion("lastchapter <=", value, "lastchapter");
            return (Criteria) this;
        }

        public Criteria andLastchapterIn(List<Integer> values) {
            addCriterion("lastchapter in", values, "lastchapter");
            return (Criteria) this;
        }

        public Criteria andLastchapterNotIn(List<Integer> values) {
            addCriterion("lastchapter not in", values, "lastchapter");
            return (Criteria) this;
        }

        public Criteria andLastchapterBetween(Integer value1, Integer value2) {
            addCriterion("lastchapter between", value1, value2, "lastchapter");
            return (Criteria) this;
        }

        public Criteria andLastchapterNotBetween(Integer value1, Integer value2) {
            addCriterion("lastchapter not between", value1, value2, "lastchapter");
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