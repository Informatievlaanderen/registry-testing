import { group } from 'k6';
import BuildingList from './list.js';
import BuildingListStatus from './list-status.js';
import BuildingDetail from './detail.js';

export default function () {
  const randomValue = Math.random();
  if (randomValue < 0.1) {
   group('BuildingList', () => { BuildingList(); });
  } else if(randomValue < 0.2) {
    group('BuildingListStatus', () => { BuildingListStatus(); });
  } else {
    group('BuildingDetail', () => { BuildingDetail(); });
  }

  //TODO: add capakey list test?
}