//////////////////////////////////////////////////////////////////////////////
// Lists
//
val foo = List("Bacon", "Cheese", "Beer", "Pants")
val bar = scala.List.apply("Butter", "Milk", "Eggs")
val baz = scala.collection.immutable.List("Canucks", "Lions", "Giants")
println(foo)
println(bar)
println(baz)

//////////////////////////////////////////////////////////////////////////////
// Maps
//
val peeps = Map(("f", ("Cameron", "Fieber")), ("j", ("Adam", "Jordens")))

println(peeps)


//////////////////////////////////////////////////////////////////////////////
// Java interoperability
//
import scala.collection.JavaConversions

val scalaList = List("One", "Two", "Three")

val javaList = JavaConversions.asJavaList(scalaList)

import JavaConversions._

val implicitConversion : java.util.List[String] = scalaList

println(scalaList)
println(javaList)
println(implicitConversion)

//////////////////////////////////////////////////////////////////////////////
// Iterating over collections with foreach
//
println("************")
val callOnEach = (x:String) => println(x)
foo.foreach(callOnEach)
println("************")

foo.foreach(println _)
println("************")

foo.sortBy(_.charAt(0)).foreach(println _)
println("************")

