# Registry Tests

## Goal

> Run various tests against the registries.

## Run example
`k6 run ./src/main.js --config ./src/configs/smoke.json`

### With an environment variable
See `./src/config.js` for variables.

`BASE_URL=http://xxx k6 run ./src/main.js --config ./src/configs/smoke.json`

## Load tests

TODO