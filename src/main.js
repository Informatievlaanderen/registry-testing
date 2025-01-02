import { group } from 'k6';
import municipalityTest from './municipality/main.js';
import postalTest from './postal/main.js';

export default function () {
  group('municipality', () => {
    municipalityTest();
  });

  group('postal', () => {
    postalTest();
  });
}
