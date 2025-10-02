const tagColors = [
  'processing',
  'success',
  'error',
  'warning',
  'lime',
  'green',
  'cyan',
  'geekblue',
  'blue',
  'purple',
  'magenta',
  'red',
  'volcano',
  'orange',
  'gold',
];

const colors = [
  '#f56a00',
  '#7265e6',
  '#ffbf00',
  '#00a2ae',
  '#43CBFF',
  '#F97794',
  '#FFD26F',
  '#FFCF71',
  '#f56a00',
  '#ffac00',
  '#4de3ff',
  '#f86fa9',
  '#ff9e50',
  '#ff9500',
  '#00d4ae',
  '#f86fae',
  '#ff9e80',
  '#FF4500',
  '#FFA500',
  '#FF8C00',
  '#9ACD32',
  '#008000',
  '#4682B4',
  '#00BFFF',
  '#4169E1',
  '#FF4500',
  '#FFA500',
  '#4682B4',
  '#4169E1',
  '#0000FF',
  '#8A2BE2',
  '#A52A2A',
  '#CC3300',
  '#CC6600',
  '#CC9900',
  '#669900',
  '#339933',
  '#009999',
  '#336699',
  '#003366',
  '#660066',
  '#660033',
];

const gradientColors: string[][] = [
  ['#43CBFF', '#9708cc'],
  ['#FFF6B7', '#f6416c'],
  ['#F97794', '#623aa2'],
  ['#5EFCE8', '#736efe'],
  ['#FFD26F', '#3677ff'],
  ['#92FFC0', '#002661'],
  ['#FFCF71', '#2376dd'],
  ['#FFD86E', '#FC6262'],
  ['#81FFEF', '#f067b4'],
  ['#F6D242', '#ff52e5'],
  ['#5ee7df', '#b490ca'],
  ['#FFA6B7', '#1e2ad2'],
  ['#9890e3', '#b114cf'],
  ['#2af598', '#009efd'],
  ['#88d3ce', '#6e45e2'],
  ['#b721ff', '#21d4fd'],
];

export const getRandomCustomizedColor = (text: string) => {
  // 随机从系统颜色列表中选取一个
  const randomIndex = getUnicodeSum(text) % tagColors.length;
  return tagColors[randomIndex];
};

const getUnicodeSum = (text: string) : number => {
  let hash = 0;
  for (let i = 0; i < text.length; i++) {
    hash += text.charCodeAt(i);
  }
  return hash + text.length;
};

const getColorByString = (name: string) : string => {
  return colors[getUnicodeSum(name) % colors.length];
};

const getGradientColorByString = (name: string) : string[] => {
  return gradientColors[getUnicodeSum(name) % gradientColors.length];
};

const getGradientDirection = (name: string) : string => {
  const directions = [
    'to bottom right',
    'to top left',
  ];
  return directions[getUnicodeSum(name) % 2];
};

export const getUserDefaultAvatarGradientColors = (name: string) => {
  const gradientColor = getGradientColorByString(name);
  const direction = getGradientDirection(name);
  return `linear-gradient(${direction}, ${gradientColor[0]}, ${gradientColor[1]})`;
};

export const getUserDefaultAvatarColors = (name: string) : string => {
  return getColorByString(name);
};
