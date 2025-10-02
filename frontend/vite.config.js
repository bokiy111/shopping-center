import { defineConfig, loadEnv } from 'vite';
import react from '@vitejs/plugin-react-swc';
import wyw from '@wyw-in-js/vite';
var byPass = function (mode, req, res, options) {
    if (mode === 'development') {
        var proxyURL = new URL(req.url, options.target);
        res.setHeader('X-Proxy-URL', proxyURL.toString());
        res.setHeader('env', mode);
    }
};
// https://vitejs.dev/config/
export default defineConfig(function (_a) {
    var mode = _a.mode;
    var env = loadEnv(mode, process.cwd());
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
                    target: "".concat(env.VITE_BASE_HOST, "/"),
                    changeOrigin: true,
                    secure: true,
                    bypass: byPass.bind(null, mode),
                },
            }
        }
    };
});
