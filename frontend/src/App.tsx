import './App.css';
import { ConfigProvider } from 'antd';
import zhCN from 'antd/locale/zh_CN';
import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom';
import dayjs from 'dayjs';
import { createStyles } from 'antd-style';
import { Account } from './entries/account';
import { Shop } from './entries/shop';
import { Seller } from './entries/seller/pages';
import { Bootstrap } from './boostrap';

dayjs.locale('zh-cn');

const useStyle = createStyles(({ prefixCls, css }) => ({
  linearGradientButton: css`
      &.${prefixCls}-btn-primary:not([disabled]):not(.${prefixCls}-btn-dangerous) {
          border-width: 0;

          > span {
              position: relative;
          }

          &::before {
              content: '';
              background: linear-gradient(135deg, #6253e1, #04befe);
              position: absolute;
              inset: 0;
              opacity: 1;
              transition: all 0.3s;
              border-radius: inherit;
          }

          &:hover::before {
              opacity: 0;
          }
      }
  `,
}));

function App() {
  const { styles } = useStyle();

  return (
    <ConfigProvider
      locale={zhCN}
      button={{
        className: styles.linearGradientButton,
      }}
    >
      <BrowserRouter>
        <Bootstrap>
          <Routes>
            <Route path="/c/*" element={<Shop />} />
            <Route path="/u/*" element={<Account />} />
            <Route path="/s/*" element={<Seller />} />
            {/*<Route path="/p" element={<Portal />} />*/}
            <Route path="*" element={<Navigate to="/c" />} />
          </Routes>
        </Bootstrap>
      </BrowserRouter>
    </ConfigProvider>
  );

}

export default App;
