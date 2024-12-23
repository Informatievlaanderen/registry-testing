import http from 'k6/http';
import { check } from 'k6';
import { SharedArray } from 'k6/data';
import { BASE_URL } from '../config.js';
import { getHeaders } from '../common.js';
import papaparse from 'https://jslib.k6.io/papaparse/5.1.1/index.js';

const csvData = new SharedArray('niscodes-data', function () {
  return papaparse.parse(open('../data/niscodes.csv'), { header: true }).data;
});

export default function () {
  // Randomly select one row
  const randomIndex = Math.floor(Math.random() * csvData.length);
  const selectedRow = csvData[randomIndex];
  const id = selectedRow.niscode; // Assuming the CSV has a column named 'id'
  const url = `${BASE_URL}/v2/gemeenten/${id}`;

  const headers = getHeaders();
  const res = http.get(url, { headers });
  check(res, {
    'status is 200': (r) => r.status === 200,
  });
}