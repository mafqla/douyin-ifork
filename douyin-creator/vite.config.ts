import { defineConfig, loadEnv } from 'vite'
import react from '@vitejs/plugin-react-swc'
import SemiPlugin from './src/utils/SemiPlugin'
import path from 'path'
// https://vitejs.dev/config/
export default defineConfig(({ mode }) => {
  const env = loadEnv(mode, process.cwd(), '')
  return {
    server: {
      open: true,
      port: 3001,
      host: '0.0.0.0',
      proxy: {
        '/api': {
          target: env.VITE_BASE_URL,
          changeOrigin: true,
          rewrite: (path) => path.replace(/^\/api/, ''),
        },
      },
    },
    resolve: {
      alias: [{ find: '@', replacement: '/src' }],
    },
    plugins: [
      react(),
      SemiPlugin({
        theme: '@semi-bot/semi-theme-doucreator',
      }),
    ],
    css: {
      preprocessorOptions: {
        scss: {
          javascriptEnabled: true,
        },
      },
    },
  }
})