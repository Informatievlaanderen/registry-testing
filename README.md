# Registry Tests

## Goal

> Run various tests against the registries.

## Load tests

### Execution

Execute `run.bat/run.sh` (only use .sh on linux or in wsl, shells like git-bash give errors) and pass in required variables.

Tests are executed in a docker container, the report can be found at `results/{scenarioname-timestamp}/index.html`

```bash
API_KEY=REPLACEME BASE_URL=https://api.basisregisters.vlaanderen/v1 WARMUP_URL=https://www.vlaanderen.be/nl ./run.sh
API_KEY=REPLACEME BASE_URL=https://api.basisregisters.dev-vlaanderen.be/v1 WARMUP_URL=https://api.basisregisters.dev-vlaanderen.be/v1/versions ./run.sh
```

### Scenarios

#### MixedSimulation

This is a typical _capacity load test_ to see when the application starts suffering.

```scala
incrementUsersPerSec(load.incrementUsersPerCycleBy)
    .times(load.numberOfCycles)
    .eachLevelLasting(load.cycleDuration)
    .separatedByRampsLasting(load.rampDuration)
    .startingFrom(load.initialUsers)
```

Inject a succession of `numberOfCycles` levels each one during `cycleDuration` and increasing the number of users per sec by `incrementUsersPerCycleBy` starting from `initialUsers` and separated by ramps lasting `rampDuration`.

Currently the following `MixedSimulation`s are defined:

| Name     | `numberOfCycles` | `cycleDuration` | `incrementUsersPerCycleBy` | `initialUsers` | `rampDuration` | Total Duration |
| -------- | ---------------- | --------------- | -------------------------- | -------------- | -------------- | -------------- |
| Standard |  5               |  10 min         |  2                         |  5             |  30 sec        | ~ 1 hour       |
| High     |  5               |  10 min         |  4                         |  10            |  30 sec        | ~ 1 hour       |
| Peak     |  19              |   5 min         |  20                        |  30            |   1 min        | ~ 2 hours      |

The validations for these `MixedSimulation`s are as follows:

| Criteria                 | Standard | High   | Peak     |  
| ------------------------ | -------- | ------ | -------- |
| requests/second          | 10 rps   | 20 rps | 380 rps  |
| Maximum response time[¹] | 250 ms   | 500 ms | 30000 ms |

To get an idea how this behaves, have a look at this visual representation of a capacity load test:

![Open model capacity load test](https://raw.githubusercontent.com/informatievlaanderen/registry-testing/master/gatling.png)

##### ¹ Response times are currently not split up in different calls. Lists and searches will take longer than (cached) detail requests, so this should be adjusted accordingly.
[¹]:#-note-two

## Prerequisites

### Git LFS

```bash
$ curl -s https://packagecloud.io/install/repositories/github/git-lfs/script.deb.sh | sudo bash
$ sudo apt-get install git-lfs
```

## License

[European Union Public Licence (EUPL)](https://joinup.ec.europa.eu/news/understanding-eupl-v12)

The new version 1.2 of the European Union Public Licence (EUPL) is published in the 23 EU languages in the EU Official Journal: [Commission Implementing Decision (EU) 2017/863 of 18 May 2017 updating the open source software licence EUPL to further facilitate the sharing and reuse of software developed by public administrations](https://eur-lex.europa.eu/legal-content/EN/TXT/?uri=uriserv:OJ.L_.2017.128.01.0059.01.ENG&toc=OJ:L:2017:128:FULL) ([OJ 19/05/2017 L128 p. 59–64](https://eur-lex.europa.eu/legal-content/EN/TXT/?uri=uriserv:OJ.L_.2017.128.01.0059.01.ENG&toc=OJ:L:2017:128:FULL)).

## Credits

### Tooling

* [Gatling](https://github.com/gatling/gatling/blob/master/LICENSE.txt) - _Async Scala-Akka-Netty based Load Test Tool._ - [Apache License 2.0](https://choosealicense.com/licenses/apache-2.0/)
