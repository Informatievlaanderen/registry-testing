import { check, sleep } from 'k6';
import { SharedArray } from 'k6/data';
import { BASE_URL, SLEEP_DURATION } from '../config.js';
import common from '../common.js';
import papaparse from 'https://jslib.k6.io/papaparse/5.1.1/index.js';

const csvData = new SharedArray('address-search-queries-data', function () {
  return papaparse.parse(open('../data/address-search-queries.csv'), { header: true }).data;
});

const csvDataMuni = new SharedArray('postal-municipality-names', function () {
  return papaparse.parse(open('../data/postal-municipality-names.csv'), { header: true }).data;
});

export default function () {
  let statusData = ['voorgesteld', 'inGebruik', 'gehistoreerd', 'afgekeurd'];
  const randomStatusIndex = Math.floor(Math.random() * statusData.length);
  const status = statusData[randomStatusIndex];

  const randomQueryIndex = Math.floor(Math.random() * csvData.length);
  const selectedQueryRow = csvData[randomQueryIndex];
  const query = encodeURIComponent(selectedQueryRow.q);

  const randomMunicipalityIndex = Math.floor(Math.random() * csvDataMuni.length);
  const selectedMunicipalityRow = csvDataMuni[randomMunicipalityIndex];
  const municipalityName = encodeURIComponent(selectedMunicipalityRow.municipalityName);

  let url = `${BASE_URL}/v2/adressen/zoeken?q=${query}`;

const randomValue = Math.random();
  if (randomValue < 0.6) {
    url += `&gemeenteNaam=${municipalityName}`;
  }

  if(randomValue < 0.33) {
    url += `&status=${status}`;
  }

  const res = common.executeHttp(url, 'adres-zoeken');
  check(res, {
    'status is 200': (r) => r.status === 200,
  });
  sleep(SLEEP_DURATION);
}
