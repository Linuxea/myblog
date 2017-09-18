<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-09-16
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>MyBlog</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/plugin/bootstrap.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/plugin/jq.js"></script>
    <script src="<%=request.getContextPath() %>/plugin/bootstrap.js"></script>
    <style type="text/css">
        .row {
            max-height: 120px;
            overflow: hidden;
        }

    </style>
</head>
<body>
<div class="container">

    <div class="page-header">
        <h1>MyBlog</h1>
    </div>

</div>

<div class="panel-footer navbar-fixed-bottom"></div>

<script>

    $(function () {

        //加载并拼凑成html
        $.ajax({
            url: "<%=request.getContextPath() %>/articleController/find",
            success: function (rs) {
                var htmlStr = "";
                for (let i = 0; i < rs.length; i++) {
                    htmlStr += "<div class=\"row\">";
                    htmlStr += "<p class=\"bg-success lead \">" + rs[i].title + "</p>";
                    htmlStr += "<p class=\"lead\">" + rs[i].content + "</p>";
                    htmlStr += "</div>";
                    htmlStr += "<p>" + rs[i].create_time + "</p>";
                    htmlStr += "<button class=\"btn btn-primary pull-right \" data-id=" + rs[i].id + ">continue</button>";
                    htmlStr += "<div class=\"row\" style='border-top: 1px solid black;' ><br/><br/><br/></div>";
                }

                $("div.container").append(htmlStr);
                $("button").click(function () {
                    window.location.href = "<%=request.getContextPath() %>/show.jsp?data-id=" + $(this).attr("data-id");
                });
            },
            error: function () {
                // errro
            }
        });

    }());


</script>
</body>
</html>
