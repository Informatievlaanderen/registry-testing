import { API_KEY } from './config.js';

export function getHeaders() {
  const headers = {};
  if (API_KEY) {
    headers['x-api-key'] = `${API_KEY}`;
  }
  return headers;
}

export function getRandomInt(min, max) {
  return Math.floor(Math.random() * (max - min + 1)) + min;
}

export function getRandomLimit() {
  return getRandomInt(1, 500);
}