import { ReactNode } from 'react';
import { useLocation, useNavigate } from 'react-router';
import { BaseLayout } from '../../../components/BaseLayout';
import { AdminMenuConfig } from '../../../components/BaseLayout/menu-config';

export const ShopLayout = ({ children }: { children: ReactNode }) => {
  const navigate = useNavigate();
  const location = useLocation();
  const currentPath = location.pathname.replace('/c/', '');

  return (
    <BaseLayout
      menuItems={AdminMenuConfig}
      onSelect={(key) => navigate(`/c/${key}`)}
      currentPath={currentPath}
      showBreadcrumb={!location.pathname.includes('/c/home')}
    >
      {children}
    </BaseLayout>
  );
};
