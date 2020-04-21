# Antes de tudo

Este programa precisa dos seguintes requisitos, para funcionar adequadamente:

1. PIJDBCDriver (Biblioteca JDBC fornecida pela OsiSoft)
2. PI DAS (fornecido pela Osisoft)

Como a dependência PIJDBCDriver não está fornecida publicamente, você deve instalá-la primeiro no seu servidor Maven. Para isso, de posse do arquivo jar da dependência, execute o seguinte comando:

````shell script
mvn install:install-file \
   -Dfile=<path-to-file> \
   -DgroupId=com.osisoft.jdbc \
   -DartifactId=PIJDBCDriver \
   -Dversion=1.0.1 \
   -Dpackaging=jar \
   -DgeneratePom=true
````

Além disso, verifique se o path do PI DAS está definido nas seguintes variáveis de ambiente: PI_RDSA_LIB64 e PI_RDSA_LIB

# pi-cli
```
Usage: <main class> [options] [command] [command options]
  Options:
  * --pi-host, -pih
      PI host
  * --das-host, -dash
      PI DAS host
  * --pi-user, -u
      PI user
  * --pi-pass, -p
      PI password
    --help
      Display command help
  Commands:
    search-values      Search for values in PI
      Usage: search-values [options]
        Options:
        * --tag, -t
            Tag to search for
          --from, -dtf
            Date to search from
          --to, -dtt
            Date to search to
          --min-value, -mnv
            Minimum value to search
          --closed-min-value, -cmnv
            Search considering the minimum value
            Default: false
          --max-value, -mxv
            Maximum value to search
          --closed-max-value, -cmxv
            Search considering the maximum value
            Default: false
          --limit, -l
            Maximum number of results to collect
```

