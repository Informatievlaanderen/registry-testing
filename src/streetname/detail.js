import { check, sleep } from 'k6';
import { SharedArray } from 'k6/data';
import { BASE_URL, SLEEP_DURATION } from '../config.js';
import { executeHttp } from '../common.js';
import papaparse from 'https://jslib.k6.io/papaparse/5.1.1/index.js';

const csvData = new SharedArray('streetname-data', function () {
  return papaparse.parse(open('../data/streetnames.csv'), { header: true }).data;
});

export default function () {
  // Randomly select one row
  const randomIndex = Math.floor(Math.random() * csvData.length);
  const selectedRow = csvData[randomIndex];
  const id = selectedRow.streetNameId;
  const url = `${BASE_URL}/v2/straatnamen/${id}`;

  const res = executeHttp(url);
  check(res, {
    'status is 200': (r) => r.status === 200,
  });
  sleep(SLEEP_DURATION);
}