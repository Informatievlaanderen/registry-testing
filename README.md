# Registry Tests

## Goal

> Run various tests against the registries.

### Load tests

Execute `run.bat/run.sh` (only use .sh on linux or in wsl, shells like git-bash give errors).  
Tests are exceuted in a docker container, the report can be found at `results/{scenarioname-timestamp}/index.html`

| Criteria | Standard | High | Peak |  
|----------|----------|------|------|
| requests/second | 10| 20| 380
| Maximum response time* | 250ms | 500ms | 30000ms

\* Response times are currently not split up in different calls. Lists and searches will take longer than (cached) detail requests, so this should be adjusted accordingly.

## Prerequisites

### Git LFS

```console
$ curl -s https://packagecloud.io/install/repositories/github/git-lfs/script.deb.sh | sudo bash
$ sudo apt-get install git-lfs
```

## License

[European Union Public Licence (EUPL)](https://joinup.ec.europa.eu/news/understanding-eupl-v12)

The new version 1.2 of the European Union Public Licence (EUPL) is published in the 23 EU languages in the EU Official Journal: [Commission Implementing Decision (EU) 2017/863 of 18 May 2017 updating the open source software licence EUPL to further facilitate the sharing and reuse of software developed by public administrations](https://eur-lex.europa.eu/legal-content/EN/TXT/?uri=uriserv:OJ.L_.2017.128.01.0059.01.ENG&toc=OJ:L:2017:128:FULL) ([OJ 19/05/2017 L128 p. 59–64](https://eur-lex.europa.eu/legal-content/EN/TXT/?uri=uriserv:OJ.L_.2017.128.01.0059.01.ENG&toc=OJ:L:2017:128:FULL)).

## Credits

### Tooling

* [Gatling](https://github.com/gatling/gatling/blob/master/LICENSE.txt) - _Async Scala-Akka-Netty based Load Test Tool._ - [Apache License 2.0](https://choosealicense.com/licenses/apache-2.0/)
