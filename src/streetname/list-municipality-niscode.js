import { check, sleep } from 'k6';
import { SharedArray } from 'k6/data';
import { BASE_URL, SLEEP_DURATION } from '../config.js';
import common from '../common.js';
import papaparse from 'https://jslib.k6.io/papaparse/5.1.1/index.js';

const csvData = new SharedArray('streetname-niscodes', function () {
  return papaparse.parse(open('../data/niscodes.csv'), { header: true }).data;
});

export default function () {
  const randomIndex = Math.floor(Math.random() * csvData.length);
  const selectedRow = csvData[randomIndex];
  const niscode = selectedRow.niscode;
  const url = `${BASE_URL}/v2/straatnamen?niscode=${niscode}&limit=${common.getRandomLimit()}`;

  const res = common.executeHttp(url);
  check(res, {
    'status is 200': (r) => r.status === 200,
  });
  sleep(SLEEP_DURATION);
}
