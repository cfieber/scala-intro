case object Pint

case object BeerPong

case object Stop

import actors.Actor
import Actor._

class PintPinger(count: Int, beerPonger: Actor) extends Actor {
    def act() {
        var pingsLeft = count - 1
        beerPonger ! Pint
        loop {
            react {
                case BeerPong =>
                    if (pingsLeft > 0) {
                        println("Sending a Pint!")
                        beerPonger ! Pint
                        pingsLeft -= 1
                    } else {
                        println("Enough already!")
                        beerPonger ! Stop
                        exit()
                    }
            }
        }
    }
}

class BeerPonger extends Actor {
    def act() {
        var pongCount = 0
        loop {
            react {
                case Pint =>
                    println("Pint number " + pongCount)
                    sender ! BeerPong
                    pongCount = pongCount + 1
                case Stop =>
                    println("BeerPonger: stop")
                    exit()
            }
        }
    }
}

object pingpong extends Application {
    val pong = new BeerPonger
    val ping = new PintPinger(25, pong)
    pong.start
    ping.start
}


pingpong.main(Array[String]())
