import main from '../main.js';

export const options = {
  stages: [
    { duration: "10s", target: 10 },
    { duration: "10s", target: 20 },
    { duration: "10s", target: 35 },
    { duration: "20s", target: 50 },
    { duration: "10s", target: 35 },
    { duration: "5s", target: 20 }
  ],
  cloud: {
      distribution: {
      DE: { loadZone: "amazon:de:frankfurt", percent: 100 }
    }
  }
};

export default function () {
  main();
}