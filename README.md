# Projet Programmation Mobile - INSA 3INFO (2025/2026)

## Présentation

Ce cours a pour but de découvrir la programmation mobile Android. Il consiste en une dizaine de TP permettant de créer une application comprenant une liste de jeux, le détail de chacun d'eux, une barre de recherche ainsi qu'un système de mise en favoris.

## Choix réalisés

Je n'ai pas effectué le TP facultatif ni le TP bonus. J'ai préféré consacrer ce temps à des améliorations visuelles plutôt qu'à l'ajout de nouvelles fonctionnalités.

J'ai ainsi :
- modifié la palette de couleurs de l'application et ajusté la transparence de certains éléments,
- intégré 2 fonds d'écran personnalisés,
- ajouté des GIFs animés (2 chats et une fleur de cerisier),
- remplacé l'icône de l'application.

## Difficultés rencontrées

- **Performance de l'émulateur** : mon PC avait beaucoup de mal à faire tourner l'API. Le problème a été résolu en passant au débogage sur mon téléphone
- **Intégration des GIFs** : j'ai dû tester plusieurs bibliothèques avant d'en trouver une fonctionnelle. J'ai finalement opté pour `AnimatedImageDrawable`, bien que je n'aie pas réussi à identifier la cause des freezes occasionnels.

## Améliorations envisagées

Des pistes d'amélioration identifiées seraient d'ajouter un accès rapide aux favoris, de mettre en place une pagination, et d'améliorer l'architecture globale de l'application.