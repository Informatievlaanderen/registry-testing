import { check, sleep } from 'k6';
import { SharedArray } from 'k6/data';
import { BASE_URL, SLEEP_DURATION } from '../config.js';
import common from '../common.js';
import papaparse from 'https://jslib.k6.io/papaparse/5.1.1/index.js';

const csvData = new SharedArray('addresses-multi-parameter-data', function () {
  return papaparse.parse(open('../data/addresses-multi-parameters.csv'), { header: true }).data;
});

export default function () {
  const randomIndex = Math.floor(Math.random() * csvData.length);
  const selectedRow = csvData[randomIndex];
  const streetName = encodeURIComponent(selectedRow.streetName);
  const houseNumber = encodeURIComponent(selectedRow.houseNumber);
  const boxNumber = encodeURIComponent(selectedRow.boxNumber);
  const postalCode = selectedRow.postalCode;
  const municipalityName = encodeURIComponent(selectedRow.municipalityName);
  const homonym = encodeURIComponent(selectedRow.homonymAddition);

  const url = `${BASE_URL}/v2/adressen?gemeentenaam=${municipalityName}`
              + `&straatnaam=${streetName}`
              + `&huisnummer=${houseNumber}`
              + `&busnummer=${boxNumber}`
              + `&postcode=${postalCode}`
              + `&homoniemtoevoeging=${homonym}`;

  const res = common.executeHttp(url, 'adres-lijst-multi-parameter');
  check(res, {
    'status is 200': (r) => r.status === 200,
  });
  sleep(SLEEP_DURATION);
}
