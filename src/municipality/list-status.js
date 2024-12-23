import http from 'k6/http';
import { check } from 'k6';
import { BASE_URL } from '../config.js';
import { getRandomLimit, getHeaders } from '../common.js';

export default function () {
  let statusData = ['inGebruik', 'voorgesteld', 'gehistoreerd'];

  const randomIndex = Math.floor(Math.random() * statusData.length);
  const status = statusData[randomIndex];
  const url = `${BASE_URL}/v2/gemeenten?status=${status}&limit=${getRandomLimit()}`;

  const headers = getHeaders();
  const res = http.get(url, { headers });
  check(res, {
    'status is 200': (r) => r.status === 200,
  });
}
