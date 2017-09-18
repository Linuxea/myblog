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
            max-height: 320px;
            overflow: hidden;
        }

    </style>
</head>
<body>

<div class="container">

    <div class="page-header">
        <h1>table</h1>
    </div>

    <div class="table-responsive">

        <form action="/loginController/login">

            <table class="table table-striped table-bordered table-hover">

                <tr class="info">
                    <td>用户名</td>
                    <td><input type="text" class="form-control" name="user.userName"/></td>
                </tr>

                <tr>
                    <td>密码</td>
                    <td><input type="text" class="form-control" name="user."/></td>
                </tr>

            </table>

        </form>

    </div>


</div>

</body>
</html>
