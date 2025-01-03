import { check, sleep } from 'k6';
import { SharedArray } from 'k6/data';
import { BASE_URL, SLEEP_DURATION } from '../config.js';
import common from '../common.js';
import papaparse from 'https://jslib.k6.io/papaparse/5.1.1/index.js';

const csvData = new SharedArray('streetnames-streetname-names', function () {
  return papaparse.parse(open('../data/streetnames-streetname-names.csv'), { header: true }).data;
});

export default function () {
  const randomIndex = Math.floor(Math.random() * csvData.length);
  const selectedRow = csvData[randomIndex];
  const name = selectedRow.streetNameName;
  const encodedName = encodeURIComponent(name);
  const url = `${BASE_URL}/v2/straatnamen?straatnaam=${encodedName}&limit=${common.getRandomLimit()}`;

  const res = common.executeHttp(url, 'straatnaam-lijst-straatnaam');
  check(res, {
    'status is 200': (r) => r.status === 200,
  });
  sleep(SLEEP_DURATION);
}
