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
                            <th><h3>Moyenne de l'étudiant</h3></th>
                            <th><h3>Moyenne de la Promotion</h3></th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list  moyennes as moyenne>
                        <tr>
                            <td>
                                <#list blocs as bloc>
                                    <#if bloc.id = moyenne.blocId>
                                        <h4>${bloc.titre}</h4>
                                    </#if>
                                </#list>
                                <ul>
                                <#list competencesParBloc as key , value>
                                    <#if key = moyenne.blocId>
                                        <#list value as comp>
                                            <li><p>${comp.titre}</p></li>
                                        </#list>
                                        <#break>
                                    </#if>
                                </#list>
                                </ul>
                            </td>
                            <td><h4>${moyenne.moyenne}</h4></td>
                            <td><h4>${moyenne.moyennePromo}</h4></td>
                        </tr>
                        </#list>
                        </tbody>
                        <tfoot>
                        <tr>
                            <td><h3>Moyenne générale :</h3></td>
                            <td class="align-right"><h3>${general.moyenne}</h3></td>
                            <td  class="align-right"><h3>${general.moyennePromo}</h3></td>
                        </tr>
                        </tfoot>
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
