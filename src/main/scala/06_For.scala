//////////////////////////////////////////////////////////////////////////////
// this looks familiar:
//
for (i <- 1 to 10) println(i)

//////////////////////////////////////////////////////////////////////////////
// multiple generators and a guard
//
for {
    x <- 1 to 10
    y <- x to 10 if x > 5
} println((x, y))


val entries = Map(
    ("a", List("Adam Jordens", "Adam Kelly")),
    ("c", List("Cam Fieber", "Chris Smith"))
)

//////////////////////////////////////////////////////////////////////////////
// cascading generators, with guard, and yielding a list
//
val aNames = for {
    (k, v) <- entries if k == "a"
    name <- v
} yield name.toUpperCase

println(aNames)

//////////////////////////////////////////////////////////////////////////////
// equivalent series of functions:
//
val aNames2 = entries.filter(_._1 == "a").flatMap(_._2).map(_.toUpperCase)
println(aNames2)


//////////////////////////////////////////////////////////////////////////////
// Option (Some and None)
//

val opt1 = Some("value")
println(opt1.get)
val opt2 = None
// .get will fail if there is None to get:
//println(opt2.get)
println(opt2.getOrElse("Bacon!"))

val suckItVowels = List(
    None,
    Some("b"),
    Some("c"),
    Some("d"),
    None,
    Some("f"),
    Some("g"),
    Some("h"),
    None,
    Some("j"),
    Some("k"),
    Some("l"),
    Some("m"),
    Some("n"),
    None,
    Some("p"),
    Some("q"),
    Some("r"),
    Some("s"),
    Some("t"),
    None,
    Some("v"),
    Some("w"),
    Some("x"),
    None,
    Some("z"))

val cons = for {
    opt <- suckItVowels
    consonant <- opt
} yield consonant

println(cons)