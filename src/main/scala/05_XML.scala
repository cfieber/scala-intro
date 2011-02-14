import java.net.URL
import java.util.Date
import xml._

//////////////////////////////////////////////////////////////////////////////
// Loading/parsing XML:
//
val projects = XML.loadFile("src/main/scala/projects.xml")
//also XML.load from stream, url, ...
println(projects)

projects.child.foreach(println _)

println("via xpath:")
projects \ "project" foreach println _

projects \\ "name" foreach { name => println(name.text) }

projects \\ "@limsid" foreach println _

val printer = new PrettyPrinter(1000, 4)
projects \ "project" foreach { project =>
    val uri = new URL((project \ "@uri").text)
    val conn = uri.openConnection
    conn.setRequestProperty("Authorization", "Basic YWRtaW46cGFzcw==")
    println(printer.format(XML.load(conn.getInputStream)))
}

//////////////////////////////////////////////////////////////////////////////
// XML variables:
//
val projectName = "API-PROJECT-qYN"
val openDate = new Date().formatted("%tF")
val researcher = "http://192.168.10.50:8080/api/v1/researchers/358"
val project =
<prj:project
    limsid="API156"
    uri="http://192.168.10.50:8080/api/v1/projects/API156"
    xmlns:prj="http://genologics.com/ri/project"
    xmlns:file="http://genologics.com/ri/file"
    xmlns:ri="http://genologics.com/ri"
    xmlns:udf="http://genologics.com/ri/userdefined">
    <name>{projectName}</name>
    <open-date>{openDate}</open-date>
    <researcher uri={researcher}/>
    <file:file uri="http://192.168.10.50:8080/api/v1/files/API156-40-11" limsid="API156-40-11"/>
    <permissions uri="http://192.168.10.50:8080/api/v1/permissions/projects/API156"/>
</prj:project>

println(project)
println(project \ "file" \ "@limsid")
