/**
 * 生产环境域名
 */
export const PRO_URL = 'https://118.25.177.82:80';

/**
 * 开发环境域名
 */
export const DEV_URL = 'http://localhost:5173';

export enum Env {
  DEV = 'dev',
  PRO = 'pro',
}

export const getEnv = (): Env => {
  if (window.location.hostname.includes('localhost')) {
    return Env.DEV;
  }

  return Env.PRO;
};

export const getUrl = () => {
  const env = getEnv();
  const map = {
    [Env.DEV]: DEV_URL,
    [Env.PRO]: PRO_URL,
  };
  return map[env];
};
