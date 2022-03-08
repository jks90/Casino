Welcome to zitro technical test!


We want you to implement CasinoService interface.

As you can see, there is no Config type, you'll need to design it. It could be as simple as a probability for each game type.

Game type is either SLOTS or VIDEOBINGO.  You play games in rounds. You start a round placing a bet. A round could be in two status ACTIVE AND FINISHED, you can't place bets on a finished round. After a bet the round should be finished.

You should also implement a REST API to this service.

Improve our code, if you think something is wrong change it.

Use clean code, and tests.

And, of course, make it works. We only need a running loop where users place bets and get balances, showing logs in console.


Want to do more? (OPTIONAL)

* Allow to place several bets in a round.
* Implement some kind of persistence.
* Add a external wallet service. This service will handle users and balance.

ejemplo "parecido" de integrarse con una wallet externa. (Metamask)
https://github.com/jks90/sorteos


Thank you for your time!

H2-DATABASE
http://localhost:8080/h2-console

SWAGGER
http://localhost:8080/swagger-ui/index.html

# Docker

- docker build -t juankanh/casino:0.1 .
- docker push juankanh/casino:0.1
- docker run --name casino -p 8080:8080 -ti juankanh/casino:0.1

# data profiles: local, k8s
HOST NAMEBD USER PASS


Dudas para la revisión:

tipo de juego se refiere al juego en si, un juego no puede ser de dos tipos

juego -> tipo

- videoBingo -> Sorteo
- slot -> Sorteo
- Blackjack -> Competición
- Poker -> Competición
- Ruleta -> apuesta

y aun así sorteo y apuesta diría que son lo mismo.


Formula de la probabilidad duda de si es la correcta.


# REQUISITOS

- num players 
- play time and credit time
- trazability
- select game
- bet
- win lose
- between min max
- probability
- log transaction



SELECT * FROM BETS bets
INNER JOIN CONFIGURATIONS conf ON conf.id = bets.CONFIGURATIONS_ID 
INNER JOIN PLAYERS player ON player.id = bets.PLAYERS_ID  
INNER JOIN BET_BET_TRACE betrace ON bets.id = betrace.BET_ID
INNER JOIN BET_TRACES trace ON betrace.BET_TRACE_ID = trace.id
INNER JOIN PROVIDERS provi ON player.USER_PROVIDER_ID = provi.id
INNER JOIN PLAYER_PRIZE plepri ON plepri.PLAYERS_ID = player.id
INNER JOIN PRIZES pri ON plepri.PRIZE_ID = pri.id
where player.id = 'dcc25b12ad0743e99e8da105bad9d695'




