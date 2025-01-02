import { group } from 'k6';
import postalList from './list.js';
import postalListMunicipality from './list-municipality-name.js';
import postalDetail from './detail.js';

export default function () {
  const randomValue = Math.random();
  if (randomValue < 0.1) {
   group('postalList', () => { postalList(); });
  } else if(randomValue < 0.2) {
    group('postalListMunicipality', () => { postalListMunicipality(); });
  } else {
    group('postalDetail', () => { postalDetail(); });
  }
}