import { group } from 'k6';
import buildingUnitList from './list.js';
import buildingUnitListStatus from './list-status.js';
import buildingUnitListAddress from './list-address-id.js';
import buildingUnitDetail from './detail.js';

export default function () {
  const randomValue = Math.random();
  if (randomValue < 0.10) {
   group('buildingUnitList', () => { buildingUnitList(); });
  } else if(randomValue < 0.20) {
   group('buildingUnitListStatus', () => { buildingUnitListStatus(); });
  } else if(randomValue < 0.35) {
   group('buildingUnitListAddress', () => { buildingUnitListAddress(); });
  } else {
   group('buildingUnitDetail', () => { buildingUnitDetail(); });
  }
}

//TODO: add building list by building id