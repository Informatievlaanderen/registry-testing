import { group } from 'k6';
import municipalityList from './list.js';
import municipalityListStatus from './list-status.js';
import municipalityDetail from './detail.js';

export default function () {
  const randomValue = Math.random();
  if (randomValue < 0.15) {
   group('municipalityList', () => { municipalityList(); });
  } else if(randomValue < 0.33) {
   group('municipalityListStatus', () => { municipalityListStatus(); });
  } else {
   group('municipalityDetail', () => { municipalityDetail(); });
  }
}