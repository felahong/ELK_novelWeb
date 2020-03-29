<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <script src="js/jquery-1.11.3.min.js"></script>
</head>

<body>
<input type="hidden" id="item" value="index">

<script>
    $(function () {
        var xx = $("#item").val();
        if(xx =="index") {
            window.location.replace("index");
        }
    })

</script>
</body>
</html>
