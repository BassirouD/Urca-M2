import { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  appId: 'io.ionic.vote',
  appName: 'Vote Mobile App',
  webDir: 'www',
  server: {
    androidScheme: 'https'
  }
};

export default config;
