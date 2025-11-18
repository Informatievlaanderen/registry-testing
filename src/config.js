//K6 argumenten:
//--config / test / registry - testing / src / configs / smoke - long - run.json--out experimental - prometheus - rw--tag testid = run#2;
//ENV variabelen:
//K6_PROMETHEUS_RW_SERVER_URL=https://metrics.grafana.dv.vlaanderen.be/api/v1/push K6_PROMETHEUS_RW_HTTP_HEADERS=X-Scope-OrgID:Basisregisters K6_PROMETHEUS_RW_TREND_STATS="min,max,med,avg,p(90),p(95),p(99)" K6_PROMETHEUS_RW_PUSH_INTERVAL=1s k6 run ./src/municipality/detail.js --config ./src/configs/high-load2.json --out experimental-prometheus-rw --tag testid=gemeenteDetailHL_2



export const API_KEY = __ENV.API_KEY || '';
export const BASE_URL = __ENV.BASE_URL || 'https://api.basisregisters.staging-vlaanderen.be';
export const SLEEP_DURATION = 0.3;