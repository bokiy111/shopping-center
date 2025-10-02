import { ReactNode } from 'react';
import { useLocation, useNavigate } from 'react-router';
import { BaseLayout } from '../../../components/BaseLayout';
import { AdminMenuConfig } from '../../../components/BaseLayout/menu-config';

export const SellerLayout = ({ children }: { children: ReactNode }) => {
  const navigate = useNavigate();
  const location = useLocation();
  const currentPath = location.pathname.replace('/s/', '');

  return (
    <BaseLayout
      menuItems={AdminMenuConfig}
      onSelect={(key) => navigate(`/s/${key}`)}
      currentPath={currentPath}
    >
      {children}
    </BaseLayout>
  );
};
