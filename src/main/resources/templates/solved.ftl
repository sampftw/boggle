<#-- @ftlvariable name="board" type="boggle.Board" -->
<#-- @ftlvariable name="answers" type="List<String>" -->
<html>
<head>
    <link rel="stylesheet" href="/static/styles.css">
</head>
<body>
<div id="boggle-container">
<div>Solved ${board.source}</div>
<div>
<form action="/solve" method="post" enctype="application/x-www-form-urlencoded">
    <div>Board:</div>
    <div><input type="text" name="board" /></div>
    <div><input type="submit" value="Solve another" /></div>
</form>
</div>
<div id="board">
<#list board.board as row>
  <div class="row">
    <#list row as l>
      <div class="boggle"><span>${l}</span></div>
    </#list>
  </div>
</#list>
</div>
</div>

<p>Answers: (Found ${answerCount})</p>
<div>
<#list answers as answer>
<p>${answer}</p>
</#list>
</div>
</body>
</html>