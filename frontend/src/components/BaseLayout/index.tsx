import { ReactNode, useState } from 'react';
import { Layout, MenuProps } from 'antd';
import { useLocation } from 'react-router';
import { Breadcrumbs } from '../Breadcrumbs';
import { Navigator } from './Navigator';
import {
  layoutTotalStyle,
  layoutContentStyle,
  contentStyle,
} from './styles';

const { Content } = Layout;

export interface BaseUserLayoutProps {
  children: ReactNode;
  menuItems: MenuProps['items'];
  currentPath: string;
  onSelect: (keyPath: string) => void;
  showBreadcrumb?: boolean;
}

// 找到路由中最长的匹配 meneItems 的 key 的项，这样才可以展开对应的菜单
const getMatchedMenuItemKey = (menuConfig: MenuProps['items'], url: string): string => {
  let longestMatch: string = '';
  const findLongestMatch = (menuConfig: MenuProps['items'], url: string) => {
    if (!menuConfig) {
      return;
    }
    menuConfig.forEach(item => {
      if (item && 'key' in item && item.key && url.startsWith(item.key.toString())) {
        longestMatch = item.key.toString();
        if ('children' in item) {
          findLongestMatch(item.children, url);
        }
      }
    });
  };
  findLongestMatch(menuConfig, url);
  return longestMatch;
};

const getAllParentKeys = (matchedKey: string): string[] => {
  const keys = matchedKey.split('/');
  return keys.map((_, index) => keys.slice(0, index + 1).join('/'));
};

export const BaseLayout = ({
  children,
  menuItems,
  currentPath,
  showBreadcrumb = false,
}: BaseUserLayoutProps) => {
  const [collapsed, setCollapsed] = useState(false);
  const matchedKey = getMatchedMenuItemKey(menuItems, currentPath);

  return (
    <Layout className={layoutTotalStyle}>
      <Navigator collapsed={collapsed} />
      {/*<Menu*/}
      {/*  multiple={false}*/}
      {/*  className={menuStyle}*/}
      {/*  selectedKeys={[matchedKey]}*/}
      {/*  // 刷新之后默认打开当前选项的所有父级菜单*/}
      {/*  defaultOpenKeys={parentKeyArray}*/}
      {/*  mode="inline"*/}
      {/*  items={menuItems}*/}
      {/*  onClick={({ keyPath }) => onSelect?.(keyPath[0])}*/}
      {/*/>*/}
      <Layout className={layoutContentStyle}>
        {
          showBreadcrumb && <Breadcrumbs />
        }
        <Content
          id="base-layout-content"
          className={
            contentStyle
          }
        >
          {children}
        </Content>
      </Layout>
    </Layout>
  );
};
