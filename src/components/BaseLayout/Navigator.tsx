import { Button, Dropdown, Flex, Layout, MenuProps, Space } from 'antd';
import { DownOutlined, HomeOutlined, LogoutOutlined } from '@ant-design/icons';
import Search from 'antd/es/input/Search';
import { useLocation, useNavigate } from 'react-router';
import { useGoHome } from '../../hooks/useGoHome';
import { clearToken } from '../../authorization/token';
import { useSetUser } from '../../models/account/hooks';
import { Category, getCategoryText } from '../../data';
import { headerStyle, navLeftStyle, search, titleFontStyle } from './styles';

const { Header } = Layout;

const items: MenuProps['items'] = [
  {
    key: '1',
    label: (
      <a target="_blank" rel="noopener noreferrer" href="https://www.antgroup.com">
        {
          getCategoryText(Category.BOOK)
        }
      </a>
    ),
  },
  {
    key: '2',
    label: (
      <a target="_blank" rel="noopener noreferrer" href="https://www.antgroup.com">
        {
          getCategoryText(Category.CLOTHING)
        }
      </a>
    ),
  },
  {
    key: '3',
    label: (
      <a target="_blank" rel="noopener noreferrer" href="https://www.antgroup.com">
        {
          getCategoryText(Category.ELECTRONIC)
        }
      </a>
    ),
  },
  {
    key: '4',
    label: (
      <a target="_blank" rel="noopener noreferrer" href="https://www.antgroup.com">
        {
          getCategoryText(Category.DAILY)
        }
      </a>
    ),
  },
  {
    key: '5',
    label: (
      <a target="_blank" rel="noopener noreferrer" href="https://www.antgroup.com">
        {
          getCategoryText(Category.SPORTS)
        }
      </a>
    ),
  },
  {
    key: '6',
    label: (
      <a target="_blank" rel="noopener noreferrer" href="https://www.antgroup.com">
        {
          getCategoryText(Category.FOOD)
        }
      </a>
    ),
  },
  {
    key: '7',
    label: (
      <a target="_blank" rel="noopener noreferrer" href="https://www.antgroup.com">
        {
          getCategoryText(Category.BEAUTY)
        }
      </a>
    ),
  },
  {
    key: '8',
    label: (
      <a target="_blank" rel="noopener noreferrer" href="https://www.antgroup.com">
        {
          getCategoryText(Category.OTHER)
        }
      </a>
    ),
  },
];

export const Navigator = () => {
  const goHome = useGoHome();
  const navigate = useNavigate();
  const setUser = useSetUser();
  const location = useLocation();

  const sellerNavigator = (
    <Button
      icon={<LogoutOutlined />}
      onClick={() => {
        clearToken();
        setUser(null);
        navigate('/c/home');
      }}
      type="primary"
    >
      退出登录
    </Button>
  );

  const customerNavigator = (
    <Flex gap="middle" align="center" justify="end"
      style={{ width: 800 }}>
      <Dropdown menu={{ items }} arrow placement="bottom">
        <h3>
          <Space>
            商品分类
            <DownOutlined />
          </Space>
        </h3>
      </Dropdown>
      <Search
        placeholder="请输入您想要的物品名称"
        className={search}
      />
      <Button
        type="primary"
        onClick={() => navigate('/u/login')}
      >
        卖家登录
      </Button>
    </Flex>
  );

  return (
    <Header className={headerStyle}>
      <div className={navLeftStyle} onClick={goHome}>
        <Space>
          <HomeOutlined className={titleFontStyle} />
          <span className={titleFontStyle}>西工大二手市场</span>
        </Space>
      </div>
      {
        location.pathname.includes('/s/') ? sellerNavigator : customerNavigator
      }
    </Header>
  );
};
