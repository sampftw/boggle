<html>
<head>
    <link rel="stylesheet" href="/static/styles.css">
</head>
<body>
<#if error??>
    <p style="color:red;">${error}</p>
</#if>
Welcome to boggle.
<form action="/solve" method="post" enctype="application/x-www-form-urlencoded">
    <div>Board:</div>
    <div><input type="text" name="board" /></div>
    <div><input type="submit" value="Solve" /></div>
</form>
<div class="note">Note: For "Qu", just type "Q"</div>
</body>
</html>