---
type: TD
number: 2
---

# Initiation au *framework* *Spring* et à *JPA*

*Jakarta EE* (anciennement *Java EE* ou *J2EE* pour *Java 2 Platform, Enterprise
Edition*) définit une spécification permettant de développer des applications
web en *Java*.
Le développement d'applications *Jakarta EE* étant relativement complexe,
différents *frameworks* ont été développés pour simplifier leur création.
Parmi ces *frameworks*, l'un des plus populaires est *Spring*, que nous allons
découvrir dans ce TD, à l'issue duquel vous devriez être capable de :

- Configurer un projet *Spring* basé sur *Gradle* et lié à une base de données
  *SQLite*.
- Définir des routes pour répondre à des requêtes utilisant différentes méthodes
  HTTP.
- Passer des paramètres *via* l'URL de la requête.
- Passer des paramètres dans le corps de la requête en utilisant le format JSON.
- Créer des *entités* (classes) pour représenter les données des tables à l'aide
  d'objets *Java*.
- Interroger une base de données depuis le code *Java*.
- Définir et utiliser un *service* pour traiter les données*.

## Configuration du projet

Dans ce sujet de TD, nous allons reprendre le code de la bibliothèque
universitaire développé lors du précédent TD, et le transformer pour en faire
une application web fournissant une API REST.
Pour cela, vous allez devoir configurer votre projet *Gradle* pour qu'il
dispose des dépendances nécessaires.

1. Dans le fichier `build.gradle.kts` de votre projet, remplacer le `plugin`
   `application` (qui permettait d'exécuter votre application en mode console)
   par les plugins suivants, afin de laisser *Spring* gérer votre application :

   ```kotlin
   id("io.spring.dependency-management") version "1.1.6"
   id("org.springframework.boot") version "3.3.3"
   ```

   Ajoutez également la dépendance sur *Spring Boot* dans la partie
   `dependencies`, afin de disposer des classes permettant la création d'une
   API REST en *Spring* :

   ```kotlin
   implementation("org.springframework.boot:spring-boot-starter-web")
   ```

2. Afin de tester votre application, ajoutez dans votre paquetage le code de la
   classe donnée ci-dessous :

   ```java
   import org.springframework.boot.SpringApplication;
   import org.springframework.boot.autoconfigure.SpringBootApplication;
   import org.springframework.web.bind.annotation.GetMapping;
   import org.springframework.web.bind.annotation.RequestParam;
   import org.springframework.web.bind.annotation.RestController;

   @RestController
   @SpringBootApplication
   public class DemoApplication {

       @GetMapping("/hello")
       public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
           return String.format("Hello %s!", name);
       }

       public static void main(String[] args) {
           SpringApplication.run(DemoApplication.class, args);
       }

   }
   ```

3. D'après vous, à quoi servent :

   - L'annotation `RestController` ?
   - L'annotation `SpringBootApplication` ?
   - L'annotation `GetMapping` ?
   - L'annotation `RequestParam` ?
   - Le paramètre `value` ?
   - Le paramètre `defaultValue` ?

4. Pour démarrer votre application, exécutez la commande suivante :

   ```
   gradle bootRun
   ```

5. Consultez dans votre navigateur préféré la page web à l'adresse
   [http://localhost:8080/hello](http://localhost:8080/hello).
   Qu'affiche cette page ?
   Quel est son format (vous pouvez utiliser l'inspecteur de votre navigateur
   pour regarder le contenu de la page) ?
   Que va-t-il se passer si l'on ajoute `?name=Toto` à la fin de cette URL ?

Dans la suite, plutôt que d'utiliser le navigateur pour tester nos applications,
nous utiliserons [*Bruno*](https://www.usebruno.com), qui est installé sur les
machines des salles TP.

## Définition de l'API REST

Nous allons maintenant voir comment définir une API REST afin de manipuler
la bibliothèque que nous avons développée précédemment.
**Dans un premier temps, vous pouvez considérer que les éléments retournés par
chacune des méthodes sont des chaînes de caractères** (vous utiliserez la
méthode `toString()` pour cela).
À chaque étape, vous vous assurerez du bon fonctionnement de le fonctionnalité
attendu en utilisant [*Bruno*](https://www.usebruno.com).

6. Définissez un nouveau contrôleur pour votre application, qui aura pour
   rôle de fournir les routes nécessaires à la gestion de la bibliothèque.
   Il s'agit d'une nouvelle classe annotée avec `@RestController`.

7. Complétez cette nouvelle classe pour qu'elle ait un attribut de type
   `Bibliotheque` (ou équivalent), que vous initialiserez avec quelques livres
   et quelques étudiants.

8. En vous inspirant de la classe d'exemple fournie plus haut, proposez des
   routes permettant de retourner :

   - l'ensemble des livres de la bibliothèque ;
   - l'ensemble des étudiants inscrits à la bibliothèque ;
   - l'ensemble des livres disponibles ;
   - l'ensemble des livres empruntés ;
   - l'ensemble des livres empruntés dont le retour est en retard ;
   - l'ensemble des livres d'une catégorie donnée ;
   - les informations d'un livre donné par son ISBN ;
   - les informations d'un étudiant donné par son numéro d'étudiant ;
   - l'ensemble des livres empruntés par un étudiant donné.

9. Proposez une route pour afficher tous les livres publiés une année donnée.
   Comment faire pour récupérer cette valeur sous la forme d'un `int` ?

10. Proposez une route permettant de supprimer un livre donné par son ISBN.
    Quelle méthode HTTP devez-vous utiliser pour cela ?
    Comment faire pour indiquer que vous souhaitez utiliser cette méthode ?
    Faites de même pour supprimer un étudiant donné par son numéro d'étudiant.

11. Proposez une route permettant d'enregistrer l'emprunt d'un livre donné par
    son ISBN par un étudiant donné par son numéro d'étudiant.
    Quelle méthode HTTP devez-vous utiliser pour cela ?
    Comment faire pour indiquer que vous souhaitez utiliser cette méthode ?

12. Proposez une route permettant d'enregistrer le retour d'un livre
    préalablement emprunté.

13. Proposez une route permettant de créer un nouveau livre, en fournissant
    toutes les informations nécessaire en paramètres de la requête.
    Quelle méthode HTTP devez-vous utiliser pour cela ?
    Comment faire pour indiquer que vous souhaitez utiliser cette méthode ?
    Faites de même pour créer un nouvel étudiant.

14. Proposez maintenant une route permettant la mise à jour des informations
    relatives à un livre donné par son ISBN.
    Quelle méthode HTTP devez-vous utiliser pour cela ?
    Comment faire pour indiquer que vous souhaitez utiliser cette méthode ?
    Faites de même pour mettre à jour les informations d'un étudiant donné par
    son numéro d'étudiant.

15. Dans cette application, quelles classes constituent le modèle ?
    La vue ?
    Le contrôleur ?

### Des annotations pour utiliser du JSON

Envoyer les paramètres de la requête *via* son URL est loin d'être idéal.
Le plus souvent, on préfère utiliser le format JSON pour envoyer ces paramètres
dans le *corps* de la requête.
Pour cela, *Spring* fournit un système d'annotations permettant d'utiliser ce
format de manière transparente.

16. L'annotation `@RequestBody` fournie par *Spring* vous permet de récupérer
    des paramètres depuis le corps de la requête lorsque celui-ci est au format
    JSON, de la même manière que `@RequestParam` vous permettait de récupérer
    les paramètres depuis son URL.

    a. Modifiez votre méthode permettant de mettre à jour livres et étudiants en
       utilisant `@RequestBody`.
       Comme vous ne savez pas quels seront les éventuels paramètres de cette
       requête, utilisez une `Map<String, Object>` comme paramètre de la méthode
       pour pouvoir les récupérer de manière « brute ».
       Assurez-vous ensuite que votre application fonctionne toujours comme
       prévu.

    b. Modifiez votre méthode permettant de créer un nouveau livre ou un nouvel
       étudiant.
       Cette fois, vous avez besoin de tous les attributs de la personne pour
       la créer : vous pouvez donc directement utiliser vos objets comme
       paramètre de la méthode.
       *Spring* fera automatiquement le *mapping* entre les clefs du JSON et
       les attributs de vos classes.
       Assurez-vous ensuite que votre application fonctionne toujours comme
       prévu.

17. L'annotation `@ResponseBody` permet de réaliser le processus inverse.
    Plutôt que d'envoyer vos réponses sous la forme de chaînes de caractères,
    vous pouvez directement renvoyer des objets *Java* au format JSON.
    Cette conversion est réalisée automatiquement par *Spring*.
    Modifiez vos différentes méthodes pour retourner des instances ou des listes
    de vos objets plutôt que des chaînes de caractères, en utilisant cette
    annotation.
    Complétez également l'annotation `@*Mapping` en ajoutant le paramètre
    `produces = MediaType.APPLICATION_JSON_VALUE` pour indiquer que votre
    application produit du JSON (cela correspond au `Content-Type` envoyé dans
    l'en-tête de la réponse HTTP).
    Assurez-vous ensuite que votre application fonctionne toujours comme
    prévu.

### Bonnes pratiques REST

Nous allons maintenant voir comment améliorer notre application pour qu'elle
respecte les bonnes pratiques du protocole HTTP et des API REST.

18. Il est souvent possible d'avoir plusieurs applications qui tournent sur
    un même serveur : ce sont les routes qui permettent de les différencier.
    Pour donner un préfixe commun aux différentes routes de votre application,
    annotez la classe avec `@RequestMapping`, en lui donnant ce préfixe (par
    exemple, `/bibliotheque`).
    Assurez-vous ensuite que votre application fonctionne toujours comme
    prévu.

19. Lorsque vous renvoyez une réponse, vous devez normalement aussi retourner
    un code de retour HTTP.
    Par défaut, c'est le code `200 OK` qui est retourné, mais ce n'est pas
    toujours le plus adapté.
    Pour préciser le code de retour de vos différentes méthodes, annotez les
    avec `@ResponseStatus`.
    Par exemple, pour la méthode permettant de créer une personne, utilisez
    `@ResponseStatus(HttpStatus.CREATED)`.
    Assurez-vous ensuite que votre application fonctionne toujours comme
    prévu.

20. Les codes de retour précisés à la question précédente sont ceux
    correspondants aux cas où la requête s'est bien passée.
    Lorsque ce n'est pas le cas (par exemple, lorsqu'une personne n'est pas
    retrouvée), il faut jeter une exception `ResponseStatusException`, en lui
    donnant le code d'erreur approprié (par exemple, `HttpStatus.NOT_FOUND`).
    Assurez-vous ensuite que votre application fonctionne toujours comme
    prévu.

## Persistence des données

Jusqu'à maintenant, nous utilisions des objets *Java* stockés en mémoire pour
notre application.
Cependant, cela signifie qu'à chaque redémarrage de l'application, toutes les
modifications sont perdues.
Pour éviter cela, nous allons maintenant relier notre application à une base de
données en utilisant *JPA* (*Java Persistence API*), qui simplifie grandement
le travail à réaliser en comparaison avec *JDBC* (*Java DataBase Connectivity).
Pour simplifier les choses nous allons utiliser *SQLite*, mais le fonctionnement
reste le même pour toutes les bases de données SQL.

21. Afin de pouvoir communiquer avec la base de données *SQLite*, complétez
    les dépendances de votre application en ajoutant celles indiquées
    ci-dessous :

    ```kotlin
    runtimeOnly("org.xerial:sqlite-jdbc")
    runtimeOnly("org.hibernate.orm:hibernate-community-dialects")
    ```

22. Pour indiquer à *Spring* comment accéder à la base de données, il faut
    configurer ces informations dans le fichier `application.properties`, que
    vous devez placer dans le dossier `src/main/resources` (ou éventuellement
    `src/main/resources/config`).
    Le contenu de ce fichier doit avoir l'allure suivante :

    ```properties
    # Le driver pour la base de données (ici, SQLite).
    spring.datasource.driverClassName = org.sqlite.JDBC

    # Le chemin vers la base de données SQLite.
    spring.datasource.url = jdbc:sqlite:chemin/vers/votre/base.db

    # Le dialecte à utiliser pour communiquer avec la base de données.
    spring.jpa.database-platform = org.hibernate.community.dialect.SQLiteDialect

    # L'action à réaliser par Spring pour maintenir la cohérence entre les
    # objets Java et les tables de la base ("create" et "validate" sont d'autres
    # valeurs possibles).
    spring.jpa.hibernate.ddl-auto = update
    ```

## Création des entités de l'application

Une *entité* est une classe *Java* qui représente une table de la base de
données.
Plus précisément, chacun des attributs de cette classe va représenter une
colonne de la base.
Dans notre cas, nous allons représenter les livres et les étudiants de la
bibliothèque.

23. Annotez les classes concernées avec `@Entity` et `@Table`.
    Cette dernière annotation prend un paramètre `name` spécifiant le nom de
    la table associée à l'entité dans la base de données.
    Vous pouvez lui donner le nom que vous souhaitez (*Spring* la créera pour
    vous).

24. Ajoutez dans cette classe un attribut numérique représentant l'identifiant
    de l'objet dans la base.
    Vous devrez l'annoter avec `@Id` (pour indiquer qu'il s'agit d'une clef
    primaire) et `@GeneratedValue(strategy = GenerationType.IDENTITY)` pour
    que cette valeur soit automatiquement générée.

25. Ajoutez l'annotation `@Enumerated` sur les attributs dont le type est une
    énumération.
    Spécifiez en paramètre soit `EnumType.ORDINAL`, soit `EnumType.STRING`,
    suivant que vous souhaitez que la valeur de l'énumération soit stockée dans
    la base sous la forme d'un entier ou d'une chaîne de caractères.

26. Comment faire pour établir le lien entre un étudiant et les livres qu'il
    a empruntés ?

27. Si ce n'est pas déjà fait, assurez-vous d'avoir des *getters* et des
    *setters* pour chacun des attributs.

## Définition d'un *repository*

En *Spring*, un *repository* fournit une partie du code permettant l'accès aux
données depuis le code *Java*, grâce à l'interface `JpaRepository`.
Pour accéder aux livres et étudiants de notre application, nous allons devoir en
définir un.

28. Définissez une interface étendant l'interface `JpaRepository`.
    Cette interface définit deux paramètres de type : le premier est le nom
    de l'entité (par exemple, `Livre`), et le second le type de l'identifiant
    de l'objet (celui choisi à la question 24).
    **ATTENTION : Vous devez bien définir une *interface* ici, vous ne devez pas
    implémenter `JpaRepository`.**

29. Consultez la *javadoc* de `JpaRepository` pour regarder les différentes
    méthodes fournies par cette interface.

30. Définissez une méthode permettant de rechercher un livre par son ISBN.
    Cette méthode doit impérativement retourner un objet de type `Livre`,
    et s'appeler `findByIsbn()` si l'attribut correspondant à l'ISBN s'appelle
    `isbn`.
    **Cette méthode sera automatiquement implémentée par *Spring*, vous devez
    donc faire attention à bien respecter les conventions de nommage.**

31. Faites de même avec l'entité représentant les étudiants.

## Définition d'un *service*

En *Spring*, un service implante la logique métier de l'application.
Un service doit réaliser les traitements fournis par l'application pour une
entité donnée.
Il peut s'agir par exemple de la validation des données fournies par
l'utilisateur, de leur transformation, de l'accès à la base de données *via* un
*repository*, *etc*.

32. Définissez une classe annotée avec `@Service` pour proposer un service
    permettant de manipuler les données relatives aux livres de l'application.
    Cette classe doit définir un attribut dont le type est le *repository*
    défini à la section précédente, ainsi qu'un constructeur permettant de
    l'initialiser.
    Ce constructeur sera automatiquement appelé par *Spring*, vous devez donc
    veiller à le définir correctement.

33. Définissez une méthode permettant de créer un livre dans la base de données.
    Une telle méthode pourrait avoir l'allure suivante :

    ```java
    public Livre creerLivre(Livre livre) {
        return livreRepository.save(livre);
    }
    ```

34. Faites de même pour définir un service permettant de gérer les étudiants.


## Adaptation du contrôleur

Pour exposer le service *via* une API REST, il faut adapter le code du
contrôleur défini plus haut.
Le contrôleur doit se charger de déléguer les opérations au service approprié.
Il ne doit pas y avoir de traitement dans le contrôleur, à moins que ce ne soit
pour extraire des informations de la requête reçue.

35. Complétez votre contrôleur pour qu'il définisse en attributs des services
    définis à la section précédente, ainsi qu'un constructeur permettant de les
    initialiser.
    Ce constructeur sera automatiquement appelé par *Spring*, vous devez donc
    veiller à le définir correctement.

36. Modifiez les méthodes associées aux différentes routes pour qu'elles
    utilisent maintenant le service plutôt que l'attribut de type
    `Bibliotheque`.

37. Lancez votre application, et essayez de nouveau les différentes
    fontionnalités qu'elle offre.
    Que se passe-t-il si vous relancez à nouveau l'application ?

## Pour aller plus loin

Si vous avez terminé, vous pouvez considérer les points suivants pour compléter
et améliorer votre application.
Notez que *Spring* fournit tout le nécessaire pour implanter les fonctionnalités
décrites ci-dessous : à vous de les retrouver !

38. Pour l'instant, les données envoyées par l'utilisateur sont toujours
    considérées comme correctes.
    Cependant, les données fournies pourraient ne pas respecter les règles de
    l'application (par exemple, l'adresse e-mail pourrait ne pas respecter
    le bon format).
    Comment pourriez-vous faire pour valider les données saisies ?

