import http from 'k6/http';
import { check } from 'k6';
import { BASE_URL } from '../config.js';
import { getRandomInt, getHeaders } from '../common.js';

export default function () {
  const limit = getRandomInt(1, 500);
  const offset = getRandomInt(1, 400);
  const url = `${BASE_URL}/v2/gemeenten?offset=${offset}&limit=${limit}`;

  const headers = getHeaders();
  const res = http.get(url, { headers });
   check(res, {
     'status is 200': (r) => r.status === 200,
  });
}
