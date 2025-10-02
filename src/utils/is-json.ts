export const isJson = (json: string) => {
  try {
    const result = JSON.parse(json);
    if (typeof result === 'object' && result !== null) {
      return true;
    }
  } catch {
    return false;
  }
  return false;
};
