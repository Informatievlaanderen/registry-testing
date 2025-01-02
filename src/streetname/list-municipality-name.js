import { check, sleep } from 'k6';
import { SharedArray } from 'k6/data';
import { BASE_URL, SLEEP_DURATION } from '../config.js';
import common from '../common.js';
import papaparse from 'https://jslib.k6.io/papaparse/5.1.1/index.js';

const csvData = new SharedArray('streetnames-municipality-names', function () {
  return papaparse.parse(open('../data/streetnames-municipality-names.csv'), { header: true }).data;
});

export default function () {
  const randomIndex = Math.floor(Math.random() * csvData.length);
  const selectedRow = csvData[randomIndex];
  const name = selectedRow.municipalityName;
  const url = `${BASE_URL}/v2/straatnamen?gemeentenaam=${name}&limit=${common.getRandomLimit()}`;

  const res = common.executeHttp(url);
  check(res, {
    'status is 200': (r) => r.status === 200,
  });
  sleep(SLEEP_DURATION);
}