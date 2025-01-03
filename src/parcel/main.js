import { group } from 'k6';
import parcelList from './list.js';
import parcelListStatus from './list-status.js';
import parcelListAddress from './list-address-id.js';
import parcelDetail from './detail.js';

export default function () {
  const randomValue = Math.random();
  if (randomValue < 0.05) {
   group('parcelList', () => { parcelList(); });
  } else if(randomValue < 0.10) {
   group('parcelListStatus', () => { parcelListStatus(); });
  } else if(randomValue < 0.20) {
   group('parcelListAddress', () => { parcelListAddress(); });
  } else {
   group('parcelDetail', () => { parcelDetail(); });
  }
}