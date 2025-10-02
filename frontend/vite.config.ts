import { defineConfig, loadEnv, ProxyOptions } from 'vite';
import react from '@vitejs/plugin-react-swc';
import wyw from '@wyw-in-js/vite';
import { IncomingMessage, ServerResponse } from 'http';

const byPass = (mode: string, req: IncomingMessage, res: ServerResponse, options: ProxyOptions) => {
  if (mode === 'development') {
    const proxyURL = new URL(req.url, options.target as string);
    res.setHeader('X-Proxy-URL', proxyURL.toString());
    res.setHeader('env', mode);
  }
};

// https://vitejs.dev/config/
export default defineConfig(({ mode }) => {
  const env = loadEnv(mode, process.cwd());

  return {
    define: {
      'process.platform': JSON.stringify(process.platform),
    },
    plugins: [react(), wyw()],
    build: {
      emptyOutDir: true
    },
    server: {
      proxy: {
        '/api': {
          target: `${env.VITE_BASE_HOST}/`,
          changeOrigin: true,
          secure: true,
          bypass: byPass.bind(null, mode),
        },
      }
    }
  };
});
