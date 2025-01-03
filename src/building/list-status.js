import { check, sleep } from 'k6';
import { BASE_URL, SLEEP_DURATION } from '../config.js';
import common from '../common.js';

export default function () {
  let statusData = ['inGebruik', 'voorgesteld', 'gehistoreerd'];
  const randomIndex = Math.floor(Math.random() * statusData.length);
  const status = statusData[randomIndex];

  const url = `${BASE_URL}/v2/gebouwen?status=${status}&limit=${common.getRandomLimit()}`;

  const res = common.executeHttp(url, 'gebouw-lijst-status');
  check(res, {
    'status is 200': (r) => r.status === 200,
  });
  sleep(SLEEP_DURATION);
}
