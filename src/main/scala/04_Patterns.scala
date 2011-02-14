//////////////////////////////////////////////////////////////////////////////
// mmm, beer
//
val beers = List("Amnesiac - IPA", "Blue Buck - Pale Ale", "Race Rocks - Amber Ale", "Fat Tug - IPA")

//////////////////////////////////////////////////////////////////////////////
// Match on object value, capture results in match
//
beers.foreach { _ match {
    case "Fat Tug - IPA" => println("Must be at the Knockanback!")
    case s if !s.endsWith("Pale Ale") => println("It's no Fat Tug, but I'll settle for a " + s)
    case s => println("I guess I'll have a water instead of a " + s)
}}



//////////////////////////////////////////////////////////////////////////////
// Match on a regular expression
//
val NameAndType = "(.*) - (.*)".r
beers.foreach { _ match {
    case NameAndType(s: String, "IPA") => println("Loves me some IPA: " + s)
    case NameAndType(_, "Amber Ale") => println("I can settle for an Amber Ale")
    case s => println("I think I'll have a water instead of " + s)
}}

// match is a function that returns a value (here the boolean return is used to filter the list):
val ipas = beers.filter(_ match {
    case NameAndType(_, "IPA") => true
    case _ => false
})

println(ipas)


//////////////////////////////////////////////////////////////////////////////
// Match on a case class
//
case class Geno(firstName : String, lastName : String, title : String)

val genos = List(
    Geno("Cliff", "McCollum", "Dev Manager"),
    Geno("Cam", "Fieber", "Tech Lead"),
    Geno("Adam", "Jordens", "Tech Lead"),
    Geno("Adam", "Kelly", "Team Lead"),
    Geno("Chris", "Smith", "Team Lead"),
    Geno("Jianping", "Roth", "Developer"))

genos.foreach { _ match {
    case g @ Geno(_, _, "Dev Manager") if g.firstName != "Cliff" => println(g.firstName + " " + g.lastName + " is delegatin' .. like a boss!")
    //case g @ Geno(_, _, "Tech Lead") => println(g.firstName + " " + g.lastName + " likes the view from the top of the ivory tower")
    case g @ Geno("Adam", _, _) => println("Adam " + g.lastName + " is too fit for his own good")
    case g @ Geno("Cam", "Fieber", _) => println(g.firstName + " " + g.lastName + " sure looks good dressed up as cupid")
    case g @ Geno(_, _, _) => println("Finally, its " + g.firstName + " a " + g.title + " who can get some work done!")
}}


