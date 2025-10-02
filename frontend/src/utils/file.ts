export const getFileExtension = (filename: string) => {
  return filename.split('.').pop();
};

export const getFileName = (url: string) => {
  return url.split('/').pop() || '';
};
