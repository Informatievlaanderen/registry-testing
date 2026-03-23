# Install
Get K6: https://grafana.com/docs/k6/latest/set-up/install-k6/

# Registry Tests

## Goal

> Run various tests against the registries.

## Go to registry testing
1. open Git Bash
2. change directory with the following command cd registry-testing

## Run example
`k6 run ./src/main.js --config ./src/configs/smoke.json` (GIT BASH)
`k6 run .\src\main.js --config .\src\configs\smoke.json` (POWERSHELL)

### with Output CSV
`k6 run ./src/main.js --config ./src/configs/smoke.json --out csv=csv_Output.csv` (GIT BASH)
`k6 run .\src\main.js --config .\src\configs\smoke.json --out csv=csv_Output.csv` (POWERSHELL)

### With output to Grafana dashboard
```bash
K6_PROMETHEUS_RW_SERVER_URL=https://metrics.grafana.dv.vlaanderen.be/api/v1/push K6_PROMETHEUS_RW_TREND_STATS="min,max,med,avg,p(90),p(95),p(99)" K6_PROMETHEUS_RW_HTTP_HEADERS={FILL IN HEADERS} K6_PROMETHEUS_RW_PUSH_INTERVAL=1s k6 run ./src/streetname/list-streetname-name.js --config ./src/streetname/configs/high-load-list-streetname-name.json --summary-mode=full --out experimental-prometheus-rw --tag testid=StreetnameListNaam_HL2
```

### With an environment variable
See `./src/config.js` for variables.

`BASE_URL=http://xxx k6 run ./src/main.js --config ./src/configs/smoke.json`

### Local config file (for secrets)
Create `./src/config.local.json` to keep secrets out of git. This file is ignored by `.gitignore`.

Example:

```json
{
	"API_KEY": "your-api-key",
	"BASE_URL": "https://api.basisregisters.staging-vlaanderen.be",
	"SLEEP_DURATION": 0.3
}
```

Priority order in `src/config.js` is:
1. Environment variables (`__ENV`)
2. `src/config.local.json`
3. Hardcoded defaults

## Load tests

TODO
