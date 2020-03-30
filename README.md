# ELK_novelWeb
基于ELK和HBase等对小说网站的存储和搜索进行重构，并实现网站的数据分析和可视化


# 功能需求及实现概述
- 大数据存储
  1. 通过Logstash 将mysql 数据保存的小说**基本信息导入ES
  2. 通过SparkSQL 将mysql 中小说**各章节内容信息导入到HBase
- 搜索引擎
  1. es client 实现**全文检索功能**
  2. 编译安装ik 插件，自定义mapping 实现**中文分词搜索**
  3. HighlightBuilder 对象通过标签渲染搜索字段，实现**关键词高亮**
  4. 安装pinyiin 插件，优化权重，提供**拼音搜索优化功能**
  5. es 排序搜索，实现主页列表**TopN 排序展示**
  6. AggregationBuilders 对keyword 聚合，实现作品**分类聚合展示**
  7. 其它：月、周排行榜；连载、完结榜单；大神俱乐部等

# 作品展示

![搜索模块](search.png)
![数据分析及可视化](dataVisual.png)

