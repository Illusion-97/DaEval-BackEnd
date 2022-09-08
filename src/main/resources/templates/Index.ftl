<!DOCTYPE HTML>
<html>
<head>
    <title>${appName}</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" href="assets/css/main.css" />
    <link rel="stylesheet" href="assets/css/index.css" />
</head>
<body class="is-preload">
<!-- Wrapper -->
<div id="wrapper">
    <!-- Main -->
    <div id="main">
        <div class="inner">
            <!-- Header -->
            <header id="header">
                <a href="index.html" class="logo"><h1><strong>Formations</strong></h1> Route definition : {API_URL}/{Service}/{Methode}</a>
                <div class="content">
                    <p>Param binding :</p>
                    <p class="colorParamBox Json"></p><p style="display: inline-block"> Body (JSON)</p>
                    <br>
                    <p class="colorParamBox Query"><p style="display: inline-block"> Request Param</p>
                </div>
            </header>
            <!-- Section -->
            <nav id="menu">
                <header class="major">
                    <h2>Services</h2>
                </header>
                <ul>
                    <#list services as service>
                        <#if service.methods?size != 0 >
                    <li>
                        <span class="opener" style="text-transform: none">${service.name}</span>
                        <ul>
                            <li>
                                <div class="row gtr-uniform tabHead" style="text-align: center">
                                    <div class="col-2"></div>
                                    <div class="col-3">Methode</div>
                                    <div class="col-7">Params</div>
                                </div>
                            </li>
                        <#list service.methods as method>
                            <li class="tabRow">
                                <div class="row gtr-uniform">
                                    <div class="col-2"><p class="httpMethod ${method.httpMethod}">${method.httpMethod}</p></div>
                                    <div class="col-3 border"><p>${method.name}</p></div>
                                    <div class="col-7"><#list method.params as param>
                                            <p class="paramType ${param.typeName}" <#list jsonStructures as struct> <#if param.typeName = struct.typeName>title='${struct.structure}' </#if> </#list>>${param.paramName} : ${param.typeName}</p>
                                            <#if param?is_last> <#else>, </#if>
                                        </#list>
                                    </div>
                                </div>
                            </li>
                        </#list>
                        </ul>
                    </li>
                        </#if>
                    </#list>
                </ul>
            </nav>
        </div>
    </div>
</div>
<!-- Scripts -->
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/browser.min.js"></script>
<script src="assets/js/breakpoints.min.js"></script>
<script src="assets/js/util.js"></script>
<script src="assets/js/main.js"></script>
</body>
</html>
