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
    <div><input type="text" name="board" />
    <input type="submit" value="Solve" /></div>
</form>
<div class="note">Note: For "Qu", just type "Q"</div>
</div>
<div id="board">
<#list board.display_board as row>
  <div class="row">
    <#list row as l>
      <div class="boggle"><span>${l}</span></div>
    </#list>
  </div>
</#list>
</div>

<p>Answers: (Found ${answerCount})</p>
<div class="answers">
<#list answers as answer>
<p>${answer}</p>
</#list>
</div>
</div>
</body>
</html>