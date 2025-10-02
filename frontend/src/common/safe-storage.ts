export const safeStorage = {
  getItem(key: string) {
    try {
      return localStorage.getItem(key);
    } catch {
      return null;
    }
  },
  setItem(key: string, value: string) {
    try {
      localStorage.setItem(key, value);
    } catch {
      //
    }
  },
  clear() {
    try {
      localStorage.clear();
    } catch {
      //
    }
  },
  removeItem(key: string) {
    try {
      localStorage.removeItem(key);
    } catch {
      //
    }
  },
};
