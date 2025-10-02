import { useLocation } from 'react-router';
import { Breadcrumb } from 'antd';

// 定义中英文对应关系的对象
const translations : Record<string, string> = {
  '/u/mine': '我的详情',
};

/**
 * 通用面包屑组件
 * @constructor
 * 用法：直接在 translations 中添加去掉了数字部分之后的完整路由即可
 * 注意：当实际路由中包含数字时，结构应该为 /u/profile/1/info，而不是 /u/profile/info/1
 * 说明：
 *   目前面包屑中不显示路由中的数字，效果如下：
 *      1. 如 /u/profile/1/info，在面包屑中对应“用户详情/基本信息”，
 *          面包屑中不显示数字1，但是点击“基本信息”,.对应的路由就是 /u/profile/1/info
 *      2. 所以添加路径到translations的时候，直接不添加路由中的数字即可
 */

export const Breadcrumbs = () => {
  const location = useLocation();
  const path = location.pathname;
  const { search } = location;

  // 分割路由，去除空格
  const splitPath = path.split('/')
    .filter((crumb) => crumb !== '' );

  // 用于点击面包屑对应的路径，初始路径直接设为第一个路径 /s /t 之类的
  let currentPath = `/${splitPath[0]}`;

  // 用于匹配定义的面包屑中文路径，不包含数字路由
  let matchPath = `/${splitPath[0]}`;

  // 用于面包屑的分割路由，去除开头的 /s /t 之类的路由
  const crumbSplitPath = splitPath.slice(1);

  // 最终面包屑数组
  const crumbItem : {title: string; href: string}[] = [];
  for (const crumb of crumbSplitPath) {
    currentPath += `/${crumb}`;
    if (!crumb.match(/\d+/)) {
      matchPath += `/${crumb}`;
    }
    if (crumb.match(/\d+/)) {
      continue;
    }
    let crumbText = translations[`${matchPath}`];
    if (!crumbText) {
      crumbText = '';
    } else if (crumbText === '用户详情') {
      currentPath = path;
    }
    crumbText && crumbItem.push({
      title: `${crumbText}`,
      href: `${currentPath}`,
    });
  }
  if (search && crumbItem.length > 0) {
    const lastItem = crumbItem[crumbItem.length - 1];
    lastItem.href += search;
  }


  return (
    <Breadcrumb
      separator=">"
      style={{ margin: '0 0 10px 5px' }}
      items={crumbItem}
    />
  );
};
