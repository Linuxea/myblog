<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-09-17
  Time: 1:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title></title>
    <script type="text/javascript"
            src="<%=request.getContextPath() %>/plugin/JHtmlArea/scripts/jquery-1.3.2.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath() %>/plugin/JHtmlArea/scripts/jquery-ui-1.7.2.custom.min.js"></script>
    <link rel="Stylesheet" type="text/css"
          href="<%=request.getContextPath() %>/plugin/JHtmlArea/style/jqueryui/ui-lightness/jquery-ui-1.7.2.custom.css"/>
    <script type="text/javascript"
            src="<%=request.getContextPath() %>/plugin/JHtmlArea/scripts/jHtmlArea-0.8.js"></script>
    <link rel="Stylesheet" type="text/css" href="<%=request.getContextPath() %>/plugin/JHtmlArea/style/jHtmlArea.css"/>

    <style type="text/css">
        /* body { background: #ccc;} */
        div.jHtmlArea .ToolBar ul li a.custom_disk_button {
            background: url(images/disk.png) no-repeat;
            background-position: 0 0;
        }

        div.jHtmlArea {
            border: solid 1px #ccc;
        }

        p {
            height: 350px;
            word-wrap: break-word;
        }

        .label-success {
            background-color: #5cb85c;
        }

        .label {
            display: inline;
            padding: .2em .6em .3em;
            font-size: 75%;
            font-weight: bold;
            line-height: 1;
            color: #fff;
            text-align: center;
            white-space: nowrap;
            vertical-align: baseline;
            border-radius: .25em;
        }


    </style>
</head>
<body>

<input type="text" id="title" placeholder="文章标题"/>
<input type="text" id="labels" placeholder="标签"/>
<div id="labelList">

</div>
<textarea id="txtDefaultHtmlArea" cols="100%" rows="50">

<!-- content here -->


</textarea>
<input type="button" id="save" value="save"/>
<input type="button" value="Set HTML"
       onclick="$('#txtDefaultHtmlArea').htmlarea('html', 'Some <strong>HTML</strong> value.')"/>
<!--<input type="button" value="Change Color to Blue" onclick="$('#txtDefaultHtmlArea').htmlarea('forecolor', 'blue');"/>-->

<script type="text/javascript">
    // You can do this to perform a global override of any of the "default" options
    // jHtmlArea.fn.defaultOptions.css = "jHtmlArea.Editor.css";

    $(function () {
        //$("textarea").htmlarea(); // Initialize all TextArea's as jHtmlArea's with default values
        $("#txtDefaultHtmlArea").htmlarea(); // Initialize jHtmlArea's with all default values


        // jq 版本问题
        $("#save").click(function () {
            var arr = [];
            var labelList = $("#labelList").find("span");
            for (let i = 0; i < labelList.length; i++) {
                arr.push(labelList[i].innerText);
            }
            $.ajax({
                url: "<%=request.getContextPath() %>/articleController/add",
                type: "post",
                dataType: "json",
                data: {
                    "article.title": $("#title").val(),
                    "article.content": $('#txtDefaultHtmlArea').htmlarea('html'),
                    "labels": arr.toString(),
                },
                success: function (rs) {
                    if (rs.stateCode === "ok") {
                        alert("save ok");
                    } else {

                    }
                },
                error: function () {
                    // errro
                }
            });
        });

        $('#labels').bind('keypress', function (event) {
            if (event.keyCode == "13") {
                var label = $(this).val();
                if (label === "") return;
                $("#labelList").append("<span class=\"label label-success\">" + label + "</span>&nbsp;");
                $(this).val("");
            }
        });

    });


</script>
</body>
</html>
