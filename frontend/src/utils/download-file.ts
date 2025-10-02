export const downloadFile = (url: string, name = '') => {
  const link = document.createElement('a');
  link.href = url;
  link.target = '_blank';
  link.download = name;
  link.download = url.split('/').pop() || '';
  document.body.appendChild(link);
  link.click();
  document.body.removeChild(link);
};
