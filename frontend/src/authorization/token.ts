import { safeStorage } from '../common/safe-storage';

const TOKEN_KEY = 'shopping-token';

export function getToken() {
  return safeStorage.getItem(TOKEN_KEY);
}

export function setToken(value: string) {
  safeStorage.setItem(TOKEN_KEY, value);
}

export function clearToken() {
  safeStorage.removeItem(TOKEN_KEY);
}
