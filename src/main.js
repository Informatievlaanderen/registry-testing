import { group } from 'k6';
import municipalityTest from './municipality/main.js';
import postalTest from './postal/main.js';
import streetnameTest from './streetname/main.js';
import buildingTest from './building/main.js';

export default function () {
  const randomValue = Math.random();
  if (randomValue < 0.25) {
    group('municipality', () => { municipalityTest(); });
  } else if(randomValue < 0.50) {
    group('postal', () => { postalTest(); });
  } else if(randomValue < 0.75) {
    group('streetname', () => { streetnameTest(); });
  } else {
    group('building', () => { buildingTest(); });
  }
}
