import { group } from 'k6';
import municipalityTest from './municipality/main.js';
import postalTest from './postal/main.js';
import streetnameTest from './streetname/main.js';
import buildingTest from './building/main.js';
import buildingUnitTest from './buildingunit/main.js';
import parcelTest from './parcel/main.js';
import addressMatchTest from './addressmatch/main.js';

export default function () {
  const randomValue = Math.random();
  if (randomValue < 0.03) {
    group('municipalityTest', () => { municipalityTest(); });
  } else if(randomValue < 0.06) {
    group('postalTest', () => { postalTest(); });
  } else if(randomValue < 0.11) {
    group('streetnameTest', () => { streetnameTest(); });
  } else if(randomValue < 0.16) {
    group('parcelTest', () => { parcelTest(); });
  } else if(randomValue < 0.32) {
    group('buildingTest', () => { buildingTest(); });
  } else if(randomValue < 0.55) {
    group('buildingUnitTest', () => { buildingUnitTest(); });
  } else if(randomValue < 0.75) {
    group('addressMatchTest', () => { addressMatchTest(); });
  } else {
    //address
  }
}
