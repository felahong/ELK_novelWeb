package com.felahong.dataMove.Bean;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Program: NovelWebOnBigDataStructure
 * @Package: com.felahong.mysql2Hbase.Bean
 * @Description: 小说内容的JavaBean
 * Created by felahong on 2020/2/24 16:25
 */

public class NovelDetail implements WritableComparable<NovelDetail>, DBWritable {

    // 与表中字段对应的成员变量
    private int id;
    private String authorName;
    private String novelName;
    private String chapterName;
    private String chapterUrl;
    private int novelId;

    public NovelDetail() {
    }

    public NovelDetail(int id, String authorName, String novelName, String chapterName, String chapterUrl, int novelId) {
        this.id = id;
        this.authorName = authorName;
        this.novelName = novelName;
        this.chapterName = chapterName;
        this.chapterUrl = chapterUrl;
        this.novelId = novelId;
    }

    /**
     * @Description 通过id 进行比较。因为表中id 本身代表前后顺序
     * @Author felahong 2020/2/24 16:48
     **/
    @Override
    public int compareTo(NovelDetail o) {
        return this.id - o.id;
    }

    /**
     * @Description 数据从MapReduce 写出
     * @Author felahong 2020/2/24 16:56
     **/
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(id);    // 写出用write ，id 用int
        dataOutput.writeUTF(authorName);    // string 用utf
        dataOutput.writeUTF(novelName);
        dataOutput.writeUTF(chapterName);
        dataOutput.writeUTF(chapterUrl);
        dataOutput.writeInt(novelId);

    }

    /**
     * @Description 读入内容，即把mysql 表中数据读入到MapReduce
     * @Author felahong 2020/2/24 16:53
     **/
    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.id = dataInput.readInt();  // id是int 类型，用readint
        this.authorName = dataInput.readUTF();  // name 是string 类型，用readutf
        this.novelName = dataInput.readUTF();
        this.chapterName = dataInput.readUTF();
        this.chapterUrl = dataInput.readUTF();
        this.novelId = dataInput.readInt();

    }

    /**
     * @Description 写入数据库，类似于jdbc，使用PreparedStatement 进行赋值，设置表的映射对象中的属性
     * @Author felahong 2020/2/24 16:59
     **/
    @Override
    public void write(PreparedStatement preparedStatement) throws SQLException {
        int index = 1;  // 按顺序写，id -> authorName -> novelName ... 的顺序
        preparedStatement.setInt(index++, id);  // 设置完id 的index 之后，index 自增一
        preparedStatement.setString(index++, authorName);
        preparedStatement.setString(index++, novelName);
        preparedStatement.setString(index++, chapterName);
        preparedStatement.setString(index++, chapterUrl);
        preparedStatement.setInt(index, novelId);

    }

    /**
     * @Description 读取数据库。类似于jbdc的查询，从结果集通过与字段匹配赋值给表的映射对象
     * @Author felahong 2020/2/24 16:54
     **/
    @Override
    public void readFields(ResultSet resultSet) throws SQLException {
        int index = 1;
        id = resultSet.getInt(index++);
        authorName = resultSet.getString(index++);
        novelName = resultSet.getString(index++);
        chapterName = resultSet.getString(index++);
        chapterUrl = resultSet.getString(index++);
        novelId = resultSet.getInt(index);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getNovelName() {
        return novelName;
    }

    public void setNovelName(String novelName) {
        this.novelName = novelName;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getChapterUrl() {
        return chapterUrl;
    }

    public void setChapterUrl(String chapterUrl) {
        this.chapterUrl = chapterUrl;
    }

    public int getNovelId() {
        return novelId;
    }

    public void setNovelId(int novelId) {
        this.novelId = novelId;
    }
}