import { group } from 'k6';
import streetnameList from './list.js';
import streetnameListMunicipality from './list-municipality-name.js';
import streetnameListStatus from './list-status.js';
import streetnameListStreetNameName from './list-streetname-name.js';
import streetnameListNisCode from './list-municipality-niscode.js';
import streetnameDetail from './detail.js';

export default function () {
  const randomValue = Math.random();
  if (randomValue < 0.05) {
    group('streetnameList', () => { streetnameList(); });
  } else if(randomValue < 0.10) {
    group('streetnameListMunicipality', () => { streetnameListMunicipality(); });
  } else if(randomValue < 0.15) {
    group('streetnameListStatus', () => { streetnameListStatus(); });
  } else if(randomValue < 0.20) {
    group('streetnameListStreetNameName', () => { streetnameListStreetNameName(); });
  } else if(randomValue < 0.25) {
    group('streetnameListNisCode', () => { streetnameListNisCode(); });
  } else {
    group('streetnameDetail', () => { streetnameDetail(); });
  }
}