const loadLocalConfig = () => {
	const configPaths = ['./src/config.local.json', './config.local.json'];

	for (const configPath of configPaths) {
		try {
			const rawConfig = open(configPath);
			return JSON.parse(rawConfig);
		} catch (error) {
			continue;
		}
	}

	return {};
};

const LOCAL_CONFIG = loadLocalConfig();

export const API_KEY = __ENV.API_KEY || LOCAL_CONFIG.API_KEY || '';
export const BASE_URL = __ENV.BASE_URL || LOCAL_CONFIG.BASE_URL || 'https://api.basisregisters.staging-vlaanderen.be';
export const SLEEP_DURATION = Number(__ENV.SLEEP_DURATION || LOCAL_CONFIG.SLEEP_DURATION || 0.3);

if (!API_KEY) {
	console.warn('[config] API_KEY is not set. Tests will continue, but rate limiting may apply.');
}

