import { check, sleep } from 'k6';
import { BASE_URL, SLEEP_DURATION } from '../config.js';
import common from '../common.js';

export default function () {
  const limit = common.getRandomLimit();
  const offset = common.getRandomInt(1, 1000);
  const url = `${BASE_URL}/v2/postinfo?offset=${offset}&limit=${limit}`;

  const res = common.executeHttp(url, 'postinfo-lijst');
  check(res, {
    'status is 200': (r) => r.status === 200,
  });
  sleep(SLEEP_DURATION);
}
