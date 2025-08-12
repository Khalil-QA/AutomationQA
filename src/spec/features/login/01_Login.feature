#language: fr
@login @smoketest @tnr
Fonctionnalité: Login
  ETQ utilisateur je souhaite me connecter sur l'application EasyGes

  @login
  Scénario: Je souhaite me connecter avec des données valides
    Etant donné que Je suis sur la page de connexion de l'application EasyGes
    Quand Je saisis une adresse mail "zied.hannachi.ext@groupama.com"
    Et Je saisis un mot de passe "Achanger1234§"
    Et Je clique sur le bouton Log in
    Alors Je suis redirigé vers la page d'accueil avec l'adresse "zied.hannachi.ext@groupama.com"

