//////////////////////////////////////////////////////////////////////////////
// Variable declaration
//
val immutable = 1
println(immutable)
var mutable = 2
println(mutable)

//error
// immutable += 1

//works
mutable += 1
println(mutable)


//////////////////////////////////////////////////////////////////////////////
// 'Operators' / Method calls
//
immutable + mutable
immutable.+(mutable)

// precedence:
// lowest   (all letters)
//          |
//          ^
//          &
//          < >
//          ! =
//          :
//          - +
//          % / *
// highest  (all other special characters)

//////////////////////////////////////////////////////////////////////////////
// Imports, using Classes
//

import java._ // Wildcard
import math.{BigInteger, BigDecimal} // scope of imports includes what you've previously imported


println(new BigInteger("12345"))
println(new BigDecimal("12345.67"))

//////////////////////////////////////////////////////////////////////////////
// Methods, functions, and Closures
//
def someMethod(a: Int, b: Int) {
    println(a + b)
}

// return type inferred:
def someFunction(a: Int, b: Int) = a + b

// return type explicit
def someOtherFunction(a: Int, b: Int) : Long = {
    a + b // by default the return value of a function is the last evaluated value

    // can also be explicit:
    // return a + b
}

val someClosure = (a: Int, b: Int) => a + b

// retrieving a reference to a function:
val someOtherClosure = someFunction _

someMethod(1, 2)
println(someFunction(1, 2))
println(someClosure(1, 2))
println(someOtherClosure(1, 2))

//partially applied functions (currying)
val plus10 = someClosure(10, _: Int)

println(plus10(1))

//////////////////////////////////////////////////////////////////////////////
// Control statements
//
if (immutable == 1) {
    println("It's one")
} else {
    println("Or not..")
}

while (mutable > 1) {
    println("loop")
    mutable -= 1
}

//control statements are functions!
val baz : Double =
    if (mutable > immutable) {
        scala.math.Pi
    } else{
        scala.math.E
    }


println(baz)

val initializerCouldFail = try {
    Integer.parseInt("Booom!")
} catch {
    // I'll discuss pattern matching later!
    case _ => 100
}

println(initializerCouldFail)
