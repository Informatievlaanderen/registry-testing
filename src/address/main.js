import { group } from 'k6';
import addressList from './list.js';
import addressSearch from './search.js';
import addressListStatus from './list-status.js';
import addressListNiscode from './list-niscode.js';
import addressListMulti from './list-multi-parameter.js';
import addressDetail from './detail.js';

export default function () {
  const randomValue = Math.random();
  if (randomValue < 0.05) {
   group('addressList', () => { addressList(); });
  } else if(randomValue < 0.1) {
   group('addressListStatus', () => { addressListStatus(); });
  } else if(randomValue < 0.15) {
   group('addressListNiscode', () => { addressListNiscode(); });
  } else if(randomValue < 0.3) {
    group('addressListMulti', () => { addressListMulti(); });
  } else {
   group('addressDetail', () => { addressDetail(); });
  }

  if(randomValue <= 0.5) {
    group('addressSearch', () => { addressSearch(); });
  }
}