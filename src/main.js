import { group } from 'k6';
import municipalityTest from './municipality/main.js';
import postalTest from './postal/main.js';
import streetnameTest from './streetname/main.js';
import buildingTest from './building/main.js';
import buildingUnitTest from './buildingunit/main.js';

export default function () {
  const randomValue = Math.random();
  if (randomValue < 0.10) {
    group('municipalityTest', () => { municipalityTest(); });
  } else if(randomValue < 0.20) {
    group('postalTest', () => { postalTest(); });
  } else if(randomValue < 0.30) {
    group('streetnameTest', () => { streetnameTest(); });
  } else if(randomValue < 0.50) {
    group('buildingTest', () => { buildingTest(); });
  } else {
    group('buildingUnitTest', () => { buildingUnitTest(); });
  }
}
