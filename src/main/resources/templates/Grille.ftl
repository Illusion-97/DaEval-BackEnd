<!DOCTYPE HTML>
<html>
<head>
    <title>${titre.titre}</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" href="../assets/css/main.css" />
    <link rel="stylesheet" href="../assets/css/index.css" />
</head>
<body class="is-preload">
<!-- Wrapper -->
<div id="wrapper">
    <!-- Main -->
    <div id="main">
        <div class="inner">
            <!-- Header -->
            <header id="header">
                <a href="index.html" class="logo align-center">
                    <h1><strong>${titre.titre?replace("Titre professionnel ","")?split('(')?first}</strong></h1>
                    <h2><strong>(${promo.dateDebut} - ${promo.dateFin})</strong></h2>
                    <h2>Etudiant : ${etudiant.nom} ${etudiant.prenom}</h2>
                </a>
            </header>
            <!-- Section -->
            <section>
                <div class="table-wrapper">
                    <table class="alt">
                        <thead>
                        <tr>
                            <th><h3>Compétences</h3></th>
                            <th><h3>Niveau de départ</h3></th>
                            <th><h3>Niveau d'arrivée</h3></th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list blocs as bloc>
                            <tr>
                                <td colspan="3">
                                    <h4>${bloc.titre}</h4>
                                </td>
                            </tr>
                            <#list competencesParBloc as key , value>
                                <#if key = bloc.id>
                                    <#list value as comp>
                                        <tr>
                                            <td>
                                                <p>${comp.titre}</p>
                                            </td>
                                            <#list positions as position>
                                                <#if position.competenceId = comp.id>
                                                    <td>
                                                        <p>${position.avant}</p>
                                                    </td>
                                                    <td>
                                                        <p>${position.apres}</p>
                                                    </td>
                                                </#if>
                                            </#list>
                                        </tr>
                                    </#list>
                                    <#break>
                                </#if>
                            </#list>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </section>
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
