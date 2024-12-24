import { group } from 'k6';
import municipalityTest from './municipality/main.js';

export default function () {
  group('municipality', () => {
    municipalityTest();
  });
}
