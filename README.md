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


Thank you for your time!






Dudas para la revisión:

tipo de juego se refiere al juego en si, un juego no puede ser de dos tipos

juego -> tipo

- videoBingo -> Sorteo
- slot -> Sorteo
- Blackjack -> Competición
- Poker -> Competición
- Ruleta -> apuesta

y aun así sorteo y apuesta diría que son lo mismo.


REQUISITOS

