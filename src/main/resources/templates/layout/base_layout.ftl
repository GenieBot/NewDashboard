<#macro layout
title="none"
>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${title} | GenieBot</title>
    <link rel="stylesheet" href="/bulma.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
</head>
<body class="layout-documentation page-overview">
<#nested />
<footer class="footer" style="background-color: white">
    <div class="container">
        <div class="content has-text-centered">
            <p>
                <a href="/legal">Legal Information</a> | <a href="/support">Support</a>
                <br />
                Copyright &copy; ${.now?string.yyyy} <a href="https://sponges.io">sponges.io</a>
            </p>
        </div>
    </div>
</footer>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script src="/visual.js"></script>
<script src="/internal.js"></script>
</body>
</#macro>