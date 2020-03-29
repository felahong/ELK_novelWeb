<%--
  Created by IntelliJ IDEA.
  User: 22969
  Date: 2019/1/8
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>
<html>
<%@ page pageEncoding="utf-8"%>
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/common.css" />
    <link rel="stylesheet" type="text/css" href="css/person-index.css" />

</head>
<body>
<div class="c_right">
    <div class="c_content_top">
        <p class="p_display">
            个人中心
        </p>
        <div class="person-info">
            <div class="head_img">
                <img src="img/person-img/shahejiade.png" style="width:115px;height: 115px;border: 1px #FFFFFF solid;border-radius: 50%;float: left;" />
            </div>
            <div class="rig_box">
                <label class="way1">130****9005</label>
                <br>
                <label class="way2">Lv1(亲 请坚持签到哦)</label>
            </div>
        </div>
        <div class="c_box">
            <ul>
                <li><span>玉佩</span>0
                    <a href="">充值</a>
                </li>
                <!--充值可用-->
                <li><span>海星</span>1</li>
                <!--签到+1-->
                <li><span>凤凰蛋</span>0</li>
                <!--根据看小说时长，2小时+1，用于投票-->
            </ul>
        </div>
    </div>
    <div class="c_content_center">
        <p class="p_display">
            订阅作品</p>
        <div class="c_table">
            <table>
                <tr>
                    <th>书名</th>
                    <th>作者</th>
                    <th>分类</th>
                    <th>最新章节</th>
                    <th>更新时间</th>
                </tr>
                <tr>
                    <td>斗破苍穹</td>
                    <td>天蚕土豆</td>
                    <td>玄幻奇幻</td>
                    <td>第五章</td>
                    <td>2014-03-15 21:11:01</td>
                </tr>
            </table>
        </div>
    </div>
    <div class="c_content_buttom">
        <p class="p_display">
            收藏作品</p>
        <div class="c_table">
            <table>
                <tr>
                    <th>书名</th>
                    <th>作者</th>
                    <th>分类</th>
                    <th>最新章节</th>
                    <th>更新时间</th>
                </tr>
                <tr>
                    <td>斗破苍穹</td>
                    <td>天蚕土豆</td>
                    <td>玄幻奇幻</td>
                    <td>第五章</td>
                    <td>2014-03-15 21:11:01</td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>
