<#-- @ftlvariable name="board" type="boggle.Board" -->
<#-- @ftlvariable name="answers" type="List<String>" -->
<html>
<head>
    <link rel="stylesheet" href="/static/styles.css">
</head>
<body>
Solved ${board.board}
<p>Answers:</p>
<ul>
    <#list answers as answer>
	    <li>${answer}</li>
    </#list>
</ul>
</body>
</html>