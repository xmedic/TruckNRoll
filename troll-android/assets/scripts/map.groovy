reader = new BufferedReader(new FileReader("LT-cities.txt"))
writer = new FileWriter("city.txt")


cities = {}
linenr = 0
reader.eachLine{ line ->
  println line.split(";")[0] + " " + linenr
  cities[line.split(";")[0]] = linenr
  // add city id by lineNumber
  writer.write("$linenr;$line\n")
  linenr = linenr + 1
}
writer.close()
reader.close()


reader = new BufferedReader(new FileReader("LT-roads.txt"))
writer = new FileWriter("road.txt")
reader.eachLine{ line ->

  newLine = ""
  for (city in line.split(";")) {
        if (newLine.size() > 0) {
            newLine = newLine + ";"
        }
        newLine = newLine + cities[city]
  }

  println newLine
  writer.write("$newLine\n")
}
writer.close()
reader.close()

