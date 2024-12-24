import { group } from 'k6';
import { BASE_URL } from './config.js';
import municipalityTest from './municipality/main.js';

export default function () {
  console.log(`BASE_URL: ${BASE_URL}`);

  group('municipality', () => {
    municipalityTest();
  });
}
