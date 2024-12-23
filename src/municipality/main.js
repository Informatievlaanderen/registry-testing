import municipalityList from './list.js';
import municipalityListStatus from './list-status.js';
import municipalityDetail from './detail.js';

export default function () {
  const randomValue = Math.random();
  if (randomValue < 0.33) {
     municipalityList();
     municipalityListStatus();
  } else {
     municipalityDetail();
  }
}