import { group } from 'k6';
import municipalityTest from './municipality/main.js';
import postalTest from './postal/main.js';
import streetnameTest from './streetname/main.js';

export default function () {
  const randomValue = Math.random();
  if (randomValue < 0.33) {
    group('municipality', () => { municipalityTest(); });
  } else if(randomValue < 0.66) {
    group('postal', () => { postalTest(); });
  } else {
    group('streetname', () => { streetnameTest(); });
  }
}
